package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.data.UserRepository;
import tacos.domain.TacoUser;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            TacoUser user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User '" + username + "' not found.");
        };
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeHttpRequests -> {
            authorizeHttpRequests.requestMatchers("/design", "/orders").hasRole("USER");
            authorizeHttpRequests.requestMatchers("/**").permitAll();
        });

        http.formLogin(formLogin -> formLogin.loginPage("/login"));
        http.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login"));
        http.logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();

    }
}
