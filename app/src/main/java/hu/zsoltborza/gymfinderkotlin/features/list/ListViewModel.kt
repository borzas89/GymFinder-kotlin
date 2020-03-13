package hu.zsoltborza.gymfinderkotlin.features.list

import com.zhuinden.simplestack.Backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.ViewModel
import hu.zsoltborza.gymfinderkotlin.data.local.dao.MapDao
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import hu.zsoltborza.gymfinderkotlin.features.dashboard.DashboardFragment
import hu.zsoltborza.gymfinderkotlin.features.dashboard.DashboardKey
import hu.zsoltborza.gymfinderkotlin.features.list.detail.DetailKey
import hu.zsoltborza.gymfinderkotlin.utils.observeOnUi
import hu.zsoltborza.gymfinderkotlin.utils.schedulers.MainScheduler
import hu.zsoltborza.gymfinderkotlin.utils.storeValue
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class ListViewModel(
    private val mapDao: MapDao,
    private val mainScheduler: MainScheduler,
    private val backstack: Backstack
) : ViewModel(backstack) {
    private val compositeDisposable = CompositeDisposable()

    private val markers: Flowable<List<Marker>> = mapDao.listenForMarkers()
        .storeValue()
        .observeOnUi(mainScheduler)

    fun getMarkersByQuery(title: String): Flowable<List<Marker>> = when {
        title.isEmpty() -> markers
        else -> mapDao.listenForMarkersByTitle(title)
    }.observeOnUi(mainScheduler)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun openGymDetailScreen(itemId: Long){
        backstack.goTo(DetailKey(itemId))
    }
}