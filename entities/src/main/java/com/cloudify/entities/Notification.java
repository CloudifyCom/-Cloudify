package com.cloudify.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Notification implements Serializable {

    private String notificationId;
    private String userId;
    //private List<User> notifiedUsers;
    private Integer textLength;
    private LocalDateTime sendingTime;
    private String title;
    private String content;
    private String notificationType;

    public Notification() {}

    public Notification(String notificationId, String userId, /*List<User> notifiedUsers,*/ Integer textLength, LocalDateTime sendingTime, String title, String content, String notificationType) {
        this.notificationId = notificationId;
        this.userId = userId;
        //this.notifiedUsers = notifiedUsers;
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
}
