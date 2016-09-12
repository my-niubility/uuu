package com.nbl;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestUnit {

	@Test
	public void testProperty() {
		String configFile = "src/main/resources/properties/baffleConfig.properties";
		org.springframework.core.io.Resource resource = new FileSystemResource(configFile);
		try {
			Properties property = PropertiesLoaderUtils.loadProperties(resource);
			String result = property.getProperty("certificate.result");
			System.out.println("读取出的结果：" + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
