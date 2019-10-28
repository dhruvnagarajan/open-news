package com.dhruvnagarajan.opennews.domain.repository

import com.dhruvnagarajan.opennews.domain.entity.CacheLedger
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.domain.entity.NewsResponse
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface NewsRepository {

    fun getCacheLedgerEntry(key: String): Observable<CacheLedger>

    fun getTopHeadlines(profile: NewsProfile): Observable<NewsResponse>
}