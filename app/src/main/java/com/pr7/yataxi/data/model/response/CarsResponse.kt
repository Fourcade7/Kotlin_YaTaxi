package com.pr7.yataxi.data.model.response

data class CarsResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultCar>
)
data class ResultCar(
    val id: Int,
    val manufacturer: Int,
    val name: String
)