package com.nbl.services.account.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.constants.ErrorCode;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.model.CustBankCard;
import com.nbl.model.CustPerson;
import com.nbl.service.business.constant.BindingStatus;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.services.account.TrdPreChkService;
import com.nbl.util.MapUtil;

@Service("trdPreChkService")
public class TrdPreChkServiceImpl implements TrdPreChkService {

	@Resource
	private CustBankCardDao custBankCardDao;
	@Resource
	private CustPersonDao custPersonDao;

	@Override
	public void chkWthdwInfo(String custId, String paypassword) throws MyBusinessCheckException {
		// 用户是否绑卡校验
		CustBankCard custBankCard = custBankCardDao
				.selectByCertInfo(MapUtil.toStrMap("custId", custId, "isCert", CertStatus.PASSED.getValue()));
		if (custBankCard == null || !BindingStatus.BIND.getValue().equals(custBankCard.getAuditBindingStatus())) {
			throw new MyBusinessCheckException(ErrorCode.USC007);
		}

		// 是否设置密码,支付密码校验
		CustPerson custPerson = custPersonDao.selectByPrimaryKey(custId);
		if (custPerson.getPayPassword() == null) {
			throw new MyBusinessCheckException(ErrorCode.USC010);
		}
		if (!paypassword.equals(custPerson.getPayPassword())) {
			throw new MyBusinessCheckException(ErrorCode.USC003);
		}

	}

}
