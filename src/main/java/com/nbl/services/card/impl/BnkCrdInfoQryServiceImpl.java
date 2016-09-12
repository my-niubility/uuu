package com.nbl.services.card.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.constants.ComConst;
import com.nbl.dao.CustBankCardDao;
import com.nbl.model.CustBankCard;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.user.dto.req.QryBnkCrdDto;
import com.nbl.service.user.dto.res.QryBnkCrdResultDto;
import com.nbl.util.MapUtil;
import com.nbl.utils.BeanParseUtils;

/**
 * 绑卡信息查询
 * 
 * @author AlanMa
 *
 */
@Service("bnkCrdInfoQryService")
public class BnkCrdInfoQryServiceImpl implements com.nbl.services.card.BnkCrdInfoQryService {
	@Resource
	private CustBankCardDao custBankCardDao;

	@Override
	public QryBnkCrdResultDto queryDefaultCard(QryBnkCrdDto qryBnkCrdDto) {
		QryBnkCrdResultDto result = new QryBnkCrdResultDto();
		Map<String, String> cond = MapUtil.toStrMap("custId", qryBnkCrdDto.getCustId(), "isDefault", ComConst.TRUE, "isCert", CertStatus.PASSED.getValue());
		CustBankCard custBankCard = custBankCardDao.selectByBindInfo(cond);
		if (custBankCard == null)
			return null;
		BeanParseUtils.copyPropertiesToString(custBankCard, result, ComConst.TRUE);
		return result;
	}

}
