package com.nbl.services.card.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.common.constants.ComConst;
import com.nbl.common.constants.PRConstant;
import com.nbl.common.constants.SeqenceConstant;
import com.nbl.dao.CustAccountDao;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustCertDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.CustRoleDao;
import com.nbl.dao.SerialRefDao;
import com.nbl.model.CustAccount;
import com.nbl.model.CustBankCard;
import com.nbl.model.CustCert;
import com.nbl.model.CustPerson;
import com.nbl.model.SerialRef;
import com.nbl.service.business.constant.AccStatus;
import com.nbl.service.business.constant.BindingStatus;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.business.constant.IsDefault;
import com.nbl.service.business.constant.ProtocolFlag;
import com.nbl.service.business.constant.SendSB;
import com.nbl.service.business.constant.VALIDFlag;
import com.nbl.service.user.constant.CardType;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.services.card.CerInfoRecService;
import com.nbl.services.sequence.IdGeneratorAppService;
import com.nbl.services.tppayment.SerialRefService;
import com.nbl.util.MapUtil;
import com.nbl.utils.DateTimeUtils;

@Service("cerInfoRecService")
public class CerInfoRecServiceImpl implements CerInfoRecService {

	private final static Logger logger = LoggerFactory.getLogger(CerInfoRecServiceImpl.class);

	@Resource
	private CustPersonDao custPersonDao;
	@Resource
	private CustBankCardDao custBankCardDao;
	@Resource
	private CustCertDao custCertDao;
	@Resource
	private CustAccountDao custAccountDao;
	@Resource
	private SerialRefDao serialRefDao;
	@Autowired
	private IdGeneratorAppService idGeneratorAppService;
	@Resource
	private CustRoleDao custRoleDao;
	@Resource
	private SerialRefService serialRefService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String[] recordCertInfo(UserCertDto userCertDto) {
		//获取用户银行卡id
		String refId = getCustBankCardId(userCertDto.getCustId(), userCertDto.getCardNo());
		// 实体主键[0]-custBankCardId;[1]-serialId
		String[] entityIds = new String[2];
		if (refId == null) {

			// 登记T_CUST_PERSON(个人客户表)
			CustPerson custPerson = new CustPerson();
			BeanUtils.copyProperties(userCertDto, custPerson);
			custPerson.setId(userCertDto.getCustId());
			custPerson.setName(userCertDto.getCardName());
			custPerson.setStatus(AccStatus.EFFECTIVE.getValue());
			custPerson.setCertStatus(CertStatus.CERTING.getValue());
			custPersonDao.updateByPrimaryKeySelective(custPerson);

			// 登记T_CUST_BANK_CARD
			String custBankCardId = PRConstant.CUST_BNK_CRD_ID + idGeneratorAppService.getNext_13Bit_Sequence(SeqenceConstant.CU_PK_SEQUENCE);
			entityIds[0] = custBankCardId;
			logger.info("[custBankCard pk]:" + custBankCardId);
			CustBankCard custBankCard = new CustBankCard();
			BeanUtils.copyProperties(userCertDto, custBankCard);

			custBankCard.setIsCert(CertStatus.NEW.getValue());
			custBankCard.setCreateDate(DateTimeUtils.now().toDate());
			custBankCard.setIsProtocol(ProtocolFlag.WITHOUT_WH_AGRT.getValue());
			custBankCard.setIsSend(SendSB.NOT_SEND.getValue());
			custBankCard.setStatus(VALIDFlag.VALID.getValue());
			custBankCard.setId(custBankCardId);
			custBankCard.setIsDefault(IsDefault.YES.getValue());
			custBankCard.setCardType(CardType.DEBIT.getValue());
			custBankCardDao.insertSelective(custBankCard);

			// 登记T_CUST_CERT
			String custCertId = PRConstant.CUST_CERT_ID + idGeneratorAppService.getNext_13Bit_Sequence(SeqenceConstant.CU_PK_SEQUENCE);
			logger.info("[custCert pk]:" + custCertId);
			CustCert custCert = new CustCert();
			BeanUtils.copyProperties(userCertDto, custCert);
			custCert.setCertUpdateTime(DateTimeUtils.now().toDate());
			custCert.setId(custCertId);
			custCertDao.insertSelective(custCert);

			// 登记T_SERIAL_REF
			entityIds[1] = serialRefService.recordSerialRef(custBankCardId);

		} else {
			entityIds[0] = refId;
			// 登记T_SERIAL_REF
			entityIds[1] = serialRefService.recordSerialRef(refId);
		}
		return entityIds;
	}

