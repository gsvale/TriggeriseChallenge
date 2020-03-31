package com.example.triggerisechallenge.viewModels

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.triggerisechallenge.AppController
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.models.Card
import com.example.triggerisechallenge.models.CardsResponse
import com.example.triggerisechallenge.models.Set
import com.example.triggerisechallenge.network.NetworkInterface
import com.example.triggerisechallenge.utils.Constants.BOOSTER_PATH
import com.example.triggerisechallenge.utils.Constants.CARDS_PATH
import com.example.triggerisechallenge.utils.Constants.SETS_PATH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

class CardsViewModel (private var context: Context?, private val set : Set , private val tag : String) : Observable() {

    var progressBarVisibility: ObservableInt? = null

    var messageVisibility: ObservableInt? = null
    var message: ObservableField<String>? = null

    var recyclerViewVisibility: ObservableInt? = null

    private lateinit var mCards: ArrayList<Card>

    private val compositeDisposable: CompositeDisposable? = CompositeDisposable()

    // Init ViewModel
    init {
        progressBarVisibility = ObservableInt(View.GONE)
        messageVisibility = ObservableInt(View.GONE)
        recyclerViewVisibility = ObservableInt(View.GONE)
        message = ObservableField(context!!.getString(R.string.error))

        // Init views and get cards
        initializeViews()
        getCards()
    }

    // Method to init views
    private fun initializeViews() {
        messageVisibility!!.set(View.GONE)
        recyclerViewVisibility!!.set(View.GONE)
    }

    // Call Retrofit API to get all cards
    private fun getCards() {

        mCards = ArrayList()

        progressBarVisibility!!.set(View.VISIBLE)

        val appController: AppController = AppController.create(context)
        val networkInterface: NetworkInterface? = appController.getNetworkInterface()

        if (networkInterface != null) {

            if(tag == context!!.getString(R.string.cards)) {

                val disposable: Disposable =
                    networkInterface.getCardsFromSet(
                        CARDS_PATH,
                        set.code!!
                    )
                        .subscribeOn(appController.subscribeScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                            // Call to update list of sets items
                            updateRecyclerView(it)
                            progressBarVisibility!!.set(View.GONE)
                            messageVisibility!!.set(View.GONE)
                            recyclerViewVisibility!!.set(View.VISIBLE)

                        }, {

                            // In case of error display error message
                            message!!.set(context!!.getString(R.string.error))
                            messageVisibility!!.set(View.VISIBLE)
                            recyclerViewVisibility!!.set(View.GONE)
                            progressBarVisibility!!.set(View.GONE)

                        })

                compositeDisposable!!.add(disposable)

            }
            else{

                val disposable: Disposable =
                    networkInterface.getCardsFromBooster(
                        SETS_PATH,
                        set.code!!,
                        BOOSTER_PATH
                    )
                        .subscribeOn(appController.subscribeScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                            // Call to update list of sets items
                            updateRecyclerView(it)
                            progressBarVisibility!!.set(View.GONE)
                            messageVisibility!!.set(View.GONE)
                            recyclerViewVisibility!!.set(View.VISIBLE)

                        }, {

                            // In case of error display error message
                            message!!.set(context!!.getString(R.string.error))
                            messageVisibility!!.set(View.VISIBLE)
                            recyclerViewVisibility!!.set(View.GONE)
                            progressBarVisibility!!.set(View.GONE)

                        })

                compositeDisposable!!.add(disposable)

            }

        }

    }

    // Method to get Cards
    fun getAllCards(): List<Card> {
        return mCards
    }

    // Method to update RecyclerView with new data, notifying observers
    private fun updateRecyclerView(cardsResponse: CardsResponse?) {
        mCards.clear()
        mCards.addAll(cardsResponse!!.getCards() as ArrayList)
        setChanged()
        notifyObservers()
    }


}