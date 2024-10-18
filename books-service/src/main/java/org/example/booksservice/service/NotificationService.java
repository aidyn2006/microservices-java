package org.example.booksservice.service;

import java.util.List;

public interface NotificationService  {
    void messageAboutSubscription(String genre);
    List<String> getSubscribers();
}
