package com.example.projeczara.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.projeczara.ui.navegation.NavigationRouter


@Composable
fun MainScreen(viewModel: CharacterViewModel = hiltViewModel(), navController: NavHostController) {

    viewModel.onRequestAndGetAllCharacter()

    val data = viewModel.character.collectAsState(initial = CharacterViewModel.UiState())
    if (data.value.character?.size != 0) {
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



