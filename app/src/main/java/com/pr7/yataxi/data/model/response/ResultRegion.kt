package com.pr7.yataxi.data.model.response

data class ResultRegion(
    val districts: List<District>,
    val id: Int,
    val name: String
)