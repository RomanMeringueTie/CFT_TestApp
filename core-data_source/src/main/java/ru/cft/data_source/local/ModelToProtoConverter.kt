package ru.cft.data_source.local

class ModelToProtoConverter {

    fun convertUsersList(usersModel: List<ru.cft.common.data.model.UserModel>): List<UserModel> {
        return usersModel.map {
            convertUser(it)
        }
    }

    private fun convertUser(model: ru.cft.common.data.model.UserModel): UserModel {
        val builder = UserModel.newBuilder()

        model.gender?.let { builder.gender = it }
        model.email?.let { builder.email = it }
        builder.setName(convertName(model))
        builder.setLocation(convertLocation(model))
        builder.setLogin(convertLogin(model))
        builder.setDob(convertDob(model))
        builder.setRegistered(convertRegistered(model))
        model.phone?.let { builder.phone = it }
        model.cell?.let { builder.cell = it }
        builder.setId(convertId(model))
        builder.setPicture(convertPicture(model))
        model.nat?.let { builder.nat = it }

        return builder.build()
    }

    private fun convertName(model: ru.cft.common.data.model.UserModel): Name {
        val nameBuilder = Name.newBuilder()
        model.name?.title?.let { nameBuilder.title = it }
        model.name?.first?.let { nameBuilder.first = it }
        model.name?.last?.let { nameBuilder.last = it }
        return nameBuilder.build()
    }

    private fun convertLocation(model: ru.cft.common.data.model.UserModel): Location {
        val locationBuilder = Location.newBuilder()
        model.location?.let { loc ->
            locationBuilder.setStreet(convertStreet(loc))
            loc.city?.let { locationBuilder.city = it }
            loc.state?.let { locationBuilder.state = it }
            loc.country?.let { locationBuilder.country = it }
            locationBuilder.setCoordinates(convertCoordinates(loc))
            locationBuilder.setTimezone(convertTimezone(loc))
        }
        return locationBuilder.build()
    }

    private fun convertStreet(loc: ru.cft.common.data.model.Location): Street {
        val streetBuilder = Street.newBuilder()
        loc.street?.number?.let { streetBuilder.number = it }
        loc.street?.name?.let { streetBuilder.name = it }
        return streetBuilder.build()
    }

    private fun convertCoordinates(loc: ru.cft.common.data.model.Location): Coordinates {
        val coordinatesBuilder = Coordinates.newBuilder()
        loc.coordinates?.latitude?.let { coordinatesBuilder.latitude = it }
        loc.coordinates?.longitude?.let { coordinatesBuilder.longitude = it }
        return coordinatesBuilder.build()
    }

    private fun convertTimezone(loc: ru.cft.common.data.model.Location): Timezone {
        val timezoneBuilder = Timezone.newBuilder()
        loc.timezone?.offset?.let { timezoneBuilder.offset = it }
        loc.timezone?.description?.let { timezoneBuilder.description = it }
        return timezoneBuilder.build()
    }

    private fun convertLogin(model: ru.cft.common.data.model.UserModel): Login {
        val loginBuilder = Login.newBuilder()
        model.login?.uuid?.let { loginBuilder.uuid = it }
        model.login?.username?.let { loginBuilder.username = it }
        model.login?.password?.let { loginBuilder.password = it }
        model.login?.salt?.let { loginBuilder.salt = it }
        model.login?.md5?.let { loginBuilder.md5 = it }
        model.login?.sha1?.let { loginBuilder.sha1 = it }
        model.login?.sha256?.let { loginBuilder.sha256 = it }
        return loginBuilder.build()
    }

    private fun convertDob(model: ru.cft.common.data.model.UserModel): Dob {
        val dobBuilder = Dob.newBuilder()
        model.dob?.date?.let { dobBuilder.date = it }
        model.dob?.age?.let { dobBuilder.age = it }
        return dobBuilder.build()
    }

    private fun convertRegistered(model: ru.cft.common.data.model.UserModel): Registered {
        val registeredBuilder = Registered.newBuilder()
        model.registered?.date?.let { registeredBuilder.date = it }
        model.registered?.age?.let { registeredBuilder.age = it }
        return registeredBuilder.build()
    }

    private fun convertId(model: ru.cft.common.data.model.UserModel): Id {
        val idBuilder = Id.newBuilder()
        model.id?.name?.let { idBuilder.name = it }
        model.id?.value?.let { idBuilder.value = it }
        return idBuilder.build()
    }

    private fun convertPicture(model: ru.cft.common.data.model.UserModel): Picture {
        val pictureBuilder = Picture.newBuilder()
        model.picture?.large?.let { pictureBuilder.large = it }
        model.picture?.medium?.let { pictureBuilder.medium = it }
        model.picture?.thumbnail?.let { pictureBuilder.thumbnail = it }
        return pictureBuilder.build()
    }
}