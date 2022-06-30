package jp.kusumotolab.InfiniteVarietyExp.database;

import java.util.stream.Stream;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableJpaRepositories(
    basePackages = {"jp.kusumotolab.InfiniteVarietyExp.repository"},
    entityManagerFactoryRef = "resultEntityManager")
@Getter
@Setter
public class ResultsConfiguration {
  @Value("${spring.datasource.results.schema}")
  private String schema;

  @Value("${spring.datasource.results.data}")
  private String data;

  @Bean(name = "resultDataSourceProperties")
  @ConfigurationProperties(prefix = "spring.datasource.results")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean(name = "resultDataSource")
  public DataSource createDataSource(
      @Qualifier("resultDataSourceProperties") DataSourceProperties dataSourceProperties) {
    return dataSourceProperties.initializeDataSourceBuilder().build();
  }

  @Bean(name = "resultDataSourceInitializer")
  public DataSourceInitializer dataSourceInitializer(
      @Qualifier("resultDataSource") DataSource dataSource) {
    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    final ResourceLoader resourceLoader = new DefaultResourceLoader();
    Stream.of(schema).map(resourceLoader::getResource).forEach(populator::addScript);

    final DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setDatabasePopulator(populator);
    return dataSourceInitializer;
  }

  @Bean(name = "resultEntityManager")
  public EntityManagerFactory resultsEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("resultDataSource") DataSource dataSource) {

    final var factory =
        builder.dataSource(dataSource).packages("jp.kusumotolab.InfiniteVarietyExp.entity").build();

    final var vendorAdapter = new HibernateJpaVendorAdapter();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.afterPropertiesSet();

    return factory.getObject();
  }
}
