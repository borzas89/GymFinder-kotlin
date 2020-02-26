package hu.zsoltborza.gymfinderkotlin.data

import hu.zsoltborza.gymfinderkotlin.data.local.dao.MapDao
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker

class MapRepository(
    val mapDao: MapDao
) {

    fun gettingAllMarker(): List<Marker>{
        return mapDao.getMarkers()
    }

    fun insertMarkers(markerList: List<Marker>){
        mapDao.insertMarkers(markerList)
    }

    fun insertMarker(marker: Marker){
        mapDao.insert(marker)
    }


}