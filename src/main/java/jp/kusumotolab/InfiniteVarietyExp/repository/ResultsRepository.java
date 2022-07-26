package jp.kusumotolab.InfiniteVarietyExp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jp.kusumotolab.InfiniteVarietyExp.entity.ResultEntity;

@Repository
public interface ResultsRepository extends JpaRepository<ResultEntity, Long> {
  List<ResultEntity> findAllByUser(String user);

  @Query(
      value =
          """
          SELECT
              *
          FROM
              results
          WHERE
              NOT EXISTS(
                  SELECT
                      1
                  FROM
                      (
                          SELECT
                              results.*
                          FROM
                              results
                          WHERE
                              results.user_name = ?1
                      ) sub
                  WHERE
                      results.pair_id = sub.pair_id
                  AND results.update_date < sub.update_date
              )
              AND results.user_name = ?1
          ;
          """,
      nativeQuery = true)
  List<ResultEntity> findLastUpdateAllByUser(String user);

  List<ResultEntity> findAllByPairIdAndUser(Integer pairId, String user);
}
