package com.example.multilanguagesupport


import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var ENGLISH = "en"
    var HINDI = "hi"
    var SPANISH = "es"
    var currentLanguage = "en"
    var currentLang: String = "language"
    var myLocale: Locale? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);

        currentLanguage = intent!!.getStringExtra(currentLang).toString()

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
            else -> {
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.local_english -> {
                //setLocale(this, ENGLISH)
                setLocales(ENGLISH)
                return true
            }
            R.id.local_hindi -> {
                //setLocale(this, HINDI)
                setLocales(HINDI)
                return true
            }
            R.id.local_spanish -> {
                //setLocale(this, SPANISH)
                setLocales(SPANISH)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setNewLocale(
        mContext: AppCompatActivity,
        @LocationManager.Companion.LocaleDef language: String
    ) {
        LocationManager.setNewLocale(this, language)
        val intent = mContext.intent
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun setLocale(mContext: AppCompatActivity, language: String) {
        LocaleHelper.setLocale(mContext, language)
        val intent = mContext.intent
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    fun setLocales(localeName: String) {
        if(!localeName.equals(currentLanguage))
        {
            myLocale = Locale(localeName)
            val res: Resources = resources
            val dm: DisplayMetrics = res.getDisplayMetrics()
            val conf: Configuration = res.getConfiguration()
            conf.locale = myLocale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(this, MainActivity::class.java)
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
         else
        {
            Toast.makeText(this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
}