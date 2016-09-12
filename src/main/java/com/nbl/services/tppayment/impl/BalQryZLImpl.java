package com.nbl.services.tppayment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.dao.CustAccountDao;
import com.nbl.service.business.app.TrdOrdQryApp;
import com.nbl.service.business.constant.PaymentType;
import com.nbl.service.user.dto.req.SerFundQryDto;
import com.nbl.service.user.dto.res.BalanceInfoResultDto;
import com.nbl.service.user.dto.res.SerFundQryRsltItemDto;
import com.nbl.service.user.dto.res.SerialFundQryRsltDto;
import com.nbl.services.tppayment.BalQryThdService;
import com.nbl.util.AmtParseUtil;

@Service("balQryZL")
public class BalQryZLImpl implements BalQryThdService {
	@Resource
	private CustAccountDao custAccountDao;
	@Resource
	private TrdOrdQryApp trdOrdQryApp;

	@Override
	public BalanceInfoResultDto queryBalance(String accountId) {
		// TODO 909模拟实现 余额=充值-提现(受理和成功)-支付(balance)
		// TODO 挡板实现，无accId暂时传custId代替
		String custId = accountId;
		Long balance = new Long(0);
		Long recharge = new Long(0);
		// Long benefit = new Long(0);
		Long withdraw = new Long(0);
		Long payment = new Long(0);
		SerFundQryDto serFundQryDto = new SerFundQryDto();
		serFundQryDto.setCustId(custId);
		serFundQryDto.setStartIndex(0);
		serFundQryDto.setRecordNum(-1);
		// 充值
		serFundQryDto.setType("01");
		CommRespDto result = trdOrdQryApp.querySerialFund4Withdr(serFundQryDto);
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
		// 收益 收益和持有资产直接打到用户卡里，不在余额体现
		// serFundQryDto.setType("04");
		// result = trdOrdQryApp.querySerialFund4Withdr(serFundQryDto);
		// if (result != null) {
		// List<SerFundQryRsltItemDto> cords = (result.getData() == null ? null
		// : (List<SerFundQryRsltItemDto>) result.getData());
		// if (cords != null && cords.size() > 0) {
		// for (SerFundQryRsltItemDto cord : cords) {
		// benefit += AmtParseUtil.strToLongAmt(cord.getBenefit());
		// }
		// }
		// }
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
		// 设置支付类型为账户支付,值为"00"
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

		balance = recharge - withdraw - payment;
		BalanceInfoResultDto dto = new BalanceInfoResultDto();
		dto.setUsableBalance(AmtParseUtil.longToStrAmt(balance));
		return dto;
	}

}
