Инструкця
Запустить приложение.
Перед началом работы в БД нужно создать схему с названием - musicstore,я работал с MySQL.
НА первой странице в разделе "Загрузка данных в БД", нажать -> Загрузить
проинициализируется база.

Потом есть два пути, я не на 100% был уверен как нужно прочитать фаил, поэтому на главной странице есть 2 кнопки:
Загрузить - тогда можно будет выбрать и прочитать csv файлик, там должна быть одна колонка, с названиями печен, файлик нужного формата
сожо взять из папки resource/static - в проектк. тогда аяксом прочитается файлик, передастся все на бэк, там запишется в базу, потом прочитается 
из базы все песни, что есть и отобразятся в таблице. Инфа передается и получается в виде json.
Вторая кнопка "Прочитать .csv фаил и загрузить в БД", при нажатии на нее откроется еще одна страница, там будет кнопка "Прочитать фаил", при нажатии на нее прочитается файлик из папки 
resource/static, запишется в базу и из базы прочитаются все песни и отобразятся на страници в виде таблицы.

Как бы все! Так плохо сделан фронт из-за того, что я с шаблонизаторами не очень дружу, а сделать так, чтобы работали статичные
html и js - не смог.

Со следующего задания перейду на скинутый boot шаблон и там попробую сделать покрасивее.
На другие ссылки просьба не обращать внимания, это от задания по JPA остались.

в ГИТ залил весь проект, а то боюсь, что чего-нибудь не хватит и проект не запустится, импорты все вроде бы почистил.