package org.changsol.apps.seqs.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.changsol.apps.customSeqs.enums.CustomSeqType;
import org.changsol.apps.customSeqs.service.CustomSeqService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SeqService {

	private final CustomSeqService customSeqService;

	/**
	 * 주문번호 년 기준 채번
	 */
	public String getOrderSeq() {
		LocalDate nowDt = LocalDate.now();
		long seq = customSeqService.createCustomSeq(CustomSeqType.ORDER, LocalDate.of(nowDt.getYear(), 1, 1));
		return String.format("ORD-%d-%s", nowDt.getYear(), StringUtils.leftPad(String.valueOf(seq), 5, "0"));
	}
}
