package com.aghogho.week6assessment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aghogho.week6assessment.database.RoomDatab
import com.aghogho.week6assessment.database.UserEntity

class ActivityModel(app: Application): AndroidViewModel(app) {
        lateinit var allUser: MutableLiveData<List<UserEntity>>
    init {
        allUser = MutableLiveData()
        getAllUser()

    }

    fun getAllUserObservers(): MutableLiveData<List<UserEntity>>{
        return allUser
    }

    fun getAllUser(){
        val userDoa = RoomDatab.getAppDatabase((getApplication()))?.userDoa()
        val list = userDoa?.getAllUserInfo()

        allUser.postValue(list)

    }

    fun insertUserInfo(entity: UserEntity){
       val userDao = RoomDatab.getAppDatabase(getApplication())?.userDoa()
        userDao?.insertUser(entity)
        getAllUser()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomDatab.getAppDatabase(getApplication())?.userDoa()
        userDao?.updateUser(entity)
        getAllUser()
    }

    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomDatab.getAppDatabase(getApplication())?.userDoa()
        userDao?.deleteUser(entity)
        getAllUser()
    }
}