package cn.gyw.individual.commons.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

/**
 * 读取本地配置文件
 *
 * ps: EnvironmentPostProcessor 扩展点使用，部署到linux 时无效
 * 1.在META-INF/spring.factories 文件中配置
 * 2.org.springframework.boot.env.EnvironmentPostProcessor=全类名
 */
@Configuration
@Profile(value = {"remote"})
@Order
public class LocalConfigLoader implements BeanFactoryPostProcessor, EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(LocalConfigLoader.class);

    public static final String KEY_FILE = "local.config.file";

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String localFile = environment.getProperty(KEY_FILE);
        Objects.requireNonNull(localFile);
        log.info("Load local config from :{}", localFile);

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(localFile))) {
            Properties properties = new Properties();
            properties.load(reader);
            log.info("配置中心：{}", properties);
            PropertiesPropertySource propertySource = new PropertiesPropertySource("customerApplicationConfig", properties);
            ConfigurableEnvironment env = (ConfigurableEnvironment) environment;
            env.getPropertySources().addLast(propertySource);
        } catch (IOException e) {
            log.error("Local config load error :", e);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
