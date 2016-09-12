package com.nbl.services.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ComConst;
import com.nbl.common.dto.CommRespDto;
import com.nbl.dao.CustAccountDao;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.CustUserDao;
import com.nbl.model.CustBankCard;
import com.nbl.model.CustPerson;
import com.nbl.service.business.constant.BindingStatus;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.business.constant.IsDefault;
import com.nbl.service.business.constant.PayChanlCode;
import com.nbl.service.user.dto.req.BalanceInfoQueryDto;
import com.nbl.service.user.dto.req.UserInfoQueryDto;
import com.nbl.service.user.dto.res.BalanceInfoResultDto;
import com.nbl.service.user.dto.res.UserInfoResultDto;
import com.nbl.services.tppayment.BalQryThdService;
import com.nbl.services.user.UserInfoQueryService;
import com.nbl.util.MapUtil;
import com.nbl.utils.BeanParseUtils;

/**
 * 用户信息查询
 * 
 * @author AlanMa
 *
 */
@Service("userInfoQueryService")
public class UserInfoQueryServiceImpl implements UserInfoQueryService {
	@Resource
	private CustPersonDao custPersonDao;
	@Resource
	private CustUserDao custUserDao;
	@Resource
	private CustBankCardDao custBankCardDao;
	@Resource
	private BalQryThdService balQryZL;
	@Resource
	private CustAccountDao custAccountDao;

	@Override
	public CommRespDto queryCustCenterInfo(UserInfoQueryDto userInfoQueryDto) {

		UserInfoResultDto result = new UserInfoResultDto();
		String certStatus = null;
		String payPwd = null;
		// 个人客户表(T_CUST_PERSON)查询
		CustPerson custPerson = custPersonDao.selectByPrimaryKey(userInfoQueryDto.getCustId());
		if (custPerson != null) {
			BeanParseUtils.copyProperties(custPerson, result);
			//BeanParseUtils.copyPropertiesToString(custPerson, result, ComConst.TRUE );
			certStatus = custPerson.getCertStatus();
			payPwd = custPerson.getPayPassword();
		}
		result.setUserName(userInfoQueryDto.getUserId());
		String isPassIdentify = CertStatus.PASSED.getValue().equals(certStatus) ? ComConst.TRUE : ComConst.FALSE;
		result.setIsPassIdentify(isPassIdentify);
		String isSetTradePwd = StringUtils.isEmpty(payPwd) ? ComConst.FALSE : ComConst.TRUE;
		// 没有通过实名认证
		if (ComConst.FALSE.equals(isPassIdentify)) {
			result.setIsSetTradePwd(ComConst.FALSE);
			result.setIsBindCard(ComConst.FALSE);
		} else if (ComConst.TRUE.equals(isSetTradePwd)) {
			// 设置了交易密码
			result.setIsSetTradePwd(ComConst.TRUE);
			result.setIsBindCard(ComConst.TRUE);
		} else {
			// 客户银行卡(T_CUST_BANK_CARD)查询
			Map<String, String> queryCond = MapUtil.toStrMap("custId", userInfoQueryDto.getCustId(), "isDefault", IsDefault.YES.getValue(), "isCert", IsDefault.YES.getValue());
			CustBankCard custBankCard = custBankCardDao.selectByBindInfo(queryCond);
			String bindStatus = custBankCard == null ? null : custBankCard.getAuditBindingStatus();
			result.setIsBindCard(BindingStatus.BIND.getValue().equals(bindStatus) ? ComConst.TRUE : ComConst.FALSE);
			result.setIsSetTradePwd(ComConst.FALSE);
		}

		return new CommRespDto().success(result);
	}

	@Override
	public BalanceInfoResultDto queryUsableBalance(BalanceInfoQueryDto balanceInfoQueryDto) {
		BalanceInfoResultDto result = null;
		if (PayChanlCode.ZLZB.getValue().equals(balanceInfoQueryDto.getPayChanlCode())) {
			// TODO 挡板实现，无accId暂时传custId代替
			// String accId =
			// custAccountDao.selectAccIdByCustId(balanceInfoQueryDto.getCustId());
			// result = balQryZL.queryBalance(accId);
			result = balQryZL.queryBalance(balanceInfoQueryDto.getCustId());
		}
		return result;
	}

	@Override
	public List<String> queryAllCustId() {
		List<String> allCustId = custUserDao.queryAllCustId();
		return allCustId;
	}

}
