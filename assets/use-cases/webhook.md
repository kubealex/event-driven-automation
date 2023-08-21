# Webhook EDA integration

This use case shows how Event Driven Automation can be integrated using Webhooks to interact with systems that are capable of making REST calls (ITSM, etc)

The endpoint will be reachable at _http://EDA_HOSTNAME:5000_ and EDA_HOSTNAME should be reachable from the caller system.

## Kafka integration

In this use case, to simulate a basic event you can [use the following script](../../utilssend-webhook) replacing **EDA_HOSTNAME** with your actual hostname/IP of the EDA Controller.

It generates a simple event, that will trigger a Job Template on AAP2, showing the information.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-webhook-setup.yml

To generate an event:

    ./utilssend-webhook

This will trigger a simple playbook that will greet the user.
