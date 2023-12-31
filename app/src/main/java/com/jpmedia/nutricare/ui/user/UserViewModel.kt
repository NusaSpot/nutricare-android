package com.jpmedia.nutricare.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpmedia.nutricare.api.ProfilResponse
import com.jpmedia.nutricare.api.Retro
import com.jpmedia.nutricare.api.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _profilData = MutableLiveData<ProfilResponse?>()
    val profilData: LiveData<ProfilResponse?> get() = _profilData

    fun loadProfilData(token: String) {
        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        val bearerToken = "Bearer $token"

        retro.getProfile(bearerToken).enqueue(object : Callback<ProfilResponse> {
            override fun onResponse(call: Call<ProfilResponse>, response: Response<ProfilResponse>) {
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    _profilData.value = usersResponse
                } else {
                }
            }
            override fun onFailure(call: Call<ProfilResponse>, t: Throwable) {

            }
        })
    }
}