package com.nbl.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.service.user.app.BnkCrdInfoQryApp;
import com.nbl.service.user.dto.req.QryBnkCrdDto;
import com.nbl.service.user.dto.res.QryBnkCrdResultDto;
import com.nbl.services.card.BnkCrdInfoQryService;

@Service("bnkCrdInfoQryApp")
public class BnkCrdInfoQryAppImpl implements BnkCrdInfoQryApp {

	@Autowired
	private BnkCrdInfoQryService bnkCrdInfoQryService;
	
	@Override
	public QryBnkCrdResultDto queryDefaultCard(QryBnkCrdDto qryBnkCrdDto) {
		return bnkCrdInfoQryService.queryDefaultCard(qryBnkCrdDto);
	}

}
