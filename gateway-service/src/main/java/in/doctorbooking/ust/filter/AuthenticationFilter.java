package in.doctorbooking.ust.filter;

import in.doctorbooking.ust.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                logger.warn("inside validator if");
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                logger.warn("authorization header"+authHeader);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    logger.warn("inside authheader if");
                    authHeader = authHeader.substring(7);
                    logger.warn("authorization header trimed"+authHeader);
                }
                try {

//                    boolean var =
                            jwtUtil.validateToken(authHeader);
//                    if(!var) {
//                        throw  new RuntimeException("token is invalid");
//                    }


                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}


