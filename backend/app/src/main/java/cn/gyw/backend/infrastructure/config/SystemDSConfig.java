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
@EnableJpaRepositories(entityManagerFactoryRef = "systemEntityManagerFactory",
        transactionManagerRef = "systemTransactionManager",
        basePackages = {"cn.gyw.backend.system", "cn.gyw.backend.template", "cn.gyw.backend.asset", "cn.gyw.backend.order"})
public class SystemDSConfig {

    public static final String[] PACKAGES_TO_SCAN = new String[]{"cn.gyw.backend.system", "cn.gyw.backend.template",
            "cn.gyw.backend.asset", "cn.gyw.backend.order"};

    @Value("${system.datasource.username}")
    private String username;

    @Value("${system.datasource.url}")
    private String jdbcUrl;

    @Value("${system.datasource.driver-class-name}")
    private String driverClass;

    private IConfiguration config = new ConfigurationOnFileYaml();

    @Bean(name = "systemDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.system")
    public DataSource systemDataSource() {
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
        // ds.setMaxLifetime(600000);
        return ds;
    }

    @Bean(name = "systemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("systemDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages(PACKAGES_TO_SCAN)
                .persistenceUnit("system").build();
    }

    @Bean(name = "systemTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("systemEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
