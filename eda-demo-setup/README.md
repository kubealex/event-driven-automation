# General configuration variables

The file [common_vars.yml](./vars/common_vars.yml) contains the common variables for configuring the use cases and token exchange:

    aap2_controller_host:
    aap2_controller_username:
    aap2_controller_password:

    eda_controller_url:
    eda_controller_user:
    eda_controller_password:

    servicenow_instance_url:
    servicenow_instance_user:
    servicenow_instance_password:

    kafka_advertised_listener:

    dynatrace_api_url:
    dynatrace_api_token:

Not all of those are required for the use cases, but some of them (i.e. the alertmanager-snow integration, requires the **servicenow_instance** vars)

## Automation controller setup

Once your setup is in place, you need to generate a token, that will be used by EDA Controller to connect to the Automation Controller. To do so, you can go in the **Users -> admin -> Token** section in the controller and _ADD_ a new token.

![](../assets/aap2_user_token.png)

Save the token and open the EDA Controller URL, go in the **Users -> admin -> Token** section and _Create controller token_

![](../assets/eda_user_token.png)

Alternatively, you can use the [configure-controller-token playbook](./configure-controller-token.yml)

To configure the predefined project, credentials and templates, you can use the playbooks located in the [eda-demo-setup directory](./).

The _configure-aap-controller_ playbook, requires an ansible-galaxy configuration that allows accessing [Red Hat Automation Hub](https://access.redhat.com/documentation/en-us/red_hat_ansible_automation_platform/2.4/html-single/getting_started_with_automation_hub/index)

First, install the requirements:

    ansible-galaxy install -r requirements.yml

## Use case setup

Each use case utilizes different variables to setup the EDA Controller and AAP Controller and create the respective projects, credentials, and all needed resources.

In [the *use-cases*folder](./use-cases) you will find all the variables files named this way:

    use-case-*-setup.yml

Each use case will require additional variables that are needed for the configuration, like credentials and/or endpoints.

Example for **Service Now** integration:

    servicenow_instance_url:
    servicenow_instance_user:
    servicenow_instance_password:

Example for **Kafka** integration:

    kafka_advertised_listener:

Example for **Dynatrace** integration:

    dynatrace_api_url:
    dynatrace_api_token:

There are also two general files, [use-case-full-setup.yml file](./vars/use-case-full-setup.yml) and [use-case-full-snow-setup.yml file](./vars/use-case-full-snow-setup.yml) to configure all of them at once, with or without Service Now integration

These variables will be used to configure both the AAP2 Controller and the EDA Controller.

You can then run the two playbooks to complete the configuration, passing the right configuration file during execution.

For kafka, you cna

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-kafka-setup.yml
