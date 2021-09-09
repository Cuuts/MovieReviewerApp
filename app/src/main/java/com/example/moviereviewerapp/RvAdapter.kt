package com.example.moviereviewerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewerapp.databinding.SingleReviewBinding

class RvAdapter(
    private var reviewList: MutableList<Review>
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    class ViewHolder (val binding: SingleReviewBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SingleReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun addReview (review: Review){
        reviewList.add(review)
        notifyItemInserted(reviewList.size-1)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(reviewList[position]) {
                binding.tvUserReviewText.text = this.message
                binding.tvUsername.text = this.user
                binding.ratingBar.rating = this.rating
            }
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

}