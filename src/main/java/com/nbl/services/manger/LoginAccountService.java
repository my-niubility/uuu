package com.nbl.services.manger;

import java.util.List;

import com.nbl.common.vo.PageVO;
import com.nbl.model.MLoginAccount;

public interface LoginAccountService {
	
	/**
	 * @param account
	 * @param reqDto
	 * @return
	 * @description:分页查询
	 */
	public List<MLoginAccount> pageListQueryAccount(PageVO<MLoginAccount> pageVO,MLoginAccount account);
	
	/**
	 * @param account
	 * @return
	 * @description:分页查询统计总数
	 */
	public int pageCountQueryAccount(MLoginAccount account);
	
	/**
	 * @param accountName
	 * @return
	 * @description:登录账户明细查询
	 */
	public MLoginAccount detailQueryAccount(String accountName);
	
	/**
	 * @param accountName
	 * @return
	 * @description:账户是否存在检查
	 */
	public boolean accountCheckQuery(String accountName);
	
	/**
	 * @param account
	 * @return
	 * @description:账户增加
	 */
	public boolean accountAdd(MLoginAccount account);

	/**
	 * @param loginName
	 * @param newPass
	 * @return
	 * @description:修改密码
	 */
	public boolean modifyPass(String loginName,String newPass);


}
