package com.pr7.yataxi.data.model.response

data class RegionsResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultRegion>
)

data class ResultRegion(
    val districts: List<District>,
    val id: Int,
    val name: String
)

data class District(
    val id: Int,
    val name: String,
    val region: Int
)