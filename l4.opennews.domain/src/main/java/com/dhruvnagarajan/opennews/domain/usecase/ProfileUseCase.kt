package com.dhruvnagarajan.opennews.domain.usecase

import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.domain.repository.ProfileRepository
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
class ProfileUseCase constructor(
    val profileRepository: ProfileRepository
) {

    fun getNewsProfiles(): Observable<List<NewsProfile>> =
        Observable.create { emitter ->
            emitter.onNext(profileRepository.getNewsProfiles())
            emitter.onComplete()
        }

    fun createNewsProfile(newsProfile: NewsProfile): Observable<Unit> =
        Observable.create { emitter ->
            emitter.onNext(profileRepository.createNewsProfile(newsProfile))
            emitter.onComplete()
        }

    fun deleteNewsProfile(newsProfile: NewsProfile): Observable<Unit> =
        Observable.create { emitter ->
            emitter.onNext(profileRepository.deleteNewsProfile(newsProfile))
            emitter.onComplete()
        }
}