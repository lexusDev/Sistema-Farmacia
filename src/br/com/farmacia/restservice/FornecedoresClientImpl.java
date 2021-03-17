package br.com.farmacia.restservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.farmacia.domain.Fornecedores;

public class FornecedoresClientImpl implements FornecedoresClient {
	@Autowired
	RestTemplate restTemplate;
	
	final String ROOT_URI = "http://localhost:8090/providers";

	@Override
	public List<Fornecedores> getAllFornecedores() {
		ResponseEntity<Fornecedores[]> response = restTemplate.getForEntity(ROOT_URI, Fornecedores[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public Fornecedores getById(Long id) {
		ResponseEntity<Fornecedores> response = restTemplate.getForEntity(ROOT_URI + "/"+id, Fornecedores.class);
		return response.getBody();
	}

	@Override
	public HttpStatus addFornecedores(Fornecedores fornecedores) {
		ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI, fornecedores, HttpStatus.class);
		return response.getBody();
	}

	@Override
	public void updateFornecedores(Fornecedores fornecedores) {
		restTemplate.put(ROOT_URI, fornecedores);
		
	}

	@Override
	public void deleteFornecedores(Long id) {
		restTemplate.delete(ROOT_URI + id);		
	}
}
