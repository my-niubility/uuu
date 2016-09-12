package com.nbl.jmokit;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.nbl.common.constants.ComConst;
import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.service.user.dto.res.CertResultDto;
import com.nbl.services.card.CertificationService;
import com.nbl.services.tppayment.impl.CertifZLImpl;
import com.nbl.util.CertCodeUtil;
import com.nbl.utils.DateTimeUtils;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestCertificationService {

	private final static Logger logger = LoggerFactory.getLogger(TestCertificationService.class);

	@Resource
	private CertificationService certificationService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestCommon.initData();
	}

	@Mocked
	CertifZLImpl certifZLImpl;

	/**
	 * 方法名要以testExpectation开头
	 */
	@Test
	public void testExpectationCerficate() {
		logger.info("【enter testExpectation】");
		new Expectations() {
			{
				logger.info("【enter Expectations】");
				certifZLImpl.certificateApply((UserCertDto) any);
				result = new Delegate<CertResultDto>() {
					// 方法名要以DelegateMethod结尾
					public CommRespDto executeDelegateMethod(UserCertDto userCertDto) {

						CommRespDto result = null;

						if (Integer.valueOf(userCertDto.getPhoneNum().substring(10, 11)) % 2 == 0) {
							// 成功
							String accountId = CertCodeUtil.getRegMsgCertCode();
							String certSerialNum = userCertDto.getCertSerialNum();
							result = new CommRespDto()
									.success(new CertResultDto(accountId, certSerialNum, DateTimeUtils.now().toDateTimeString()));
						}

						if (Integer.valueOf(userCertDto.getPhoneNum().substring(10, 11)) % 2 != 0) {
							// 失败
							result = new CommRespDto().fail("ETEST001", "测试认证失败");
						}

						return result;
					}
				};
			}
		};

		// 测试逻辑
		logger.info("【Test content】");
		UserCertDto inputParam = JSONObject.parseObject(TestCommon.inputMap.get("certificate001.js"),
				UserCertDto.class);
		CommRespDto result = certificationService.certificate(inputParam);

		if (ComConst.SUCCESS.equals(result.getResIdentifier().getReturnType())) {
			logger.info("【SUCCESS result】:" + result.toString());
		} else {
			logger.info("【FAIL result】:" + result.toString());
		}
	}
}
