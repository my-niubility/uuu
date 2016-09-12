package com.nbl.services.account.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.nbl.common.constants.ErrorCode;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.CustUserDao;
import com.nbl.model.CustPerson;
import com.nbl.model.CustUser;
import com.nbl.service.business.constant.SetLoginPwdType;
import com.nbl.service.business.constant.SetPwdType;
import com.nbl.service.user.dto.req.LogPwdInfoDto;
import com.nbl.service.user.dto.req.PayPwdInfoDto;
import com.nbl.services.account.SetPwdService;

@Service("setPwdService")
public class SetPwdServiceImpl implements SetPwdService {

	private final static Logger logger = LoggerFactory.getLogger(SetPwdServiceImpl.class);

	@Resource
	private CustPersonDao custPersonDao;
	@Resource
	private CustUserDao custUserDao;

	@Override
	@Transactional
	public void setPayPwd(PayPwdInfoDto payPwdInfoDto) throws MyBusinessCheckException {

		CustPerson custPerson = custPersonDao.selectByPrimaryKey(payPwdInfoDto.getCustId());

		if (custPerson == null) {
			logger.error("custId:" + payPwdInfoDto.getCustId() + "select return null");
			throw new MyBusinessCheckException(ErrorCode.USD001, "CustPerson");
		}

		if (SetPwdType.MOD_PAY_PWD.getValue().equals(payPwdInfoDto.getSetType())) {
			// 修改密码
			if (payPwdInfoDto.getOrgPayPwd().equals(custPerson.getPayPassword())) {
				custPerson.setPayPassword(payPwdInfoDto.getNewPayPwd());
			} else {
				logger.error("payment password doesn't match");
				throw new MyBusinessCheckException(ErrorCode.USC004);
			}
		} else if (SetPwdType.SET_PAY_PWD.getValue().equals(payPwdInfoDto.getSetType())) {
			// 设置新密码
			if (StringUtils.isNotEmpty(custPerson.getPayPassword())) {
				logger.error("payment password is not null");
				throw new MyBusinessCheckException(ErrorCode.USC005);
			} else {
				custPerson.setPayPassword(payPwdInfoDto.getNewPayPwd());
			}
		} else {
			// 重置密码
			custPerson.setPayPassword(payPwdInfoDto.getNewPayPwd());
		}

		custPersonDao.updateByPrimaryKeySelective(custPerson);
	}

	@Override
	public void setLoginPwd(LogPwdInfoDto payPwdInfoDto) throws MyBusinessCheckException {

		CustUser custUser = custUserDao.selectByUserName(payPwdInfoDto.getPhoneNum());

		if (custUser == null) {
			logger.error("custId:" + payPwdInfoDto.getCustId() + "select return null");
			throw new MyBusinessCheckException(ErrorCode.USD001, "CustUser");
		}

		if (SetLoginPwdType.MOD_LOG_PWD.getValue().equals(payPwdInfoDto.getSetType())) {
			// 修改密码
			if (payPwdInfoDto.getOrgLoginPwd().equals(custUser.getPassword())) {
				custUser.setPassword(payPwdInfoDto.getNewLoginPwd());
			} else {
				logger.error("login password doesn't match");
				throw new MyBusinessCheckException(ErrorCode.USC011);
			}
		} else {
			// 重置密码
			custUser.setPassword(payPwdInfoDto.getNewLoginPwd());
		}

		custUserDao.updateByPrimaryKeySelective(custUser);
	}

	@Override
	public void checkPayPwd(String custId, String payPwd) throws MyBusinessCheckException {
		// 是否设置密码,支付密码校验
		CustPerson custPerson = custPersonDao.selectByPrimaryKey(custId);
		if (custPerson.getPayPassword() == null) {
			throw new MyBusinessCheckException(ErrorCode.USC010);
		}
		if (!payPwd.equals(custPerson.getPayPassword())) {
			throw new MyBusinessCheckException(ErrorCode.USC003);
		}

	}

}
