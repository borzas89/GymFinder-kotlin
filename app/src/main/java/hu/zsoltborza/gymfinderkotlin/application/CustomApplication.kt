package hu.zsoltborza.gymfinderkotlin.application

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.zsoltborza.gymfinderkotlin.data.local.dao.MapDao
import hu.zsoltborza.gymfinderkotlin.data.local.database.DatabaseManager

class CustomApplication : Application() {

    lateinit var databaseManager: DatabaseManager
    lateinit var mapDao: MapDao
        private set

    override fun onCreate() {
        super.onCreate()

        databaseManager = Room.databaseBuilder(this, DatabaseManager::class.java, "markers")
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .allowMainThreadQueries()
            // TODO - update...
            .createFromAsset("markers.db")
            .build()

        mapDao = databaseManager.mapDao


    }

}