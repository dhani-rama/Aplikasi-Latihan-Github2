package id.research.githubuser2.ui.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.research.githubuser2.R
import id.research.githubuser2.databinding.ActivityMainBinding
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.ui.adapter.MainAdapter
import id.research.githubuser2.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mMainBinding: ActivityMainBinding
    private lateinit var mMainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mMainBinding.root)

        setSupportActionBar(mMainBinding.tbMain)

        supportActionBar?.title = "Github APP"
        mMainBinding.tbMain.setTitleTextColor(resources.getColor(R.color.white))
        mMainAdapter = MainAdapter()

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.mSetUser.observe(this, { mSetUser ->
            mMainAdapter.setListUser(mSetUser as ArrayList<ItemsItem>?)
            with(mMainBinding.rvList){
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = mMainAdapter
            }
        })

        mainViewModel.isLoading.observe(this,{
            showLoading(it)
        })

    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            mMainBinding.progressBar.visibility = View.VISIBLE
        }
        else{
            mMainBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.menu_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.searchUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

}