package hu.zsoltborza.gymfinderkotlin.core.viewmodels
import androidx.annotation.CallSuper
import com.zhuinden.simplestack.Backstack

@Suppress("UnnecessaryAbstractClass")
abstract class ViewModel(@JvmField protected val navigator: Backstack) {

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @CallSuper
    open fun onCleared() {

    }

    open fun navigateBack(){
        navigator.goBack()
    }
}