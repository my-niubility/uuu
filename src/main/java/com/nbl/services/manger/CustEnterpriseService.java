package com.nbl.services.manger;

import java.util.List;

import com.nbl.common.vo.PageVO;
import com.nbl.model.CustEnterprise;

public interface CustEnterpriseService {
	/**
	 * @param pageVO
	 * @param enterprise
	 * @return
	 * @description:分页查询
	 */
	public List<CustEnterprise> pageListQueryEnterprise(PageVO<CustEnterprise> pageVO,CustEnterprise enterprise);
	
	/**
	 * @param enterprise
	 * @return
	 * @description:分页查询统计总数
	 */
	public int pageCountQueryEnterprise(CustEnterprise enterprise);
	
	/**
	 * @param name
	 * @return
	 * @description:登录账户明细查询
	 */
	public CustEnterprise detailQueryEnterprise(String name);
	
	/**
	 * @param name  企业名称
	 * @return
	 * @description:账户是否存在检查
	 */
	public boolean enterpriseCheckQuery(String name);
	
	/**
	 * @param cb
	 * @return
	 * @description:账户增加
	 */
	public boolean enterpriseAdd(CustEnterprise enterprise);
}
