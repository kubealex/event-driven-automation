package org.acme.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    @JsonProperty("name")
    public String eventName;
    @JsonProperty("message")
    public String eventMessage;
    @JsonProperty("target_hosts")
    public String eventTargetHosts;

public Event() {}
public Event(String eventName, String eventMessage, String eventTargetHosts){
    this.eventMessage=eventMessage;
    this.eventName=eventName;
    this.eventTargetHosts=eventTargetHosts;

}

}
