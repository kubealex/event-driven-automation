package org.acme.eda.webhook.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import org.acme.eda.webhook.model.WebhookEvent;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/endpoint")
@RegisterRestClient
public interface WebhookService {
    @POST
    Response sendEvent(WebhookEvent event);
}
