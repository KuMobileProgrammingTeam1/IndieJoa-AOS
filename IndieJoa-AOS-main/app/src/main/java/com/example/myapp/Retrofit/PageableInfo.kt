package com.example.myapp.Retrofit

data class PageableInfo(
    val offset: Int,
    val sort: SortInfo,
    val paged: Boolean,
    val pageNumber: Int,
    val pageSize: Int,
    val unpaged: Boolean
)
