package org.changsol.apps.customSeqs.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.changsol.apps.customSeqs.domain.CustomSeq;
import org.changsol.apps.customSeqs.enums.CustomSeqType;
import org.changsol.utils.bases.repository.ChangSolBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSeqRepository extends ChangSolBaseRepository<CustomSeq, Long> {
	Optional<CustomSeq> findByTypeAndCheckDt(CustomSeqType type, LocalDate checkDt);
}
