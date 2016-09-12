package com.nbl.services.sendmsg.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ComConst;
import com.nbl.common.dto.CommRespDto;
import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.model.CustBankCard;
import com.nbl.model.CustPerson;
import com.nbl.service.business.app.UserMessageApp;
import com.nbl.service.business.constant.CredentialsType;
import com.nbl.service.business.constant.ParamKeys;
import com.nbl.service.business.constant.UserMessageType;
import com.nbl.service.business.dto.req.MessageReqDto;
import com.nbl.service.manager.app.GeneralParameterApp;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.services.sendmsg.StaMsgSender;
import com.nbl.util.HideSensInfoUtil;
import com.nbl.utils.DateTimeUtils;
import com.nbl.utils.threadpool.ThreadPoolProcessor;

/**
 * 发送认证通知
 * 
 * @author AlanMa
 *
 */
@Service("certNotMsg")
public class CertNotMsgImpl implements StaMsgSender {
	private final static Logger logger = LoggerFactory.getLogger(CertNotMsgImpl.class);

	private UserCertDto userCertDto = null;
	/**
	 * 记录认证信息[0]-custBankCardId;[1]-serialId
	 */
	private String[] entityIds = null;
	private CommRespDto commRespDto = null;
	/**
	 * 用户编号
	 */
	private String custId;
	@Resource
	private GeneralParameterApp generalParameterApp;
	@Resource
	private UserMessageApp userMessageApp;
	@Resource
	private CustPersonDao custPersonDao;
	@Resource
	private CustBankCardDao custBankCardDao;

	@Override
	public void sendStationMessage(Object... msgInfo) {
		userCertDto = (UserCertDto) msgInfo[0];
		entityIds = (String[]) msgInfo[1];
		commRespDto = (CommRespDto) msgInfo[2];
		// 获取custId
		custId = userCertDto.getCustId();
		SendStaMsgCert msg = new SendStaMsgCert(UserMessageType.BUSINESS.getValue());
		ThreadPoolProcessor tpProcessor = ThreadPoolProcessor.getInstanceFixed(ComConst.TP_MAX);
		tpProcessor.execute(msg);
	}

	class SendStaMsgCert implements Runnable {

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

		public SendStaMsgCert() {
			super();
		}

		public SendStaMsgCert(String msgType) {
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

			CustBankCard custBankCard = custBankCardDao.selectByPrimaryKey(entityIds[0]);
			CustPerson custPerson = custPersonDao.selectByPrimaryKey(custId);

			if (ComConst.SUCCESS.equals(commRespDto.getResIdentifier().getReturnType())) {
				// 信息内容KEY
				msgTitle = ParamKeys.SMTS_CERT.getValue();
				msgCont = ParamKeys.SMCS_CERT.getValue();
				// 内容变量值:0-认证时间，1-姓名，2-证件类型，3-证件号，4-银行卡号
				contentVariables = new String[5];
			} else {
				// 信息内容KEY
				msgTitle = ParamKeys.SMTF_CERT.getValue();
				msgCont = ParamKeys.SMCF_CERT.getValue();
				// 内容变变量值: 0-认证时间，1-姓名，2-证件类型，3-证件号，4-银行卡号,5-失败原因
				contentVariables = new String[6];
				contentVariables[5] = commRespDto.getResIdentifier().getReturnMsg();
			}
			try {
				contentVariables[0] = new DateTimeUtils(custPerson.getCreatedTime()).toDateTimeString();
				contentVariables[1] = URLDecoder.decode(custPerson.getName(), "UTF-8");
				contentVariables[2] = URLDecoder.decode(CredentialsType.parseOf(custPerson.getCredentialsType()).getDisplayName(), "UTF-8");
				contentVariables[3] = HideSensInfoUtil.hideIdNum(custPerson.getIdentityCardNumber());
				contentVariables[4] = HideSensInfoUtil.hideCardNum(custBankCard.getCardNo());
			} catch (UnsupportedEncodingException e) {
				logger.error("UnsupportedEncodingException", e);
			}
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
