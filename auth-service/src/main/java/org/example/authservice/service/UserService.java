package org.example.authservice.service;

public interface UserService {
    String getEmailByUserId() throws Exception;
    Long getUserIdFromContext() throws Exception;
}
