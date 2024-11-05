package org.example.authservice.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public interface EmailServiceDecorator extends EmailService{
}
