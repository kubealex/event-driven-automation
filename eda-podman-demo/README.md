# EDA Demo with Podman

## Requirements

To run the demo, **podman** and **podman-compose** should be installed in your system. Yes, nothing more! :)

Follow the instructions to install them, based on your system.

[Podman setup](https://podman.io/getting-started/installation)

[podman-compose setup](https://github.com/containers/podman-compose)

## Components

The stack is composed by four containers:

- Kafka+zookeeper container to instantiate the broker
- Alertmanager container to set-up an alertmanager instance
- Quarkus-client container that will act as an event generator
- Ansible EDA container image, running the ansible bits

The *eda-ansible* directory, all rulebooks and playbooks are present.
The *eda-quarkus-tester* directory contains the source code for the quarkus client

A pre-built **podman-compose.yml** is present in the repo to allow a quick setup of the kafka/alertmanager/quarkus stack.

## Usage

Start your stack by issuing:

    podman-compose -d up

For demo purposes, we are starting it in interactive mode, so you can follow the logs during executions.

To test the services, you can call the following endpoints, where the Quarkus application is listening and will generate events accordingly:

    curl localhost:8080/alertmanager/
    curl localhost:8080/webhook/
    curl localhost:8080/kafka/

A sample output after an event is captured by the event listener is below:

    INFO:ansible_rulebook.builtin:ruleset: Listen for events on multiple sources (Kafka, Webhook, AlertManager), rule: Let Ansible print information about the alert
    INFO:ansible_rulebook.builtin:Calling Ansible runner

    PLAY [Playbook reacting to AlertManager Event] *********************************

    TASK [Gathering Facts] *********************************************************
    ok: [localhost]

    TASK [Debug message] ***********************************************************
    ok: [localhost] => {
        "msg": "The event myalert just triggered to greet Ansible Event Driven!"
    }

    TASK [Remediating issue to resolve the alert] **********************************
    ok: [localhost] => {
        "msg": "Tring to solve myalert"
    }

    TASK [Notify resolution] *******************************************************
    skipping: [localhost]

    PLAY RECAP *********************************************************************
    localhost                  : ok=3    changed=0    unreachable=0    failed=0    skipped=1    rescued=0    ignored=0
