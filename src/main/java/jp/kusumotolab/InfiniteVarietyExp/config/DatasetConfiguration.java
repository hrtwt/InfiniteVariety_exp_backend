package jp.kusumotolab.InfiniteVarietyExp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class DatasetConfiguration {
  @Value("${spring.datasource.dataset.driverClassName}")
  private String driverClassName;

  @Value("${spring.datasource.dataset.url}")
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
