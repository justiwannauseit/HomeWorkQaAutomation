# Домашнее задание к 18 лекции Qa Automation
Написать автотесты на базу данных H2.

База данных заполняется в BeforeAll. (Вызвать метод BeforeUtils.createData();)

Интеграцию с базой данных реализовать либо с помощью JDBC либо с помощью Hibernate

Создать класс DbClient в котором и реализовать логику работы с базой.

Каждый тест должен запускаться в рамках одного Connection (или Session в случае с Hibernate)

Написать автотесты на следующие условия (на каждое условие отдельный автотест)
1. В таблице public.animal ровно 10 записей
2. В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
3. В таблицу public.workman нельзя добавить строку с name = null
4. Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
5. В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный' 