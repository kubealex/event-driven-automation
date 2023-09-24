# Alertmanager EDA integration

This use case shows how Event Driven Automation can be integrated with Alertmanager, to interact with alerts coming from monitoring systems (OCP, Prometheus) and react accordingly.

There are two scenarios, a simple alert handler that only prints information and another one interacting with Service Now to raise an incident, resolve the issue (simulated) and close the incident.

## Running Alertmanager container

This integration uses an Alertmanager instance, present as a container in the [utils folder](../../utils) to handle the alerts, that instantiates a container for AlertManager (and kafka) available on port _9093_ of the host.

To properly configure it, replace the **EDA_NODE_HOSTNAME** variable in [alertmanager configuration](../../utils/podman-compose/alertmanager/alertmanager.yml) to properly send the alert on the listener that is created via the rulebooks (port defaults to 5001, you can change it).

A _podman-compose_ file is available [here](../../utils/podman-compose/alertmanager-compose.yml), and automatically exposes port 9093 on all interfaces.

To run it:

    cd utils/podman-compose/
    podman-compose -f alertmanager-compose.yml up

## Alertmanager integration

In this use case, you can simulate an alert, [using the following script](../../utils/send-alert), replacing **ALERTMANAGER_HOST** placeholder with the hostname/IP where the container is running (it can likely be _localhost_).

It generates a simple alert, that will trigger a Job Template on AAP2, showing the alert information or raising and resolving a Service Now incident.

### Configuration

Since the use case uses integration with Service Now, fill the following vars in [common_vars.yml file](./vars/common_vars.yml)

    servicenow_instance_url:
    servicenow_instance_user:
    servicenow_instance_password:

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/) and run:

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-alertmanager-setup.yml

### Testing the alertmanager integration

Assuming Alertmanager is listening on _localhost_, you can use the following cURL command to trigger a **Alertmanager** automation (replace localhost with any host/IP where Alertmanager is listening):

    curl -H 'Content-Type: application/json' -d '[{"labels":{"alertName":"my-eda-alert", "alertMessage": "This is a test alert firing", "alertTargetHosts": "localhost"}}]' http://localhost:9093/api/v1/alerts
