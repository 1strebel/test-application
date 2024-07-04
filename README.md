Для каждого пользователя из тестового задания логин совпадает с паролем:

    admin:admin
    user1:user1
    user2:user2
    user3:user3

Запуск тестов:

    ./gradlew test

Запуск с gradle:

    ./gradlew bootRun
        
Запуск в докере:

     ./gradlew clean build && docker compose build && docker compose up