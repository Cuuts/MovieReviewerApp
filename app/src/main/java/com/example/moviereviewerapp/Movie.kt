package com.example.moviereviewerapp

import android.media.Image
import android.os.Parcelable
import java.util.*
import kotlinx.parcelize.*
import kotlin.collections.ArrayList

@Parcelize
class Movie(
    val uuid: UUID,
    val title: String,
    val genre: Genre,
    val releaseDate: Date,
    val reviewList: ArrayList<Review> = arrayListOf()
) : Parcelable