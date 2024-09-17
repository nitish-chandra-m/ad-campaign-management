package com.github.nitish_chandra_m.ad_campaign_tool.notifications;

import java.util.List;

public interface NotificationService<T> {
    void sendToOne(T identifier, byte[] msg);
    void sendToGroup(List<T> identifiers, byte[] msg);
}
