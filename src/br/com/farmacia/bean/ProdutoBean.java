package br.com.farmacia.bean;

//import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.HttpClientErrorException;

//import br.com.farmacia.DAO.FornecedoresDAO;
//import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.restservice.AppConfig;
import br.com.farmacia.restservice.FornecedoresClient;
import br.com.farmacia.restservice.ProdutosClient;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private Produtos produtos;
	private ArrayList<Produtos>itens;
	private ArrayList<Produtos>itensFiltrados;
	
	private ArrayList<Fornecedores>comboFornecedores;
	
	public Produtos getProdutos() {
		return produtos;
	}


	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	
	
	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	

	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		ProdutosClient client = applicationContext.getBean(ProdutosClient.class);
		
		try {
			itens = client.getAllProdutos();
			//ProdutoDAO fdao = new ProdutoDAO();
			//itens = fdao.listar();
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
		
	}
	
	public void prepararNovo(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		FornecedoresClient client = applicationContext.getBean(FornecedoresClient.class);
				
		try {
			produtos = new Produtos();
			/*FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();*/
			comboFornecedores = client.getAllFornecedores();
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
	
	public void novo(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		ProdutosClient client = applicationContext.getBean(ProdutosClient.class);
		
		try {
			/*ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(produtos);
			
			
			itens = fdao.listar();*/
			client.addProdutos(produtos);
			itens = client.getAllProdutos();
			
			
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
	
	public void excluir(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		ProdutosClient client = applicationContext.getBean(ProdutosClient.class);
		try {
			/*ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produtos);
			
			
			itens = fdao.listar();*/
			client.deleteProdutos(produtos.getCodigo());
			itens = client.getAllProdutos();
			
			
			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
	
	public void editar(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		ProdutosClient client = applicationContext.getBean(ProdutosClient.class);
		try {
			/*ProdutoDAO fdao = new ProdutoDAO();
			fdao.editar(produtos);
			
			
			itens = fdao.listar();*/
			client.updateProdutos(produtos);
			itens = client.getAllProdutos();
			
			
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
	
	public void prepararEditar(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		FornecedoresClient client = applicationContext.getBean(FornecedoresClient.class);
		
		try {
			produtos = new Produtos();
			/*FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();*/
			comboFornecedores = client.getAllFornecedores();
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
}