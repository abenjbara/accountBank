//package ing.kata.common;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BankBasicAuthenticationEntryPoint  extends BasicAuthenticationEntryPoint{
//	
//	@Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
//      throws IOException {
//        response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().println("Vous devez vous authentifier pour acceder a cette resource");
//    }
//
//	@Override
//    public void afterPropertiesSet() {
//        setRealmName("BankStack");
//        super.afterPropertiesSet();
//    }
//}
