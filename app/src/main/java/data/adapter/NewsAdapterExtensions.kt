package data.adapter

import com.opensource.news.domain.model.Article
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.model.Source
import data.persistence.news.ArticleRO
import data.persistence.news.NewsRO
import data.persistence.news.SourceRO
import io.realm.RealmList

/**
 * @author Dhruvaraj Nagarajan
 */
fun List<Article>.toStorage(): RealmList<ArticleRO> {
    val storageList = RealmList<ArticleRO>()
    this.forEach { article -> storageList.add(article.toStorage()) }
    return storageList
}

fun RealmList<ArticleRO>.fromStorage(): List<Article> {
    val list = ArrayList<Article>()
    this.forEach { articleRO -> list.add(articleRO.fromStorage()) }
    return list
}

fun NewsResponse.toStorage() = NewsRO(
    this.status,
    this.totalResults,
    this.articles?.toStorage()
)

fun NewsRO.fromStorage() = NewsResponse(
    this.status,
    this.totalResults,
    this.articles?.fromStorage()
)

fun Article.toStorage() = ArticleRO(
    this.source?.toStorage(),
    this.author,
    this.title,
    this.description,
    this.url,
    this.urlToImage,
    this.publishedAt,
    this.content
)


fun ArticleRO.fromStorage() = Article(
    this.source?.fromStorage(),
    this.author,
    this.title,
    this.description,
    this.url,
    this.urlToImage,
    this.publishedAt,
    this.content
)

fun Source.toStorage() = SourceRO(this.id, this.name)

fun SourceRO.fromStorage() = Source(this.id, this.name)