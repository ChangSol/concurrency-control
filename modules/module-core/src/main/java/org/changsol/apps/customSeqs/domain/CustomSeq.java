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
import org.changsol.apps.customSeqs.enums.CustomSeqType;
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
	uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "check_dt"})}
)
public class CustomSeq extends ChangSolBaseDomainIdentity {

	/**
	 * 채번 타입
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private CustomSeqType type;

	/**
	 * 채번 기준일자
	 */
	private LocalDate checkDt;

	/**
	 * 채번 값
	 */
	private Long seq;

	/**
	 * 채번 값 증가
	 */
	public void seqUp() {
		++this.seq;
	}
}