Запуск кафка:
    Запуск зоокипера
    cd C:\kafka\bin\windows
    cmd
    start zookeeper-server-start.bat c:\kafka\config\zookeeper.properties
    cmd
    start kafka-server-start.bat c:\kafka\config\server.properties

Запуск в консоле
java -jar jms-sender.jar l locationKafka t topicName f filetoparse