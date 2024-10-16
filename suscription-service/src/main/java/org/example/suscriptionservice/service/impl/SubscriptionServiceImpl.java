package org.example.suscriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.suscriptionservice.entity.Subscription;
import org.example.suscriptionservice.repository.SubscriptionRepository;
import org.example.suscriptionservice.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;


    @Override
    public void saveSubscription(Long userId, String genre) {
        Subscription subscription= Subscription.builder()
                .userId(userId)
                .genre(genre)
                .createdAt(LocalDateTime.now())
                .build();
        subscriptionRepository.save(subscription);

    }

    @Override
    public List<String> getSubscription(Long userId) {
        return subscriptionRepository.findAllByUserId(userId)
                .stream()
                .map(Subscription::getGenre)
                .collect(Collectors.toList());
    }
}
