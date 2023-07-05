package com.easyskillup.securityservice.controller.dto;

public record RegisterRequest(String email, String password, String role) {
}
