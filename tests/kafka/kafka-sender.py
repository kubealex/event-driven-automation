import json
from aiokafka import AIOKafkaProducer
import asyncio

async def produce():
    producer = AIOKafkaProducer(
        bootstrap_servers='KAFKA_HOST:9092', value_serializer=lambda m: json.dumps(m).encode('utf-8'))
    await producer.start()
    await producer.send("eda-topic", {'name': 'greeting', 'message': 'TEST'})
    await producer.stop()
asyncio.run(produce())

