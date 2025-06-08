package com.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import com.manager.exceptionhandling.CustomAccessDeniedHandlerImpl;
import com.manager.exceptionhandling.CustomBasicAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@Profile("!prod")
public class SecurityConfig {
	
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(smc->smc.invalidSessionUrl("/invalidsession"))
		.requiresChannel(rcc->rcc.anyRequest().requiresInsecure())
		.csrf(csrfConfig -> csrfConfig.disable())
		.authorizeHttpRequests((requests) -> requests
		.requestMatchers("/mystats")
		.authenticated()
		.requestMatchers("/offers","/register","/error","/invalidsession")
		.permitAll());
	//   http.formLogin(flc->flc.disable());
	http.formLogin(withDefaults());
	http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
	http.exceptionHandling(ehc->ehc.accessDeniedHandler(new CustomAccessDeniedHandlerImpl()));
//	http.exceptionHandling(ehc->ehc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
	return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource datasource) {
//        UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$Y0BaK4L1r/uX47WvnMgGtuhRMcjLDXqJBv65WDdEqoH9/9.nfGioi").authorities("read").build();
//        UserDetails admin = User.withUsername("admin").password("{noop}Api@5678").authorities("admin").build();
//        return new InMemoryUserDetailsManager(user, admin);
//        return new JdbcUserDetailsManager(datasource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
