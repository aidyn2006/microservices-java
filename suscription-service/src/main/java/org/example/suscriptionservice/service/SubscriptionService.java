package org.example.suscriptionservice.service;

import java.util.List;

public interface SubscriptionService {
    void saveSubscription(Long userId, String genre);
    List<String> getSubscription(Long userId);

}
