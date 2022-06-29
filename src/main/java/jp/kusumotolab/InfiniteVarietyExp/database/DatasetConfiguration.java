package jp.kusumotolab.InfiniteVarietyExp.database;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "spring.datasource.dataset")
@Getter
@Setter
public class DatasetConfiguration {
  private String driverClassName;
  private String url;

  @Bean
  @Primary
  public DataSource createDataSource() {
    return DataSourceBuilder.create().driverClassName(driverClassName).url(url).build();
  }

  @Bean
  @Primary
  public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
