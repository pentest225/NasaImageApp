package ci.orange.nasaimageapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ci.orange.nasaimageapp.data.Asteroid
import ci.orange.nasaimageapp.databinding.ItemAsteroidBinding


class AsteroidAdapter(private val dataList:List<Asteroid>,val onSelect:(data:Asteroid)->Unit):RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {

    inner class AsteroidViewHolder(val binding: ItemAsteroidBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val binding = ItemAsteroidBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val selectedItem = dataList[position]
        holder.binding.clItemAsteroid.setOnClickListener {
            onSelect(selectedItem)
        }
        holder.binding.asteroid = selectedItem
    }

    override fun getItemCount() = dataList.count()
}