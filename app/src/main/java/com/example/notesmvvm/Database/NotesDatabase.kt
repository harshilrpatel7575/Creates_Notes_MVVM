package com.example.notesmvvm.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesmvvm.Dao.NotesDao
import com.example.notesmvvm.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun myNotesDao(): NotesDao

    companion object{

//        val migration_1_2 = object : Migration(1,2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//
//            }
//        }
        @Volatile
       var INSTANCE: NotesDatabase?=null

        fun getDatabaseInstance(context: Context):NotesDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context,
                        NotesDatabase::class.java,
                        "Notes")
                        .allowMainThreadQueries()
//                        .addMigrations(migration_1_2)
                        .build()
                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }

        }
    }
}