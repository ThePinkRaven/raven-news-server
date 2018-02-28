package com.example.news.repository;

import com.example.client.model.Client;
import com.example.news.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Long>
{
	List<News> findByClient(Client client);

	void removeByClient(Client client);
}
