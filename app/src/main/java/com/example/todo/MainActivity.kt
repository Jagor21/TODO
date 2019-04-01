package com.example.todo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import layout.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            navDrawerLayout.closeDrawers()

            when(menuItem.itemId){

                R.id.mainFragment -> {
                    val mainFragment: Fragment = MainFragment()
                    replaceFragmentSafely(mainFragment, "MainFragment", container.id)
                    Toast.makeText(this, "main fragment touched", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.item2 -> {
                    Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show()
                    true
                }
            }

            true
        }

        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                navDrawerLayout.openDrawer(GravityCompat.START)
                Toast.makeText(this, "home button touched", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    tag: String,
    allowsStateLoss: Boolean = false,
    containerViewId: Int,
    enterAnimation: Int = 0,
    exitAnimation: Int = 0,
    popEnterAnimation: Int = 0,
    popExitAnimation: Int = 0
) {
    val ft = supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        .replace(containerViewId, fragment, tag)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowsStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    tag: String,
    containerViewId: Int
) {
    val ft = supportFragmentManager
        .beginTransaction()
        .replace(containerViewId, fragment, tag)
    ft.commit()
}