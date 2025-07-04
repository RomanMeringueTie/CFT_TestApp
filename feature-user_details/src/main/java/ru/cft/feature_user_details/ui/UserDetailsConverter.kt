package ru.cft.feature_user_details.ui

import android.annotation.SuppressLint
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import ru.cft.common.data.model.UserModel


class UserDetailsConverter {

    fun convertUser(userModel: UserModel): UserUI {
        val id = convertId(userModel)
        val fullName = convertFullName(userModel)
        val gender = userModel.gender ?: "Unknown"
        val email = userModel.email ?: "N/A"
        val phone = userModel.phone ?: "N/A"
        val cell = userModel.cell ?: "N/A"
        val username = userModel.login?.username ?: "N/A"
        val nationality = userModel.nat ?: "N/A"
        val dob = convertDob(userModel)
        val registered = convertRegistered(userModel)
        val pictureLarge = userModel.picture?.large ?: ""
        val address = convertAddress(userModel)
        val coordinates = convertCoordinates(userModel)
        val timezone = convertTimezone(userModel)

        return UserUI(
            id = id,
            fullName = fullName,
            gender = gender,
            email = email,
            phone = phone,
            cell = cell,
            username = username,
            nationality = nationality,
            dob = dob,
            registered = registered,
            picture = pictureLarge,
            address = address,
            coordinates = coordinates,
            timezone = timezone
        )
    }

    private fun convertId(userModel: UserModel): String {
        return userModel.login?.uuid ?: userModel.id?.value ?: "N/A"
    }

    private fun convertFullName(userModel: UserModel): String {
        return buildString {
            append(userModel.name?.title?.let { "$it " } ?: "")
            append(userModel.name?.first ?: "")
            append(" ")
            append(userModel.name?.last ?: "")
        }.trim().ifEmpty { "Unknown Name" }
    }

    private fun convertDob(userModel: UserModel): String {
        val customFormat = LocalDateTime.Format {
            dayOfMonth(); char('.'); monthNumber(); char('.'); year()
        }
        val localDateTime = LocalDateTime.parse(
            userModel.dob?.date.toString().dropLast(1)
        )
        val date = localDateTime.format(customFormat)
        return userModel.dob?.let { "$date (${it.age ?: "?"} y.o.)" } ?: "N/A"
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertRegistered(userModel: UserModel): String {
        return userModel.registered?.let {
            val customFormat = LocalDateTime.Format {
                dayOfMonth(); char('.'); monthNumber(); char('.'); year()
            }
            val localDateTime = LocalDateTime.parse(
                userModel.registered?.date.toString().dropLast(1)
            )
            val date = localDateTime.format(customFormat)
            "$date (${it.age ?: "?"} years)"
        } ?: "N/A"
    }

    private fun convertAddress(userModel: UserModel): String {
        return buildString {
            userModel.location?.let { loc ->
                append(loc.street?.number?.toString()?.plus(" ") ?: "")
                append(loc.street?.name ?: "")
                append(", ")
                append(loc.city ?: "")
                append(", ")
                append(loc.state ?: "")
                append(", ")
                append(loc.country ?: "")
            }
        }.trimEnd(',').ifEmpty { "Unknown address" }
    }

    private fun convertCoordinates(userModel: UserModel): String {
        return userModel.location?.coordinates?.let {
            "Lat: ${it.latitude ?: "?"}, Lng: ${it.longitude ?: "?"}"
        } ?: "N/A"
    }

    private fun convertTimezone(userModel: UserModel): String {
        return userModel.location?.timezone?.let {
            "${it.offset ?: ""} — ${it.description ?: ""}"
        } ?: "N/A"
    }
}