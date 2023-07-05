package com.easyskillup.securityservice.controller;

import com.easyskillup.securityservice.controller.dto.AuthenticationRequest;
import com.easyskillup.securityservice.controller.dto.AuthenticationResponse;
import com.easyskillup.securityservice.controller.dto.RegisterRequest;
import com.easyskillup.securityservice.service.AuthenticationService;
import com.easyskillup.securityservice.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JWTService jwtService;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        authenticationService.register(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok().body(response);
    }
}














//        logger.info("response is" + response);
//        String usernametoken = jwtService.extractUserName(response.token());
////        if (Objects.equals(usernametoken, request.email())) {
//            if (response != null) {
//                String token = response.token();
//                if (token != null && !token.isEmpty()) {
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setBearerAuth(token);
////                headers.set("Authorization", "Bearer " + token);
//                    logger.info("token is" + token);
//                    logger.info("headers is" + headers);
//                    logger.info("response is" + response);
//                    return ResponseEntity.ok().headers(headers).body(response);
//                }
//            }

            // Invalid token or authentication failed
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }

