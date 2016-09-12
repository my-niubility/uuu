package com.nbl.services.card;

import java.util.List;

import com.nbl.common.vo.PageVO;
import com.nbl.model.FundsLimitBank;

/**
 * @author gcs
 * @createdate 2016年8月3日	
 * @version 1.0
 * 银行资金限额业务层接口
 */

public interface FundsLimitService {
	
	/**
	 * @param pageVO
	 * @param fundsLimit
	 * @return
	 * @description:分页查询
	 */
	public List<FundsLimitBank> pageListQueryFundsLimit(PageVO<FundsLimitBank> pageVO,FundsLimitBank fundsLimit);
	
	/**
	 * @param fundsLimit
	 * @return
	 * @description:分页查询统计总数
	 */
	public int pageCountQueryFundsLimit(FundsLimitBank fundsLimit);
}
