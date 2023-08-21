# Alertmanager EDA integration

This use case shows how Event Driven Automation can be integrated with Alertmanager, to interact with alerts coming from monitoring systems (OCP, Prometheus) and react accordingly.

There are two scenarios, a simple alert handler that only prints information and another one interacting with Service Now to raise an incident, resolve the issue (simulated) and close the incident.

This integration uses an Alertmanager instance, present as a container in the [tests folder](../../utils) to handle the alerts, that instantiates a container for AlertManager (and kafka) available on port _9093_ of the host.

To properly configure it, replace the **EDA_NODE_HOSTNAME** variable in [alertmanager configuration](../../utilsalertmanager/alertmanager.yml) to properly send the alert on the listener that is created via the rulebooks.

## Alertmanager simple integration

In this use case, you can simulate an alert, [using the following script](../../utilssend-alert), replacing **ALERTMANAGER_HOST** placeholder with the hostname/IP where the container is running (it can likely be _localhost_).

It generates a simple alert, that will trigger a Job Template on AAP2, showing the alert information.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-alertmanager-setup.yml

## Alertmanager Service Now integration

In this use case, you can simulate an alert, [using the following script](../../utilssend-alert), replacing **ALERTMANAGER_HOST** placeholder with the hostname/IP where the container is running (it can likely be _localhost_).

Upon receiving the alert, it will create a Service Now ticket, simulate the resolution, and wait for the alert to be in _Resolved_ status to close the Incident.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-alertmanager-snow-setup.yml

### Testing the alertmanager integration

You can use the following cURL command to trigger a **Alertmanager** automation:

    curl -H 'Content-Type: application/json' -d '[{"labels":{"alertName":"my-eda-alert", "alertMessage": "This is a test alert firing", "alertTargetHosts": "localhost"}}]' http://localhost:9093/api/v1/alerts
