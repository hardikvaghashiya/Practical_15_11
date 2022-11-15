package com.practicaltest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.practicaltest.adapter.DocumentAdapter
import com.practicaltest.databinding.FragmentHomeBinding
import com.practicaltest.factory.MyViewModelFactory
import com.practicaltest.listner.RecyclerRowClick
import com.practicaltest.repository.MainRepository
import com.practicaltest.retrofit.RetrofitService
import com.practicaltest.viewmodel.MainViewModel

class HomeFragment : Fragment(), RecyclerRowClick {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: DocumentAdapter

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val TAG = "HomeFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        adapter = DocumentAdapter(this)
        binding.adapter = adapter
        binding.btnHomeFragmentSearch.setOnClickListener {
            val navDirections = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            Navigation.findNavController(binding.btnHomeFragmentSearch).navigate(navDirections)
        }

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: ${it.content.size}")
            adapter.setMovieList(it.content)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.getAllDocuments()
    }

    override fun rowClick(pos: Int) {

    }
}