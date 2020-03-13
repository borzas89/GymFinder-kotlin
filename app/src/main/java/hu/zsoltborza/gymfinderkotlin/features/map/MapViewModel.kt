package hu.zsoltborza.gymfinderkotlin.features.map

import android.content.Context
import com.zhuinden.simplestack.Backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.ViewModel
import hu.zsoltborza.gymfinderkotlin.data.local.dao.MapDao
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import hu.zsoltborza.gymfinderkotlin.utils.LocationQueryUtil
import hu.zsoltborza.gymfinderkotlin.utils.getDataFromFile

class MapViewModel(
    private val appContext: Context,
    private val backstack: Backstack,
    private val mapDao: MapDao
) : ViewModel(backstack) {
    var mapName: String = "mapName from viewModel"

    fun isDatabaseEmpty(): Boolean = mapDao.findAll().isEmpty()

    fun insertDataFromFile(){
        if (isDatabaseEmpty()) {
            val gymListItemsFromFile = getDataFromFile(appContext)
            for (currentItem in gymListItemsFromFile) {
                val markerEntity = Marker(currentItem.id!!.toLong(),
                    currentItem.title!!,
                    currentItem.info!!,
                    currentItem.address!!,
                    java.lang.Double.parseDouble(currentItem.latitude!!.replace(",", ".")),
                    java.lang.Double.parseDouble(currentItem.longitude!!.replace(",", ".")),
                    false)
                    .apply {
                        cosLatitude = LocationQueryUtil.cosTransform(currentItem.latitude!!.replace(",",".").toDouble())
                        sinLatitude = LocationQueryUtil.sinTransform(currentItem.latitude!!.replace(",",".").toDouble())
                        cosLongitude = LocationQueryUtil.cosTransform(currentItem.longitude!!.replace(",",".").toDouble())
                        sinLongitude = LocationQueryUtil.sinTransform(currentItem.longitude!!.replace(",",".").toDouble())
                    }

                    mapDao.insertMarker(markerEntity)
            }
        }
    }
}