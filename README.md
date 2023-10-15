# ВТБ Маршрут
Android-приложение для подбора оптимального отделения банка с учетом потребностей клиента и доступности услуг

## Скриншоты приложения
![image](https://github.com/KatbertAllgood/vtb_hack/assets/73115406/96c68d90-871a-4485-849d-032843fb6422)
![image](https://github.com/KatbertAllgood/vtb_hack/assets/73115406/339a0278-1f7b-428b-844f-d58464b43882)
![image](https://github.com/KatbertAllgood/vtb_hack/assets/73115406/0d1b2850-b135-49fb-a02b-fdebd8ec820f)
![image](https://github.com/KatbertAllgood/vtb_hack/assets/73115406/5e43da21-35cc-47c4-a224-5c7bd87a1a47)
![image](https://github.com/KatbertAllgood/vtb_hack/assets/73115406/6a119f06-9c34-41a2-bd4c-3626ea7c1a84)


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
