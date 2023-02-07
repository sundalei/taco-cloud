package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;
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
        return http
                .authorizeHttpRequests()
                    .requestMatchers("/design", "/orders").hasRole("USER")
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/", "/**").permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .csrf().ignoringRequestMatchers(new RequestMatcher() {

                        @Override
                        public boolean matches(HttpServletRequest request) {
                            String path = request.getRequestURI();
                            String method = request.getMethod();
                            return path.startsWith("/h2-console/") || method.equalsIgnoreCase("post");
                        }
                        
                    })
                .and()
                    .headers().frameOptions().disable().and()
                .build();
    }
}
