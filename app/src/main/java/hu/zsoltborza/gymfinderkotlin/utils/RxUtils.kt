package hu.zsoltborza.gymfinderkotlin.utils

import hu.zsoltborza.gymfinderkotlin.utils.schedulers.MainScheduler
import io.reactivex.Flowable

fun <T> Flowable<T>.storeValue(): Flowable<T> = this.replay(1).autoConnect(0)

fun <T> Flowable<T>.observeOnUi(mainScheduler: MainScheduler) = observeOn(mainScheduler.asRxScheduler())