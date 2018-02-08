package com.example.client.service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService
{
	private ClientRepository repository;

	@Autowired
	public ClientServiceImpl(ClientRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public long addClient(Client client)
	{
		Client newClient = repository.save(client);
		return newClient.getId();
	}

	@Override
	public Client getClient(long id)
	{
		Client one = repository.findOne(id);

		if (one != null)
		{
			return one;
		} else
		{
			throw new RuntimeException(String.format("Client with id=%d was not found", id));
		}
	}
}
