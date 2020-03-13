package hu.zsoltborza.gymfinderkotlin.utils

object LocationQueryUtil {
    private const val EARTH_RADIUS = 6371

    fun cosTransform(value: Double): Double {
        return Math.cos(value * Math.PI / 180)
    }

    fun sinTransform(value: Double): Double {
        return Math.sin(value * Math.PI / 180)
    }

    fun radiusTransform(value: Double): Double {
        return Math.cos(value / EARTH_RADIUS)
    }
}