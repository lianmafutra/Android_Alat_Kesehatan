package com.app.kesehatan

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kesehatan.adapter.AdapterListAlatKesehatan
import com.app.kesehatan.databinding.ActivityAlatKesehatanListBinding
import com.app.kesehatan.model.ModelAlatKesehatanItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

class AlatKesehatanListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlatKesehatanListBinding

    lateinit var adapaterAlatKesehatan: AdapterListAlatKesehatan

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

        getDataAlatKesehatan(this);
        adapaterAlatKesehatan.notifyDataSetChanged()
    }

    private fun initToolbar(title : String) {
        val actionBar = supportActionBar
        actionBar!!.title = title
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
        val filteredlist: ArrayList<ModelAlatKesehatanItem> = ArrayList()
        for (item in getDataAlatKesehatan(this)) {
            if (item.nama!!.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            print("Data Tidak Ditemukan")
        } else {
            adapaterAlatKesehatan.filterList(filteredlist)
        }
    }

    private fun getDataAlatKesehatan(context: Context): List<ModelAlatKesehatanItem> {
        lateinit var jsonString: String
        try {

            lateinit var type: String
            if(intent.extras != null)
            {
                val bundle = intent.extras

                when (bundle!!.getString(HomeActivity.Data.TYPE)) {
                    "alat" -> {
                        type = "data_alat.json"
                        initToolbar("List Alat Kehamilan")
                    }
                    "bahan" -> {
                        type = "data_bahan.json"
                        initToolbar("List Bahan Kehamilan")
                    }
                    else -> println("tidak ada data")
                }

            }

            jsonString =  context.assets.open(type)
                    .bufferedReader()
                    .use {
                        it.readText()
                    }

        } catch (t: Throwable) {
            Toast.makeText(this, "error : $t", Toast.LENGTH_SHORT).show()
        }
        val listData = object : TypeToken<List<ModelAlatKesehatanItem>>() {}.type
        return Gson().fromJson(jsonString, listData)
    }

}