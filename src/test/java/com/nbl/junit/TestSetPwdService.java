package com.nbl.junit;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.req.LogPwdInfoDto;
import com.nbl.service.user.dto.req.PayPwdInfoDto;
import com.nbl.services.account.SetPwdService;
import com.nbl.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml","classpath*:/spring/spring-mvc.xml" })
public class TestSetPwdService {

	private final static Logger logger = LoggerFactory.getLogger(TestSetPwdService.class);
	private static String PARAMS_URI = "src/test/java/com/zlebank/junit/data";

	@Resource
	private SetPwdService setPwdService;

	private static Map<String, String> inputMap;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputMap = FileUtil.getInputParams(PARAMS_URI, null);
		logger.info("【input param is:】" + inputMap.toString());
	}

	@Ignore
	public void testSetPayPwd() {
		logger.info("【enter testSetPayPwd】");
		PayPwdInfoDto inputParam = JSONObject.parseObject(inputMap.get("SetPayPwd001.js"), PayPwdInfoDto.class);
		try {
			setPwdService.setPayPwd(inputParam);
		} catch (MyBusinessCheckException e) {
			logger.error("【failed】", e);
		}

		logger.info("【success~~~~~~~~~~~~】");

	}
	
	@Test
	public void testSetLoginPwd() {
		logger.info("【enter testSetLoginPwd】");
		LogPwdInfoDto inputParam = JSONObject.parseObject(inputMap.get("SetLoginPwd001.js"), LogPwdInfoDto.class);
		try {
			setPwdService.setLoginPwd(inputParam);
		} catch (MyBusinessCheckException e) {
			logger.error("【failed】", e);
		}

		logger.info("【success~~~~~~~~~~~~】");

	}

}
