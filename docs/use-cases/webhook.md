# Webhook EDA integration

This use case shows how Event Driven Automation can be integrated using Webhooks to interact with systems that are capable of making REST calls (ITSM, etc)

The endpoint will be reachable at _http://EDA_HOSTNAME:5000_ and EDA_HOSTNAME should be reachable from the caller system.

## Webhook integration

This integration exposes an webhook event source using a rulebook, that creates an HTTP listener on port 5001 of the EDA Controller host.
It is possible to interact with it with third party systems.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-webhook-setup.yml

### Testing the integration

In this use case, to simulate a basic event you can run the following *cURL* command replacing **EDA_HOSTNAME** with your actual hostname/IP of the EDA Controller.

    curl -X POST http://EDA_HOSTNAME -d '{"name":"greeting","message":"hello"}'

It generates a simple event, that will trigger a Job Template on AAP2, showing the information.
