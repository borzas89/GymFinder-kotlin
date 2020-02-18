package hu.zsoltborza.gymfinderkotlin.features.dashboard

import hu.zsoltborza.gymfinderkotlin.application.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.application.navigation.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DashboardKey(private val placeholder: String = "") : BaseKey() { // generate reliable `toString()` for no-args data class
    override fun createFragment(): BaseFragment = DashboardFragment()
}