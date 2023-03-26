package ci.orange.nasaimageapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ci.orange.nasaimageapp.databinding.ItemAsteroidBinding
import ci.orange.nasaimageapp.domain.model.Asteroid

class AsteroidListAdapter(val onSelect:(data:Asteroid)->Unit): ListAdapter<Asteroid,AsteroidListAdapter.AsteroidViewHolder>(DiffCallback) {
    inner class AsteroidViewHolder(val binding: ItemAsteroidBinding): RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Asteroid>() {
            override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val binding = ItemAsteroidBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val selectedItem = getItem(position)
        holder.binding.clItemAsteroid.setOnClickListener {
            onSelect(selectedItem)
        }
        holder.binding.asteroid = selectedItem
    }
}