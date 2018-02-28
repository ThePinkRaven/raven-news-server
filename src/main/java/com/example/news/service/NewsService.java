package com.example.news.service;

import com.example.client.model.Client;
import com.example.news.model.News;

import java.util.List;

public interface NewsService
{
	long addNews(News news);

	List<News> getAllNews(Client client);

	News getNews(long newsId);

	void removeNews(long newsId);

	void removeAllNews(Client client);
}
