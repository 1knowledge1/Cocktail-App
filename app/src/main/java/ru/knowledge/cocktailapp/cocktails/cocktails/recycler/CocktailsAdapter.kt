package ru.knowledge.cocktailapp.cocktails.cocktails.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.knowledge.cocktailapp.R
import ru.knowledge.cocktailapp.cocktails.StartFragmentDetailsListener
import ru.knowledge.cocktailapp.cocktails.database.dto.CocktailDto
import ru.knowledge.cocktailapp.databinding.ItemCocktailBinding

class CocktailsAdapter(private val startListener: StartFragmentDetailsListener?)
    : RecyclerView.Adapter<CocktailsViewHolder>() {

    private var cocktailsList: MutableList<CocktailDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cocktail, parent, false)
        val binding = ItemCocktailBinding.bind(view)
        return CocktailsViewHolder(binding, startListener)
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        holder.bind(cocktailsList[position])
    }

    override fun getItemCount() = cocktailsList.size

    fun setCocktails(cocktails: List<CocktailDto>) {
        cocktailsList.clear()
        cocktailsList.addAll(cocktails)
        notifyDataSetChanged()
    }
}