from aiokafka import AIOKafkaConsumer
import asyncio
import argparse

async def consume():
    parser = argparse.ArgumentParser()
    parser.add_argument('--topic', type=str, default="eda-topic")
    parser.add_argument('--host', type=str, default="localhost")
    args = parser.parse_args()

    consumer = AIOKafkaConsumer(
        args.topic, bootstrap_servers=args.host +':9092')
    await consumer.start()
    try:
        async for msg in consumer:
            print(msg.value)
    finally:
        await consumer.stop()

asyncio.run(consume())
