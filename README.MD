Запуск зоокипера
    cd C:\kafka\bin\windows
    cmd
    start zookeeper-server-start.bat c:\kafka\config\zookeeper.properties

Запуск кафка
    cmd (новая консоль)
    start kafka-server-start.bat c:\kafka\config\server.properties

Пример json файла resources/templates/sample.json

Остановка зоокипера и кафка
    start kafka-server-stop.bat
    start zookeeper-server-stop.bat

Пример запуска в консоле:
    java -jar jms-sender.jar l localhost:9092 t test-topic18 f C:\Users\anton\IdeaProjects\sample.json

Порядок параметров не важен, допустима замена алиасов.
    адрес кафка:
    l -l -location location
    топик:
    t -t topic -topic
    расположение файла, содержащего json. Или абсолютный или относительно jar файла:
    f -f file -file