package jp.kusumotolab.InfiniteVarietyExp.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jp.kusumotolab.InfiniteVarietyExp.data.SourceCode;

@Repository
public class DatasetRepository {
  @Autowired private JdbcTemplate jdbcTemplate;

  public SourceCode findSourceCodeByPairId(final int pairId) {
    final int leftMethodID =
        jdbcTemplate.queryForObject(
            "select leftMethodID from pairs where id=?", Integer.class, pairId);
    final int rightMethodID =
        jdbcTemplate.queryForObject(
            "select rightMethodID from pairs where id=?", Integer.class, pairId);

    final String leftMethod = findMethodById(leftMethodID);
    final String rightMethod = findMethodById(rightMethodID);

    return new SourceCode(pairId, leftMethod, leftMethodID, rightMethod, rightMethodID);
  }

  public String findMethodById(final int id) {
    return jdbcTemplate.queryForObject("select rtext from methods where id=?", String.class, id);
  }

  public List<Integer> findAllPairId() {
    final String sql = "select id from pairs";
    final var ids = jdbcTemplate.queryForList(sql);
    return ids.stream()
        .map(Map::values)
        .flatMap(Collection::stream)
        .map(Object::toString)
        .map(Integer::parseInt)
        .sorted()
        .collect(Collectors.toList());
  }
}
