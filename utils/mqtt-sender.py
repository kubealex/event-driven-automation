#! /usr/bin/python3
import paho.mqtt.client as mqtt
import json
import sys

def on_connect(client, userdata, flags, rc):
    print(f"Connected with result code {rc}")

def send_mqtt_message(host, port, payload):
    client = mqtt.Client()
    client.on_connect = on_connect

    client.connect(host, port, 60)

    client.loop_start()

    topic = "eda-topic"  # Specify the MQTT topic to publish to
    client.publish(topic, payload)

    client.loop_stop()
    client.disconnect()

def main():
    host = "localhost"  # Default host
    port = 1883  # Default port

    if len(sys.argv) > 1:
        host = sys.argv[1]
    if len(sys.argv) > 2:
        port = int(sys.argv[2])

    payload = {
        "eventName": "MQTT event",
        "eventSource": "EDA Demo environment",
        "eventBody": "This is an example message"
    }

    payload_json = json.dumps(payload)

    send_mqtt_message(host, port, payload_json)

if __name__ == "__main__":
    main()

