package com.example.moviereviewerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moviereviewerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val tsg = TestDataGenerator()
    var testData: ArrayList<Movie> = tsg.testData
    lateinit var movieData: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableArrayListExtra<Movie>("EXTRA_MOVIE_LIST_UPDATE")
        //val item2 = intent.getParcelableArrayListExtra<Movie>("EXTRA_MOVIE_LIST2")


        val arrayList = testData
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


        val rb = binding.reviewButton
        rb.setOnClickListener {
            if (binding.rgMovies.checkedRadioButtonId == -1){
                Toast.makeText(this@MainActivity, "Please select a movie", Toast.LENGTH_SHORT).show()
            } else {
                switchActivity(arrayList[binding.rgMovies.checkedRadioButtonId], arrayList)
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()

    }
    private fun switchActivity(movie: Movie, movieDataInput: ArrayList<Movie>) {
        val intent = Intent(this, ReviewActivity::class.java).apply {
            putExtra("EXTRA_CHOSEN_MOVIE", movie)
            putParcelableArrayListExtra("EXTRA_MOVIE_DATA", movieDataInput)
        }
        startActivity(intent)
    }
    private fun initMovieData(data: ArrayList<Movie>){
        data.clear()
        data.addAll(testData)
    }

}