package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.databinding.AdapterMainBinding
import com.example.retrofit.model.MainModel

class MainAdapter(
    // variable imutable yang akan menyimpan Array dari class MainModel
    private val results: ArrayList<MainModel.Result>,
    // variable imutable yang akan menyimpan hasil dari interface OnAdapterListener
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    // Class untuk menampung list item yang ada pada recyclerView
    // yang nantinya akan di gunakan
    class ViewHolder(val binding: AdapterMainBinding) : RecyclerView.ViewHolder(binding.root)

    // Sebuah method pada class RecyclerView bertujuan untuk membuat dan menginisialisasi
    // instance(object) dari class viewHolder dan method ini berjalan saat pertama kali
    // xml di tampilkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // kode tersebut mengambil LayoutInflater dari konteks parent, menggunakan inflate untuk
        // membuat objek View dari layout XML adapter_main, dan mengembalikan objek View yang siap
        // digunakan sebagai tampilan satu item di dalam RecyclerView
        val binding = AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    // Sebuah method pada class RecyclerView bertujuan untuk menentukan berapa banyak
    // Item yang akan di tampilkan dalam set data
    override fun getItemCount(): Int = results.size

    // Sebuah method pada class RecyclerView bertujuan untuk mengikat data setelah onCreateViewHolder
    // Menangani pembuatan tampilan dan di eksetend dengan class ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // tanpa with
        holder.binding.textView.text = results[position].title

        // dengan with
        // with(holder){
        //    with(results[position]) {
        //        binding.textView.text = this.title
        //   }
        // }

        // Menggunakan Glide untuk menambahkan animasi loading pada gambar
        Glide.with(holder.itemView)
            // akan membuat animasi pada setiap pemanggilan gambar dengan api
            .load(results[position].image)
            // Menjadikan gambar terpotong pas di tengah agar presisi atau
            // sejajar
            .centerCrop()
            // Memasukan value yang di simpan pada Glide kedalam xml
            .into(holder.binding.imageView)

        // Menangani aksi ketika text pada recyclerView di click
        // holder.binding.textView.setOnClickListener {
        //    listener.onClick(results[position])
        // }

        // Menangani aksi ketika container di klik
        holder.itemView.setOnClickListener {
            listener.onClick(results[position])
        }
    }

    fun setData(data: List<MainModel.Result>) {
        // Jika terdapat data maka bersihkan
        results.clear()
        // jika terdapat data baru maka tambahkan
        results.addAll(data)
        // refresh recyclerView
        notifyDataSetChanged()
    }

    // Membuat interface untuk menangani even click pada item recyclerView
    interface OnAdapterListener {
        // Method interface yang akan di gunakan pada class lain
        // yang akan menyimpan nilai Result dari class MainModel
        fun onClick(result: MainModel.Result)
    }
}

// Cara berjalannya program di belakang layar
// 1. class ViewHolder di buat terlebih dahulu dan sudah menampung list item yang ada pada
//    recylerView
// 2. Kemudian onCreateViewHolder yang menyimpan parameter parent extends ViewGroup, ViewGroup
//    adalah subclass untuk mengatur tata letak child
// 3. kemudian paramter position di extend dengan integer
// 4. kemudian class override tersebut akan mengembalikan ViewHolder class yang sudah di buatkan
// 5. kemudian di dalamnya terdapat variable imutable bernama binding yang menampung inflasi
//    dari class xml adapter_main.xml
// 6. parameter pertama class tersebut juga menjalankan aksi penampungan xml dari
//    file adapter_main.xml yang dinyatakan dengan LayoutInflater dan akan merubahnya menjadi
//    object View
// 7. parameter kedua menggunakan parent kembali untuk menampung hasil dari inflasi yang
//    akan menjadi recyclerView
// 8. dan paramter ketiga di jadikan false mengartikan apakah hasil dari inflasi harus langsung
//    di tambahkan ke tampilan induk atau tidak pada kasus itu tidak menambahkan tampilan induk
// 9. kemudian terdapat method getItemCount yang mengembalikan Integer yang berisikan
//    ukuran dari variabel imutable results yang di extends dengan ArrayLits<MainModel>
// 10. kemudian method onBindViewHolder akan mengikat semua yang di hasilkan dari method
//     onCreateViewHolder dan method getItemCount dan juga class ViewHolder dan menterjemahkanya
//     kedalam xml adapter_main.xml untuk menampilkan hasilnya
// 11. method setData adalah method yang menjalankan perilaku pengecekan apakah results sebelumya
//     terdapat data atau tidak jika ya maka di hapuskan dan akan mengecek apakah ada data baru
//     atau tidak jika ada maka di tambahkan, kemudian method notifyDataSetChanged() akan mengecek
//     perubahan pada recyclerView dan memastikan bahwa data recyclerView tersebut wajib
//     menggunakan data terbaru