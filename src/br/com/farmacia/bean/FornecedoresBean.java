package br.com.farmacia.bean;

//import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.HttpClientErrorException;

//import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.restservice.AppConfig;
import br.com.farmacia.restservice.FornecedoresClient;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	
	private Fornecedores fornecedores;
	private ArrayList<Fornecedores>itens;
	private ArrayList<Fornecedores>itensFiltrados;
	
	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		FornecedoresClient client = applicationContext.getBean(FornecedoresClient.class);
	
		try {
			itens = client.getAllFornecedores();
			//FornecedoresDAO fdao = new FornecedoresDAO();
			//itens = fdao.listar();
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
		
	}

	public void prepararNovo(){
		fornecedores = new Fornecedores();
	}
	
	public void novo(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		FornecedoresClient client = applicationContext.getBean(FornecedoresClient.class);
		
		try {
			/*FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			
			itens = fdao.listar();*/
			client.addFornecedores(fornecedores);
			itens = client.getAllFornecedores();
			
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}

	public void excluir(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		FornecedoresClient client = applicationContext.getBean(FornecedoresClient.class);
		
		try {
			//FornecedoresDAO fdao = new FornecedoresDAO();
			//fdao.excluir(fornecedores);
			//itens = fdao.listar();
			client.deleteFornecedores(fornecedores.getCodigo());
			
			itens = client.getAllFornecedores();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("N�o � poss�vel excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}

	public void editar(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		FornecedoresClient client = applicationContext.getBean(FornecedoresClient.class);
		try {
			/*FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);
			
			
			itens = fdao.listar();*/
			client.updateFornecedores(fornecedores);;
			
			itens = client.getAllFornecedores();
			
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
			
		} catch (HttpClientErrorException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
}
