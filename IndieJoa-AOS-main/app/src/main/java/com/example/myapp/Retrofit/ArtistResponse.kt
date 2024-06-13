package com.example.myapp.Retrofit

data class ArtistResponse(
    val totalPages: Int,
    val totalElements: Int,
    val size: Int,
    val content: List<ArtistData>,
    val number: Int,
    val sort: SortInfo,
    val first: Boolean,
    val last: Boolean,
    val numberOfElements: Int,
    val pageable: PageableInfo,
    val empty: Boolean
)
