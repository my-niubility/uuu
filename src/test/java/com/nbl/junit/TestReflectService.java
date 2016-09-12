package com.nbl.junit;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestReflectService {

	private final static Logger logger = LoggerFactory.getLogger(TestReflectService.class);
	private static String PARAMS_URI = "src/test/java/com/zlebank/junit/data";

	// @Resource
	// private ReflectTest reflectTest;
	//
	// private static Map<String, String> inputMap;
	//
	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {
	// inputMap = FileUtil.getInputParams(PARAMS_URI, null);
	// logger.info("【input param is:】" + inputMap.toString());
	// }
	//
	// @Test
	// public void testReflect() {
	// logger.info("【enter testReflect】");
	// String productPrefix = "eng";
	// String productId = "0001";
	// Object obj = reflectTest.getProductFeatureInfo(productPrefix, productId);
	// CommRespDto commRep = new CommRespDto().success(obj);
	// logger.info("【success~~~~~~~~~~~~】:" + commRep.toString());
	// }

}
