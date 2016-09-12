package com.nbl.services.sequence.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.common.constants.SeqenceConstant;
import com.nbl.common.exception.MyBusinessRuntimeException;
import com.nbl.dao.CuCbSequenceDao;
import com.nbl.dao.CuCpSequenceDao;
import com.nbl.dao.CuOtSequenceDao;
import com.nbl.dao.CuPkSequenceDao;
import com.nbl.model.CuCpSequenceKey;
import com.nbl.model.CuOtSequenceKey;
import com.nbl.model.CuPkSequenceKey;
import com.nbl.services.sequence.IdGeneratorAppService;
import com.nbl.utils.DateTimeUtils;

/**
 * @author sunny-tang
 * @createdate 2016年4月13日
 * @version 1.0
 * @description :
 */
@Service("idGeneratorAppService")
public class IdGeneratorAppServiceImpl implements IdGeneratorAppService {

	private final static Logger logger = LoggerFactory.getLogger(IdGeneratorAppServiceImpl.class);
	@Resource
	private CuCpSequenceDao cuCpSeqDao;
	@Resource
	private CuPkSequenceDao cuPkSequenceDao;
	@Resource
	private CuOtSequenceDao cuOtSequenceDao;
	@Resource
	private CuCbSequenceDao cuCbSeqDao;

	/**
	 * @param seqName
	 * @return
	 * @description: 1、长度生成规则为:8位日期+5位的序列号，比如客户号为：2016040500001
	 *               2、不同业务不同流水，比如产品编号、项目编号各自独立的流水。
	 *               3、根据seqName的不同来确定生成不同的流水号,可提供的seqName:
	 *               CU_CP_SEQUENCE(个人客户号序列)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getNext_13Bit_Sequence(String seqName) {

		if (logger.isInfoEnabled()) {
			logger.info("开始生成序列号，序列号的名称是：" + seqName);
		}

		if (seqName == null || "".equals(seqName)) {
			logger.error("序列号名称为空，请输入");
			throw new MyBusinessRuntimeException("seqName is empty,please input it");
		}
		String date = DateTimeUtils.now().toDate8String();

		String retSeq = null;
		if (SeqenceConstant.CU_CP_SEQUENCE.equals(seqName)) {
			CuCpSequenceKey cuCpSeq = new CuCpSequenceKey();
			cuCpSeq.setCurdate(date);
			cuCpSeqDao.insert(cuCpSeq);
			String seq = String.format("%07d", cuCpSeq.getId());
			retSeq = date + seq;
		} else if (SeqenceConstant.CU_PK_SEQUENCE.equals(seqName)) {
			CuPkSequenceKey cuPkSeq = new CuPkSequenceKey();
			cuPkSeq.setCurdate(date);
			cuPkSequenceDao.insert(cuPkSeq);
			String seq = String.format("%05d", cuPkSeq.getId());
			retSeq = date + seq;
		} else if (SeqenceConstant.BI_OT_SEQUENCE.equals(seqName)) {
			CuOtSequenceKey cuOtSeq = new CuOtSequenceKey();
			cuOtSeq.setCurdate(date);
			cuOtSequenceDao.insert(cuOtSeq);
			String seq = String.format("%05d", cuOtSeq.getId());
			retSeq = date + seq;
		}
		if (logger.isInfoEnabled()) {
			logger.info("生成序列号结束，序列号值为：" + retSeq);
		}

		return retSeq;
	}

	/**
	 * @param seqName
	 * @return
	 * @description: 1、长度生成规则为:8位日期+5位的序列号，比如客户号为：2016040500001
	 *               2、不同业务不同流水，比如产品编号、项目编号各自独立的流水。
	 *               3、根据seqName的不同来确定生成不同的流水号,可提供的seqName:
	 *               CU_CB_SEQUENCE(企业客户号序列)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getNext_13Bit_SequenceCb(String seqName) {

		if (logger.isInfoEnabled()) {
			logger.info("开始生成序列号，序列号的名称是：" + seqName);
		}

		if (seqName == null || "".equals(seqName)) {
			logger.error("序列号名称为空，请输入");
			throw new MyBusinessRuntimeException("seqName is empty,please input it");
		}
		String date = DateTimeUtils.now().toDate8String();

		String retSeq = null;
		if (SeqenceConstant.CU_CB_SEQUENCE.equals(seqName)) {
			CuCpSequenceKey cuCpSeq = new CuCpSequenceKey();
			cuCpSeq.setCurdate(date);
			cuCpSeqDao.insert(cuCpSeq);
			String seq = String.format("%05d", cuCpSeq.getId());
			retSeq = date + seq;
		} else if (SeqenceConstant.CU_PK_SEQUENCE.equals(seqName)) {
			CuPkSequenceKey cuPkSeq = new CuPkSequenceKey();
			cuPkSeq.setCurdate(date);
			cuPkSequenceDao.insert(cuPkSeq);
			String seq = String.format("%05d", cuPkSeq.getId());
			retSeq = date + seq;
		}
		if (logger.isInfoEnabled()) {
			logger.info("生成序列号结束，序列号值为：" + retSeq);
		}

		return retSeq;
	}

	/**
	 * @param seqName
	 * @return
	 * @description: 1、长度生成规则为:8位日期+7位的序列号，比如客户号为：201604050000001
	 *               2、此流水号可以用于一般的表主键等场景:BI_PK_SEQUENCE
	 */
	@Override
	public String getNext_15Bit_Sequence() {

		return null;
	}

	/**
	 * @param seqName
	 * @return
	 * @description:得到发往支付渠道的流水号
	 */
	@Override
	public String getNext_CH_Sequence() {

		return null;
	}

}
