package com.example.bookhub.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionManager{
    fun checkConnectivity(context: Context):Boolean{

        // first check that app is connected to hotspot,wifi or mobile data
        // device provides the network info at some context

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // this line will check all the connections that are possible like wifi , mobile data , etc. and tell us which one is in active state
        val activeNetwork:NetworkInfo? = connectivityManager.activeNetworkInfo

        // it will return three values :
        // null -> if connection is broken , true -> if network is connected to internet , false -> if network does not have internet
        if(activeNetwork?.isConnected!=null){
            return activeNetwork.isConnected
        }else{
            return false
        }
    }
}