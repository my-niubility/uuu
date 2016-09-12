package com.nbl.junit;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.nbl.model.CustPerson;
import com.nbl.services.manger.CustPersonService;
import com.nbl.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml", "classpath*:/spring/applicationContext-dataSource.xml", "classpath*:/spring/spring-mvc.xml" })
public class TestUserInfoUpdate {

	private final static Logger logger = LoggerFactory.getLogger(TestUserInfoUpdate.class);
	private static String PARAMS_URI = "src/test/java/com/zlebank/junit/data";

	@Resource
	private CustPersonService custPersonService;

	private static Map<String, String> inputMap;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputMap = FileUtil.getInputParams(PARAMS_URI, null);
		logger.info("【input param is:】" + inputMap.toString());
	}

	@Test
	public void testCustPersonService() {
		logger.info("【enter testCustPersonService】");
		CustPerson custPerson = JSONObject.parseObject(inputMap.get("UserInfoUpdate.js"), CustPerson.class);
		
		logger.info("【success~~~~~~~~~~~~】" + custPersonService.updateCustCommonInfo(custPerson));

	}

}
