package org.example.suscriptionservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.suscriptionservice.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{genre}/{userId}")
    public void saveDownload(@PathVariable("genre") String genre, @PathVariable("userId") Long userId) {
        subscriptionService.saveSubscription(userId,genre);
    }
    @GetMapping("/{userId}")
    public List<String> getDownloads(@PathVariable Long userId) {
        return subscriptionService.getSubscription(userId);
    }
}
