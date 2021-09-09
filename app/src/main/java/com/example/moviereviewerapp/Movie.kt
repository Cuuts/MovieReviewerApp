package com.example.moviereviewerapp

import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Parcelable
import java.util.*
import kotlinx.parcelize.*
import java.io.File
import kotlin.collections.ArrayList

@Parcelize
class Movie(
    val uuid: UUID,
    val title: String,
    val genre: Genre,
    val releaseDate: Date,
    val file: File = File("\"C:\\\\Users\\\\tobi3\\\\AndroidStudioProjects\\\\MovieReviewerApp\\\\app\\\\src\\\\main\\\\res\\\\drawable\\\\monke.png\""),
    val reviewList: ArrayList<Review> = arrayListOf()
) : Parcelable