package com.example.triggerisechallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.databinding.ActivityDetailsBinding
import com.example.triggerisechallenge.models.Card
import com.example.triggerisechallenge.viewModels.CardDetailViewModel
import java.util.*

class DetailsActivity : AppCompatActivity(), Observer {

    private lateinit var mBinding: ActivityDetailsBinding

    private lateinit var cardDetailViewModel: CardDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_details
        )

        // Get values from intent
        val card: Card = intent.getParcelableExtra(Card::TAG.name)

        // Set ViewModel
        cardDetailViewModel = CardDetailViewModel(applicationContext, card)
        mBinding.cardDetailsViewModel = cardDetailViewModel
        cardDetailViewModel.addObserver(this)

        title = card.name

    }

    override fun update(o: Observable?, arg: Any?) {

    }
}
