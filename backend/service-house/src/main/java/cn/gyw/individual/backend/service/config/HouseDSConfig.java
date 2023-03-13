package cn.gyw.individual.backend.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @date 2023/3/13
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "houseEntityManagerFactory",
        transactionManagerRef = "houseTransactionManager", basePackages = "cn.gyw.individual.backend.service.repository")
public class HouseDSConfig {

    @Bean(name = "houseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.house")
    public DataSource houseDataSource() {
        // 自动选择
        DataSource ds = DataSourceBuilder.create().build();

        return ds;
    }

    @Bean(name = "houseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("houseDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("cn.gyw.individual.backend.service.domain")
                .persistenceUnit("house").build();
    }

    @Bean(name = "houseTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("houseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
