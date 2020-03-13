package hu.zsoltborza.gymfinderkotlin.features.list

import com.zhuinden.simplestack.ServiceBinder
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseKey
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.HasServices
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.add
import hu.zsoltborza.gymfinderkotlin.features.map.MapFragment
import hu.zsoltborza.gymfinderkotlin.features.map.MapViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListKey(private val placeholder: String = "") : BaseKey(), HasServices {
    override fun bindServices(serviceBinder: ServiceBinder, application: CustomApplication) {
        with(serviceBinder) {
            add(ListViewModel(application.mapDao, application.mainScheduler, backstack))
        }
    }

    // generate reliable `toString()` for no-args data class
    override fun createFragment(): BaseFragment = ListFragment()
}