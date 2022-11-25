package org.acme.alertmanager.service;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.acme.alertmanager.model.Alert;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/v1/alerts")
@RegisterRestClient
public interface AlertService {
    @POST
    Response sendAlert(List<Alert> alert);
}
