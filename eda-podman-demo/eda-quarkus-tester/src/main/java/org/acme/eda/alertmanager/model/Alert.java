package org.acme.eda.alertmanager.model;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Alert {
    @JsonIgnore
    public String alertName;
    @JsonProperty("labels")
    public LinkedHashMap<String, String> alertLabels;
    @JsonIgnore
    public String alertMessage;
    @JsonIgnore
    public String alertTargetHosts;

    public Alert() {
    }

    public Alert(String alertName, String alertMessage, String alertTargetHosts) {
        this.alertMessage = alertMessage;
        this.alertName = alertName;
        this.alertTargetHosts = alertTargetHosts;
        this.alertLabels = new LinkedHashMap<String, String>();
        this.alertLabels.put("alertName", this.alertName);
        this.alertLabels.put("alertMessage", this.alertMessage);
        this.alertLabels.put("alertTargetHosts", this.alertTargetHosts);
    }

}
