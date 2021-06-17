package com.example.myweather2.network
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class AirPollution(
    @Json(name = "coord")
    val coord: Coord,
    @Json(name = "list")
    val list: List<AirPollutionData>
)

@JsonClass(generateAdapter = true)
data class AirPollutionData(
    @Json(name = "components")
    val components: Components,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "main")
    val main: MainData
)

@JsonClass(generateAdapter = true)
data class Components(
    @Json(name = "co")
    val co: Double,
    @Json(name = "nh3")
    val nh3: Double,
    @Json(name = "no")
    val no: Double,
    @Json(name = "no2")
    val no2: Double,
    @Json(name = "o3")
    val o3: Double,
    @Json(name = "pm10")
    val pm10: Double,
    @Json(name = "pm2_5")
    val pm2_5: Double,
    @Json(name = "so2")
    val so2: Double
)

@JsonClass(generateAdapter = true)
data class MainData(
    @Json(name = "aqi")
    val aqi: Int
)