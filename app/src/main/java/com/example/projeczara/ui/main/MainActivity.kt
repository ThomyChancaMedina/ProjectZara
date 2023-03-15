package com.example.projeczara.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.projeczara.data.CharacterRepository
import com.example.projeczara.data.database.GetCharacterRoomData
import com.example.projeczara.data.server.GetCharacterRemote
import com.example.projeczara.data.server.RetrofitServer
import com.example.projeczara.ui.common.app
import com.example.projeczara.ui.navegation.NavigationRouter
import com.example.projeczara.ui.navegation.NonAnimationApp
import com.example.projeczara.ui.common.theme.ProjecZaraTheme
import com.example.projeczara.usescase.GetCharacterUseCase
import com.example.projeczara.usescase.RequestCharactersUseCase

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjecZaraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NonAnimationApp()
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val repository = CharacterRepository(
        GetCharacterRoomData(LocalContext.current.app.db.characterDao()),
        GetCharacterRemote(RetrofitServer())
    )
    val viewModelFactory = CharacterViewModelFactory(
        GetCharacterUseCase(repository),
        RequestCharactersUseCase(repository)
    )

    val viewModel: CharacterViewModel = viewModel(factory = viewModelFactory)

    viewModel.onRequestAndGetAllCharacter()

    val data = viewModel.character.collectAsState(initial = CharacterViewModel.UiState())
    if (data.value.character?.size!=0) {
        MainGrid(data = data, navController)
    }

}

@Composable
fun MainGrid(data: State<CharacterViewModel.UiState>, navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        content = {
            items(data.value.character?.size ?: 0) { index ->
                val item = data.value.character?.get(index)
                Column(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = item?.image,
                        contentDescription = item?.name,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                navController.navigate(

                                    NavigationRouter.Detail.routerWithId(id = item!!.id)
                                )
                            }
                    )
                    item?.name?.let { Text(text = it) }
                }
            }
        })
}
