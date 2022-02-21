package id.namikaze.moviescatalog.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.namikaze.moviescatalog.databinding.ItemGenreBinding
import id.namikaze.moviescatalog.domain.model.Genre

class GenreAdapter(val onItemClick: ((String, String) -> Unit)? = null) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var listData = ArrayList<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre)= with(itemView) {
            binding.apply{
                tvGenreInisialItem.text = genre.name
                tvGenreNameItem.text = genre.name
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(genre.id.toString(), genre.name.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Genre>?) {
        if (newListData != null)  {
            listData.clear()
            listData.addAll(newListData)
            notifyDataSetChanged()
        }
    }
}