package com.example.news.service;

import com.example.client.model.Client;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService
{
	private NewsRepository repository;

	@Autowired
	public NewsServiceImpl(NewsRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public long addNews(News news)
	{
		if (news.getCreateDate() == null)
		{
			news.setCreateDate(Calendar.getInstance().getTime());
		}

		News newsAdded = repository.save(news);

		return newsAdded.getId();
	}

	@Override
	public List<News> getAllNews(Client client)
	{
		List<News> news = repository.findByClient(client);

		return news;
	}

	@Override
	public News getNews(long newsId)
	{
		News news = repository.findOne(newsId);

		return news;
	}

	@Override
	public void removeNews(long newsId)
	{
		repository.delete(newsId);
	}

	@Override
	public void removeAllNews(Client client)
	{
		repository.removeByClient(client);
	}

}
