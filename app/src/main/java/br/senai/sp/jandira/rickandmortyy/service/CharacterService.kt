package br.senai.sp.jandira.rickandmortyy.service

import br.senai.sp.jandira.rickandmortyy.model.Character
import br.senai.sp.jandira.rickandmortyy.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    fun getAllCharacters(): Call<Result>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Character>

}