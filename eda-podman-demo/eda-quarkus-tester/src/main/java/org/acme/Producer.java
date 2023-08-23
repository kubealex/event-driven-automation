package org.acme;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.acme.eda.alertmanager.model.Alert;
import org.acme.eda.alertmanager.service.AlertService;
import org.acme.eda.kafka.model.Event;
import org.acme.eda.webhook.model.WebhookEvent;
import org.acme.eda.webhook.service.WebhookService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
public class Producer {

    @RestClient
    WebhookService webhookService;
    @RestClient
    AlertService alertService;

    @Channel("eda-topic")
    Emitter<Event> quoteRequestEmitter;

    @GET
    @Path("/kafka/")
    @Produces(MediaType.APPLICATION_JSON)
    public Event sendKafkaEvent() {
        Event testEvent = new Event("greeting", "Hello from Quarkus");
        quoteRequestEmitter.send(testEvent);
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
