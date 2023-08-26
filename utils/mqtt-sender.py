#! /usr/bin/python3
import argparse
import asyncio
import json
from aiomqtt import Client

async def send_mqtt_message(host, port, payload):
    async with Client(hostname=host, port=port) as client:
        topic = "eda-topic"
        await client.publish(topic, payload)
        await client.disconnect()

async def main():
    parser = argparse.ArgumentParser(description="Send MQTT message with JSON payload")
    parser.add_argument("--host", default="localhost", help="MQTT broker host")
    parser.add_argument("--port", type=int, default=1883, help="MQTT broker port")
    args = parser.parse_args()

    payload = {
        "eventName": "MQTT event",
        "eventSource": "EDA Demo environment",
        "eventBody": "This is an example message"
    }

    payload_json = json.dumps(payload)

    await send_mqtt_message(args.host, args.port, payload_json)

if __name__ == "__main__":
    asyncio.run(main())
