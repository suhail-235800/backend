package com.easyskillup.securityservice.controller.dto;


public record AuthenticationResponse(String token, String role,int UserId) {
}
