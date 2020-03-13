package hu.zsoltborza.gymfinderkotlin.data.local.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import hu.zsoltborza.gymfinderkotlin.data.local.entity.Marker
import io.reactivex.Flowable

@Dao
abstract class MapDao {

    @Query("SELECT * FROM Marker LIMIT 50")
    abstract fun findAllWithLimit(): List<Marker>

    @Query("SELECT * FROM Marker")
    abstract fun findAll(): List<Marker>

    @Query("SELECT * FROM Marker WHERE markerId == :id")
    abstract fun findById(id: Long): Marker

    @Query("SELECT * FROM marker")
    abstract fun listenForMarkers(): Flowable<List<Marker>>

    @Query("SELECT * FROM marker WHERE title LIKE '%' || lower(:title) || '%'")
    abstract fun listenForMarkersByTitle(title: String): Flowable<List<Marker>>

    @Query(
        "SELECT * FROM Marker WHERE (:southLat < :northLat AND latitude BETWEEN :southLat AND :northLat)" +
                " OR (:southLat > :northLat AND (latitude BETWEEN :southLat AND 180 OR latitude BETWEEN -180 AND :northLat))" +
                "OR (:southLon < :northLon AND longitude BETWEEN :southLon AND :northLon) OR (:southLon > :northLon AND " +
                "(longitude BETWEEN :southLon AND 180 OR longitude BETWEEN -180 AND :northLon)) "
    )
    abstract fun listenMarkersByBounds(
        northLat: Double,
        southLat: Double,
        northLon: Double,
        southLon: Double
    ): Flowable<List<Marker>>

    @Query(
        "SELECT * FROM Marker WHERE (:southLat < :northLat AND latitude BETWEEN :southLat AND :northLat)" +
                " OR (:southLat > :northLat AND (latitude BETWEEN :southLat AND 180 OR latitude BETWEEN -180 AND :northLat))" +
                "OR (:southLon < :northLon AND longitude BETWEEN :southLon AND :northLon) OR (:southLon > :northLon AND " +
                "(longitude BETWEEN :southLon AND 180 OR longitude BETWEEN -180 AND :northLon)) "
    )
    abstract fun findMarkerByBounds(
        northLat: Double,
        southLat: Double,
        northLon: Double,
        southLon: Double
    ): List<Marker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMarker(entity: Marker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMarkers(entities: List<Marker>?)

    @RawQuery
    abstract fun executeRawQuerySingle(query: SupportSQLiteQuery?): Marker

    @RawQuery
    abstract fun executeRawQueryList(query: SupportSQLiteQuery?): List<Marker>?

    @RawQuery
    abstract fun executeRawQuery(query: SupportSQLiteQuery?): Int

    @Update
    abstract fun update(entity: Marker)

    @Delete
    abstract fun delete(entity: Marker)

    fun deleteAll() {
        executeRawQuery(SimpleSQLiteQuery("DELETE FROM marker"))
    }

}