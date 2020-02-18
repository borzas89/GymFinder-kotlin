package hu.zsoltborza.gymfinderkotlin.application.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

abstract class BaseKey : Parcelable {
    val fragmentTag: String
        get() = toString()

    fun newFragment(): BaseFragment = createFragment().apply {
        arguments = (arguments ?: Bundle()).also { bundle ->
            bundle.putParcelable("KEY", this@BaseKey)
        }
    }

    protected abstract fun createFragment(): BaseFragment
}