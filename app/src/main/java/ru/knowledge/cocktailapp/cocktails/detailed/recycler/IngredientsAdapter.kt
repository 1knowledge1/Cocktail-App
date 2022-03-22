package ru.knowledge.cocktailapp.cocktails.detailed.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.knowledge.cocktailapp.R
import ru.knowledge.cocktailapp.cocktails.database.dto.IngredientDto
import ru.knowledge.cocktailapp.databinding.ItemIngredientBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsViewHolder>() {

    private var ingredientsList: MutableList<IngredientDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        val binding = ItemIngredientBinding.bind(view)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(ingredientsList[position])
    }

    override fun getItemCount() = ingredientsList.size

    fun setIngredients(actors: List<IngredientDto>) {
        ingredientsList.clear()
        ingredientsList.addAll(actors)
        notifyDataSetChanged()
    }
}