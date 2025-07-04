package ru.cft.data_source.local

class ProtoToModelConverter {

    fun convertResults(proto: UsersListResponse): ru.cft.common.data.model.UsersListResponse {
        return ru.cft.common.data.model.UsersListResponse(
            results = proto.resultsList.map { convertUser(it) }
        )
    }

    private fun convertUser(proto: UserModel): ru.cft.common.data.model.UserModel {
        return ru.cft.common.data.model.UserModel(
            gender = proto.gender.ifEmpty { null },
            email = proto.email.ifEmpty { null },
            name = convertName(proto),
            location = convertLocation(proto),
            login = convertLogin(proto),
            dob = convertDob(proto),
            registered = convertRegistered(proto),
            phone = proto.phone.ifEmpty { null },
            cell = proto.cell.ifEmpty { null },
            id = convertId(proto),
            picture = convertPicture(proto),
            nat = proto.nat.ifEmpty { null }
        )
    }

    private fun convertName(proto: UserModel): ru.cft.common.data.model.Name {
        return ru.cft.common.data.model.Name(
            title = proto.name.title.ifEmpty { null },
            first = proto.name.first.ifEmpty { null },
            last = proto.name.last.ifEmpty { null },
        )
    }

    private fun convertLocation(proto: UserModel): ru.cft.common.data.model.Location {
        return ru.cft.common.data.model.Location(
            street = convertStreet(proto),
            city = proto.location.city.ifEmpty { null },
            state = proto.location.state.ifEmpty { null },
            country = proto.location.country.ifEmpty { null },
            coordinates = convertCoordinates(proto),
            timezone = convertTimezone(proto)
        )
    }

    private fun convertStreet(proto: UserModel): ru.cft.common.data.model.Street {
        return ru.cft.common.data.model.Street(
            number = proto.location.street.number,
            name = proto.location.street.name.ifEmpty { null },
        )
    }

    private fun convertCoordinates(proto: UserModel): ru.cft.common.data.model.Coordinates {
        return ru.cft.common.data.model.Coordinates(
            latitude = proto.location.coordinates.latitude.ifEmpty { null },
            longitude = proto.location.coordinates.longitude.ifEmpty { null },
        )
    }

    private fun convertTimezone(proto: UserModel): ru.cft.common.data.model.Timezone {
        return ru.cft.common.data.model.Timezone(
            offset = proto.location.timezone.offset.ifEmpty { null },
            description = proto.location.timezone.description.ifEmpty { null },
        )
    }

    private fun convertLogin(proto: UserModel): ru.cft.common.data.model.Login {
        return ru.cft.common.data.model.Login(
            uuid = proto.login.uuid.ifEmpty { null },
            username = proto.login.username.ifEmpty { null },
            password = proto.login.password.ifEmpty { null },
            salt = proto.login.salt.ifEmpty { null },
            md5 = proto.login.md5.ifEmpty { null },
            sha1 = proto.login.sha1.ifEmpty { null },
            sha256 = proto.login.sha256.ifEmpty { null },
        )
    }

    private fun convertDob(proto: UserModel): ru.cft.common.data.model.Dob {
        return ru.cft.common.data.model.Dob(
            date = proto.dob.date.ifEmpty { null },
            age = proto.dob.age
        )
    }

    private fun convertRegistered(proto: UserModel): ru.cft.common.data.model.Registered {
        return ru.cft.common.data.model.Registered(
            date = proto.registered.date.ifEmpty { null },
            age = proto.registered.age
        )
    }

    private fun convertId(proto: UserModel): ru.cft.common.data.model.Id {
        return ru.cft.common.data.model.Id(
            name = proto.id.name.ifEmpty { null },
            value = proto.id.value.ifEmpty { null },
        )
    }

    private fun convertPicture(proto: UserModel): ru.cft.common.data.model.Picture {
        return ru.cft.common.data.model.Picture(
            large = proto.picture.large.ifEmpty { null },
            medium = proto.picture.medium.ifEmpty { null },
            thumbnail = proto.picture.thumbnail.ifEmpty { null },
        )
    }
}
