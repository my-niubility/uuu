package com.nbl.services.tppayment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.business.app.TrdOrdQryApp;
import com.nbl.service.business.constant.PaymentType;
import com.nbl.service.user.dto.req.SerFundQryDto;
import com.nbl.service.user.dto.req.WithdrawAlyThdInfoDto;
import com.nbl.service.user.dto.res.SerFundQryRsltItemDto;
import com.nbl.service.user.dto.res.SerialFundQryRsltDto;
import com.nbl.service.user.dto.res.WthdrwAlyThdResultDto;
import com.nbl.services.tppayment.WithdrawThdService;
import com.nbl.util.AmtParseUtil;
import com.nbl.util.DateTimeUtils;

@Service("withdrawZL")
public class WithdrawZLImpl implements WithdrawThdService {
	
	@Resource
	private TrdOrdQryApp trdOrdQryApp;
	
	private final static Logger logger = LoggerFactory.getLogger(WithdrawZLImpl.class);

	@Override
	public CommRespDto withdrawApply(WithdrawAlyThdInfoDto wthdwAlyInfoDto) {
		logger.info("【enter withdrawApply 】：" + wthdwAlyInfoDto.toString());
		// TODO 调用第三方支付
		CommRespDto resp = null;
		WthdrwAlyThdResultDto result = new WthdrwAlyThdResultDto();
		//用户账户可用余额
//		Long userBalance = getUserBalance(wthdwAlyInfoDto.getCustId());

//		if (wthdwAlyInfoDto.getAmt().compareTo(userBalance) == -1
//				|| wthdwAlyInfoDto.getAmt().compareTo(userBalance) == 0) {
			// 成功
		
			result.setAccountId(wthdwAlyInfoDto.getAccId());
			result.setBalance("400.66");
			result.setResultInfo("提现成功（第三方返回）");
			result.setSerailNum(wthdwAlyInfoDto.getSerialNum());
			result.setWithdrawId("THD_TEST_WTHDRW_00001");
			result.setUpdateTime(DateTimeUtils.now().toDate());
			result.setRemark("测试挡板模拟返回");
			resp = new CommRespDto().success(result);
//		}
//
//
//		if (wthdwAlyInfoDto.getAmt().compareTo(userBalance) == 1) {
//			// 失败
//			result.setBalance("0.5");
//			result.setResultInfo("E0003-用户余额不足");
//			result.setSerailNum(wthdwAlyInfoDto.getSerialNum());
//			result.setWithdrawId("THD_TEST_WTHDRW_00001");
//			result.setUpdateTime(DateTimeUtils.now().toDate());
//			result.setRemark("测试挡板模拟返回");
//			resp = new CommRespDto(result).fail("E0003", result.getResultInfo());
//		}
//
//
//		logger.info("[withdrawApply return resp]:" + resp.toString());

		return resp;
	}

	public static void main(String[] args) {
		Long amt1 = new Long(100);
		Long amt2 = new Long(100);

		System.out.println(amt1.compareTo(amt2));
	}

	@Override
	public Long getUserBalance(String custId) {
		// TODO 909模拟实现 余额=充值+收益-提现-支付(balance)
		Long balance = new Long(0);
		Long recharge = new Long(0);
		Long benefit = new Long(0);
		Long withdraw = new Long(0);
		Long payment = new Long(0);
		SerFundQryDto serFundQryDto = new SerFundQryDto();
		serFundQryDto.setCustId(custId);
		serFundQryDto.setStartIndex(0);
		serFundQryDto.setRecordNum(-1);
		// 充值
		serFundQryDto.setType("01");
		CommRespDto result = trdOrdQryApp.querySerialFund(serFundQryDto);
		if (result != null) {
			SerialFundQryRsltDto rechargeDto = (result.getData() == null ? null : (SerialFundQryRsltDto) result.getData());
			if (rechargeDto != null) {
				List<SerFundQryRsltItemDto> cords = rechargeDto.getSerFundQryRsltItemDto();
				if (cords != null && cords.size() > 0) {
					for (SerFundQryRsltItemDto cord : cords) {
						recharge += AmtParseUtil.strToLongAmt(cord.getBenefit());
					}
				}
			}
		}
		// 收益
		serFundQryDto.setType("04");
		result = trdOrdQryApp.querySerialFund4Withdr(serFundQryDto);
		if (result != null) {
			List<SerFundQryRsltItemDto> cords = (result.getData() == null ? null : (List<SerFundQryRsltItemDto>) result.getData());
			if (cords != null && cords.size() > 0) {
				for (SerFundQryRsltItemDto cord : cords) {
					benefit += AmtParseUtil.strToLongAmt(cord.getBenefit());
				}
			}
		}
		// 提现
		serFundQryDto.setType("03");
		result = trdOrdQryApp.querySerialFund4Withdr(serFundQryDto);
		if (result != null) {
			SerialFundQryRsltDto rechargeDto = (result.getData() == null ? null : (SerialFundQryRsltDto) result.getData());
			if (rechargeDto != null) {
				List<SerFundQryRsltItemDto> cords = rechargeDto.getSerFundQryRsltItemDto();
				if (cords != null && cords.size() > 0) {
					for (SerFundQryRsltItemDto cord : cords) {
						withdraw += AmtParseUtil.strToLongAmt(cord.getExpend());
					}
				}
			}
		}
		// 购买支付
		serFundQryDto.setType("02");
		//设置支付类型为账户支付,值为"00"
		serFundQryDto.setPaymentType(PaymentType.ACCOUNT_PAYMENT.getValue());
		result = trdOrdQryApp.querySerialFund4Withdr(serFundQryDto);
		if (result != null) {
			SerialFundQryRsltDto rechargeDto = (result.getData() == null ? null : (SerialFundQryRsltDto) result.getData());
			if (rechargeDto != null) {
				List<SerFundQryRsltItemDto> cords = rechargeDto.getSerFundQryRsltItemDto();
				if (cords != null && cords.size() > 0) {
					for (SerFundQryRsltItemDto cord : cords) {
						payment += AmtParseUtil.strToLongAmt(cord.getExpend());
					}
				}
			}
		}

		balance = recharge + benefit - withdraw - payment;
		return balance;
	}

}
