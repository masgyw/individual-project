package cn.gyw.backend.infrastructure.config;

import cn.gyw.platform.configuration.interfaces.IConfiguration;
import cn.gyw.platform.configuration.service.ConfigurationOnFileYaml;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        transactionManagerRef = "houseTransactionManager", basePackages = "cn.gyw.backend.house")
public class HouseDSConfig {

    @Value("${house.datasource.username}")
    private String username;

    @Value("${house.datasource.url}")
    private String jdbcUrl;

    @Value("${house.datasource.driver-class-name}")
    private String driverClass;

    private IConfiguration config = new ConfigurationOnFileYaml();

    @Primary
    @Bean(name = "houseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.house")
    public DataSource houseDataSource() {
        String host = config.getValue("hw-cloud.datasource", "host");
        String pwd = config.getValue("hw-cloud.datasource", "password");
        // 自动选择
        HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create()
                .driverClassName(driverClass)
                .url(String.format(jdbcUrl, host))
                .username(username)
                .password(pwd)
                .build();

        ds.setMaximumPoolSize(2);
        ds.setMaxLifetime(70000);
        return ds;
    }

    @Primary
    @Bean(name = "houseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("houseDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("cn.gyw.backend.house")
                .persistenceUnit("house").build();
    }

    @Primary
    @Bean(name = "houseTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("houseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
