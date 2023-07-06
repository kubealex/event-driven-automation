from aiokafka import AIOKafkaConsumer
import asyncio

async def consume():
    consumer = AIOKafkaConsumer(
        "eda-topic", bootstrap_servers='KAFKA_HOST:9092')
    await consumer.start()
    try:
        async for msg in consumer:
            print(msg.value)
    finally:
        await consumer.stop()

asyncio.run(consume())
