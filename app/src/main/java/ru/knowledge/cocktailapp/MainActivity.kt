package ru.knowledge.cocktailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.knowledge.cocktailapp.cocktails.StartFragmentDetailsListener
import ru.knowledge.cocktailapp.cocktails.detailed.CocktailsDetailedFragment

class MainActivity : AppCompatActivity(), StartFragmentDetailsListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.main_fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onStartFragmentDetails(cocktailId: Long) {
        val args = bundleOf(CocktailsDetailedFragment.COCKTAIL_ID to cocktailId)
        navController.navigate(R.id.action_cocktailsFragment_to_cocktailsDetailedFragment, args)
    }
}