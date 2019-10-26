package com.opensource.news.data.repository

import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Singleton
class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {

    override fun getNewsProfiles(): List<NewsRequest> {
        TODO("not implemented")
    }

    override fun createNewsProfile(newsRequest: NewsRequest) {
    }

    override fun deleteNewsProfile(newsRequest: NewsRequest) {
        TODO("not implemented")
    }
}