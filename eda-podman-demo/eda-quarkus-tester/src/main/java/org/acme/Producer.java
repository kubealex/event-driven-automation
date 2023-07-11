package org.acme;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.acme.alertmanager.model.Alert;
import org.acme.alertmanager.service.AlertService;
import org.acme.model.Event;
import org.acme.webhook.model.WebhookEvent;
import org.acme.webhook.service.WebhookService;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/")
public class Producer {

    @RestClient
    WebhookService webhookService;
    @RestClient
    AlertService alertService;

    @GET
    @Path("/kafka/")
    @Outgoing("eda-topic")
    @Produces(MediaType.APPLICATION_JSON)
    public Event sendKafkaEvent() {
        Event testEvent = new Event("greeting", "Hello from Quarkus");
        return testEvent;
    }

    @GET
    @Path("/webhook/")
    @Produces(MediaType.APPLICATION_JSON)
    public WebhookEvent sendWebhookEvent() {
        WebhookEvent testEvent = new WebhookEvent("greeting", "Hello from Quarkus", null);
        webhookService.sendEvent(testEvent);
        return testEvent;
    }

    @GET
    @Path("/alertmanager/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alert> sendAlert() {
        List<Alert> alertList = new ArrayList<>();
        Alert testAlert = new Alert("quarkusAlert", "Quarkus fired this alert", "localhost");
        alertList.add(testAlert);
        alertService.sendAlert(alertList);
        return alertList;
    }

}
