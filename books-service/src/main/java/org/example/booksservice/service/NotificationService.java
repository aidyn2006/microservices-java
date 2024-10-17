package org.example.booksservice.service;

public interface NotificationService  {
    void messageAboutSubscription(String genre);
    String getSubscribers();
}
