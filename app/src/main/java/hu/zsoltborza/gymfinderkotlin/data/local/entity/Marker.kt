package hu.zsoltborza.gymfinderkotlin.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marker")
data class Marker (
    @NonNull
    @PrimaryKey
    var markerId: Long,
    var title: String,
    var information: String,
    var address: String,
    var latitude: Double,
    var longitude: Double,
    var isFavourite: Boolean
){
}
