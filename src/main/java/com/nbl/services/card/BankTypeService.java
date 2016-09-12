package com.nbl.services.card;

import java.util.List;

import com.nbl.common.vo.PageVO;
import com.nbl.model.BankType;
/**
 * @author gcs
 * @createdate 2016年8月3日	
 * @version 1.0
 * 对应的银行类别实体类
 */

public interface BankTypeService {
	
	/**
	 * @param pageVO
	 * @param cb
	 * @return
	 * @description:分页查询
	 */
	public List<BankType> pageListQueryBankType(PageVO<BankType> pageVO,BankType cb);
	
	/**
	 * @param cb
	 * @return
	 * @description:分页查询统计总数
	 */
	public int pageCountQueryBankType(BankType cb);
}
