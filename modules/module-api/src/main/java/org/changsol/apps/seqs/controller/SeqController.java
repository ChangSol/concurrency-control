package org.changsol.apps.seqs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.changsol.apps.seqs.service.SeqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "채번", description = "채번 서비스 입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/seq")
public class SeqController {

	private final SeqService seqService;

	@Operation(summary = "주문 채번", description = "주문 채번 API입니다. 채번 양식 : ORD-2023-00001")
	@GetMapping
	public ResponseEntity<String> getOrderSeq() {
		return ResponseEntity.ok(seqService.getOrderSeq());
	}
}
