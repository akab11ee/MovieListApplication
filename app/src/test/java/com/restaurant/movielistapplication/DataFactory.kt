package com.restaurant.movielistapplication

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.restaurant.movielistapplication.data.storage.model.ListMovieSectionsEntity
import java.io.File


private val gson: Gson = GsonBuilder().create()


fun getMovieSectionsEntityResponse(): ListMovieSectionsEntity {
    val jsonString = getJson("MovieSectionResponse.json")
    return buildDataClassFromJson(jsonString)
}

private fun getJson(resourceName: String): String {
    val file = File("src/androidTest/resources/$resourceName")

    return String(file.readBytes())
}

//generic function to  generate data classes from json file path
private inline fun <reified T> buildDataClassFromJson(json: String): T {
    val jsonAdapter: TypeAdapter<T> = gson.getAdapter(T::class.java)
    return jsonAdapter.fromJson(json)!!
}
