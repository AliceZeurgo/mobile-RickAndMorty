package br.senai.sp.jandira.rickandmortyy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.rickandmortyy.model.Character
import br.senai.sp.jandira.rickandmortyy.model.Result
import br.senai.sp.jandira.rickandmortyy.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CharactersList(modifier: Modifier = Modifier) {

    var characterList by remember {
        mutableStateOf(listOf<Character>())
    }

    val callCharactersList = RetrofitFactory()
        .getCharacterService().getAllCharacters()

    callCharactersList.enqueue(object : Callback<Result> {
        override fun onResponse(p0: Call<Result>, p1: Response<Result>) {
            characterList = p1.body()!!.results
        }

        override fun onFailure(p0: Call<Result>, p1: Throwable) {

        }
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xC4FFB3CC)
    )
    {
        Column(
            modifier = Modifier
                .padding(20.dp)

        ) {
            Text(
                text = "Rick & Morty API",
                fontSize = 19.sp,
                color = Color.White

            )
            Spacer(modifier = Modifier.height(32.dp))


            LazyColumn {
                items(characterList) {
                    CharacterCard(character = it)
                }
            }
        }

    }
}

@Composable
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x72B664BE))
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(modifier = Modifier.size(80.dp)) {
                AsyncImage(
                    model = character.image,
                    contentDescription = ""
                )

            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = character.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = character.species)
            }


        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun CharactersListPreview() {
    CharactersList()
}