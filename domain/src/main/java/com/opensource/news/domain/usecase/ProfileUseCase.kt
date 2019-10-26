package com.opensource.news.domain.usecase

import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.repository.ProfileRepository
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
class ProfileUseCase constructor(
    val profileRepository: ProfileRepository
) {

    fun getNewsProfiles(): Observable<List<NewsRequest>> =
        Observable.create { emitter ->
            emitter.onNext(profileRepository.getNewsProfiles())
            emitter.onComplete()
        }

    fun createNewsProfile(newsRequest: NewsRequest): Observable<Unit> =
        Observable.create { emitter ->
            emitter.onNext(profileRepository.createNewsProfile(newsRequest))
            emitter.onComplete()
        }

    fun deleteNewsProfile(newsRequest: NewsRequest): Observable<Unit> =
        Observable.create { emitter ->
            emitter.onNext(profileRepository.deleteNewsProfile(newsRequest))
            emitter.onComplete()
        }
}