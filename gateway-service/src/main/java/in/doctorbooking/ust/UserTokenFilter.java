package in.doctorbooking.ust;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import org.springframework.http.HttpHeaders;


@Component
public class UserTokenFilter extends AbstractGatewayFilterFactory<UserTokenFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(UserTokenFilter.class);

    public UserTokenFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Retrieve the Authorization header value
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            logger.info(authorizationHeader);

            // Set X-User-Token header with Authorization header value
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header(config.getTokenHeaderName(), authorizationHeader)
                    .build();

            // Use the modified request in the filter chain
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }


    public static class Config {
        private String tokenHeaderName;

        public String getTokenHeaderName() {
            return tokenHeaderName;
        }

        public void setTokenHeaderName(String tokenHeaderName) {
            this.tokenHeaderName = tokenHeaderName;
        }
    }

}
