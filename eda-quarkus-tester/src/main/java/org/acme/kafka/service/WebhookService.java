package org.acme.kafka.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.acme.kafka.model.Event;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/endpoint")
@RegisterRestClient
public interface WebhookService {
    @POST
    Response sendEvent(Event event);
}

