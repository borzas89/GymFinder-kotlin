package hu.zsoltborza.gymfinderkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhuinden.simplestack.*
import com.zhuinden.simplestack.navigator.Navigator
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.core.navigation.FragmentStateChanger
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.ServiceProvider
import hu.zsoltborza.gymfinderkotlin.features.dashboard.DashboardKey
import hu.zsoltborza.gymfinderkotlin.features.list.ListKey
import hu.zsoltborza.gymfinderkotlin.features.map.MapKey
import hu.zsoltborza.gymfinderkotlin.utils.backstack
import hu.zsoltborza.gymfinderkotlin.utils.replaceHistory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SimpleStateChanger.NavigationHandler {
    private lateinit var fragmentStateChanger: FragmentStateChanger

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

        Navigator.configure()
            .setStateChanger(SimpleStateChanger(this))
            .setScopedServices(ServiceProvider(application as CustomApplication))
            .install(this, root, History.single(DashboardKey()))
    }

    override fun onBackPressed() {
        if (!backstack.goBack()) {
            super.onBackPressed()
        }
    }

    override fun onNavigationEvent(stateChange: StateChange) {
        fragmentStateChanger.handleStateChange(stateChange)
    }
}
