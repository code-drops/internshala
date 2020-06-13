package com.example.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookhub.R
import com.example.bookhub.fragment.AboutFragment
import com.example.bookhub.fragment.DashboardFragment
import com.example.bookhub.fragment.FavouritesFragment
import com.example.bookhub.fragment.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout:CoordinatorLayout
    lateinit var frameLayout:FrameLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView:NavigationView

    var previousMenuItem: MenuItem? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        frameLayout = findViewById(R.id.frame)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)

        setUpToolbar()

        // to open the dashboard when the app opens
        supportFragmentManager.beginTransaction().replace(
            R.id.frame,
            DashboardFragment()
        ).addToBackStack("Dashboard").commit()
        supportActionBar?.title ="Dashboard"
        // to check the dashboard menu item when the app is opened
        navigationView.setCheckedItem(R.id.dashboard)


        // created a toggle at the action bar and add listener to hamburger icon
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // adding click listeners to navigation drawer
        navigationView.setNavigationItemSelectedListener {

            // check the current item and uncheck previous item
            if(previousMenuItem!=null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            // it refers to the current menu item that is clicked
            when(it.itemId){
                R.id.dashboard -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        DashboardFragment()
                    ).commit()
                    supportActionBar?.title ="Dashboard"
                    drawerLayout.closeDrawers()
                }
                R.id.favourites -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        FavouritesFragment()
                    ).commit()
                    supportActionBar?.title ="Favourites"
                    drawerLayout.closeDrawers()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        ProfileFragment()
                    ).commit()
                    supportActionBar?.title ="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.about -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        AboutFragment()
                    ).commit()
                    supportActionBar?.title ="About"
                    drawerLayout.closeDrawers()
                }
            }

            return@setNavigationItemSelectedListener true
        }

    }

    fun setUpToolbar(){
        // setting up toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Book Hub"

        // enabling the toggle button
        supportActionBar?.setHomeButtonEnabled(true)

        // displaying the hamburger icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // onclick functionality of toggle button as we put the icon on toolbar not on the default action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id==android.R.id.home){
            // if toggle icon is clicked
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    // handles functionality of back button
    override fun onBackPressed() {
        // if the current fragment is not fragment, back button will take it to dashboard fragment otherwise it will exit the app
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is DashboardFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    DashboardFragment()
                ).addToBackStack("Dashboard").commit()
                supportActionBar?.title ="Dashboard"
            }
            else -> super.onBackPressed()
        }
    }
}