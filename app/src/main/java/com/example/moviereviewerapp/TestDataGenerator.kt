package com.example.moviereviewerapp

import android.media.Image
import android.media.ImageReader
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class TestDataGenerator {
    var testData: ArrayList<Movie> = generateData()
}
private fun generateData(): ArrayList<Movie> {
    var list: ArrayList<Movie> = arrayListOf()
    list.apply {
        add(Movie(UUID.randomUUID(), "The Shawshank Redemption", Genre.DRAMA, Date(1994,10,14), ))
        add(Movie(UUID.randomUUID(),"The Godfather", Genre.CRIME, Date(1972,3,19)))
        add(Movie(UUID.randomUUID(),"The Godfather: Part II", Genre.CRIME, Date(1974,12,18)))
        add(Movie(UUID.randomUUID(),"The Dark Knight", Genre.ACTION, Date(2008,7,18)))
        add(Movie(UUID.randomUUID(),"12 Angry Men", Genre.ACTION, Date(1957,4,10)))
        add(Movie(UUID.randomUUID(),"Schindler's List", Genre.DRAMA, Date(1994,2,4)))
        add(Movie(UUID.randomUUID(),"The Lord of the Rings: The Return of the King", Genre.ADVENTURE, Date(2003,12,17)))
        add(Movie(UUID.randomUUID(),"Pulp Fiction", Genre.CRIME, Date(1994,10,14)))
        add(Movie(UUID.randomUUID(),"The Good, the Bad and the Ugly", Genre.WESTERN, Date(1967,12,29)))
        add(Movie(UUID.randomUUID(),"The Lord of the Rings: The Fellowship of the Ring", Genre.ADVENTURE, Date(2001,12,19)))

    }

    return list
}