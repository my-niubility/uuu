package com.nbl.services.card;

import com.nbl.service.user.dto.req.QryBnkCrdDto;
import com.nbl.service.user.dto.res.QryBnkCrdResultDto;

/**
 * 银行卡信息查询
 * @author AlanMa
 *
 */
public interface BnkCrdInfoQryService {
	/**
	 * 查询默认银行卡信息
	 * @param qryBnkCrdDto
	 * @return
	 */
	public QryBnkCrdResultDto queryDefaultCard(QryBnkCrdDto qryBnkCrdDto);
}
