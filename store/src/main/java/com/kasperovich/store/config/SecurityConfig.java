package com.kasperovich.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableAspectJAutoProxy
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/app/**"),
            new AntPathRequestMatcher(  "/error"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/resources/**"),
            new AntPathRequestMatcher("/static/**" ),
            new AntPathRequestMatcher("/public/**"),
            new AntPathRequestMatcher("/provision/**"),
            new AntPathRequestMatcher("/webui/**"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher( "/configuration/**"),
            new AntPathRequestMatcher("/swagger-ui/**" ),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/api-docs"),
            new AntPathRequestMatcher("/api-docs/**"),
            new AntPathRequestMatcher("/api/v1/**"),
            new AntPathRequestMatcher("/nack040103x"),
            new AntPathRequestMatcher("/nack040103x/**"),
            new AntPathRequestMatcher("/v2/api-docs/**"),
            new AntPathRequestMatcher("/v3/**"),
            new AntPathRequestMatcher("/*.html"),
            new AntPathRequestMatcher("/**/*.html" ),
            new AntPathRequestMatcher("/**/*.css"),
            new AntPathRequestMatcher("/**/*.js"),
            new AntPathRequestMatcher("/**/*.png"),
            new AntPathRequestMatcher("/**/*.jpg"),
            new AntPathRequestMatcher("/**/*.gif"),
            new AntPathRequestMatcher("/**/*.svg"),
            new AntPathRequestMatcher("/**/*.ico"),
            new AntPathRequestMatcher("/**/*.ttf"),
            new AntPathRequestMatcher("/**/*.woff"),
            new AntPathRequestMatcher("/**/*.otf")
    );
    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);


    @Override
    public void configure(WebSecurity web) throws Exception {
        // Filters will not get executed for the resources
//        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    //If Security is not working check application.yaml if it is set to ignore
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling()
                .and()
                .requestMatcher(PROTECTED_URLS)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
//                .addFilterBefore(new VerifyTokenFilter(tokenUtil), AnonymousAuthenticationFilter.class);
    }
    //    @Bean
//    SimpleUrlAuthenticationSuccessHandler successHandler() {
//        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//        successHandler.setRedirectStrategy(new NoRedirectStrategy());
//        return successHandler;
//    }
    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(FORBIDDEN);
    }
}
