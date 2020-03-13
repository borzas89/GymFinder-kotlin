package hu.zsoltborza.gymfinderkotlin.features.list.detail

import com.zhuinden.simplestack.Backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.ViewModel
import hu.zsoltborza.gymfinderkotlin.data.local.dao.MapDao
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import hu.zsoltborza.gymfinderkotlin.utils.schedulers.MainScheduler

class DetailViewModel(
    private val mapDao: MapDao,
    private val mainScheduler: MainScheduler,
    private val backstack: Backstack,
    private val key: DetailKey
) : ViewModel(backstack) {

    fun getMarkerById(): Marker {
        return mapDao.findById(key.itemId)
    }



}