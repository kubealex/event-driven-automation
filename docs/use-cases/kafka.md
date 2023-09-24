# Kafka EDA integration

This use case shows how Event Driven Automation can be integrated with Kafka, to interact with events streamed by other platforms

This integration uses a containerized Kafka instance, present as a container in the [utils folder](../../utils) to handle the events.

## Running Kafka container

This integration uses a Kafka instance, present as a compose in the [utils folder](../../utils) to handle the events, that instantiates a container for Kafka, available on port _9092_ of the host.

A dedicated *podman-compose* file is available [here](../../utils/podman-compose/kafka-compose.yml), and automatically exposes port 9092 on all interfaces.

To properly configure it, replace the **ADVERTISED_HOST/IP** variable in the compose file to match the IP that EDA Controller will need to contact, **it should not be localhost!**

To run it:

    cd utils/podman-compose/
    podman-compose -f kafka-compose.yml up

## Kafka integration

In this use case, you can simulate an event, [using the following script](../../utils/kafka-sender.py) specifying the hostname/IP advertised by kafka.

It generates a simple alert, that will trigger a Job Template on AAP2, showing the alert information.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-kafka-setup.yml

### Testing the Kafka integration

A script in the [utils directory](./utils/) allows to connect to a _--host_ (defaults to localhost) on port 9092 and send a test message to trigger automation

    cd utils/
    python3 kafka-sender.py --host MY_KAFKA_HOST
