package com.blingwei.musicService.bean.requestBaen;

public class SendMessageRequest {
    private String chatName;
    private String content;

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
