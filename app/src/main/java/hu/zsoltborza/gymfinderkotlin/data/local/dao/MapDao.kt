package hu.zsoltborza.gymfinderkotlin.data.local.dao

import androidx.room.*
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker

@Dao
interface MapDao {

    @Insert
    abstract fun insert(marker: Marker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMarkers(markers: List<Marker>)

    // TODO  - rx....
  //  @Query("SELECT * FROM marker")
  //  abstract fun listenForMarkers(): Flowable<List<Marker>>

    @Update
    abstract fun update(marker: Marker)

    @Delete
    abstract fun delete(marker: Marker)

    @Query("SELECT * FROM marker")
    abstract fun getMarkers(): List<Marker>

    @Query("SELECT * FROM marker WHERE markerId = :markerId")
    abstract fun getMarkerById(markerId: Long): Marker
}