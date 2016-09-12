package com.nbl.services.account.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.req.ChkWtDwInfoDto;
import com.nbl.services.account.ChkWthdwService;
import com.nbl.services.account.TrdPreChkService;

@Service("chkWthdwService")
public class ChkWthdwServiceImpl implements ChkWthdwService {

	@Resource
	private TrdPreChkService trdPreChkService;

	@Override
	public void chkWthdwInfo(ChkWtDwInfoDto chkWtDwInfoDto) throws MyBusinessCheckException {
		trdPreChkService.chkWthdwInfo(chkWtDwInfoDto.getCustId(), chkWtDwInfoDto.getPayPassword());
	}

}
