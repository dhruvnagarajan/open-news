package data.persistence.news

import io.realm.RealmList
import io.realm.RealmObject

/**
 * @author Dhruvaraj Nagarajan
 */
open class NewsRO(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: RealmList<ArticleRO>? = null
) : RealmObject()

open class ArticleRO(
    var source: SourceRO? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
) : RealmObject()

open class SourceRO(
    var id: String? = null,
    var name: String? = null
) : RealmObject()