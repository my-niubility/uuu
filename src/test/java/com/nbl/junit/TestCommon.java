package com.nbl.junit;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nbl.utils.FileUtil;

public abstract class TestCommon {

	private final static Logger logger = LoggerFactory.getLogger(TestCommon.class);

	private static String PARAMS_URI = "src/test/java/com/zlebank/junit/data";
	public static Map<String, String> inputMap;

	public static void initData() throws Exception {
		inputMap = FileUtil.getInputParams(PARAMS_URI, null);
		logger.info("【input param is:】" + inputMap.toString());
	}
}
