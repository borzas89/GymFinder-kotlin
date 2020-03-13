package hu.zsoltborza.gymfinderkotlin.features.list.detail

import com.zhuinden.simplestack.ServiceBinder
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseKey
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.HasServices
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.add
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailKey(val itemId: Long) : BaseKey(), HasServices {
    override fun bindServices(serviceBinder: ServiceBinder, application: CustomApplication) {
        with(serviceBinder) {
            add(DetailViewModel(application.mapDao, application.mainScheduler, backstack, getKey()))
        }
    }

    // generate reliable `toString()` for no-args data class
    override fun createFragment(): BaseFragment = DetailFragment()

}