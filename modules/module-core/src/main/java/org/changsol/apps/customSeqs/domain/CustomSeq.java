package org.changsol.apps.customSeqs.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.changsol.utils.bases.domain.ChangSolBaseDomainIdentity;

/**
 * 채번 테이블
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(
	uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "checkDt"})}
)
public class CustomSeq extends ChangSolBaseDomainIdentity {
	/**
	 * 채번 타입 ENUM
	 */
	public enum CustomSeqType {
		ORDER, // 주문 번호 채번
		PAY // 결제 번호 채번
	}

	/**
	 * 채번 타입
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private CustomSeqType type;

	/**
	 * 채번 기준일자
	 */
	@NotNull
	private LocalDate checkDt;

	/**
	 * 채번 값
	 */
	@NotNull
	private Long seq;

	/**
	 * 채번 값 증가
	 */
	public void seqUp() {
		++this.seq;
	}
}
