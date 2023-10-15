# ВТБ Маршрут
Android-приложение для подбора оптимального отделения банка с учетом потребностей клиента и доступности услуг

## Инструкция по запуску Android-приложения:
### Вариант 1
1. Скачать APK-файл, хранящийся во вкладке Releases данного репозитория;
2. Установить скачанный файл на Android-утройстве;
3. Запустить уставновленное приложение.

### Вариант 2
1. Склонировать ветку android из данного репозитория в Android Studio;
2. Перейти в файлы конфигурации Gradle;
3. Нажать на "Sync now" в появившемся над файлом уведомлении;
4. После синхронизации запустить приложение на эмуляторе или подключенном Android-устройстве.

## Инструкция по развертыванию Backend:
1. Установите все библиотеки и зависимости: npm install node express mongoose body-parser cors 
2. Для AI: pip install tensorflow Flask, cv2, numpy

## Описание используемых технологий, библиотек и фреймворков
### Android:
* Архитектурные паттерны: Clean Architecure + Model View ViewModel
* Dependency Injection: Dagger Hilt
* Верстка экранов: Jetpack Compose
* Карты: Yandex MapKit
* Локальная база данных: Room
* Асинхронность: Coroutines + Flow

### Backend и Нейронная сеть:
* Mongoose, JavaScript, Node.js, Express
* Python, Flask, Open-cv2, Numpy
