package hu.zsoltborza.gymfinderkotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhuinden.simplestack.GlobalServices
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.core.navigation.FragmentStateChanger
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.ServiceProvider
import hu.zsoltborza.gymfinderkotlin.data.local.database.DatabaseManager
import hu.zsoltborza.gymfinderkotlin.features.dashboard.DashboardKey
import hu.zsoltborza.gymfinderkotlin.features.list.ListKey
import hu.zsoltborza.gymfinderkotlin.features.map.MapKey
import hu.zsoltborza.gymfinderkotlin.utils.backstack
import hu.zsoltborza.gymfinderkotlin.utils.getDataFromFile
import hu.zsoltborza.gymfinderkotlin.utils.replaceHistory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),StateChanger {
    private lateinit var fragmentStateChanger: FragmentStateChanger
    private lateinit var appContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigation.setOnNavigationItemSelectedListener { item ->
            val destination = when (item.itemId) {
                R.id.navigation_dashboard -> DashboardKey()
                R.id.navigation_map -> MapKey()
                R.id.navigation_list -> ListKey()
                else -> null
            }

            destination?.let { key ->
                backstack.replaceHistory(key)
                true
            } ?: false
        }

        fragmentStateChanger = FragmentStateChanger(supportFragmentManager, R.id.root)
        appContext = applicationContext  as CustomApplication


        Navigator.configure()
            .setStateChanger(this)
            .setScopedServices(ServiceProvider())
            .setGlobalServices(
                GlobalServices.builder()
                    .addService("appContext", appContext)
                    .build()
            )

            .install(this, root, History.single(DashboardKey()))
    }

    override fun onBackPressed() {
        if (!backstack.goBack()) {
            super.onBackPressed()
        }
    }

    override fun handleStateChange(
        stateChange: StateChange,
        completionCallback: StateChanger.Callback
    ) {
        if (stateChange.isTopNewKeyEqualToPrevious) {
            completionCallback.stateChangeComplete()
            return
        }
        fragmentStateChanger.handleStateChange(stateChange)
        completionCallback.stateChangeComplete()
    }
}