	private String getCustBankCardId(String custId, String cardNo) {
		Map<String, String> certInfo = MapUtil.toStrMap("isCert", CertStatus.CERTING.getValue(), "custId", custId, "cardNo", cardNo);
		CustBankCard custBankCard = custBankCardDao.selectByCertInfo(certInfo);
		if (custBankCard == null) {
			return null;
		}
		return custBankCard.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void certRespProcess(String resultType, String accountId, String custId, String serialId, String custType, String respTime, String custBankCardId, String returnCode, String returnMessage) {

		CustPerson custPerson = new CustPerson();
		CustBankCard custBankCard = new CustBankCard();
		CustAccount custAccount = new CustAccount();
		SerialRef serialRef = new SerialRef();
		Date respTimeDate = null;
		try {
			respTimeDate = DateTimeUtils.parseDateTime(respTime).toDate();
		} catch (ParseException e) {
			logger.error("time parse exception", e);
		}
		serialRef.setSerialId(serialId);
		serialRef.setUpdateTime(respTimeDate);
		serialRef.setReturnCode(returnCode);
		serialRef.setReturnMessage(returnMessage);
		custPerson.setId(custId);
		custBankCard.setId(custBankCardId);
		custBankCard.setUpdateTime(respTimeDate);

		if (ComConst.UNKNOWN.equals(resultType)) {
			// 同步应答未知，更新认证信息
			serialRef.setRecFlag(ComConst.FALSE);

			custPerson.setCertStatus(CertStatus.CERTING.getValue());

			custBankCard.setIsSend(SendSB.SEND.getValue());
			custBankCard.setIsCert(CertStatus.CERTING.getValue());
		} else {
			// 同步成功应答，更新认证信息
			serialRef.setRecFlag(ComConst.TRUE);

			if (ComConst.SUCCESS.equals(resultType)) {
				custPerson.setCertStatus(CertStatus.PASSED.getValue());

				custBankCard.setIsSend(SendSB.SEND.getValue());
				custBankCard.setIsCert(CertStatus.PASSED.getValue());
				custBankCard.setIsProtocol(ProtocolFlag.WITH_WH_AGRT.getValue());
				custBankCard.setAuditBindingStatus(BindingStatus.BIND.getValue());

				String id = PRConstant.CUST_ACC_ID + idGeneratorAppService.getNext_13Bit_Sequence(SeqenceConstant.CU_PK_SEQUENCE);
				logger.info("[custAccount pk]:" + id);
				custAccount.setId(id);
				custAccount.setAccountId(accountId);
				custAccount.setCustId(custId);
				custAccount.setType(custType);
				custAccountDao.insertSelective(custAccount);
			}
			if (ComConst.ERROR.equals(resultType)) {
				// 同步应答失败，更新认证信息
				custPerson.setCertStatus(CertStatus.NOT_PASSED.getValue());

				custBankCard.setIsSend(SendSB.SEND.getValue());
				custBankCard.setIsCert(CertStatus.NOT_PASSED.getValue());
				custBankCard.setIsProtocol(ProtocolFlag.WITHOUT_WH_AGRT.getValue());
				custBankCard.setAuditBindingStatus(BindingStatus.FAIL_BIND.getValue());
			}
		}
		serialRefDao.updateByPrimaryKeySelective(serialRef);
		custPersonDao.updateByPrimaryKeySelective(custPerson);
		custBankCardDao.updateByPrimaryKeySelective(custBankCard);
	}

}
