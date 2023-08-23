# Dynatrace EDA integration

This use case shows how Event Driven Automation can be integrated with Dynatrace, handling and managing problems coming from the platform.

As the Alertmanager setup, it has two different integrations, one only showing problem information, and one raising a Service Now Incident upon problem trigger.

Both use cases will require a [Dynatrace instance]() and you will need to have the API URL and and [API Token]() available to configure the integration properly.

The simpler way to generate a fake problem is to generate an [HTTP Monitor in Synthetic Monitoring](https://www.dynatrace.com/support/help/platform-modules/digital-experience/synthetic-monitoring/http-monitors/create-an-http-monitor) and [configure it]() in order to generate a fake request that will fail for sure. You can [have a look here for ideas](https://community.dynatrace.com/t5/Alerting/How-to-generate-a-fake-Problem-immediately/m-p/119896).

## Dynatrace integration

In this use case we will generate a Problem on Dynatrace and gather the information using the integration.

This will trigger a problem and EDA will convert the problem event to consumable information or a Service Now Incident.

### Configuration

Configure the following variables in [the common variables file](../../eda-demo-setup/vars/common_vars.yml)

    dynatrace_api_url:
    dynatrace_api_token:

and run:

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-dynatrace-setup.yml

To integrate with Service Now, fill the following vars in [common_vars.yml file](./vars/common_vars.yml)

    servicenow_instance_url:
    servicenow_instance_user:
    servicenow_instance_password:

and run:

    ansible-playbook configure-use-case.yml -e @use-cases/use-case-dynatrace-snow-setup.yml
