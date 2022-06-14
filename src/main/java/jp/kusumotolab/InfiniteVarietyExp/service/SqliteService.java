package jp.kusumotolab.InfiniteVarietyExp.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jp.kusumotolab.InfiniteVarietyExp.data.SourceCode;

@Repository
public class SqliteService {
  @Autowired private JdbcTemplate jdbcTemplate;

  public List<SourceCode> findSourceCodeById(final int id) {
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

    return Arrays.asList(new SourceCode(id, code1.rtext), new SourceCode(id, code2.rtext));
  }
}

class Pairs {
  int leftMethodID;
  int rightMethodID;

  public void setLeftMethodID(final int leftMethodID) {
    this.leftMethodID = leftMethodID;
  }

  public void setRightMethodID(final int rightMethodID) {
    this.rightMethodID = rightMethodID;
  }
}

class Rtext {
  String rtext;

  public void setRtext(final String rtext) {
    this.rtext = rtext;
  }
}
