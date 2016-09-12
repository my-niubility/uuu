package com.nbl.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nbl.service.manager.constant.SystemType;
import com.nbl.services.manger.RoleResourcePermService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestResourceCacheService {

	@Resource
	private RoleResourcePermService roleResourcePermService;

	@Test
	public void testFindUserService() throws InstantiationException, IllegalAccessException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		SystemType systemType = SystemType.MANAGER;
		System.out.println("~~~~~~~~~~~~systemType~~~~~~~~~~~~~~~~"+systemType);
 		List<String> list = roleResourcePermService.getResourceIdCache(systemType);
 		for(String ss:list){
 			System.out.println("list.name=" + ss);

 		}
 		

	}

}
