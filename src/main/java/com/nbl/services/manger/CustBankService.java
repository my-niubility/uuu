package com.nbl.services.manger;

import java.util.List;

import com.nbl.common.vo.PageVO;
import com.nbl.model.CustBankCard;

/**
 * @author gcs
 * @createdate 2016年7月13日
 * @version 1.0
 * @description 客户银行卡接口管理
 * */
public interface CustBankService {

	/**
	 * @param pageVO
	 * @param cb
	 * @return
	 * @description:分页查询
	 */
	public List<CustBankCard> pageListQueryCustBankCard(PageVO<CustBankCard> pageVO, CustBankCard cb);

	/**
	 * @param cb
	 * @return
	 * @description:分页查询统计总数
	 */
	public int pageCountQueryCustBank(CustBankCard cb);

	/**
	 * @param custId
	 * @return obj
	 * @description: 根据客户ID查询银行卡信息
	 */
	public CustBankCard custBankDetail(String custId);
	
}
