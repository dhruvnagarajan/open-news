package com.dhruvnagarajan.opennews.domain.repository

import com.dhruvnagarajan.opennews.domain.entity.NewsProfile

/**
 * @author Dhruvaraj Nagarajan
 */
interface ProfileRepository {

    fun getNewsProfiles(): List<NewsProfile>

    fun createNewsProfile(newsProfile: NewsProfile)

    fun deleteNewsProfile(newsProfile: NewsProfile)
}