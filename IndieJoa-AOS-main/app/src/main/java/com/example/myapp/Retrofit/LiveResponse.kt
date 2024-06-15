package com.example.myapp.Retrofit

data class LiveResponse(
    val totalPages: Int,
    val totalElements: Int,
    val size: Int,
    val content: List<LiveData>,
    val number: Int,
    val sort: SortInfo,
    val numberOfElements: Int,
    val pageable: PageableInfo,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean
)
