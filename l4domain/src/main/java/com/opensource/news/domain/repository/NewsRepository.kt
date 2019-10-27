package com.opensource.news.domain.repository

import com.opensource.news.domain.entity.CacheLedger
import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.domain.entity.NewsResponse
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface NewsRepository {

    fun getCacheLedgerEntry(key: String): Observable<CacheLedger>

    fun getTopHeadlines(profile: NewsProfile): Observable<NewsResponse>
}