package jp.kusumotolab.InfiniteVarietyExp.database;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.results")
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

  public void setDriverClassName(final String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public String getUrl() {
    return url;
  }
}
