package com.nbl.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nbl.model.MRole;
import com.nbl.service.manager.dto.LoginAccountDto;
import com.nbl.services.manger.ShiroAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestCacheService {

	@Resource
	private ShiroAccountService shiroAccountService;

	@Test
	public void testFindUserService() {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		LoginAccountDto dto = shiroAccountService.getByUserName("Donald");

		System.out.println("dto=" + dto.toString());

	}

	@Test
	public void testFindRoleService() {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		List<MRole> list = shiroAccountService.getRoles("0010100");

		System.out.println("list.size=" + list.size());

	}

}
