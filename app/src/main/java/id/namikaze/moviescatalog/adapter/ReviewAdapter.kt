package id.namikaze.moviescatalog.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.R
import id.namikaze.moviescatalog.databinding.ItemReviewMovieBinding
import id.namikaze.moviescatalog.domain.model.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private var listData = ArrayList<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(private val binding: ItemReviewMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Review)= with(itemView) {
            binding.apply{
                Glide.with(context).load(BuildConfig.IMAGE_URL+data.avatarPath).placeholder(R.drawable.ic_default_cover_movie).into(ivAuthorMovieDetailItem)
                tvNameAuthorMovieDetailItem.text = data.author
                tvContentAuthorMovieDetailItem.text = data.content
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Review>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
}