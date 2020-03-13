package hu.zsoltborza.gymfinderkotlin.data.local.model

/**
 * Created by Zsolt Borza on 2018.01.30..
 * Gym datas from file...
 */

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GymListItem protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("markerId")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("address1")
    @Expose
    var address1: String? = null
    @SerializedName("address2")
    @Expose
    var address2: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: String? = null
    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("information")
    @Expose
    var info: String? = null

    init {
        id = `in`.readString()
        title = `in`.readString()
        address1 = `in`.readString()
        address2 = `in`.readString()
        address = `in`.readString()
        latitude = `in`.readString()
        longitude = `in`.readString()
        info = `in`.readString()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {

        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(address1)
        parcel.writeString(address2)
        parcel.writeString(address)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(info)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<GymListItem> = object : Parcelable.Creator<GymListItem> {
            override fun createFromParcel(`in`: Parcel): GymListItem {
                return GymListItem(`in`)
            }

            override fun newArray(size: Int): Array<GymListItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}
