package com.example.projeczara.data.server


import com.example.projeczara.data.database.toDomainDataBase
import com.example.projeczara.data.database.toRoomCharacter
import com.example.projeczara.data.domain.Character
import com.example.projeczara.data.database.character.Character as DBCharacter
import com.example.projeczara.data.server.model.Character as ServerCharacter


import com.example.projeczara.data.domain.Origin
import com.example.projeczara.data.server.model.Origin as ServerOrigin

import com.example.projeczara.data.domain.Location
import com.example.projeczara.data.server.model.Location as ServerLocation

fun List<DBCharacter>.toDomainCharacter(): List<Character> = map { it.toDomainDataBase() }

fun ServerCharacter.toDomainCharacter(): Character =
    Character(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin.toDomainOrigin(),
        location.toDomainLocation(),
        image,
        episode,
        url,
        created
    )

fun ServerOrigin.toDomainOrigin(): Origin =
    Origin(
        name, url
    )

fun ServerLocation.toDomainLocation(): Location =
    Location(
        name, url
    )


