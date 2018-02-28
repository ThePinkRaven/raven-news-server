package com.example.news.controller;

import com.example.client.model.Client;
import com.example.client.service.ClientService;
import com.example.news.model.News;
import com.example.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/news")
public class NewsController
{
	private NewsService newsService;
	private ClientService clientService;

	@Autowired
	public NewsController(NewsService newsService, ClientService clientService)
	{
		this.newsService = newsService;
		this.clientService = clientService;
	}

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<News> getAllNews(@RequestHeader String clientName)
	{
		Client client = clientService.getClient(clientName);

		return newsService.getAllNews(client);
	}

	@ResponseBody
	@GetMapping(value = "/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public News getNews(@PathVariable Long newsId)
	{
		return newsService.getNews(newsId);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public long addNews(@RequestBody(required = true) News news, @RequestHeader String clientName)
	{
		Client client = clientService.getClient(clientName);
		news.setClient(client);

		return newsService.addNews(news);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeNews(@PathVariable Long newsId)
	{
		newsService.removeNews(newsId);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeNewsByClient(@RequestHeader String clientName)
	{
		Client client = clientService.getClient(clientName);

		newsService.removeAllNews(client);
	}
}
