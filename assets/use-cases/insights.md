# Insights EDA integration

This use case shows how Event Driven Automation can be integrated with [Red Hat Insights](https://console.redhat.com/insights/dashboard).

Using native integration, it is possible to configure [Event Driven Automation destination](https://console.redhat.com/settings/integrations).

![](../insights_eda_integration.png)

Note that you will need a public reachable URL/IP to be able to receive events coming from Insights.

You can use [ngrok](https://ngrok.com/) to set-up a temporary public address to use for the use case.

## Resolve a recommendation using Insights and EDA

The setup will configure a playbook you can use to simulate a recommendation on Insights, in our case it will trigger a suggestion to disable SSH Root Login on a RHEL machine.

In order to replicate this use case, you will need to:

- have a RHEL Machine up and running
- a service user with sudo privileges on the machine with **sysadmin/redhat** credentials
- [edit the inventory accordingly](../../inventory)

The machine needs to be reachable from the EDA Controller.

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-insights-setup.yml

On AAP Controller, it will create a Job Template called **[EDA] Insights - Generate recommendation** that you can run against your machine to generate the recommendation, and wait a few seconds before the event is triggered and handled.

EDA Controller will then run a Job Template to remediate the recommendation and you will receive soon the resolution notification.

## Malware detection and reporting

The setup will configure a job template on AAP to simulate malware on a RHEL machine, named
The EDA rulebook detects a malware event and reports information in a Service Now Incident for further investigation.

- have a RHEL Machine up and running
- a service user with sudo privileges on the machine with **sysadmin/redhat** credentials
- [edit the inventory accordingly](../../inventory)
- a [Service Now instance](https://developer.servicenow.com/)

### Configuration

In order to run these use cases, setup scripts [are available here](../../eda-demo-setup/):

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-insights-setup.yml

or, with Service Now integration, fill the following vars in [common_vars.yml file](./vars/common_vars.yml)

    servicenow_instance_url:
    servicenow_instance_user:
    servicenow_instance_password:

and run:

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-insights-snow-setup.yml

On AAP Controller, it will create a Job Template called **"[EDA] Insights - Configure Malware Detection"** that you can use to configure malware detection with Insights and another job template, **"[EDA] Insights - Trigger Malware"** that you can run against your machine to trigger malwared detection, and wait a few seconds before the event is triggered and handled.

EDA Controller will then run a Job Template to store the information about the malware in a Service Now incident
