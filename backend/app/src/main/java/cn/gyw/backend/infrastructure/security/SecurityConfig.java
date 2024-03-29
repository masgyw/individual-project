package cn.gyw.backend.infrastructure.security;

import cn.gyw.backend.infrastructure.security.authentication.admin.password.AdminPasswordAuthenticationProvider;
import cn.gyw.backend.infrastructure.security.authentication.admin.password.AdminPasswordLoginProcessFilter;
import cn.gyw.backend.infrastructure.security.authentication.admin.sms.AdminSmsAuthenticationProvider;
import cn.gyw.backend.infrastructure.security.authentication.admin.sms.AdminSmsLoginProcessFilter;
import cn.gyw.individual.starters.security.base.*;
import cn.gyw.individual.starters.security.config.SecurityCommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.cors.CorsUtils;

/**
 * @date 2023/5/23
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan(value = {"cn.gyw.individual.starters.security.base", "cn.gyw.individual.starters.security.config"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityCommonProperties commonProperties;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private AdminPasswordAuthenticationProvider adminPasswordProvider;

    @Autowired
    private AdminSmsAuthenticationProvider adminSmsAuthenticationProvider;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(adminPasswordProvider);
        auth.authenticationProvider(adminSmsAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    public AdminPasswordLoginProcessFilter adminPassFilter() {
        return new AdminPasswordLoginProcessFilter(authenticationManager, failureHandler, successHandler);
    }

    public AdminSmsLoginProcessFilter smsFilter() {
        return new AdminSmsLoginProcessFilter(failureHandler, successHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**")
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html", "/**/*.css",
                        "/**/*.js");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.requiresChannel()
                .anyRequest().requiresInsecure()
                .and()
                .csrf().disable()//csrf取消
                .cors().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()//不再存储session
                .headers().frameOptions().disable()
                .and()
                .headers().addHeaderWriter(
                        new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html", "/**/*.css",
                        "/**/*.js").permitAll()
                .antMatchers("/trace/users/**").permitAll()
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers(commonProperties.getUnAuthUrls()
                        .toArray(new String[commonProperties.getUnAuthUrls().size()])).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(adminPassFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationTokenFilterBean(),
                        UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().cacheControl();
    }

}
