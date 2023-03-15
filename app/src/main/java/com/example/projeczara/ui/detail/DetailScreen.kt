package com.example.projeczara.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.projeczara.data.CharacterRepository
import com.example.projeczara.data.database.GetCharacterRoomData
import com.example.projeczara.data.domain.Character
import com.example.projeczara.data.server.GetCharacterRemote
import com.example.projeczara.data.server.RetrofitServer
import com.example.projeczara.ui.common.app
import com.example.projeczara.usescase.FindItemCharacterUseCase

@Composable
fun DetailScreenContent(param: Int?) {

    val repository = CharacterRepository(
        GetCharacterRoomData(LocalContext.current.app.db.characterDao()),
        GetCharacterRemote(RetrofitServer())
    )
    val detailViewModelFactory = DetailViewModelFactory(
        param!!,
        FindItemCharacterUseCase(repository)
    )

    val viewModel: DetailViewModel = viewModel(factory = detailViewModelFactory)

    val itemData by viewModel.itemCharacter.collectAsState()

    val character = itemData.itemCharacter
    if (character != null) {
        Column {
            CharacterBox(character)

        }
    }
}

@Composable
fun CharacterBox(character: Character) {
    val contain = "circular"
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                        .border(
                            width = 10.dp,
                            color = Color.Red,
                            shape = CircleShape
                        ),
                    painter = rememberAsyncImagePainter(model = character.image),
                    contentDescription = contain
                )
            }

        }
    }
}
