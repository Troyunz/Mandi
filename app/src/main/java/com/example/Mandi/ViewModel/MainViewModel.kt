package com.example.Mandi.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.Mandi.model.OtherMandi
import com.example.Mandi.model.form
import com.example.Mandi.repository.FRepository

class MainViewModel(private val repository:FRepository):ViewModel() {

//    init {
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getFitems()
//        }
//    }
    var liveDataLogin: LiveData<form>? = null
    var othermandi:LiveData<List<OtherMandi>>? = null

//    val JLive: LiveData<Mandi>
//    get() = repository.FLive

    fun insertData(context: Context,id:Int, name: String, mail: String, image:String) {
        repository.insertData(context, id, name, mail, image)
    }

    fun getLoginDetails(context: Context) : LiveData<form>? {
        liveDataLogin = repository.getLoginDetails(context)
        return liveDataLogin
    }

    fun insertdetails(context: Context){
        repository.insertdetails(context)
    }

    fun getchdetails(context: Context) : LiveData<List<OtherMandi>>?{
        othermandi = repository.fetchdetails(context)
        return othermandi
    }
}