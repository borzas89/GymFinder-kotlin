package hu.zsoltborza.gymfinderkotlin.features.map

import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MapKey(private val placeholder: String = "") : BaseKey() { // generate reliable `toString()` for no-args data class
    override fun createFragment(): BaseFragment = MapFragment()
}