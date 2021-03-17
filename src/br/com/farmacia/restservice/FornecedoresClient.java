package br.com.farmacia.restservice;

import br.com.farmacia.domain.*;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

public interface FornecedoresClient {
	ArrayList<Fornecedores> getAllFornecedores();

	Fornecedores getById(Long id);

	HttpStatus addFornecedores(Fornecedores fornecedores);

	void updateFornecedores(Fornecedores fornecedores);

	void deleteFornecedores(Long id);
}
