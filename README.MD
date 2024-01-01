# Android Studio Kotlin
## _Using Retrofit, GsonConverter, Logging OkHttp and Glide_

[![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/studio?hl=id)
[![Windows 11](https://img.shields.io/badge/Windows%2011-%230079d5.svg?style=for-the-badge&logo=Windows%2011&logoColor=white)](https://www.microsoft.com/software-download/windows11)
[![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)](https://gradle.org/)
[![Retrofit](https://img.shields.io/badge/Retrofit-Official%20Site-green.svg)](https://square.github.io/retrofit/)
[![OkHttp](https://img.shields.io/badge/Android%20Studio-Kotlin%20with%20OkHttp-brightgreen.svg)](https://square.github.io/okhttp/)
[![Glide](https://img.shields.io/badge/Android%20Studio-Kotlin%20with%20Glide-orange.svg)](https://github.com/bumptech/glide)

Retrofit is a library that handles Https APIs, and GsonConverter is a library for translating JSON to Java, which will be configured so that Kotlin can use it. Logging OkHttp is a library that handles client or server request. With this, when the server is under maintenance or you have incorrectly written the BASE_URL or endpoint, you can use the error message as an action that you can modify.

- Easy to use 
- All source code is accompanied by comments, making it easier for you to understand the code. 
- ✨Magic ✨

## Features

- Utilizing Retrofit's GET method, equipped with OkHttp to handle cases of endpoint or BASE_URL misspelling errors.
- Translating JSON into Java and configuring it for use in Kotlin, then displaying it in the Android Studio logcat terminal.
- Clicking on the image displays the full version corresponding to the clicked image.
- Drag and drop markdown and HTML files into Dillinger
- Loading or progress bar indicating the speed of your internet connection when loading data using Retrofit's GET method and JSON converted with GsonConverter.

## Tech
This simple application is created using several libraries:
- [Retrofit](https://square.github.io/retrofit/) - is used to handle Https API.
- [GsonConverter](https://square.github.io/retrofit/) - is employed to translate JSON into Java and will be configured for Kotlin usage. It also works the other way around, allowing you to translate Java or Kotlin into JSON.
- [Logging OkHttp](https://github.com/square/okhttp) - is utilized to handle user requests to the server, commonly referred to as the client. You can manage actions such as success with code 200 and not found with code 404.
- [Glide](https://github.com/bumptech/glide) - is used to handle the loading of JSON data into Java, configured for use in Kotlin. You can provide a default image when the data loading process is in progress and an error image when the desired image data is unavailable
- The source code contains comments, but they are in the Indonesian language since I am from Indonesia. Therefore, you just need to open the translation in your preferred web platform. You can simply block the comments, use Ctrl + / to disable them, and then copy and paste them into your preferred translation platform.

## Requitment
- Android Studio 2023.3.1
- JDK 21.0.1
- Android Gradle Plugin Version 8.1.0
- Gradle Version 8.0
- CompileSdk 34
- minSdk 24
- targetSdk 33
- ViewBinding = True
- implementation("com.squareup.retrofit2:retrofit:2.9.0")
- implementation("com.squareup.retrofit2:converter-gson:2.9.0")
- implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
- implementation("com.github.bumptech.glide:glide:4.16.0")
```
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
```

# Instalation

- Create a new folder for cloning on GitHub, name it whatever you like, and enter to open
```
📁 Example
```
- Open the Git terminal and type the following syntax, then press enter.
```
git clone "A link copied"
```
- When the cloning process is complete, there will be a new folder usually named after the GitHub repository. Right-click and hold on the folder, then point it to Android Studio Kotlin and release the right-click, it will automatically open the project folder.
- Double-check the requirements to ensure they are correct or if there are any errors. If everything is correct, you can proceed to run the AVD and explore the code in each file for your learning. 