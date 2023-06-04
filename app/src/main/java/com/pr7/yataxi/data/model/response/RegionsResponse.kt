package com.pr7.yataxi.data.model.response

data class RegionsResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val resultRegions: List<ResultRegion>
)