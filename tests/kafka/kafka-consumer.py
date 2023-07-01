from aiokafka import AIOKafkaConsumer
import asyncio

async def consume():
    consumer = AIOKafkaConsumer(
        "eda-topic", bootstrap_servers='192.168.230.1:9092')
    # Get cluster layout and topic/partition allocation
    await consumer.start()
    try:
        async for msg in consumer:
            print(msg.value)
    finally:
        await consumer.stop()

asyncio.run(consume())
