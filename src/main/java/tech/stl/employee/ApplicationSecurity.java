package tech.stl.employee;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tech.stl.employee.repository.EmployeeRepository;
import tech.stl.employee.util.JwtTokenFilter;


@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter  {

	 @Autowired private EmployeeRepository employeeRepository;
    
    @Autowired 
    private JwtTokenFilter jwtTokenFilter;
    
   

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> employeeRepository.findByEmailId(email));
                
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.authorizeRequests()
                .antMatchers("/auth/login", "/auth/signup/employee","/employee/*","/employee/managerid/*","/leave/update/*","/employee/update/*","/leave","/leave/*","/gettask/empId/*","/gettask/update/*","/leave/managerId/*","/leave/id/*").permitAll()
                .anyRequest().authenticated();
        
        http.exceptionHandling()
                .authenticationEntryPoint(
                    (request, response, ex) -> {
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                        );
                    }
                );
        
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
  }
}
