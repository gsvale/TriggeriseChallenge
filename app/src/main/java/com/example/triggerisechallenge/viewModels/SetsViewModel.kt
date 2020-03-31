package com.example.triggerisechallenge.viewModels

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.triggerisechallenge.AppController
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.models.Set
import com.example.triggerisechallenge.models.SetsResponse
import com.example.triggerisechallenge.network.NetworkInterface
import com.example.triggerisechallenge.utils.Constants.SETS_PATH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*
import kotlin.collections.ArrayList

class SetsViewModel(private var context: Context?) : Observable() {

    var progressBarVisibility: ObservableInt? = null

    var messageVisibility: ObservableInt? = null
    var message: ObservableField<String>? = null

    var recyclerViewVisibility: ObservableInt? = null

    private lateinit var mSets: ArrayList<Set>

    private val compositeDisposable: CompositeDisposable? = CompositeDisposable()

    // Init ViewModel
    init {
        progressBarVisibility = ObservableInt(View.GONE)
        messageVisibility = ObservableInt(View.GONE)
        recyclerViewVisibility = ObservableInt(View.GONE)
        message = ObservableField(context!!.getString(R.string.error))

        // Init views and get sets
        initializeViews()
        getSets()
    }

    // Method to init views
    private fun initializeViews() {
        messageVisibility!!.set(View.GONE)
        recyclerViewVisibility!!.set(View.GONE)
    }

    // Call Retrofit API to get all sets
    private fun getSets() {

        mSets = ArrayList()

        progressBarVisibility!!.set(View.VISIBLE)

        val appController: AppController = AppController.create(context)
        val networkInterface: NetworkInterface? = appController.getNetworkInterface()

        if (networkInterface != null) {

            val disposable: Disposable =
                networkInterface.getSets(
                    SETS_PATH
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

    // Method to get Sets
    fun getAllSets(): List<Set> {
        return mSets
    }

    // Method to update RecyclerView with new data, notifying observers
    private fun updateRecyclerView(setsResponse: SetsResponse?) {
        mSets.clear()
        mSets.addAll(setsResponse!!.getSets() as ArrayList)
        setChanged()
        notifyObservers()
    }

}