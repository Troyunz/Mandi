package com.example.Mandi.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.Mandi.api.ApiService
import com.example.Mandi.db.MDatabase
import com.example.Mandi.db.fDatabase
import com.example.Mandi.model.Mandi
import com.example.Mandi.model.OtherMandi
import com.example.Mandi.model.form
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FRepository(
    private val fdatabase:fDatabase,
    private val apiService: ApiService,
    private val mDatabase: MDatabase,
    private val applicationContext:Context
) {

    private val FLiveData = MutableLiveData<Mandi>()
    var loginTableModel: LiveData<form>? = null
    var mandimodel: LiveData<List<OtherMandi>>? = null

//    val FLive : LiveData<Mandi>
//    get() = FLiveData


suspend fun  getFitems(){

//        if(NetworkUtils.isInternetAvailable(applicationContext)) {
//            val result = apiService.getothermandi()
//            if (result?.body() != null) {
//                FLiveData.postValue(result.body())
//                mDatabase.mDao().addMitems(result.body()!!.data.other_mandi)
//            }
//        }
//        else{
//            mandimodel = mDatabase.mDao().getmitems()
//            val list: List<Any> = emptyList()
//            val dummydata = Data(list, mandimodel)
//            val dummClass = Mandi(1, mandimodel)
//            FLiveData.postValue(mandimodel)
//        }

    }

    fun insertData(context: Context,id:Int, name: String, mail: String, image:String) {

        CoroutineScope(IO).launch {
            val loginDetails = form(id, name, mail, image)
            fdatabase!!.fDao().addJclass(loginDetails)
        }

    }

    fun getLoginDetails(context: Context) : LiveData<form>? {

        loginTableModel = fdatabase!!.fDao().getJclass()

        return loginTableModel
    }

    fun insertdetails(context: Context)
    {
        CoroutineScope(IO).launch {
            val result = apiService.getothermandi()
            if(result?.body() != null)
            {
                FLiveData.postValue(result.body())
                mDatabase.mDao().addMitems(result.body()!!.data.other_mandi)
            }
        }
    }

    fun fetchdetails(context: Context):LiveData<List<OtherMandi>>?{
        mandimodel = mDatabase.mDao().getmitems()
        return mandimodel
    }
}