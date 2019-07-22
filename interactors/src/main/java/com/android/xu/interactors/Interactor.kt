
package com.android.xu.interactors

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface Interactor<in P> {
    val dispatcher: CoroutineDispatcher
    suspend operator fun invoke(params: P)
}

abstract class SuspendingWorkInteractor<P : Any, T> : Interactor<P> {
    private val subject: Subject<T> = BehaviorSubject.create()

    override suspend operator fun invoke(params: P) = subject.onNext(doWork(params))

    abstract suspend fun doWork(params: P): T

    fun observe(): Observable<T> = subject
}



fun <P> CoroutineScope.launchInteractor(interactor: Interactor<P>, param: P): Job {
    return launch(context = interactor.dispatcher, block = { interactor(param) })
}

