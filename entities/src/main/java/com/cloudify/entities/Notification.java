package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notification")
@Schema(description = "Notification information")
public class Notification implements Serializable {

    @Schema(description = "notificationId", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String notificationId;

    @Schema(description = "userId", example = "198772")
    private String userId;

    @Schema(description = "notifiedUsers")
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<User> notifiedUsers;

    @Schema(description = "textLength", example = "70")
    private Integer textLength;

    @Schema(description = "sendingTime", example = "2024-11-24T14:30:45")
    private LocalDateTime sendingTime;

    @Schema(description = "title", example = "Attention weather forecast")
    private String title;

    @Schema(description = "content", example = "Due to bad weather, your next flight on 2024-11-26 will be cancelled.")
    private String content;

    @Schema(description = "notificationType", example = "warning")
    private String notificationType;


    public Notification() {}

    public Notification(String notificationId, String userId, List<User> notifiedUsers, Integer textLength, LocalDateTime sendingTime, String title, String content, String notificationType) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.notifiedUsers = notifiedUsers;
        this.textLength = textLength;
        this.sendingTime = sendingTime;
        this.title = title;
        this.content = content;
        this.notificationType = notificationType;
    }

    public Notification(String notificationId, String userId, String title, String content) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public List<User> getNotifiedUsers() {
        return notifiedUsers;
    }

    public void setNotifiedUsers(List<User> notifiedUsers) {
        this.notifiedUsers = notifiedUsers;
    }
}
