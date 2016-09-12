package com.nbl.services.sendmsg.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ComConst;
import com.nbl.service.business.app.UserMessageApp;
import com.nbl.service.business.constant.ParamKeys;
import com.nbl.service.business.constant.UserMessageType;
import com.nbl.service.business.dto.req.MessageReqDto;
import com.nbl.service.manager.app.GeneralParameterApp;
import com.nbl.services.sendmsg.StaMsgSender;
import com.nbl.utils.threadpool.ThreadPoolProcessor;

/**
 * 发送注册结果通知
 * 
 * @author AlanMa
 *
 */
@Service("regNotMsg")
public class RegNotMsgImpl implements StaMsgSender {
	private final static Logger logger = LoggerFactory.getLogger(RegNotMsgImpl.class);

	private String custId = null;
	private String createDateStr = null;
	private String phoneNum = null;
	@Resource
	private GeneralParameterApp generalParameterApp;
	@Resource
	private UserMessageApp userMessageApp;

	@Override
	public void sendStationMessage(Object... msgInfo) {
		custId = (String) msgInfo[0];
		createDateStr = (String) msgInfo[1];
		phoneNum = (String) msgInfo[2];

		SendStaMsgReg msg = new SendStaMsgReg(UserMessageType.BUSINESS.getValue());
		ThreadPoolProcessor tpProcessor = ThreadPoolProcessor.getInstanceFixed(ComConst.TP_MAX);
		tpProcessor.execute(msg);
	}

	class SendStaMsgReg implements Runnable {

		/**
		 * 信息类型（枚举类UserMessageType）
		 */
		private String msgType;
		/**
		 * 信息标题KEY（枚举类ParamKeys）
		 */
		private String msgTitle;
		/**
		 * 信息内容KEY（枚举类ParamKeys）
		 */
		private String msgCont;
		/**
		 * 信息标题变量值
		 */
		private String[] titlesVariables;
		/**
		 * 信息内容变量值
		 */
		private String[] contentVariables;

		public SendStaMsgReg() {
			super();
		}

		public SendStaMsgReg(String msgType) {
			super();
			this.msgType = msgType;
		}

		/**
		 * 替换消息内容配置中的通配符%s
		 * 
		 * @param variables
		 */
		public String fillContent() {
			msgCont = generalParameterApp.getValueByCode(msgCont);
			if (contentVariables != null) {
				for (int index = 0; index < contentVariables.length; index++) {
					if (msgCont.indexOf("%s") != -1) {
						msgCont = msgCont.replaceFirst("%s", contentVariables[index]);
					}
				}
			}
			return msgCont;
		}

		/**
		 * 替换消息标题配置中的通配符%s
		 * 
		 * @param variables
		 */
		public String fillTitle() {
			msgTitle = generalParameterApp.getValueByCode(msgTitle);
			if (titlesVariables != null) {
				for (int index = 0; index < titlesVariables.length; index++) {
					if (msgTitle.indexOf("%s") != -1) {
						msgTitle = msgCont.replaceFirst("%s", titlesVariables[index]);
					}
				}
			}
			return msgTitle;
		}

		@Override
		public void run() {
			logger.info("【send station message begin】");
			// 信息内容KEY
			msgTitle = ParamKeys.SMT_REG.getValue();
			msgCont = ParamKeys.SMC_REG.getValue();
			// 内容变量值:0-注册送时间，1-电话号码
			contentVariables = new String[2];
			contentVariables[0] = createDateStr;
			contentVariables[1] = phoneNum;

			// 发送信息
			MessageReqDto reqDto = new MessageReqDto();
			reqDto.setUserId(generalParameterApp.getValueByCode(ParamKeys.CUST_ID_ZGPT.getValue()));
			List<String> receptUserIds = new ArrayList<String>();
			receptUserIds.add(custId);
			reqDto.setUserIds(receptUserIds);
			reqDto.setMessageType(msgType);
			reqDto.setTitle(fillTitle());
			reqDto.setContent(fillContent());
			userMessageApp.addMessage(reqDto);
			logger.info("【send station message finish】");
		}

	}

}
