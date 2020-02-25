package hu.zsoltborza.gymfinderkotlin.features.map

import android.content.Context
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.Bundleable
import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.statebundle.StateBundle
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.data.MapRepository
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import hu.zsoltborza.gymfinderkotlin.utils.getDataFromFile

class MapViewModel(
    private val appContext: Context,
    private val backstack: Backstack
): Bundleable, ScopedServices.HandlesBack {

    var mapName: String = "mapName from viewModel"

    fun insertDataFromFile(){
        appContext as CustomApplication


        // if database is empty, insert markers.db from file - mapper...
        if ( appContext.mapDao.getMarkers().isEmpty()) {
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
                appContext.mapDao.insert(markerEntity)
            }

        }
    }


    override fun toBundle(): StateBundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromBundle(bundle: StateBundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackEvent(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}