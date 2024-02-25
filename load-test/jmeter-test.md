Файл Kafka-jmeter-test.jmx содержит тест jmeter, который запускает текущее приложение. Описание параметров в readme.md

Инструкция по настройке.
    Скачиваем бинарник apache-jmeter-x.x.x.zip 
    https://jmeter.apache.org/download_jmeter.cgi
    Разархивируем и внутренности копируем в папку для jmeter (Пример: C:\jmeter\bin)
    Запускаем C:\jmeter\bin\jmeter.bat должен запуститься UI.
    File->Open->Путь к файлу load-test\kafka-jmeter-test.jmx   
    Устанавливаем параметры нагрузки thread group
    В проекте в User Defined Variables (kafka config) меняем параметры кафки (адрес, топик, путь к файлу с json, который парсится и отправляется  в  кафку)
    Ctrl+Shift+S

    Создаем артефакт jms-sender.jar (папка out\atifacts\jms-sender_jar)
        (Project structure->Artifacts->"+"->JAR->from modules with dependency->main class = ru.titov.JmsSender -> ok
         Build->build artefact->jms0sender.jar->build)
    Копируем в папку для тестирования (Например C:test)
    файлы jms-sender.jar и kafka-jmeter-test.jmx 

    В этой папке запускаем cmd  выполняем
    C:\jmeter\bin\jmeter.bat -n -t сохраненный_тест.jmx -l RESULT/logs/log1000.jtl -e -o RESULT