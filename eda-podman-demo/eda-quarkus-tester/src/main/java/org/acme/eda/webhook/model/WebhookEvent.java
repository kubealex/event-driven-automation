package org.acme.eda.webhook.model;

import org.acme.eda.kafka.model.Event;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class WebhookEvent extends Event {
    @JsonProperty("target_hosts")
    public String eventTargetHosts;

    public WebhookEvent() {
    }

    public WebhookEvent(String eventName, String eventMessage, String eventTargetHosts) {
        this.eventMessage = eventMessage;
        this.eventName = eventName;
        this.eventTargetHosts = eventTargetHosts;
    }

}
