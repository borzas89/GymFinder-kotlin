package hu.zsoltborza.gymfinderkotlin.core.viewmodels

import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestack.ServiceBinder
import hu.zsoltborza.gymfinderkotlin.core.navigation.BaseKey

class ServiceProvider : ScopedServices {
    override fun bindServices(serviceBinder: ServiceBinder) {
        val key = serviceBinder.getKey<BaseKey>()

        val scope = serviceBinder.scopeTag

        if (key is HasServices && key.scopeTag == scope) {
            key.bindServices(serviceBinder) // screen-bound shared services
        }
    }
}