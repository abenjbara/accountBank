//package ing.kata.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private BankBasicAuthenticationEntryPoint authenticationEntryPoint;
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//          .anyRequest().authenticated()
//          .and()
//          .httpBasic()
//          .authenticationEntryPoint(authenticationEntryPoint);
//    }
//}
