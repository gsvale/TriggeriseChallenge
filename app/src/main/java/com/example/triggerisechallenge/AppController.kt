package com.example.triggerisechallenge

import android.app.Application
import android.content.Context
import com.example.triggerisechallenge.network.NetworkClient
import com.example.triggerisechallenge.network.NetworkInterface
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

// Application class with reference for Network interface and scheduler to be used in Network API calls
class AppController: Application() {

    private var mNetworkInterface: NetworkInterface? = null
    private var mScheduler: Scheduler? = null

    companion object {

        private operator fun get(context: Context): AppController {
            return context.applicationContext as AppController
        }

        fun create(context: Context?): AppController {
            return AppController[context!!]
        }
    }

    fun getNetworkInterface(): NetworkInterface? {
        if (mNetworkInterface == null) {
            mNetworkInterface = NetworkClient.create(applicationContext)
        }
        return mNetworkInterface
    }

    fun subscribeScheduler(): Scheduler? {
        if (mScheduler == null) {
            mScheduler = Schedulers.io()
        }
        return mScheduler
    }

    fun setNetworkInterface(networkInterface: NetworkInterface?) {
        mNetworkInterface = networkInterface
    }

    fun setScheduler(scheduler: Scheduler?) {
        this.mScheduler = scheduler
    }


}