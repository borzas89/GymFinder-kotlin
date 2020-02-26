package hu.zsoltborza.gymfinderkotlin.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.zsoltborza.gymfinderkotlin.R
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.lookup
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment: BaseFragment() {
    private val viewModel by lazy { backstack.lookup<MapViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(
            R.layout.fragment_map
            , container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapText.setText(viewModel.mapName + " " + viewModel.dataSize())

        //viewModel.insertDataFromFile()
        viewModel.checkDatabase()
    }
}