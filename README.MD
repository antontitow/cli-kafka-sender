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

_Пример запуска в консоле_
    java -jar jms-sender.jar l localhost:9092 t test-topic23 f C:\Users\anton\IdeaProjects\sample.json

Порядок параметров (параметр-значение) не важен, допустима замена алиасов.
    адрес кафка:
    l -l -location location
    топик:
    t -t topic -topic
    расположение файла, содержащего json. Или абсолютный или относительно jar файла:
    f -f file -file

_Настройка SSL для работы с кафка_ (только для тип SSL)
    Рядом с jms-sender.jar необходимо разместить файл kafkaconfig.properties.
    Пример содержимого в templates/kafkaconfig.properties