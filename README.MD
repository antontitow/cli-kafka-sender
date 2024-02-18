Запуск кафка:
    Запуск зоокипера
    cd C:\kafka\bin\windows
    cmd
    start zookeeper-server-start.bat c:\kafka\config\zookeeper.properties
    cmd
    start kafka-server-start.bat c:\kafka\config\server.properties

start kafka-server-stop.bat
start zookeeper-server-stop.bat

Запуск в консоле
java -jar jms-sender.jar l localhost:9092 t test-topic2 f C:\Users\anton\IdeaProjects\sample.json