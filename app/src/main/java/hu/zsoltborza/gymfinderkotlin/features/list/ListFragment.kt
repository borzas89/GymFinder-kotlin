package hu.zsoltborza.gymfinderkotlin.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.zsoltborza.gymfinderkotlin.R
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.lookup
import hu.zsoltborza.gymfinderkotlin.features.list.adapter.MarkerRecyclerAdapter
import hu.zsoltborza.gymfinderkotlin.features.list.adapter.OnItemClickedListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.concurrent.TimeUnit


class ListFragment : BaseFragment(), SearchView.OnQueryTextListener, OnItemClickedListener {
    private val viewModel by lazy { backstack.lookup<ListViewModel>() }
    private lateinit var recyclerView: RecyclerView
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View =
        inflater.inflate(
            R.layout.fragment_list
            , container, false
        )

    /*/ override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         binding = ListFragmentBinding.inflate(inflater, container, false)
         return binding.root
     }*/

    private lateinit var markerAdapter: MarkerRecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private val queryString = BehaviorProcessor.createDefault("")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        markerAdapter = MarkerRecyclerAdapter(activity!!.applicationContext,this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = markerAdapter

        compositeDisposable += queryString
            .debounce(225L, TimeUnit.MILLISECONDS, Schedulers.computation())
            .observeOn(Schedulers.io())
            .switchMap { query ->
                viewModel.getMarkersByQuery(query)
            }.subscribeBy(onNext = { markers ->
                markerAdapter.updateData(markers)
            })

        searchView.setOnQueryTextListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        queryString.offer(newText)
        return true
    }

    override fun onItemClicked(itemId: Long) {
        viewModel.openGymDetailScreen(itemId)
    }


}