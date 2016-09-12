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
import com.nbl.service.user.dto.req.BndBnkCardDto;
import com.nbl.service.user.dto.res.BndBnkCrdResultDto;
import com.nbl.services.card.BindBankCardService;
import com.nbl.services.tppayment.impl.BndBnkCrdZLImpl;
import com.nbl.utils.DateTimeUtils;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestBindBankCardService {

	private final static Logger logger = LoggerFactory.getLogger(TestBindBankCardService.class);

	@Resource
	private BindBankCardService bindBankCardService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestCommon.initData();
	}

	@Mocked
	BndBnkCrdZLImpl bndBnkCrdZL;

	/**
	 * 方法名要以testExpectation开头
	 */
	@Test
	public void testExpectationBindCard() {
		logger.info("【enter testExpectation】");
		new Expectations() {
			{
				logger.info("【enter Expectations】");
				bndBnkCrdZL.bndBnkCrdApply((BndBnkCardDto) any);
				result = new Delegate<BndBnkCrdResultDto>() {
					// 方法名要以DelegateMethod结尾
					public CommRespDto executeDelegateMethod(BndBnkCardDto bndBnkCardDto) {

						CommRespDto result = null;

						if (Integer.valueOf(bndBnkCardDto.getPhoneNum().substring(10, 11)) % 2 == 0) {
							// 成功
							String accountId = bndBnkCardDto.getAccountId();
							String bindSerialNum = bndBnkCardDto.getBindSerialNum();
							result = new CommRespDto().success(new BndBnkCrdResultDto(accountId, bindSerialNum,
									DateTimeUtils.now().toDateTimeString()));

						}

						if (Integer.valueOf(bndBnkCardDto.getPhoneNum().substring(10, 11)) % 2 != 0) {
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
		BndBnkCardDto inputParam = JSONObject.parseObject(TestCommon.inputMap.get("certificate001.js"),
				BndBnkCardDto.class);
		CommRespDto result = bindBankCardService.bindCardApply(inputParam);

		if (ComConst.SUCCESS.equals(result.getResIdentifier().getReturnType())) {
			logger.info("【SUCCESS result】:" + result.toString());
		} else {
			logger.info("【FAIL result】:" + result.toString());
		}
	}
}
