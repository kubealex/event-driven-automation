# MQTT EDA integration

This use case shows how Event Driven Automation can be integrated with MQTT messaging brokers, to interact with messages received from other platforms.

This integration uses a containerized [Mosquitto](https://mosquitto.org/) instance, present as a container in the [utils folder](../../utils) to handle the events.

## Running Mosquitto container

This integration uses a Mosquitto instance, present as a compose in the [utils folder](../../utils) to handle the messages, that instantiates a container for Mosquitto, available on port _1883_ of the host.

No additional configuration is needed.

A *podman-compose* file is available [here](../../utils/podman-compose/mosquitto-compose.yml), and automatically exposes port 1883 on all interfaces.

To run it:

    cd utils/
    podman-compose -f mosquitto-compose.yml up

## MQTT integration

In this use case, you can simulate a message, [using the following script](../../utils/mqtt-sender.py) specifying the hostname/IP of the Mosquitto instance (_defaults to localhost_) and the port (_defaults to 1883_).

It generates a simple message, that will trigger a Job Template on AAP2, showing the event information.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-mqtt-setup.yml

### Testing the MQTT integration

A script in the [utils directory](./utils/) allows to connect to a _--host_ (defaults to localhost) on _--port_ (defaults to 1883) and send a test message to trigger automation:

    cd utils/
    python3 mqtt-sender.py
