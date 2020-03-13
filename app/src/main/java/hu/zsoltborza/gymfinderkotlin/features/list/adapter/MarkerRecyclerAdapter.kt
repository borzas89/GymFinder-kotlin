package hu.zsoltborza.gymfinderkotlin.features.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import hu.zsoltborza.gymfinderkotlin.R
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import java.text.DecimalFormat
import java.util.*

class MarkerRecyclerAdapter(
    private val context: Context,
    listener: OnItemClickedListener
) :
    RecyclerView.Adapter<MarkerRecyclerAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val listener: OnItemClickedListener = listener
    var df = DecimalFormat("#.00")

    var actucalPosition: LatLng? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val rootView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_marker_item, parent, false)
        return ViewHolder(rootView)
    }

    private var gymList: List<Marker> = Collections.emptyList()

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item: Marker = gymList!![position]
        holder.titleTV.setText(item.title)
        holder.addressTV.setText(item.address)
        actucalPosition = LatLng(47.4544331, 19.633235)
        //        double itemLat = Double.valueOf(item.getLatitude());
//        double itemLon = Double.valueOf(item.getLongitude());
        val gymPosition = LatLng(item.latitude, item.longitude)
        val distance = df.format(
            SphericalUtil.computeDistanceBetween(
                actucalPosition,
                gymPosition
            ) / 1000
        ) + " km"
        holder.infoTV.text = distance
       holder.frameLayout.setOnClickListener { listener.onItemClicked(item.markerId) }
    }



    fun updateData(gyms: List<Marker>?) {
        this.gymList = gyms ?: Collections.emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return gymList?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView
        val addressTV: TextView
        val infoTV: TextView
        val frameLayout: FrameLayout
        val image: ImageView

        init {
            frameLayout = itemView.findViewById(R.id.list_frame_item)
            titleTV = itemView.findViewById(R.id.titleTV)
            addressTV = itemView.findViewById(R.id.addressTV)
            infoTV = itemView.findViewById(R.id.infoTV)
            image = itemView.findViewById(R.id.image)
        }
    }


}