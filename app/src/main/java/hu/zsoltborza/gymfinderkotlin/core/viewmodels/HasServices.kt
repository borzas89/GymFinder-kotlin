package hu.zsoltborza.gymfinderkotlin.core.viewmodels

import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestack.ServiceBinder

interface HasServices: ScopeKey {
    fun bindServices(serviceBinder: ServiceBinder)

    override fun getScopeTag(): String = javaClass.name
}