package hu.zsoltborza.gymfinderkotlin.features.map

import android.content.Context
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.Bundleable
import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.statebundle.StateBundle

class MapViewModel(
    private val appContext: Context,
    private val backstack: Backstack
): Bundleable, ScopedServices.HandlesBack {

    var mapName: String = "mapName from viewModel"


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