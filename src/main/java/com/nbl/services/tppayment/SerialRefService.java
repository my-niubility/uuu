package com.nbl.services.tppayment;

import java.util.Date;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.res.SerialRefResultDto;

public interface SerialRefService {
	public String recordSerialRef(String refId);

	public SerialRefResultDto updateSerialRef(String serialId, Date updateTime, String returnCode, String returnMessage,
			String remark) throws MyBusinessCheckException;

}
