package com.example.moviereviewerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewerapp.databinding.ActivityReviewBinding
import java.util.*
import kotlin.collections.ArrayList

class ReviewActivity : AppCompatActivity() {

    /**
     * Instantiate binding, Adapter and test data
     */
    private lateinit var binding: ActivityReviewBinding
    private lateinit var rvAdapter: RvAdapter
    val tsg = TestDataGenerator()
    var testData: ArrayList<Movie> = tsg.testData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * The movie data of the chosen movie is collected in item
         * The list with all old or new comments is collected in item2
         */
        val item = intent.getParcelableExtra<Movie>("EXTRA_CHOSEN_MOVIE")
        val item2 = intent.getParcelableArrayListExtra<Movie>("EXTRA_MOVIE_DATA")

        /**
         * We first check i we received data on the selected movie
         * Then the text in the title banner is set
         * Then the adapter is initialized with any reviews avaiable for selected movie
         */
        if (item != null) {
            binding.tvFilmTitle.text = item.title
            rvAdapter = RvAdapter(item.reviewList)
        }
        /**
         * Here the review data is being passed to the view and displayed
         */
        binding.rvUserReviews.adapter = rvAdapter
        binding.rvUserReviews.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /**
         * arrayList is initialized with testData so that arrayList is indexable
         * Then each movies review list in arrayList is wiped of data, and replaced with the the data from the list with all reviews
         */
        val arrayList = testData
        for ((count, i) in arrayList.withIndex()) {
            i.reviewList.clear()
            item2?.get(count)?.reviewList?.let { i.reviewList.addAll(it) }
        }

        /**
         * OnClickListener on "BACK" button
         * Switches back to MainActivity carrying arraylist which holds all old or new reviews
         */
        val btnBack = binding.backButton
        btnBack.setOnClickListener {
            switchActivity(arrayList)
        }

        /**
         * OnClickListener on "SUBMIT" button
         * First grab data for all relevant fields
         * Then if there is no data for review text, display message
         * Else store review in the proper place, add to recyclerview and clear user input
         */
        val btnSubmit = binding.btnSubmit
        btnSubmit.setOnClickListener {
            val reviewText = binding.etmReviewInput.text.toString()
            var reviewUser = binding.etUsernameInput.text.toString()
            val reviewRating = binding.ratingBarInput.rating
            if (reviewText.isEmpty()) {

                val toast = Toast.makeText(
                    this@ReviewActivity,
                    "Please write a review before submitting",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                if (reviewUser == "") reviewUser = "Anonymous Reviewer"
                val reviewEntry = Review(UUID.randomUUID(), reviewText, reviewUser, reviewRating)

                for ((count, i) in testData.withIndex()) {
                    if (i.title == item?.title) arrayList[count].reviewList.add(reviewEntry)
                }

                rvAdapter.addReview(reviewEntry)
                //item?.reviewList?.add(reviewEntry)


                binding.etmReviewInput.setText("")
                binding.etUsernameInput.setText("")
                binding.ratingBarInput.rating = 0f
            }

        }

    }

    /**
     * This is a helping function which creates an intent with an extra ArrayList of movies
     */
    private fun switchActivity(/*movie: Movie,*/ movieList: ArrayList<Movie>) {
        val intent =
            Intent(this, MainActivity::class.java).putExtra("EXTRA_MOVIE_LIST_UPDATE", movieList)
        startActivity(intent)
    }
}