package com.dhruvnagarajan.opennews.data.repository

import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Singleton
class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {

    override fun getNewsProfiles(): List<NewsProfile> {
        TODO("not implemented")
    }

    override fun createNewsProfile(newsProfile: NewsProfile) {
    }

    override fun deleteNewsProfile(newsProfile: NewsProfile) {
        TODO("not implemented")
    }
}