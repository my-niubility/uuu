package com.nbl.services.card.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ErrorCode;
import com.nbl.common.dto.CommRespDto;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.dao.CustAccountDao;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.SerialRefDao;
import com.nbl.model.CustBankCard;
import com.nbl.model.CustPerson;
import com.nbl.model.SerialRef;
import com.nbl.service.business.constant.CertStatus;
import com.nbl.service.user.dto.req.BindNoticeDto;
import com.nbl.service.user.dto.req.BndBnkCardDto;
import com.nbl.service.user.dto.req.ChkBndCrdInfoDto;
import com.nbl.service.user.dto.res.BndBnkCrdResultDto;
import com.nbl.services.card.BindBankCardService;
import com.nbl.services.card.BindInfoRecService;
import com.nbl.services.sequence.IdGeneratorAppService;
import com.nbl.services.tppayment.BndBnkCrdThdService;

@Service("bindBankCardService")
public class BindBankCardServiceImpl implements BindBankCardService {

	private final static Logger logger = LoggerFactory.getLogger(BindBankCardServiceImpl.class);
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
	private BindInfoRecService bindInfoRecService;

	@Override
	public CommRespDto bindCardApply(BndBnkCardDto bndBnkCardDto) {

		// 记录绑卡流水信息 实体主键[0]-custBankCardId;[1]-serialId
		String[] entityIds = bindInfoRecService.recordBindInfo(bndBnkCardDto);
		logger.info("【record bind info success】" + "[custBankCardId]:" + entityIds[0] + "[serialId]:" + entityIds[1]);
		// 调用第三方支付，发送綁卡申请
		//设置accountId、绑卡流水号
		bndBnkCardDto.setAccountId(custAccountDao.selectAccIdByCustId(bndBnkCardDto.getCustId()));
		bndBnkCardDto.setBindSerialNum(entityIds[1]);
		CommRespDto commRespDto = bndBnkCrdZL.bndBnkCrdApply(bndBnkCardDto);
		BndBnkCrdResultDto thdPtyRslt = (BndBnkCrdResultDto) commRespDto.getData();
		logger.info("【third party response】：" + commRespDto.toString());
		// 更新绑卡状态
		bindInfoRecService.certRespProcess(commRespDto.getResIdentifier().getReturnType(), entityIds[1],
				thdPtyRslt.getRespTime(), entityIds[0], commRespDto.getResIdentifier().getReturnCode(),
				commRespDto.getResIdentifier().getReturnMsg());
		logger.info("【update cert info success】" + "[AccountId]:" + thdPtyRslt.getAccountId());

		return commRespDto;

	}

	@Override
	public void bindNotice(BindNoticeDto bindNoticeDto) {
		String certSerialNum = bindNoticeDto.getBindSerialNum();
		SerialRef serialRef = serialRefDao.selectByPrimaryKey(certSerialNum);
		String serialId = serialRef.getSerialId();
		String custBankCardId = serialRef.getRefId();
		bindInfoRecService.certRespProcess(bindNoticeDto.getReturnType(), serialId, bindNoticeDto.getRespTime(),
				custBankCardId, bindNoticeDto.getReturnCode(), bindNoticeDto.getReturnMsg());
	}

	@Override
	public void checkBnkCrdInfo(ChkBndCrdInfoDto chkBndCrdInfoDto) throws MyBusinessCheckException {
		// 是否已认证
		if (!CertStatus.PASSED.getValue()
				.equals(custPersonDao.selectByPrimaryKey(chkBndCrdInfoDto.getCustId()).getCertStatus())) {
			throw new MyBusinessCheckException(ErrorCode.USC006);
		}
		//验证ChkBndCrdInfoDto信息与实名认证提供的信息是否相同
		CustPerson custPerson = custPersonDao.selectByPrimaryKey(chkBndCrdInfoDto.getCustId());
		CustBankCard custBankCard = custBankCardDao.selectByCustId(chkBndCrdInfoDto.getCustId());
		//验证银行卡开户姓名
		if(!chkBndCrdInfoDto.getCardName().equals(custPerson.getName())){
			throw new MyBusinessCheckException(ErrorCode.USC012);
		}
		//验证证件类型
		if(!chkBndCrdInfoDto.getCredentialsType().equals(custPerson.getCredentialsType())){
			throw new MyBusinessCheckException(ErrorCode.USC012);
		}
		//验证证件编号
		if(!chkBndCrdInfoDto.getId().equals(custPerson.getIdentityCardNumber())){
			throw new MyBusinessCheckException(ErrorCode.USC012);
		}
		//验证银行卡所属银行
		if(!chkBndCrdInfoDto.getBankType().equals(custBankCard.getBankType())){
			throw new MyBusinessCheckException(ErrorCode.USC012);
		}
		//验证银行卡号
		if(!chkBndCrdInfoDto.getCardNum().equals(custBankCard.getCardNo())){
			throw new MyBusinessCheckException(ErrorCode.USC012);
		}
		
	}

}
