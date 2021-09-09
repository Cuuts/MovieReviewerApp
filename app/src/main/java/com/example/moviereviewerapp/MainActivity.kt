package com.example.moviereviewerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moviereviewerapp.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    /**
     * Instantiate binding and test data
     */
    private lateinit var binding: ActivityMainBinding
    val tsg = TestDataGenerator()
    var testData: ArrayList<Movie> = tsg.testData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * If there are any reviews, new or old, they are fetched here
         */
        val item = intent.getParcelableArrayListExtra<Movie>("EXTRA_MOVIE_LIST_UPDATE")

        /**
         * Here we init a new array with testData
         * testData holds the: UUID, Title, Genre, Release date and an empty Review list for every movie in the given testdata
         * arrayList is used for sending the updated review list of each movie, but this way it is never null
         */
        val arrayList = testData

        /**
         * This for loop iterates over every movie in testData
         * On every loop a radio button is created with a unique id, and its text being that of the current movie in testData
         * Then the current radio button is added to the radio group view
         *
         * If the list with updated movie data isn't empty (null) we first sanity check by checking that the updated list isn't out of order
         * Then we update arrayList at the proper index with the same index in the list of all reviews
         */
        for ((count, i) in testData.withIndex()) {
            val rb = RadioButton(this)
            rb.text = i.title
            rb.id = count

            binding.rgMovies.addView(rb)

            if (item != null) {
                if (i.title == item[count].title) {
                    arrayList[count] = item[count]
                }
            }
        }

        /**
         * OnClickListener on "VIEW REVIEW" button
         * If no movie is selected, a messasge appears
         * Else we switch to ReviewActivity carrying the data of the movie we selected, and arrayList which should now hold all reviews
         */
        val rb = binding.reviewButton
        rb.setOnClickListener {
            if (binding.rgMovies.checkedRadioButtonId == -1) {
                Toast.makeText(this@MainActivity, "Please select a movie", Toast.LENGTH_SHORT)
                    .show()
            } else {
                switchActivity(arrayList[binding.rgMovies.checkedRadioButtonId], arrayList)
            }
        }

    }

    /**
     * This is a helping function which creates an intent with an extra movie and an extra ArrayList of movies
     */
    private fun switchActivity(movie: Movie, movieDataInput: ArrayList<Movie>) {
        val intent = Intent(this, ReviewActivity::class.java).apply {
            putExtra("EXTRA_CHOSEN_MOVIE", movie)
            putParcelableArrayListExtra("EXTRA_MOVIE_DATA", movieDataInput)
        }
        startActivity(intent)
    }

}