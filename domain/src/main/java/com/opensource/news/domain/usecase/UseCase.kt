package com.opensource.news.domain.usecase

import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface UseCase<Params, Result> {

    fun execute(params: Params): Observable<Result>
}