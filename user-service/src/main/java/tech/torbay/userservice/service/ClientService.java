package tech.torbay.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.userservice.entity.Client;
import tech.torbay.userservice.repository.ClientRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;

	public List<Client> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(clientRepository.findAll());
	}

	public Client findByEmail(String email) {
		// TODO Auto-generated method stub
		Client client = clientRepository.findByEmail(email);
		System.out.println(client.toString());
		return client;
	}
	
	public int add(int a, int b) {
		return a+b;
	}
}

