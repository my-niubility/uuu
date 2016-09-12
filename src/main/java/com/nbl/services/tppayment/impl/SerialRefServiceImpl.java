package com.nbl.services.tppayment.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ErrorCode;
import com.nbl.common.constants.PRConstant;
import com.nbl.common.constants.SeqenceConstant;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.dao.SerialRefDao;
import com.nbl.model.SerialRef;
import com.nbl.service.business.constant.VALIDFlag;
import com.nbl.service.user.dto.res.SerialRefResultDto;
import com.nbl.services.sequence.IdGeneratorAppService;
import com.nbl.services.tppayment.SerialRefService;
import com.nbl.util.DateTimeUtils;
import com.nbl.utils.BeanParseUtils;

/**
 * 流水信息查询
 * @author AlanMa
 *
 */
@Service("serialRefService")
public class SerialRefServiceImpl implements SerialRefService {

	private final static Logger logger = LoggerFactory.getLogger(SerialRefServiceImpl.class);

	@Resource
	private SerialRefDao serialRefDao;
	@Resource
	private IdGeneratorAppService idGeneratorAppService;

	@Override
	public String recordSerialRef(String refId) {
		SerialRef serialRef = new SerialRef();
		// TODO 根据不同渠道制定流水号规则
		String serialId = PRConstant.SERL_REF_ID + idGeneratorAppService.getNext_13Bit_Sequence(SeqenceConstant.CU_PK_SEQUENCE);
		serialRef.setSerialId(serialId);
		serialRef.setRefId(refId);
		serialRef.setRecFlag(VALIDFlag.INVALID.getValue());
		serialRef.setCreateTime(DateTimeUtils.now().toDate());
		serialRef.setUpdateTime(DateTimeUtils.now().toDate());
		serialRefDao.insertSelective(serialRef);
		logger.info("[recordSerialRef success serialId is]:" + serialId);
		return serialId;
	}

	@Override
	public SerialRefResultDto updateSerialRef(String serialId, Date updateTime, String returnCode, String returnMessage, String remark) throws MyBusinessCheckException {

		SerialRef serialRef = serialRefDao.selectByPrimaryKey(serialId);
		if (serialRef == null) {
			logger.error("[none SerialRef selected by serialId]:" + serialId);
			throw new MyBusinessCheckException(ErrorCode.USD003, "SerialRef");
		}
		Date updateTimeDate = (updateTime == null ? DateTimeUtils.now().toDate() : updateTime);
		serialRef.setUpdateTime(updateTimeDate);
		serialRef.setReturnCode(returnCode);
		serialRef.setReturnMessage(returnMessage);
		serialRef.setRemark(remark);
		serialRef.setRecFlag(VALIDFlag.VALID.getValue());
		serialRefDao.updateByPrimaryKeySelective(serialRef);
		SerialRefResultDto resp = new SerialRefResultDto();
		BeanParseUtils.copyProperties(serialRef, resp);
		logger.info("[updateSerialRef success]:" + resp.toString());
		return resp;
	}

}
