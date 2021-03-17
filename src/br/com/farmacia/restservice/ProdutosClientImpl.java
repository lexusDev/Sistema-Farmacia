package br.com.farmacia.restservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.farmacia.domain.Produtos;

@Service
public class ProdutosClientImpl implements ProdutosClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	final String ROOT_URI = "http://localhost:8090/products";

	@Override
	public ArrayList<Produtos> getAllProdutos() {
		ResponseEntity<Produtos[]> response = restTemplate.getForEntity(ROOT_URI, Produtos[].class);
		return (ArrayList<Produtos>) Arrays.asList(response.getBody());
	}

	@Override
	public Produtos getById(Long id) {
		ResponseEntity<Produtos> response = restTemplate.getForEntity(ROOT_URI + "/"+id, Produtos.class);
		return response.getBody();
	}

	@Override
	public HttpStatus addProdutos(Produtos produtos) {
		ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI, produtos, HttpStatus.class);
		return response.getBody();
	}

	@Override
	public void updateProdutos(Produtos produtos) {
		restTemplate.put(ROOT_URI, produtos);
		
	}

	@Override
	public void deleteProdutos(Long id) {
		restTemplate.delete(ROOT_URI + id);		
	}
}
