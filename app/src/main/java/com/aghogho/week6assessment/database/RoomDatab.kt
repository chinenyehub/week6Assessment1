package com.aghogho.week6assessment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
 abstract class RoomDatab: RoomDatabase() {

    abstract fun userDoa(): UserDao?

    companion object{
        private var INSTANCE: RoomDatab? = null

        fun getAppDatabase(context: Context): RoomDatab? {
            if (INSTANCE == null){

                INSTANCE = Room.databaseBuilder<RoomDatab>(
                    context.applicationContext, RoomDatab::class.java, "AppDB"
                )

                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
    }
}