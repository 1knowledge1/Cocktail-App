package ru.knowledge.cocktailapp.cocktails.cocktails

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import ru.knowledge.cocktailapp.App
import ru.knowledge.cocktailapp.R
import ru.knowledge.cocktailapp.cocktails.CocktailViewModelFactory
import ru.knowledge.cocktailapp.cocktails.StartFragmentDetailsListener
import ru.knowledge.cocktailapp.cocktails.cocktails.recycler.CocktailsAdapter
import ru.knowledge.cocktailapp.cocktails.cocktails.recycler.ItemOffsetDecoration
import ru.knowledge.cocktailapp.databinding.FragmentCocktailsBinding

class CocktailsFragment : Fragment() {

    private var startFragmentDetailsListener: StartFragmentDetailsListener? = null
    private lateinit var binding: FragmentCocktailsBinding
    private val cocktailViewModel: CocktailsViewModel by viewModels {
        CocktailViewModelFactory((activity?.application as App).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StartFragmentDetailsListener) {
            startFragmentDetailsListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsBinding.bind(view)
        val cocktailsAdapter = CocktailsAdapter(startFragmentDetailsListener)
        binding.recyclerCoctails.apply {
            adapter = cocktailsAdapter
            layoutManager = GridLayoutManager(context, COLUMNS_NUMBER)
            addItemDecoration(ItemOffsetDecoration(
                COLUMNS_NUMBER,
                context?.resources?.getDimensionPixelSize(R.dimen.dimen_150)
                    ?: toDp(DEFAULT_ITEM_SIZE)))
        }

        cocktailViewModel.cocktailList.observe(viewLifecycleOwner) { cocktails ->
            if (cocktails != null && cocktails.isNotEmpty()) {
                binding.pbFragmentCocktails.visibility = View.INVISIBLE
            }
            cocktailsAdapter.setCocktails(cocktails)
            binding.swipeContainerCocktails.isRefreshing = false
        }

        cocktailViewModel.errorState.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast
                    .makeText(view.context, "Не удалось обновить: $message", Toast.LENGTH_SHORT)
                    .show()
                binding.pbFragmentCocktails.visibility = View.INVISIBLE
                binding.swipeContainerCocktails.isRefreshing = false
                cocktailViewModel.resetError()
            }
        }

        binding.swipeContainerCocktails.setOnRefreshListener {
            cocktailViewModel.getRefreshCocktails()
        }

        cocktailViewModel.getCocktails()
    }

    override fun onDetach() {
        super.onDetach()
        startFragmentDetailsListener = null
    }

    companion object {
        const val COLUMNS_NUMBER = 2
        const val DEFAULT_ITEM_SIZE = 150

        fun toDp(value: Int) = (value * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }
}