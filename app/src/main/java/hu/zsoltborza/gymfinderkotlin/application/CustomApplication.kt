package hu.zsoltborza.gymfinderkotlin.application

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.zsoltborza.gymfinderkotlin.data.local.dao.MapDao
import hu.zsoltborza.gymfinderkotlin.data.local.database.DatabaseManager
import hu.zsoltborza.gymfinderkotlin.utils.schedulers.IoScheduler
import hu.zsoltborza.gymfinderkotlin.utils.schedulers.MainScheduler
import hu.zsoltborza.gymfinderkotlin.utils.schedulers.NetworkScheduler

class CustomApplication : Application() {

    lateinit var databaseManager: DatabaseManager
        private set
    lateinit var mapDao: MapDao
        private set

    lateinit var mainScheduler: MainScheduler
        private set

    lateinit var networkScheduler: NetworkScheduler
        private set

    lateinit var ioScheduler: IoScheduler
        private set

    override fun onCreate() {
        super.onCreate()

        mainScheduler = MainScheduler()
        ioScheduler = IoScheduler()
        networkScheduler = NetworkScheduler()

        databaseManager = Room.databaseBuilder(this, DatabaseManager::class.java, "markers")
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .allowMainThreadQueries()
          //  .fallbackToDestructiveMigration()
            .createFromAsset("markers_v2.db")
            .build()

        mapDao = databaseManager.mapDao
    }
}