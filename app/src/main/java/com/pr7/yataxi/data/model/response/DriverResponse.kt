package com.pr7.yataxi.data.model.response

data class DriverResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)

data class Car(
    val id: Int,
    val manufacturer: Int,
    val name: String
)

data class DirectionFrom(
    val id: Int,
    val name: String,
    val region: Int
)
data class DirectionTo(
    val id: Int,
    val name: String,
    val region: Int
)
data class Owner(
    val car: Car,
    val first_name: String,
    val id: Int,
    val image: Any,
    val last_name: String,
    val phone_number: String
)
data class Result(
    val direction_from: DirectionFrom,
    val direction_to: DirectionTo,
    val id: Int,
    val owner: Owner,
    val price: Int,
    val rating: Any
)