# kafka-poc

brew install kafka

Start Zookeeper:
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

Start Broker:
kafka-server-start /usr/local/etc/kafka/server.properties

List topics:
Kafka-topics —list —zookeeper localhost:2181
