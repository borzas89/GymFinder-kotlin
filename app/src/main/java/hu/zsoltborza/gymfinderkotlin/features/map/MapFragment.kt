package hu.zsoltborza.gymfinderkotlin.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import hu.zsoltborza.gymfinderkotlin.R
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseFragment
import hu.zsoltborza.gymfinderkotlin.core.navigation.backstack
import hu.zsoltborza.gymfinderkotlin.core.viewmodels.lookup

class MapFragment: BaseFragment() {
    private val viewModel by lazy { backstack.lookup<MapViewModel>() }

    private var map: GoogleMap? = null

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

        val supportMapFragment: SupportMapFragment
        if (savedInstanceState == null) {
            supportMapFragment = SupportMapFragment.newInstance(createDefaultMapOptions())
            childFragmentManager.beginTransaction()
                .replace(R.id.mapContainer, supportMapFragment, MAP_FRAGMENT_TAG)
                .commit()
        } else {
            supportMapFragment = childFragmentManager.findFragmentByTag(MAP_FRAGMENT_TAG) as SupportMapFragment
        }

        supportMapFragment.getMapAsync { googleMap ->
            this.map = googleMap
        }
    }


    private fun createDefaultMapOptions(): GoogleMapOptions {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(HUNGARY_LATITUDE, HUNGARY_LONGITUDE))
            .zoom(6f)
            .build()

        return GoogleMapOptions()
            .camera(cameraPosition)
    }

    companion object{
        private const val MAP_FRAGMENT_TAG = "MapFragment"
        private const val HUNGARY_LATITUDE = 47.1804973
        private const val HUNGARY_LONGITUDE = 20.1136325

    }
}