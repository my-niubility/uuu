package com.nbl.services.account.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.common.constants.ComConst;
import com.nbl.common.constants.ErrorCode;
import com.nbl.common.constants.PRConstant;
import com.nbl.common.constants.ProjectConstants;
import com.nbl.common.constants.SeqenceConstant;
import com.nbl.common.dto.CommRespDto;
import com.nbl.dao.CustAgreementDao;
import com.nbl.dao.CustLoginFailureDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.CustUserDao;
import com.nbl.model.CustLoginFailure;
import com.nbl.model.CustPerson;
import com.nbl.model.CustUser;
import com.nbl.service.business.constant.AgrtType;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.business.constant.LockStatus;
import com.nbl.service.business.constant.PhoneStatus;
import com.nbl.service.user.dto.req.UserInfoDto;
import com.nbl.service.user.dto.req.UserLoginDto;
import com.nbl.service.user.dto.res.LoginResultDto;
import com.nbl.service.user.dto.res.ReLgnErrNumResultDto;
import com.nbl.service.user.dto.res.RegArgtResult;
import com.nbl.service.user.dto.res.RegChkResultDto;
import com.nbl.service.user.dto.res.RegisteResultDto;
import com.nbl.service.user.dto.res.UserInfo;
import com.nbl.services.account.RegisteUserService;
import com.nbl.services.sequence.IdGeneratorAppService;
import com.nbl.util.DateTimeUtils;
import com.nbl.util.ErrCodeUtil;
import com.nbl.utils.BeanParseUtils;

/**
 * 用户注册
 * 
 * @author AlanMa
 *
 */
@Service("registeUserService")
public class RegisteUserServiceImpl implements RegisteUserService {
	private final static Logger logger = LoggerFactory.getLogger(RegisteUserServiceImpl.class);

	@Resource
	private IdGeneratorAppService idGeneratorAppService;
	@Resource
	private CustUserDao custUserDao;
	@Resource
	private CustAgreementDao custAgreementDao;
	@Resource
	private CustLoginFailureDao custLoginFailureDao;
	@Resource
	private CustPersonDao custPersonDao;

	@Override
	@Transactional
	public CommRespDto registeUserApp(UserInfoDto userInfoDto) {
		CommRespDto result;
		// 此手机号是否已注册
		String isRegisted = isRegistedApp(userInfoDto.getLoginName()).getIsRegisted();
		if (ComConst.TRUE.equals(isRegisted)) {
			logger.warn(userInfoDto.getLoginName() + "此手机号已经注册");
			String[] errInfo = ErrCodeUtil.getErrMsg(ErrorCode.USC001, userInfoDto.getLoginName());
			result = new CommRespDto().fail(errInfo[0], errInfo[1]);
			return result;
		}

		// 登记个人用户表(T_CUST_USER)
		String custNum = userInfoDto.getRegChanCode() + idGeneratorAppService.getNext_13Bit_Sequence(SeqenceConstant.CU_CP_SEQUENCE);
		String custId = PRConstant.CUST_PER_ID + custNum;
		logger.info("[custId=]" + custId);
		CustUser userInfo = new CustUser();
		userInfo.setId(custId);
		userInfo.setCustId(custId);
		userInfo.setEnabled(PhoneStatus.USABLE.getValue());
		userInfo.setCreateDate(DateTimeUtils.now().toDate());
		BeanParseUtils.copyProperties(userInfoDto, userInfo);
		custUserDao.insert(userInfo);

		// 登记个人客户表(T_CUST_PERSON)
		CustPerson custPerson = new CustPerson();
		custPerson.setId(custId);
		// 如下字段数据库原来为必输项，现在为非必输
		// custPerson.setRegisteredType(ComConst.NULL);
		// custPerson.setName(ComConst.NULL);
		// custPerson.setCredentialsType(ComConst.NULL);
		// custPerson.setIdentityCardNumber(ComConst.NULL);
		// custPerson.setStatus(ComConst.NULL);
		custPerson.setCertStatus(CertStatus.NEW.getValue());
		custPerson.setCreatedTime(DateTimeUtils.now().toDate());
		custPerson.setIp(userInfoDto.getIp());
		try {
			// 设置证件有效期
			custPerson.setCertExpiryDate(DateTimeUtils.parseDate(ComConst.DEF_EXP_DATE).toDate());
		} catch (ParseException e) {
			logger.error("failed to parse time:", e);
		}
		custPersonDao.insertSelective(custPerson);

		RegisteResultDto registeResult = new RegisteResultDto(custId);
		UserInfo userInfoRet = new UserInfo();
		BeanParseUtils.copyProperties(userInfo, userInfoRet);
		registeResult.setUserInfo(userInfoRet);

		logger.info("[registeResult=]" + registeResult.toString());
		result = new CommRespDto().success(registeResult);
		return result;
	}

	@Override
	public RegChkResultDto isRegistedApp(String userName) {

		RegChkResultDto result;
		CustUser custUser = custUserDao.selectByUserName(userName);

		if (custUser == null || custUser.getId() == null) {
			result = new RegChkResultDto(ComConst.FALSE);
		} else {
			logger.info(custUser.toString());
			result = new RegChkResultDto(ComConst.TRUE);
		}

		return result;
	}

