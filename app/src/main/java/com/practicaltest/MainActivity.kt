package com.practicaltest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.practicaltest.activity.DetailActivity
import com.practicaltest.adapter.DocumentAdapter
import com.practicaltest.databinding.ActivityMainBinding
import com.practicaltest.factory.MyViewModelFactory
import com.practicaltest.listner.RecyclerRowClick
import com.practicaltest.repository.MainRepository
import com.practicaltest.retrofit.RetrofitService
import com.practicaltest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), RecyclerRowClick {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    lateinit var adapter: DocumentAdapter

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeView()
        /*navController = Navigation.findNavController(this, R.id.fragment1)
        NavigationUI.setupActionBarWithNavController(this, navController)*/

    }

    private fun initializeView() {
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        adapter = DocumentAdapter(this)
        binding.adapter = adapter
        /*binding.btnHomeFragmentSearch.setOnClickListener {
            val navDirections = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            Navigation.findNavController(binding.btnHomeFragmentSearch).navigate(navDirections)
        }*/

        viewModel.movieList.observe(this) {
            Log.d(TAG, "onCreate: ${it.content.size}")
            adapter.setMovieList(it.content)
            binding.cardProgress.visibility = View.GONE
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.cardProgress.visibility = View.VISIBLE
        viewModel.getAllDocuments()
    }

    override fun rowClick(pos: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("model", adapter.getDocumentFromPosition(pos))
        startActivity(intent)
    }
}