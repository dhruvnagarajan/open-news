package com.opensource.news.data.repository

import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.domain.repository.ProfileRepository
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