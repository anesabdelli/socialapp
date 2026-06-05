package com.example.socialapp.service;

import com.example.socialapp.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class NotificationService {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String userId) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(userId, sseEmitter);
        sseEmitter.onCompletion(() -> emitters.remove(userId));
        sseEmitter.onTimeout(() -> emitters.remove(userId));
        log.info("User {} connected to notifications", userId);
        try {
            sseEmitter.send(SseEmitter.event().name("CONNECTED").data("connected"));
        } catch (IOException e) {
            emitters.remove(userId);
        }
        return sseEmitter;
    }

    public void notify(String userId, String type, String entityId) {
        notify(userId, type, entityId, null);
    }

    public void notify(String userId, String type, String messageId, String discussionId) {
        SseEmitter sseEmitter = emitters.get(userId);
        if (sseEmitter == null) {
            return;
        }
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            userId,
            type,
            messageId,
            discussionId,
            LocalDateTime.now()
        );
        try {
            sseEmitter.send(SseEmitter.event().name(type).data(notification, MediaType.APPLICATION_JSON));
        } catch (IOException e) {
            emitters.remove(userId);
            log.warn("Failed to notify user {}, connection lost", userId);
        }
    }
}
