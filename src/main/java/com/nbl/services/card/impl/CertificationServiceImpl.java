package com.nbl.services.card.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ErrorCode;
import com.nbl.common.constants.PRConstant;
import com.nbl.common.dto.CommRespDto;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.dao.CustAccountDao;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustCertDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.CustRoleDao;
import com.nbl.dao.SerialRefDao;
import com.nbl.model.CustPerson;
import com.nbl.model.SerialRef;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.business.constant.CustType;
import com.nbl.service.user.dto.req.CertNoticeDto;
import com.nbl.service.user.dto.req.ChkCertInfoDto;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.service.user.dto.res.CertResultDto;
import com.nbl.services.card.CerInfoRecService;
import com.nbl.services.card.CertificationService;
import com.nbl.services.sendmsg.StaMsgSender;
import com.nbl.services.tppayment.CertifiThdService;

@Service("certificationService")
public class CertificationServiceImpl implements CertificationService {
	private final static Logger logger = LoggerFactory.getLogger(CertificationServiceImpl.class);
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
	private CertifiThdService certifZL;
	@Resource
	private CustRoleDao custRoleDao;
	@Resource
	private CerInfoRecService cerInfoRecService;
	@Resource
	private StaMsgSender certNotMsg;
	

	@Override
	public CommRespDto certificate(UserCertDto userCertDto) {
		logger.info("【enter user certificate inputParams is】:" + userCertDto.toString());
		// 记录认证信息[0]-custBankCardId;[1]-serialId
		String[] entityIds = cerInfoRecService.recordCertInfo(userCertDto);
		logger.info("【record cert info success】" + "【custBankCardId】:" + entityIds[0] + "【serialId】:" + entityIds[1]);
		// 调用第三方支付，发送认证申请
		userCertDto.setCertSerialNum(entityIds[1]);
		CommRespDto commRespDto = certifZL.certificateApply(userCertDto);
		CertResultDto thdPtyRslt = (CertResultDto) commRespDto.getData();
		logger.info("【third part payment response is】:" + thdPtyRslt.toString());
		// 更新认证状态
		cerInfoRecService.certRespProcess(commRespDto.getResIdentifier().getReturnType(), thdPtyRslt.getAccountId(), userCertDto.getCustId(), entityIds[1], userCertDto.getCustAccType(),
				thdPtyRslt.getRespTime(), entityIds[0], commRespDto.getResIdentifier().getReturnCode(), commRespDto.getResIdentifier().getReturnMsg());
		logger.info("【update cert info success】" + "【AccountId】:" + thdPtyRslt.getAccountId());

		// 发送站内信
		certNotMsg.sendStationMessage(userCertDto,entityIds,commRespDto);
		return commRespDto;
	}

	@Override
	public void checkCertInfo(ChkCertInfoDto chkCertInfoDto) throws MyBusinessCheckException {
		// 是否已认证
		CustPerson custPerson = custPersonDao.selectByPrimaryKey(chkCertInfoDto.getCustId());
		if (custPerson != null && CertStatus.PASSED.getValue().equals(custPerson.getCertStatus())) {
			throw new MyBusinessCheckException(ErrorCode.USC008);
		}

	}

	@Override
	public void certNotice(CertNoticeDto certNoticeDto) {
		String certSerialNum = certNoticeDto.getCertSerialNum();
		SerialRef serialRef = serialRefDao.selectByPrimaryKey(certSerialNum);
		String serialId = serialRef.getSerialId();
		String custBankCardId = serialRef.getRefId();

		String custId = custBankCardDao.selectByPrimaryKey(custBankCardId).toString();
		String custType = custId.startsWith(PRConstant.CUST_PER_ID) ? CustType.PERONAL.getValue() : CustType.ENTERPRISE.getValue();

		cerInfoRecService.certRespProcess(certNoticeDto.getReturnType(), certNoticeDto.getAccountId(), custId, serialId, custType, certNoticeDto.getRespTime(), custBankCardId,
				certNoticeDto.getReturnCode(), certNoticeDto.getReturnMsg());
	}
}
