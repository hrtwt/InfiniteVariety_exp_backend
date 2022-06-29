package jp.kusumotolab.InfiniteVarietyExp.database;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "spring.datasource.results")
@Getter
@Setter
public class ResultsConfiguration {
  private String driverClassName;
  private String url;

  @Bean(name = "resultsDs")
  public DataSource createDataSource() {
    return DataSourceBuilder.create().driverClassName(driverClassName).url(url).build();
  }

  @Bean(name = "resultsJdbc")
  public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
