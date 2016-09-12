package com.nbl.services.card.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.common.constants.ComConst;
import com.nbl.dao.CustAccountDao;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.SerialRefDao;
import com.nbl.model.CustBankCard;
import com.nbl.model.SerialRef;
import com.nbl.service.business.constant.BindingStatus;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.business.constant.ProtocolFlag;
import com.nbl.service.user.dto.req.BndBnkCardDto;
import com.nbl.services.card.BindInfoRecService;
import com.nbl.services.sequence.IdGeneratorAppService;
import com.nbl.services.tppayment.BndBnkCrdThdService;
import com.nbl.services.tppayment.SerialRefService;
import com.nbl.util.MapUtil;
import com.nbl.utils.DateTimeUtils;

@Service("bindInfoRecService")
public class BindInfoRecServiceImpl implements BindInfoRecService {
	private final static Logger logger = LoggerFactory.getLogger(BindInfoRecServiceImpl.class);
	@Resource
	private CustAccountDao custAccountDao;
	@Resource
	private IdGeneratorAppService idGeneratorAppService;
	@Resource
	private CustBankCardDao custBankCardDao;
	@Resource
	private SerialRefDao serialRefDao;
	@Resource
	private BndBnkCrdThdService bndBnkCrdZL;
	@Resource
	private CustPersonDao custPersonDao;
	@Resource
	private SerialRefService serialRefService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String[] recordBindInfo(BndBnkCardDto bndBnkCardDto) {
		// 实体主键[0]-custBankCardId;[1]-serialId
		String[] entityIds = new String[2];
		// 登记T_SERIAL_REF
		Map<String, String> condMap = MapUtil.toStrMap("custId", bndBnkCardDto.getCustId(), "cardNo",
				bndBnkCardDto.getCardNo(), "isCert", CertStatus.PASSED.getValue());
		CustBankCard custBankCard = custBankCardDao.selectByCertInfo(condMap);
		entityIds[0] = custBankCard.getId();
		entityIds[1] = serialRefService.recordSerialRef(entityIds[0]);
		return entityIds;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void certRespProcess(String resultType, String serialId, String respTime, String custBankCardId,
			String returnCode, String returnMessage) {

		CustBankCard custBankCard = new CustBankCard();
		SerialRef serialRef = new SerialRef();
		Date respTimeDate = null;
		try {
			respTimeDate = DateTimeUtils.parseDate(respTime).toDate();
		} catch (ParseException e) {
			logger.error("parse time exception", e);
		}
		serialRef.setSerialId(serialId);
		serialRef.setUpdateTime(respTimeDate);
		serialRef.setReturnCode(returnCode);
		serialRef.setReturnMessage(returnMessage);
		custBankCard.setId(custBankCardId);
		custBankCard.setUpdateTime(respTimeDate);

		if (ComConst.UNKNOWN.equals(resultType)) {
			// 同步应答未知，更新认证信息
			serialRef.setRecFlag(ComConst.FALSE);
		} else {
			// 同步成功应答，更新认证信息
			serialRef.setRecFlag(ComConst.TRUE);

			if (ComConst.SUCCESS.equals(resultType)) {
				custBankCard.setIsProtocol(ProtocolFlag.WITH_WH_AGRT.getValue());
				custBankCard.setAuditBindingStatus(BindingStatus.BIND.getValue());
			}
			if (ComConst.ERROR.equals(resultType)) {
				// 同步应答失败，更新认证信息
				custBankCard.setIsProtocol(ProtocolFlag.WITHOUT_WH_AGRT.getValue());
				custBankCard.setAuditBindingStatus(BindingStatus.FAIL_BIND.getValue());
			}
		}
		serialRefDao.updateByPrimaryKeySelective(serialRef);
		custBankCardDao.updateByPrimaryKeySelective(custBankCard);
	}

}
