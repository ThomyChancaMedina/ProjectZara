package com.example.projeczara.testmock

import com.example.projeczara.data.domain.Character
import com.example.projeczara.data.domain.Location
import com.example.projeczara.data.domain.Origin

val mockCharacter: Character
    get() = Character(
        1,
        "name",
        "",
        "",
        "",
        "",
        mockOrigin,
        mockLocation,
        "",
        listOf(),
        "",
        ""
    )

val mockOrigin = Origin(
    "",
    ""
)

val mockLocation = Location(
    "",
    ""
)