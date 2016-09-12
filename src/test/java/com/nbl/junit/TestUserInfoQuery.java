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
import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.UserInfoQueryDto;
import com.nbl.services.user.UserInfoQueryService;
import com.nbl.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml", "classpath*:/spring/applicationContext-dataSource.xml", "classpath*:/spring/spring-mvc.xml" })
public class TestUserInfoQuery {

	private final static Logger logger = LoggerFactory.getLogger(TestUserInfoQuery.class);
	private static String PARAMS_URI = "src/test/java/com/zlebank/junit/data";

	@Resource
	private UserInfoQueryService userInfoQueryService;

	private static Map<String, String> inputMap;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputMap = FileUtil.getInputParams(PARAMS_URI, null);
		logger.info("【input param is:】" + inputMap.toString());
	}

	@Test
	public void testUserInfoQueryService() {
		logger.info("【enter testUserInfoQueryService】");
		UserInfoQueryDto inputParam = JSONObject.parseObject(inputMap.get("UserInfoQuery.js"), UserInfoQueryDto.class);
		CommRespDto result = userInfoQueryService.queryCustCenterInfo(inputParam);

		logger.info("【success~~~~~~~~~~~~】" + result.toString());

	}

}
