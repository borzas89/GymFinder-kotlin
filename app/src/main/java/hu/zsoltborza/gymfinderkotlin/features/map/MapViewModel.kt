package hu.zsoltborza.gymfinderkotlin.features.map

import android.content.Context
import com.zhuinden.simplestack.Backstack
import hu.zsoltborza.gymfinderkotlin.data.MapRepository
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import hu.zsoltborza.gymfinderkotlin.utils.getDataFromFile

class MapViewModel(
    private val appContext: Context,
    private val backstack: Backstack,
    private val mapRepository: MapRepository
) {
    var mapName: String = "mapName from viewModel"

    fun checkDatabase(): Boolean{
        return mapRepository.gettingAllMarker().isEmpty()
    }

    fun dataSize(): Int {
        return mapRepository.gettingAllMarker().size
    }

    fun insertDataFromFile(){
        // if database is empty, insert markers.db from file - mapper...
        if ( mapRepository.gettingAllMarker().isEmpty()) {
            // reading from file..
            val gymListItemsFromFile = getDataFromFile(appContext)
            for (currentItem in gymListItemsFromFile) {
                val markerEntity = Marker(currentItem.id!!.toLong(),
                    currentItem.title!!,
                    currentItem.info!!,
                    currentItem.address!!,
                    java.lang.Double.parseDouble(currentItem.latitude!!.replace(",", ".")),
                    java.lang.Double.parseDouble(currentItem.longitude!!.replace(",", ".")),
                    false)
                mapRepository.insertMarker(markerEntity)
            }

        }
    }
}