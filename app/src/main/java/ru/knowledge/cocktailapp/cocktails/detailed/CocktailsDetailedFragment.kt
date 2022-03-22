package ru.knowledge.cocktailapp.cocktails.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import ru.knowledge.cocktailapp.App
import ru.knowledge.cocktailapp.R
import ru.knowledge.cocktailapp.cocktails.CocktailViewModelFactory
import ru.knowledge.cocktailapp.cocktails.detailed.recycler.IngredientsAdapter
import ru.knowledge.cocktailapp.databinding.FragmentDetailedCocktailBinding

class CocktailsDetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedCocktailBinding
    private val cocktailViewModel: CocktailDetailedViewModel by viewModels {
        CocktailViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_cocktail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailedCocktailBinding.bind(view)
        val cocktailId = arguments?.getLong(COCKTAIL_ID) ?: 0
        val ingredientsAdapter = IngredientsAdapter()
        binding.rvFragmentDetailed.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
        }
        cocktailViewModel.cocktail.observe(viewLifecycleOwner) { cocktail ->
            if (cocktail != null) {
                binding.tvFragmentDetailedCocktailTitle.text = cocktail.name
                binding.ivFragmentDetailedCocktail.load(cocktail.imageUrl)
                binding.tvFragmentDetailedType.text = cocktail.category
                binding.tvFragmentDetailedTypeAlcoholic.text = cocktail.alcoholType
                binding.tvFragmentDetailedInstruction.text = cocktail.instruction
                binding.tvFragmentDetailedCocktailTitle.visibility = View.VISIBLE
                binding.tvFragmentDetailedType.visibility = View.VISIBLE
                binding.tvFragmentDetailedTypeAlcoholic.visibility = View.VISIBLE
                binding.tvFragmentDetailedInstruction.visibility = View.VISIBLE
                binding.tvFragmentDetailedIngredientTitle.visibility = View.VISIBLE
                binding.tvFragmentDetailedInstructionTitle.visibility = View.VISIBLE
                binding.ivFragmentDetailedCocktail.visibility = View.VISIBLE
                binding.cvFragmentDetailedCocktail.visibility = View.VISIBLE
                ingredientsAdapter.setIngredients(cocktail.ingredients ?: emptyList())
            }
            binding.pbFragmentCocktails.visibility = View.INVISIBLE
        }


        cocktailViewModel.errorState.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast
                    .makeText(view.context, "Не удалось обновить: $message", Toast.LENGTH_SHORT)
                    .show()
                binding.pbFragmentCocktails.visibility = View.INVISIBLE
                //swipeContainer.isRefreshing = false
                cocktailViewModel.resetError()
            }
        }

        cocktailViewModel.getCocktailById(cocktailId)
    }

    companion object {
        const val COCKTAIL_ID = "cocktailId"
    }
}