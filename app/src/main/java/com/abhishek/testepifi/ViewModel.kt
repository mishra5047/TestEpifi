package com.abhishek.testepifi

import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.dmoral.toasty.Toasty

class ViewModel : ViewModel() {

    var panNumber : MutableLiveData<String> = MutableLiveData<String>()
    var birthDay : MutableLiveData<String> = MutableLiveData<String>()
    var birthMonth : MutableLiveData<String> = MutableLiveData<String>()
    var birthYear : MutableLiveData<String> = MutableLiveData<String>()
    var userMutableData = MutableLiveData<User>()

    fun getUser() : MutableLiveData<User>{
        return userMutableData
    }

    fun onNextClick(view : View){

        //Toast.makeText(view.context, "UserName " + panNumber.value + " Pan " + birthDay.value, Toast.LENGTH_SHORT).show()
            //Toasty.info(view.context, birthDate).show();

        val birthDate = birthDay.value.toString() + "/" + birthMonth.value.toString() + "/" +  birthYear.value.toString();
        val user = User(panNumber.value.toString(), birthDate)
            userMutableData.value = user
    }

    fun exitAppCode(){
        Handler().postDelayed({
            android.os.Process.killProcess(android.os.Process.myPid())
        }, 1000)
    }

    fun onNoPanClick(view : View){
        Toasty.info(view.context, "Closing the application").show()
        exitAppCode();
    }

}