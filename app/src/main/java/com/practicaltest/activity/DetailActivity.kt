package com.practicaltest.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practicaltest.R
import com.practicaltest.databinding.ActivityDetailBinding
import com.practicaltest.model.DocContent


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var model: DocContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        getIntentData()
        initializeView()
    }

    private fun getIntentData() {
        if (intent != null) {
            intent.let {
                model = intent.getParcelableExtra("model")!!
            }
        }
    }

    private fun initializeView() {
        binding.model = model
        binding.cardDocType.setOnClickListener {
            openPDFInBrowser(model.mediaUrl)
        }
    }

    private fun openPDFInBrowser(uri: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }
}