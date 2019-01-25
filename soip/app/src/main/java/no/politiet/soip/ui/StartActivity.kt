package no.politiet.soip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import no.politiet.soip.R
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class StartActivity : AppCompatActivity() {

    //1
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //2
        navigationController = findNavController(R.id.navigationHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navigationController)
    }

    //3
    override fun onSupportNavigateUp() = navigationController.navigateUp()
}
