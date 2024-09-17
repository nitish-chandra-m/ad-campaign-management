package com.github.nitish_chandra_m.ad_campaign_tool.notifications;

import java.util.List;

public class EmailNotificationService implements NotificationService<String> {

    @Override
    public void sendToOne(String identifier, byte[] msg) {

    }

    @Override
    public void sendToGroup(List<String> identifiers, byte[] msg) {

    }
}
