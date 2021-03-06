package hu.zsoltborza.gymfinderkotlin.features.map

import com.zhuinden.simplestack.ServiceBinder
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseKey
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.HasServices
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.add
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MapKey(private val placeholder: String = "") : BaseKey(), HasServices {
    override fun bindServices(serviceBinder: ServiceBinder, application: CustomApplication) {
        with(serviceBinder) {
            add(MapViewModel(application, backstack, application.mapDao))
        }
    }// generate reliable `toString()` for no-args data class
    override fun createFragment(): BaseFragment = MapFragment()
}