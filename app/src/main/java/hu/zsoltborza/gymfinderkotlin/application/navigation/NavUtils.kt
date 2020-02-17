package hu.zsoltborza.gymfinderkotlin.application.navigation

import androidx.fragment.app.Fragment
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.navigator.Navigator

val Fragment.backstack: Backstack
    get() = Navigator.getBackstack(requireContext())