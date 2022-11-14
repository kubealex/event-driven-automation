package org.acme.kafka;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.kafka.model.Event;
import org.acme.kafka.service.WebhookService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.vertx.http.runtime.devmode.Json;

@Path("/event")
public class Producer {

    @Channel("eda-topic")
    Emitter<String> eventEmitter;
    @Inject
    @RestClient
    WebhookService webhookService;

    @GET
    @Path("/kafka")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendKafkaEvent() throws JsonProcessingException {
        Event testEvent = new Event("greeting","Hello from Quarkus", null);
        ObjectMapper mapper = new ObjectMapper();
        String eventJson = mapper.writeValueAsString(testEvent);
        eventEmitter.send(eventJson);
        return eventJson;
    }

    @GET
    @Path("/webhook")
    @Produces(MediaType.APPLICATION_JSON)
    public void sendWebhookEvent() {
        Event testEvent = new Event("greeting","Hello from Quarkus", null);
        webhookService.sendEvent(testEvent);
    }

}
