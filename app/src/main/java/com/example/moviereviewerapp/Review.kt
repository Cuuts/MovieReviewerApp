package com.example.moviereviewerapp

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.*
import java.util.*

@Parcelize
class Review(
    val uuid: UUID,
    val message: String,
    val user: String,
    val rating: Float
) : Parcelable
