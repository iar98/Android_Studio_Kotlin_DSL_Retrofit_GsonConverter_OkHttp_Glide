package com.example.retrofit.model

// JSON dengan structure
// [
//   {},
//   {},
// ]

//data class MainModel(
//    val id: Int,
//    val title: String,
//    val url: String
//)


// Json baru dengan structur
// {
//   result: [
//   {},
//   {},
//   ],
// }

// Cara menyusun data class
// Sunanan 1
//data class MainModel(val result: ArrayList<Result>)
//data class Result(val id: Int, val title: String, val image: String)

// Susunan 2
data class MainModel(val result: ArrayList<Result>) {
    data class Result(val id: Int, val title: String, val image: String)
}