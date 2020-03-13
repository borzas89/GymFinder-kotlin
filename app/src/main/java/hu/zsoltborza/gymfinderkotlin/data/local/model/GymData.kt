package hu.zsoltborza.gymfinderkotlin.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Zsolt Borza on 2018.01.30..
 * Gym datas from file...
 */

class GymData {

    @SerializedName("gym_list")
    @Expose
    var gymList: List<GymListItem>? = null
}
