package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.MainAdapter
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.MainModel
import com.example.retrofit.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Cara 1 getApi dari jsonPlaceHolder
//    // Membuat variabel tag untuk Logcat
//    private val TAG: String = "MainActivity"
//    // Binding xml
//    private lateinit var binding: ActivityMainBinding
//
//    // lifecycle yang berjalan ketika activitie di buka
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//    // lifecycle yang berjalan ketika semua ui/xml sudah di buat
//    override fun onStart() {
//        super.onStart()
//        getDataFromApi()
//    }
//    // Function yang akan menjalankan logic dari file
//    // interface ApiEndPoint.kt dan singleton object dari ApiService
//    private fun getDataFromApi() {
//        ApiService.endpoint.getData()
//            .enqueue(object: Callback<List<MainModel>> {
//                // Method pada class Callback retrofit yang menangani kasus
//                // ketika sukses
//                override fun onResponse(
//                    call: Call<List<MainModel>>,
//                    response: Response<List<MainModel>>
//                ) {
//                    if(response.isSuccessful) {
//                        // Membuat instance atau object yang berisikan response.body
//                        // yang mana akan berisikan data dari json yang sudah di konversi
//                        // ke java yang akan bisa di baca dengan kotlin
//                        val result = response.body()
//                        // 1. Menampilkan keseluruhan data pada terminal dan 1 line
//                        // mengunakan function printlog untu mencetak list jsonnya dan ubah ke string agar
//                        // mudah di baca di terminal
//                        // printlog(result.toString())
//
//                        // 2. Menampilkan keseluruhan data tertentu pada terminal dan menjadikan
//                        //    beberapa line
//                        // Menggunakan function showPhotos untuk menampilkan data tittle pada json yang
//                        // sudah di konversi ke java dan dapat di baca dengan kotlin
//                        showPhotos(result!!)
//                        // Catatan :
//                        // Karena result merupakan object yang menangkap response dan ketika
//                        // program belum di run maka result akan di anggap null maka anda
//                        // wajib mendeklarasikannya dengan simbol tanda seru 2 kali (!!)
//                        // Ini menandakan bahwa object tersebut di paksa tidak null
//                        // Tetapi perlu di perhatikan dengan baik sebelum mendeklarasikannya
//                        // dengan double bang karena jika object tersebut adalah null ini
//                        // akan menyebabkan nullPointerException
//                    }
//                }
//                // Method pada class Callback retrofit yang menangani kasus
//                // ketika gagal
//                override fun onFailure(call: Call<List<MainModel>>, t: Throwable) {
//                    // gunakan function printlog untu mencetak t yang akan mengembalikan error throwable
//                    // dan ubah ke string agar bisa di baca di terminal
//                    printlog(t.toString())
//                }
//
//            })
//    }
//    // Function yang ketika di jalankan akan menangani tampilnya data pada
//    // terminal di logcat
//    private fun printlog(message: String) {
//        // Menampilkan pesannya pada message d atau Debug
//        Log.d(TAG, message)
//    }
//
//    // Membuat function yang parameternya di extends dengan List<MainModel>
//    // dimana List berisikan object dari data class
//    private fun showPhotos(photos: List<MainModel>) {
//        // Membuat object dari class StringBuilder()
//        // Penggunaan StringBuilder sangat cocok di karenakan data
//        // yang di tampung berupa string sangat panjang
//        val allDataStringBuilder = StringBuilder()
//        // Catatan
//
//        // stringBuilder() membuat alokasi memory menjadi sedikit sementara jika tanpa
//        // menggunakan stringBuilder() maka akan membuat alokasi memory yang sangat banyak
//        // tergantung dari kebutuhan berapa banyak object yang di iterasikan
//
//        // Iterabel data list dari parameter photos
//        for(photo in photos) {
//            // Eksekusi function printlog yang akan menampilkan data
//            // pada logcat
//            printlog("Tittle: ${photo.title}")
//            // Menambahkan data kedalam StringBuilder
//            allDataStringBuilder.append("Tiitle: ${photo.title} \n")
//        }
//        // Mengatur string yang berisi semua data sebagai teks dari editText
//        binding.tvText1.text = allDataStringBuilder.toString()
//        // Ubah ke array
//        val resultString = allDataStringBuilder.toString().split("\n").toTypedArray()
//        Log.d("array", "${resultString[0]}")
//    }

    // Cara ke 2 get Api dari demo.lazday.com api
    // Membuat variabel tag untuk Logcat
    private val TAG: String = "MainActivity"
    // Binding xml
    private lateinit var binding: ActivityMainBinding
    // RecyclerView
    private lateinit var mainAdapter: MainAdapter

    // lifecycle yang berjalan ketika activitie di buka
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    // lifecycle yang berjalan ketika semua ui/xml sudah di buat
    override fun onStart() {
        super.onStart()
        getDataFromApi()
        setupRecyclerView()
    }

    // Function yang akan menjalankan recyclerView
    private fun setupRecyclerView() {
        // inisialisasi variabel imutable menjadi object
        // yang berisikan class MainAdapter(arrayListOf())
        // yang akan di kirimkan ke dalam class MainAdapter dan
        // di terima sebagai primary constructor dari class tersebut
        mainAdapter = MainAdapter(
            // Untuk menampung array
            arrayListOf(),
            // Untuk menangani click listener pada container
            object: MainAdapter.OnAdapterListener{
                override fun onClick(result: MainModel.Result) {
                    // Debuging apakah benar ketika di click data yang di click sesuai denga judulnya
                    // Toast.makeText(applicationContext, "${result.title}", Toast.LENGTH_SHORT).show()

                    // Membuat perpindahan halaman ketika item di click akan berpindah ke activity
                    // DetailActivity
                    startActivity(
                        Intent(applicationContext, DetailActivity::class.java)
                            .putExtra("intent_title", result.title)
                            .putExtra("intent_image", result.image)
                    )
                }
            }
        )
        // Menetapkan perilaku dari RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    // Function yang akan menjalankan logic dari file
    // interface ApiEndPoint.kt dan singleton object dari ApiService
    private fun getDataFromApi() {
        // Menghilangkan progressbar
        binding.progressBar.visibility = View.VISIBLE

        ApiService.endpoint.getData()
            .enqueue(object: Callback<MainModel> {
                // Method pada class Callback retrofit yang menangani kasus
                // ketika sukses
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    binding.progressBar.visibility = View.GONE
                    if(response.isSuccessful) {
                        // Membuat instance atau object yang berisikan response.body
                        // yang mana akan berisikan data dari json yang sudah di konversi
                        // ke java yang akan bisa di baca dengan kotlin
                        val result = response.body()
                        // 1. Menampilkan keseluruhan data pada terminal dan 1 line
                        // mengunakan function printlog untu mencetak list jsonnya dan ubah ke string agar
                        // mudah di baca di terminal
                        // printlog(result.toString())

                        // 2. Menampilkan keseluruhan data tertentu pada terminal dan menjadikan
                        //    beberapa line
                        // Menggunakan function showPhotos untuk menampilkan data tittle pada json yang
                        // sudah di konversi ke java dan dapat di baca dengan kotlin
                        showData(result!!)
                        // Catatan :
                        // Karena result merupakan object yang menangkap response dan ketika
                        // program belum di run maka result akan di anggap null maka anda
                        // wajib mendeklarasikannya dengan simbol tanda seru 2 kali (!!)
                        // Ini menandakan bahwa object tersebut di paksa tidak null
                        // Tetapi perlu di perhatikan dengan baik sebelum mendeklarasikannya
                        // dengan double bang karena jika object tersebut adalah null ini
                        // akan menyebabkan nullPointerException
                    }
                }
                // Method pada class Callback retrofit yang menangani kasus
                // ketika gagal
                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    // gunakan function printlog untu mencetak t yang akan mengembalikan error throwable
                    // dan ubah ke string agar bisa di baca di terminal
                    binding.progressBar.visibility = View.GONE
                    printlog("onFailur : $t")
                }

            })
    }
    // Function yang ketika di jalankan akan menangani tampilnya data pada
    // terminal di logcat
    private fun printlog(message: String) {
        // Menampilkan pesannya pada message d atau Debug
        Log.d(TAG, message)
    }

    // Membuat function yang parameternya di extends dengan List<MainModel>
    // dimana List berisikan object dari data class
    private fun showData(data: MainModel) {
        // membuat variabel imutable yang mendapatkan nilai dari data.result
        val results = data.result
        // menggunakan method dari class MainAdapter yang berisikan results
        // yang sudah terisi data
        mainAdapter.setData(results)
        for(result in results){
            printlog("Tittle: ${result.title}")
        }
    }
}