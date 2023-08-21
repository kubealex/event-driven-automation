import json
from aiokafka import AIOKafkaProducer
import asyncio

async def produce():
    parser = argparse.ArgumentParser()
    parser.add_argument('--topic', type=str, default="eda-topic")
    parser.add_argument('--host', type=str, default="localhost")
    args = parser.parse_args()

    producer = AIOKafkaProducer(
        bootstrap_servers=args.host + ':9092', value_serializer=lambda m: json.dumps(m).encode('utf-8'))
    await producer.start()
    await producer.send(args.topic, {'name': 'greeting', 'message': 'TEST'})
    await producer.stop()
asyncio.run(produce())

