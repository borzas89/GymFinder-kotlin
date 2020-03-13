package hu.zsoltborza.gymfinderkotlin.features.list.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.zsoltborza.gymfinderkotlin.R
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.lookup
import hu.zsoltborza.gymfinderkotlin.features.list.ListViewModel
import kotlinx.android.synthetic.main.fragment_list_detail.*


class DetailFragment: BaseFragment() {
    private val viewModel by lazy { backstack.lookup<DetailViewModel>() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(
            R.layout.fragment_list_detail
            , container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = viewModel.getMarkerById().title
        info.text = viewModel.getMarkerById().information
        address.text = viewModel.getMarkerById().address

        // ...
    }
}