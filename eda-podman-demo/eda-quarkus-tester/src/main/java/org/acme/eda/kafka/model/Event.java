package org.acme.eda.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Event {
    @JsonProperty("name")
    public String eventName;
    @JsonProperty("message")
    public String eventMessage;

    public Event() {
    }

    public Event(String eventName, String eventMessage) {
        this.eventMessage = eventMessage;
        this.eventName = eventName;

    }

}
