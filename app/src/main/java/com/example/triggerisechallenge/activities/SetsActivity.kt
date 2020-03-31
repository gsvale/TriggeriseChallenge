package com.example.triggerisechallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.adapters.SetsAdapter
import com.example.triggerisechallenge.databinding.ActivityMainBinding
import com.example.triggerisechallenge.viewModels.SetsViewModel
import java.util.*

class SetsActivity : AppCompatActivity(), Observer {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var setsViewModel: SetsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        // Init RecyclerView setup
        setupRecyclerView(mBinding.setsRv)

        // Set ViewModel
        setsViewModel = SetsViewModel(applicationContext)
        mBinding.setsViewModel = setsViewModel
        setsViewModel.addObserver(this)

        title = getString(R.string.list_of_sets)

    }

    private fun setupRecyclerView(setsRv: RecyclerView) {

        // Init RecyclerView and its adapter
        val setsAdapter = SetsAdapter(applicationContext, ArrayList())
        setsRv.adapter = setsAdapter
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        setsRv.layoutManager = linearLayoutManager

    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is SetsViewModel) {

            // Update RecyclerView with new data
            val setsAdapter: SetsAdapter =
                mBinding.setsRv.adapter as SetsAdapter
            val setsViewModel: SetsViewModel = o
            setsAdapter.addItems(setsViewModel.getAllSets())
        }
    }
}
