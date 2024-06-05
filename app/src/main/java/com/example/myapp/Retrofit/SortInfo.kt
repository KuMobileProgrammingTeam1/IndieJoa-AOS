package com.example.myapp.Retrofit

data class SortInfo(
    val direction: String,
    val nullHandling: String,
    val ascending: Boolean,
    val property: String,
    val ignoreCase: Boolean
)
