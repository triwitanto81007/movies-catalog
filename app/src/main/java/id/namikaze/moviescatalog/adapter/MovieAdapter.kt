package id.namikaze.moviescatalog.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.R
import id.namikaze.moviescatalog.databinding.ItemGenreBinding
import id.namikaze.moviescatalog.databinding.ItemMovieBinding
import id.namikaze.moviescatalog.domain.model.Genre
import id.namikaze.moviescatalog.domain.model.Movie

class MovieAdapter(val onItemClick: ((String) -> Unit)? = null) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listData = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie)= with(itemView) {
            binding.apply{
                Glide.with(context).load(BuildConfig.IMAGE_URL + movie.posterPath).placeholder(R.drawable.ic_default_cover_movie).into(ivCoverMovieItem)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(movie.id.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
}