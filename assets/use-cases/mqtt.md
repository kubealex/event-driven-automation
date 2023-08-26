# MQTT EDA integration

This use case shows how Event Driven Automation can be integrated with MQTT messaging brokers, to interact with messages received from other platforms.

This integration uses a containerized [Mosquitto](https://mosquitto.org/) instance, present as a container in the [utils folder](../../utils) to handle the events.

## Running Mosquitto container

This integration uses a Mosquitto instance, present as a compose in the [utils folder](../../utils) to handle the events, that instantiates a container for Kafka, available on port _9092_ of the host.

To properly configure it, replace the **ADVERTISED_HOST/IP** variable in [kafka compose](../../utils/podman-compose.yml) to match the IP that EDA Controller will need to contact, **it should not be localhost!**

A *podman-compose* file is available [here](../../utils/kafka-compose.yml), and automatically exposes port 9092 on all interfaces.

To run it:

    cd utils/
    podman-compose -f kafka-compose.yml up

## Kafka integration

In this use case, you can simulate an event, [using the following script](../../utils/kafka/kafka-sender.py) specifying the hostname/IP advertised by kafka.

It generates a simple alert, that will trigger a Job Template on AAP2, showing the alert information.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-kafka-setup.yml

### Testing the Kafka integration

A script in the [utilskafka directory](./utils/kafka/) allows to connect to a _--host_ (defaults to localhost) on port 9092 and send a test message to trigger automation

    python3 utilskafka/kafka-sender.py --host MY_KAFKA_HOST
