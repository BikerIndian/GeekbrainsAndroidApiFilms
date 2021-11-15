package net.svichch.geekbrains.films

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import net.svichch.geekbrains.films.placeholder.PlaceholderContent.PlaceholderItem
import net.svichch.geekbrains.films.databinding.FragmentItemBinding

class FilmsRecyclerAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<FilmsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val contentView: TextView = binding.title

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}