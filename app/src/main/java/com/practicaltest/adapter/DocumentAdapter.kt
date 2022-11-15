package com.practicaltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicaltest.databinding.RowDocumentListBinding
import com.practicaltest.listner.RecyclerRowClick
import com.practicaltest.model.DocContent

class DocumentAdapter(private val recyclerRowClick: RecyclerRowClick) : RecyclerView.Adapter<MainViewHolder>() {

    var documents = mutableListOf<DocContent>()

    fun setMovieList(movies: List<DocContent>) {
        this.documents = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun getDocumentFromPosition(pos: Int): DocContent {
        return documents[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = RowDocumentListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(documents[position])
        holder.itemView.setOnClickListener {
            recyclerRowClick.rowClick(position)
        }
    }

    override fun getItemCount(): Int {
        return documents.size
    }
}

class MainViewHolder(val binding: RowDocumentListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: DocContent) {
        binding.model = data
        binding.executePendingBindings()
    }
}