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

    private lateinit var binding: ActivityReviewBinding
    private lateinit var rvAdapter: RvAdapter
    val tsg = TestDataGenerator()
    var testData: ArrayList<Movie> = tsg.testData
    private lateinit var updateList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<Movie>("EXTRA_CHOSEN_MOVIE")
        val item2 = intent.getParcelableArrayListExtra<Movie>("EXTRA_MOVIE_DATA")

        if (item != null) {
            binding.tvFilmTitle.text = item.title
            rvAdapter = RvAdapter(item.reviewList)
        }
        binding.rvUserReviews.adapter = rvAdapter
        binding.rvUserReviews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val arrayList = testData
        for ((count, i) in arrayList.withIndex()) {
            i.reviewList.clear()
            item2?.get(count)?.reviewList?.let { i.reviewList.addAll(it) }
        }

        val btnBack = binding.backButton
        btnBack.setOnClickListener {
            switchActivity(arrayList/*, item2*/)
        }

        val btnSubmit = binding.btnSubmit
        btnSubmit.setOnClickListener {
            val reviewText = binding.etmReviewInput.text.toString()
            var reviewUser = binding.etUsernameInput.text.toString()
            val reviewRating = binding.ratingBarInput.rating
            if (reviewText.isEmpty()){

                val toast = Toast.makeText(this@ReviewActivity,"Please write a review before submitting", Toast.LENGTH_LONG).show()

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

    private fun switchActivity(/*movie: Movie,*/ movieList: ArrayList<Movie>) {
        val intent = Intent(this, MainActivity::class.java).putExtra("EXTRA_MOVIE_LIST_UPDATE", movieList)
        //intent.putExtra("EXTRA_MOVIE_LIST2", movieList)
        startActivity(intent)
    }

    private fun helper(arrayList: ArrayList<Movie>) {

    }

}