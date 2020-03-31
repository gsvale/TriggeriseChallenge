package com.example.triggerisechallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.adapters.CardsAdapter
import com.example.triggerisechallenge.databinding.ActivityCardsBinding
import com.example.triggerisechallenge.models.Set
import com.example.triggerisechallenge.utils.Constants.CARDS_TAG
import com.example.triggerisechallenge.viewModels.CardsViewModel
import java.util.*

class CardsActivity : AppCompatActivity(), Observer {

    private lateinit var mBinding: ActivityCardsBinding

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_cards
        )
        // Init RecyclerView setup
        setupRecyclerView(mBinding.cardsRv)

        // Get values from intent
        val set : Set = intent.getParcelableExtra(Set::TAG.name)
        val cardsTag = intent.getStringExtra(CARDS_TAG)

        // Set ViewModel
        cardsViewModel = CardsViewModel(applicationContext, set, cardsTag)
        mBinding.cardsViewModel = cardsViewModel
        cardsViewModel.addObserver(this)


        title = getString(R.string.list_of_cards)

    }

    private fun setupRecyclerView(cardsRv: RecyclerView) {

        // Init RecyclerView and its adapter
        val cardsAdapter = CardsAdapter(applicationContext, ArrayList())
        cardsRv.adapter = cardsAdapter
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        cardsRv.layoutManager = linearLayoutManager

    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is CardsViewModel) {

            // Update RecyclerView with new data
            val cardsAdapter: CardsAdapter =
                mBinding.cardsRv.adapter as CardsAdapter
            val cardsViewModel: CardsViewModel = o
            cardsAdapter.addItems(cardsViewModel.getAllCards())
        }
    }
}
