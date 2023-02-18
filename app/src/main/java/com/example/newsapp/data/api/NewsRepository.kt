package com.example.newsapp.data.api

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun getNews(countryCode: String, pageNamber: Int) =
        newsService.getHeadlines(countryCode = countryCode, page = pageNamber)
}