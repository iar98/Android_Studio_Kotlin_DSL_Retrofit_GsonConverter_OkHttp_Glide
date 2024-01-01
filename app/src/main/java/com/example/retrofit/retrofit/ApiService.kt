package com.example.retrofit.retrofit

// Memanggil class Retrofit dari retrofit2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
// Memanggil class GsonConverterFactory untuk
// menerjemahkan json ke java dan sebaliknya
import retrofit2.converter.gson.GsonConverterFactory

// Membuat singleton yang nantinya akan di gunakan
// pada seluruh project ini menggunakan object kotlin
object ApiService {
    // Retrofit
    // Membuat properties yang berisikan link utama yang akan
    // di gabungkan pada endpoint interface
    // val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    // New BASE_URL
    val BASE_URL: String = "https://demo.lazday.com/rest-api-sample/"
    // Membuat variabel endpoint yang akan di binding atau extends dengan interface
    // bernama ApiEndpoint
    val endpoint: ApiEndpoint
        // Menyatakan bahwa variable endpoint memiliki getter
        get() {

            // Logging okhttp interceptor
            // membuat object dari class HttpLoggingInterceptor
            val interceptor = HttpLoggingInterceptor()
            // Membuat object menggunakan level yang akan melihat seluruh body
            // dari request dan response http
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            // Level adalah cara okhttp mengatur tingkatan pada log terminal
            // Beberapa macam penggunaan level
            // NONE: Tidak ada logging.
            // BASIC: Logging hanya menampilkan URL, metode, dan kode respons.
            // HEADERS: Logging menampilkan informasi dasar ditambah dengan header.
            // BODY: Logging menampilkan seluruh informasi termasuk body dari request dan response.

            // Membuat client atau permintaan ke server dengan loggging okhttp interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()


            // Membuat objek Retrofit Builder yang akan digunakan untuk melakukan HTTP requests
            val retrofit = Retrofit.Builder()
                // Mengatur base URL untuk API
                .baseUrl(BASE_URL)
                // Menambahkan client pada method client dari logging Okhttp untuk menangani permintaan
                .client(client)
                // Menambahkan converter factory untuk mengubah JSON menjadi objek Kotlin
                .addConverterFactory(GsonConverterFactory.create())
                // Membuat objek Retrofit
                .build()

            // Mengembalikan object atau instance dari interface ApiEndpoint yang dihasilkan oleh Retrofit
            return retrofit.create(ApiEndpoint::class.java)
        }
}