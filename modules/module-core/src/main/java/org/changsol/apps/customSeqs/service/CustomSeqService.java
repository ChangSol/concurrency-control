package org.changsol.apps.customSeqs.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.changsol.apps.customSeqs.domain.CustomSeq;
import org.changsol.apps.customSeqs.enums.CustomSeqType;
import org.changsol.apps.customSeqs.repository.CustomSeqRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomSeqService {

	private final CustomSeqRepository customSeqRepository;

	/**
	 * 채번 생성
	 * <br>년 기준 형식 : yyyy-01-01
	 * <br>월 기준 형식 : yyyy-mm-01
	 * <br>일 기준 형식 : yyyy-mm-dd
	 * @param type    채번 타입
	 * @param checkDt 채번 기준일자
	 * @return 채번 값
	 */
	@Transactional
	public long createCustomSeq(CustomSeqType type, LocalDate checkDt) {
		// 채번 데이터 GET
		CustomSeq customSeq = customSeqRepository.findByTypeAndCheckDt(type, checkDt).orElse(CustomSeq.builder()
																									  .type(type)
																									  .checkDt(checkDt)
																									  .seq(0L)
																									  .build());
		// 채번 값 증가
		customSeq.seqUp();

		customSeqRepository.save(customSeq);

		return customSeq.getSeq();
	}
}
