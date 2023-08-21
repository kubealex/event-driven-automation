# Kafka EDA integration

This use case shows how Event Driven Automation can be integrated with Kafka, to interact with events streamed by other platforms

This integration uses a containerized Kafka instance, present as a container in the [tests folder](../../utils) to handle the events.

To properly configure it, replace the **ADVERTISED_HOST/IP** variable in [kafka compose](../../utilspodman-compose.yml) to match the IP that EDA Controller will need to contact, **it should not be localhost!**

## Kafka integration

In this use case, you can simulate an event, [using the following script](../../utilskafka/kafka-sender.py) specifying the hostname/IP advertised by kafka.

It generates a simple alert, that will trigger a Job Template on AAP2, showing the alert information.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-kafka-setup.yml

### Testing the Kafka integration

A script in the [utilskafka directory](./utilskafka/) allows to connect to a _--host_ (defaults to localhost) on port 9092 and send a test message to trigger automation

    python3 utilskafka/kafka-sender.py --host MY_KAFKA_HOST
