package com.nbl.app.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.app.SerialRefProxyApp;
import com.nbl.service.user.dto.req.SerialRefDto;
import com.nbl.service.user.dto.res.SerialRefResultDto;
import com.nbl.services.tppayment.SerialRefService;

@Service("serialRefProxyApp")
public class SerialRefProxyAppImpl implements SerialRefProxyApp {
	@Resource
	private SerialRefService serialRefService;

	@Override
	public SerialRefResultDto updateSerialRef(SerialRefDto serialRefDto) throws MyBusinessCheckException {
		return serialRefService.updateSerialRef(serialRefDto.getSerialId(), null, serialRefDto.getReturnCode(),
				serialRefDto.getReturnMessage(), serialRefDto.getRemark());
	}

}
