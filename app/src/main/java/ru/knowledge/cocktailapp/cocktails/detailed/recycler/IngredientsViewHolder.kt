package ru.knowledge.cocktailapp.cocktails.detailed.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.knowledge.cocktailapp.cocktails.database.dto.IngredientDto
import ru.knowledge.cocktailapp.databinding.ItemIngredientBinding

class IngredientsViewHolder(private val binding: ItemIngredientBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(ingredient: IngredientDto) {
        binding.ivItemIngredient.load(ingredient.imageUrl)
        binding.tvItemIngredient.text = ingredient.name
        binding.tvItemIngredientMeasure.text = ingredient.measure
    }
}