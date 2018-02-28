package com.example.client.factory;

import com.example.client.model.Client;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ClientFactory
{
	static HashMap<String, Client> clientDictionary = new HashMap<>();
	static Lock lock = new ReentrantLock();

	public static synchronized Client getClient(String name)
	{
		Client client = null;
		lock.lock();
		if (clientDictionary.containsKey(name))
		{
			client = clientDictionary.get(name);
		}
		lock.unlock();

		return client;
	}

	public static synchronized void setClient(String name, Client client)
	{
		lock.lock();
		clientDictionary.put(name, client);
		lock.unlock();
	}
}
