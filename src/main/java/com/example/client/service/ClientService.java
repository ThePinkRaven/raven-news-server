package com.example.client.service;

import com.example.client.model.Client;

public interface ClientService
{
	long addClient(Client client);

	Client getClient(long id);
}
