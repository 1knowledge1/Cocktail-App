package ru.knowledge.cocktailapp.cocktails.cocktails.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.knowledge.cocktailapp.cocktails.StartFragmentDetailsListener
import ru.knowledge.cocktailapp.cocktails.database.dto.CocktailDto
import ru.knowledge.cocktailapp.databinding.ItemCocktailBinding

class CocktailsViewHolder(
    private val binding: ItemCocktailBinding,
    private val startFragmentDetailsListener: StartFragmentDetailsListener?)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(cocktail: CocktailDto) {
        binding.tvItemCocktailTitle.text = cocktail.name
        binding.ivItemCocktail.load(cocktail.imageUrl)
        itemView.setOnClickListener {
            startFragmentDetailsListener?.onStartFragmentDetails(cocktail.id)
        }
    }
}