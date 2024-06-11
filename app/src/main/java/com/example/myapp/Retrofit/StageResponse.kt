package com.example.myapp.Retrofit

data class StageResponse(
    val totalPages: Int,
    val totalElements: Long,
    val size: Int,
    val content: List<StageData>,
    val number: Int,
    val sort: SortInfo,
    val first: Boolean,
    val last: Boolean,
    val numberOfElements: Int,
    val pageable: PageableInfo,
    val empty: Boolean
)