	@Override
	@Transactional
	public CommRespDto loginApp(UserLoginDto loginInfo) {

		// 根据电话查询用户
		CommRespDto result;
		String userName = loginInfo.getMobile();
		CustUser custUser = custUserDao.selectByUserName(userName);
		if (custUser == null || custUser.getId() == null) {
			// 未注册
			String[] errInfo = ErrCodeUtil.getErrMsg(ErrorCode.USC002, userName);
			result = new CommRespDto().fail(errInfo[0], errInfo[1]);
		} else {
			// 已注册
			CustLoginFailure custLoginFailureCond = new CustLoginFailure();
			custLoginFailureCond.setUserId(custUser.getCustId());
			custLoginFailureCond.setSessionId(loginInfo.getSessionId());
			custLoginFailureCond.setIp(loginInfo.getIp());
			// 查询用户登录失败记录
			CustLoginFailure custLoginFailure = custLoginFailureDao.selectByBusUniqCond(custLoginFailureCond);

			if (loginInfo.getPassword().equals(custUser.getPassword())) {
				// 密码正确
				if (custLoginFailure != null && LockStatus.LOCK.getValue().equals(custLoginFailure.getLockState())) {
					// 登录错误次数超限，提示稍后再试
					String[] errInfo = ErrCodeUtil.getErrMsg(ErrorCode.USC009);
					result = new CommRespDto().fail(errInfo[0], errInfo[1]);
					return result;
				}
				// 更新登录时间，返回登录时间
				custUser.setPeriodStart(DateTimeUtils.now().toDate());
				custUser.setPeriodEnd(new Date(DateTimeUtils.now().toDate().getTime() + 1800000));
				Date lastLoginDate = (custUser.getLoginDate() == null ? DateTimeUtils.now().toDate() : custUser.getLoginDate());
				custUser.setLoginDate(DateTimeUtils.now().toDate());
				custUserDao.updateByPrimaryKey(custUser);
				String startTime = new DateTimeUtils(custUser.getPeriodStart()).toDateTimeString();
				String endTime = new DateTimeUtils(custUser.getPeriodEnd()).toDateTimeString();
				String lastLoginTime = new DateTimeUtils(lastLoginDate).toDateTimeString();

				LoginResultDto loginResultDto = new LoginResultDto(startTime, endTime, lastLoginTime);
				UserInfo userInfoRet = new UserInfo();
				BeanParseUtils.copyProperties(custUser, userInfoRet);
				loginResultDto.setUserInfo(userInfoRet);

				logger.info("[loginResultDto=]" + loginResultDto.toString());

				result = new CommRespDto().success(loginResultDto);
			} else {
				if (custLoginFailure == null) {
					// 首次记录登录错误
					custLoginFailureCond.setId(idGeneratorAppService.getNext_13Bit_Sequence(SeqenceConstant.BI_OT_SEQUENCE));
					custLoginFailureCond.setIp(loginInfo.getIp());
					custLoginFailureCond.setFailureCount(new Long(0));
					custLoginFailureCond.setCreatedTime(DateTimeUtils.now().toDate());
					custLoginFailureCond.setUpdateTime(DateTimeUtils.now().toDate());
					custLoginFailureCond.setCurrentFailCount(new Long(1));
					custLoginFailureCond.setLockState(LockStatus.UN_LOCK.getValue());
					custLoginFailureDao.insertSelective(custLoginFailureCond);
				} else {
					// 更新登录错误信息
					custLoginFailure.setUpdateTime(DateTimeUtils.now().toDate());
					Long count = custLoginFailure.getCurrentFailCount();
					if (++count < ProjectConstants.MAX_WR_TIME) {
						custLoginFailure.setCurrentFailCount(count);
					} else {
						custLoginFailure.setCurrentFailCount(new Long(0));
						custLoginFailure.setLockState(LockStatus.LOCK.getValue());
					}
					custLoginFailure.setFailureCount(custLoginFailure.getFailureCount() + 1);
					custLoginFailureDao.updateByPrimaryKeySelective(custLoginFailure);
				}

				String[] errInfo = ErrCodeUtil.getErrMsg(ErrorCode.USC003, userName);
				result = new CommRespDto().fail(errInfo[0], errInfo[1]);
			}
		}

		return result;
	}

	@Override
	public RegArgtResult getRegAgrt() {
		byte[] agrtContent = custAgreementDao.selectByAgrtType(AgrtType.REGISTE_AGRT.getValue()).getAgrtContent();
		String agrtCont = null;
		try {
			agrtCont = new String(agrtContent, "utf8");
		} catch (UnsupportedEncodingException e) {
			logger.error("byte to string exception", e);
		}

		return new RegArgtResult(agrtCont);
	}

	@Override
	public CommRespDto resetLgnErrNum(String time) {
		logger.info("enter business disabledTradeOrder");
		CommRespDto result = null;
		Date updateTime = null;
		try {
			updateTime = DateTimeUtils.getMinAgoDate(time);
		} catch (ParseException e) {
			logger.error("parse date error", e);
			String[] errMsg = ErrCodeUtil.getErrMsg(ErrorCode.USE001);
			return new CommRespDto().fail(errMsg[0], errMsg[1]);
		}
		int resultNum = custLoginFailureDao.updateLockStatusByUpdateTime(updateTime, LockStatus.LOCK.getValue(), LockStatus.UN_LOCK.getValue());
		if (resultNum != 1) {
			String[] errMsg = ErrCodeUtil.getErrMsg(ErrorCode.USD002);
			result = new CommRespDto().fail(errMsg[0], errMsg[1]);
		} else {
			result = new CommRespDto().success(new ReLgnErrNumResultDto(String.valueOf(resultNum)));
		}
		return result;
	}
}
