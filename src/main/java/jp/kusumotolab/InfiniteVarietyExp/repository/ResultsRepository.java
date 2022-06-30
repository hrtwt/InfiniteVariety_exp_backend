package jp.kusumotolab.InfiniteVarietyExp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.kusumotolab.InfiniteVarietyExp.entity.ResultEntity;

@Repository
public interface ResultsRepository extends JpaRepository<ResultEntity, Long> {
  List<ResultEntity> findAllByUser(String user);

  List<ResultEntity> findAllByPairIdAndUser(Integer pairId, String user);
}
