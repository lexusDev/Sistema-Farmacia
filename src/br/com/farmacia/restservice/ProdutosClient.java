package br.com.farmacia.restservice;

import br.com.farmacia.domain.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public interface ProdutosClient {
	ArrayList<Produtos> getAllProdutos();

	Produtos getById(Long id);

	HttpStatus addProdutos(Produtos produtos);

	void updateProdutos(Produtos produtos);

	void deleteProdutos(Long id);
}
