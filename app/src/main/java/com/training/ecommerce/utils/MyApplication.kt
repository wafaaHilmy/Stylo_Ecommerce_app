package com.training.ecommerce.utils

import android.app.Application

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()


    }
companion object{
  private val TAG = MyApplication::class.simpleName
}
}