package com.opensource.news.domain.repository

import com.opensource.news.domain.entity.NewsRequest

/**
 * @author Dhruvaraj Nagarajan
 */
interface ProfileRepository {

    fun getNewsProfiles(): List<NewsRequest>

    fun createNewsProfile(newsRequest: NewsRequest)

    fun deleteNewsProfile(newsRequest: NewsRequest)
}