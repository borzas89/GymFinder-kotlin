package hu.zsoltborza.gymfinderkotlin.application.navigation

import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    fun <T : BaseKey> getScreen(): T = requireArguments().getParcelable<T>("KEY")!!
}