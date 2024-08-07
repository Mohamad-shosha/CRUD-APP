package com.luv2code.springboot.thymeleafdemo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FilterRole {
    //	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(configurer ->
//				configurer
//						.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
//						.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
//						.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
//						.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
//						.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));
//		// use HTTP Basic authentication
//		http.httpBasic(Customizer.withDefaults());
//		// disable Cross Site Request Forgery (CSRF)
//		http.csrf(csrf -> csrf.disable());
//		return http.build(
//	}
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails mohamed = User.builder()
                .username("mohamed")
                .password("{bcrypt}$2a$12$3J06kgNaw79WCedOqZhq5.JJU/ZqMzlYdi.HDRYDwjg9q.VrehrLe")
                .roles("EMPLOYEE")
                .build();
        UserDetails john = User.builder()
                .username("ebrahim")
                .password("{bcrypt}$2a$12$8c994cQFOsgAcbcS35DEnucD6KWdoaCJtScXLaY9VXQk8Z5UUEv3a")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails shosha = User.builder()
                .username("shosha")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(mohamed, john, shosha);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/listForEmployee").hasRole("EMPLOYEE")
                                .requestMatchers("/listForEmployee/**").hasRole("EMPLOYEE")
                                .requestMatchers("/listForManager").hasRole("MANAGER")
                                .requestMatchers("/listForManager/**").hasRole("MANAGER")
                                .requestMatchers("/list").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied"))
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll())
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}
