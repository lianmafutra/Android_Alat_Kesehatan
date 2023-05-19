package com.app.kesehatan

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kesehatan.adapter.AdapterListAlatKesehatan
import com.app.kesehatan.adapter.RecyclerViewClickListener
import com.app.kesehatan.databinding.ActivityAlatKesehatanListBinding
import com.app.kesehatan.model.ModelAlatKesehatanItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.Locale

class AlatKesehatanListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlatKesehatanListBinding

    lateinit var adapaterAlatKesehatan: AdapterListAlatKesehatan
//    lateinit var modelAlatKesehatanItem: ArrayList<ModelAlatKesehatanItem>


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlatKesehatanListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapaterAlatKesehatan = AdapterListAlatKesehatan(getDataAlatKesehatan(this))
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.rvAlatKesehatan.layoutManager = layoutManager
        binding.rvAlatKesehatan.itemAnimator = DefaultItemAnimator()
        binding.rvAlatKesehatan.adapter = adapaterAlatKesehatan

//        modelAlatKesehatanItem = ArrayList()

        initToolbar()
        getDataAlatKesehatan(this);
        adapaterAlatKesehatan.notifyDataSetChanged()
    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        actionBar!!.title = "List Alat Kesehatan"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<ModelAlatKesehatanItem> = ArrayList()

        // running a for loop to compare elements.
        for (item in getDataAlatKesehatan(this)) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.nama!!.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapaterAlatKesehatan.filterList(filteredlist)
        }
    }

    private fun getDataAlatKesehatan(context: Context): List<ModelAlatKesehatanItem> {
        lateinit var jsonString: String
        try {
            jsonString =  context.assets.open("data.json")
                    .bufferedReader()
                    .use {
                        it.readText()
                    }


        } catch (t: Throwable) {
            Toast.makeText(this, "error : $t", Toast.LENGTH_SHORT).show()
        }
        val listCountryType = object : TypeToken<List<ModelAlatKesehatanItem>>() {}.type


        return Gson().fromJson(jsonString, listCountryType)
    }

}