import json
from aiokafka import AIOKafkaProducer
import asyncio

async def produce():
    producer = AIOKafkaProducer(
        bootstrap_servers='<IP/HOST PLACEHOLDER>:9092', value_serializer=lambda m: json.dumps(m).encode('utf-8'))
    # Get cluster layout and topic/partition allocation
    await producer.start()
    # Produce messages
    await producer.send("eda-topic", {'name': 'greeting', 'message': 'TEST'})
    await producer.stop()
asyncio.run(produce())

