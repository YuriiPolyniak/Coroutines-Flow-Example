package com.flowpreviewapplication.ui.pokemon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.ItemPokemonBinding
import com.flowpreviewapplication.domain.model.Pokemon

class PokemonsListAdapter(
    private var items: List<Pokemon> = emptyList(),
    private val listener: Listener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE -> ItemViewHolder(
                ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("ViewHolder not supported")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ITEM_VIEW_TYPE
    }

    override fun getItemId(position: Int): Long {
        return items[position].id ?: position.toLong()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE -> {
                val viewHolder = holder as ItemViewHolder
                viewHolder.bind(items[position], listener)
            }
            else -> throw IllegalArgumentException("ViewHolder not supported")
        }
    }

    fun updateItems(items: List<Pokemon>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon, listener: Listener?) = with(binding) {
            itemPokemonName.text = item.name
            itemPokemonLevel.text = root.context.getString(R.string.pokemon_level, item.level)
            itemPokemonSpecies.text = item.species?.speciesName ?: ""
            itemPokemonImage.load(item.species?.imageUrl)
            root.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }

    }

    interface Listener {

        fun onItemClicked(item: Pokemon)

    }

    companion object {
        private const val ITEM_VIEW_TYPE = 1
    }

}