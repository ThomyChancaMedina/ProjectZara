package com.example.projeczara.data.database

import com.example.projeczara.data.domain.Character
import com.example.projeczara.data.database.character.Character as DBCharacter

fun Character.toRoomCharacter(): DBCharacter =
    DBCharacter(
        id, name, status, species, type, gender, origin, location, image, episode, url, created
    )

fun DBCharacter.toDomainDataBase(): Character =
    Character(
        id, name, status, species, type, gender, origin, location, image, episode, url, created
    )
