package com.rktuhinbd.splitxpens.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rktuhinbd.splitxpens.add_member.model.MemberEntityData

@Database(entities = [MemberEntityData::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            // if the INSTANCE is not null, then return it, if it is then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "split_db"
                )
                    .allowMainThreadQueries()
                    //.addMigrations(migration)
                    .build()
                INSTANCE = instance
                // return instance
                return instance
            }
        }
    }
}