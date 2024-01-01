package com.example.retrofit.retrofit

// Import annotation GET dari library retrofit
import com.example.retrofit.model.MainModel
import retrofit2.Call
import retrofit2.http.GET

// Membuat interface bluprint untuk api yang nantinya
// akan di turunkan kepada class yang ingin menggunakannya
interface ApiEndpoint {
    // Membuat annotation yang di bawakan oleh Retrofit
    // @GET("photos")
    // Membuat function yang nantinya akan di turunkan kepada
    // class yang ingin menggunakannya atau di override
    // fun getPhotos(): Call<List<MainModel>>
    // Catatan untuk function getPhoto
    // - Call<> adalah interface bawaan dari retrofit yang tujuannya
    //   mengembalikan suatu nilai tertentu dalam kasus ini akan
    //   mengembalikan MainModel yang mana berisikan data class yang
    //   sudah di tentukan, karena pada susunan jsonnya di awali dengan
    //   kurung array "[]" dan di dalamnya terdapat kurung kurawal
    //   yang di baca sebagai list-list json maka penggunaan List dengan
    //   diamond branch atau generic kotlin di perlukan

    // Menggunakan GET data terbaru untuk endpoint terbaru
    @GET("data.php")
    fun getData(): Call<MainModel>
}