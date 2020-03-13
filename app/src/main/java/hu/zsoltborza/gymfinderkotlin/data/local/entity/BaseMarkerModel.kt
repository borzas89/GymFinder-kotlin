package hu.zsoltborza.gymfinderkotlin.data.local.entity

abstract class BaseMarkerModel {

    abstract val markerId: Long

    abstract val latitude: Double

    abstract val longitude: Double

    // these columns are used to support location based retrieval
    var cosLatitude: Double = 0.0
    var sinLatitude: Double = 0.0
    var cosLongitude: Double = 0.0
    var sinLongitude: Double = 0.0


}