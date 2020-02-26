package hu.zsoltborza.gymfinderkotlin.core.viewmodels

import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestack.ServiceBinder
import hu.zsoltborza.gymfinderkotlin.application.CustomApplication

interface HasServices: ScopeKey {
    fun bindServices(serviceBinder: ServiceBinder, application: CustomApplication)

    override fun getScopeTag(): String = javaClass.name
}