package com.myrealpush.sample.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.myrealpush.sdk.MyRealPush


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val pushToken: LiveData<String> = Transformations.map(MyRealPush.pushToken) {
        it
    }

}