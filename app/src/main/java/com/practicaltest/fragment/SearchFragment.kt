package com.practicaltest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.MultiAutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.practicaltest.R

class SearchFragment : Fragment() {

    lateinit var btn_search: Button
    lateinit var autoTextView: MultiAutoCompleteTextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        Cast(view)

        return view
    }

    private fun Cast(view: View) {

        autoTextView = view.findViewById(R.id.multiAutoCompleteTextView_searchFragment_Search)
        btn_search = view.findViewById(R.id.btn_searchFragment_Search)


        val dataSearch = listOf(
                "ali",
                "reza",
                "mvvm",
                "nav",
                "android",
                "alireza",
                "mmd",
                "mvp",
                "kotlin",
                "java",
                "python",
                "telegram",
                "api",
                "android 10",
                "android 11",
                "android studio"
        )

        autoTextView.setAdapter(ArrayAdapter(view.context,android.R.layout.simple_list_item_1,dataSearch))
        autoTextView.threshold = 1
        autoTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search.setOnClickListener {

            val data = Bundle()
            data.putString("data", autoTextView.text.toString())
            Navigation.findNavController(btn_search).navigate(R.id.action_searchFragment_to_responsFragment,data)

        }
    }

}