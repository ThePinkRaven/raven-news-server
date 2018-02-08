package com.example.client.controller;

import com.example.client.model.Client;
import com.example.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping(value = "/api/client")
public class ClientController
{
	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService)
	{
		this.clientService = clientService;
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Client getClient(@PathVariable("id") long id)
	{
		// do serwisu verifyFruitExists(id);

		return clientService.getClient(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public long addClient(@RequestBody(required = false) Client client)
	{
		verifyCorrectPayload(client);

		return clientService.addClient(client);
	}

	private void verifyCorrectPayload(Client client)
	{
		if (Objects.isNull(client))
		{
			throw new RuntimeException("Client cannot be null");
		}
	}
}
