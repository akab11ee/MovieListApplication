package com.restaurant.movielistapplication.domain.models.moviesection

import android.os.Parcel
import android.os.Parcelable

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class MovieDetails(

    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(adult)
        parcel.writeString(backdropPath)
        parcel.writeValue(id)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)
        parcel.writeValue(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetails> {
        override fun createFromParcel(parcel: Parcel): MovieDetails {
            return MovieDetails(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetails?> {
            return arrayOfNulls(size)
        }
    }
}