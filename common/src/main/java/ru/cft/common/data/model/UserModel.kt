package ru.cft.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersListResponse(
    @SerialName("results") var results: List<UserModel> = listOf(),
)

@Serializable
data class UserModel(
    @SerialName("gender") val gender: String? = null,
    @SerialName("name") val name: Name? = Name(),
    @SerialName("location") val location: Location? = Location(),
    @SerialName("email") val email: String? = null,
    @SerialName("login") val login: Login? = Login(),
    @SerialName("dob") val dob: Dob? = Dob(),
    @SerialName("registered") val registered: Registered? = Registered(),
    @SerialName("phone") val phone: String? = null,
    @SerialName("cell") val cell: String? = null,
    @SerialName("id") val id: Id? = Id(),
    @SerialName("picture") val picture: Picture? = Picture(),
    @SerialName("nat") val nat: String? = null,
)

@Serializable
data class Name(
    @SerialName("title") val title: String? = null,
    @SerialName("first") val first: String? = null,
    @SerialName("last") val last: String? = null,
)

@Serializable
data class Street(
    @SerialName("number") val number: Int? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
data class Coordinates(
    @SerialName("latitude") val latitude: String? = null,
    @SerialName("longitude") val longitude: String? = null,
)

@Serializable
data class Timezone(
    @SerialName("offset") val offset: String? = null,
    @SerialName("description") val description: String? = null,
)

@Serializable
data class Location(
    @SerialName("street") val street: Street? = Street(),
    @SerialName("city") val city: String? = null,
    @SerialName("state") val state: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("coordinates") val coordinates: Coordinates? = Coordinates(),
    @SerialName("timezone") val timezone: Timezone? = Timezone(),
)

@Serializable
data class Login(
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("password") val password: String? = null,
    @SerialName("salt") val salt: String? = null,
    @SerialName("md5") val md5: String? = null,
    @SerialName("sha1") val sha1: String? = null,
    @SerialName("sha256") val sha256: String? = null,
)

@Serializable
data class Dob(
    @SerialName("date") val date: String? = null,
    @SerialName("age") val age: Int? = null,
)

@Serializable
data class Registered(
    @SerialName("date") val date: String? = null,
    @SerialName("age") val age: Int? = null,
)

@Serializable
data class Id(
    @SerialName("name") val name: String? = null,
    @SerialName("value") val value: String? = null,
)

@Serializable
data class Picture(
    @SerialName("large") val large: String? = null,
    @SerialName("medium") val medium: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null,
)