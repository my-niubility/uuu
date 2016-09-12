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
import com.nbl.dao.CustRoleDao;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.services.card.CertificationService;
import com.nbl.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestCertificationService {

	private final static Logger logger = LoggerFactory.getLogger(TestCertificationService.class);
	private static String PARAMS_URI = "src/test/java/com/zlebank/junit/data";

	@Resource
	private CertificationService certificationService;
	@Resource
	private CustRoleDao custRoleDao;

	private static Map<String, String> inputMap;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputMap = FileUtil.getInputParams(PARAMS_URI, null);
		logger.info("【input param is:】" + inputMap.toString());
	}

	@Test
	public void testCert() {
		logger.info("【enter testSetPayPwd】");
		UserCertDto inputParam = JSONObject.parseObject(inputMap.get("certificate001.js"), UserCertDto.class);

		certificationService.certificate(inputParam);

		logger.info("【success~~~~~~~~~~~~】");

	}

}
