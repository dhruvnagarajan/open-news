package com.opensource.news.domain.repository

import com.opensource.news.domain.entity.NewsProfile

/**
 * @author Dhruvaraj Nagarajan
 */
interface ProfileRepository {

    fun getNewsProfiles(): List<NewsProfile>

    fun createNewsProfile(newsProfile: NewsProfile)

    fun deleteNewsProfile(newsProfile: NewsProfile)
}