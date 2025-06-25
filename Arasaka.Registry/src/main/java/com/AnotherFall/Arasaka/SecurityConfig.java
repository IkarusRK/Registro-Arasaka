package com.AnotherFall.Arasaka; // Certifique-se de que o pacote está correto!

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> {
                // --- A NOVA LISTA DE PERMISSÕES ---
                auth
                    // Permite acesso a pagina inicial
                    .requestMatchers("/", "/index.html", "/admin.html", "/cidadaos/**").permitAll()
                    
                    // Permite acesso a recursos
                    .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                    
                    // Qualquer outra requisição precisa estar autenticada
                    .anyRequest().authenticated();
            })
            // Habilita o formulário de login padrão do Spring para as páginas protegidas
            .formLogin(withDefaults())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
