package jp.kusumotolab.InfiniteVarietyExp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jp.kusumotolab.InfiniteVarietyExp.data.SourceCode;
import lombok.Getter;
import lombok.Setter;

@Repository
public class SqliteService {
  @Autowired private JdbcTemplate jdbcTemplate;

  public SourceCode findSourceCodeById(final int id) {
    final String sql1 = "select leftMethodID, rightMethodID from pairs where id=?";
    final Pairs pair =
        jdbcTemplate.queryForObject(sql1, new BeanPropertyRowMapper<>(Pairs.class), id);
    final String sql2 = "select rtext from methods where id=?";
    final Rtext code1 =
        jdbcTemplate.queryForObject(
            sql2, new BeanPropertyRowMapper<>(Rtext.class), pair.leftMethodID);
    final Rtext code2 =
        jdbcTemplate.queryForObject(
            sql2, new BeanPropertyRowMapper<>(Rtext.class), pair.rightMethodID);

    return new SourceCode(id, code1.rtext, pair.leftMethodID, code2.rtext, pair.rightMethodID);
  }
}

@Getter
@Setter
class Pairs {
  int leftMethodID;
  int rightMethodID;
}

@Getter
@Setter
class Rtext {
  String rtext;
}
