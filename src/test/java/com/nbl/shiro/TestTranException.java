package com.nbl.shiro;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nbl.model.MLoginAccount;
import com.nbl.service.manager.constant.SystemType;
import com.nbl.services.manger.LoginAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestTranException {

	@Resource
	private LoginAccountService loginAccountService;

	@Test
	public void testFindUserService() {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		SystemType systemType = SystemType.MANAGER;
		System.out.println("~~~~~~~~~~~~systemType~~~~~~~~~~~~~~~~"+systemType);
		MLoginAccount account = new MLoginAccount();
		account.setAccountId("tets11");
		account.setLoginName("dtate111");
		account.setPassword("fadfadf11");
		account.setCreateDate(new Date());
		account.setPartyId("dfafd11");
		account.setRoleId("fddaf11");
		
 		loginAccountService.accountAdd(account);

	}

}
