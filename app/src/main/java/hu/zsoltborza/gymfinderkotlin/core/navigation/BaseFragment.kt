package hu.zsoltborza.gymfinderkotlin.core.navigation

import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    fun <T : BaseKey> getScreen(): T = requireArguments().getParcelable<T>("KEY")!!
}