package com.luizaLabs.compras;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizaLabs.compras.domain.model.Produto;
import com.luizaLabs.compras.domain.repository.ProdutoRepository;
import com.luizaLabs.compras.util.DatabaseCleaner;
import com.luizaLabs.compras.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroProdutosIT {
	
	private Produto produto;
	private int contaProduto = 0;
	private String jsonCorretoProdutoTeste;
	
	private static final int PRODUTO_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	
	@Before
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/produtos";
		
		databaseCleaner.clearTables();
		prepararDados();
		
		jsonCorretoProdutoTeste = ResourceUtils.getContentFromResource(
				"/json/correto/produto-teste.json");
		
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarProdutos() {

		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConterQuantidadeDeProdutosInseridasPeloPrepararDados_QuandoConsultarProdutos() {

		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(contaProduto));
	}
	
	@Test
	public void deveRetornar201_QuandoCadastrarProduto() {
		given()
			.body(jsonCorretoProdutoTeste)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarProdutoExistente() {
		given()
			.pathParam("produtoId", produto.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{produtoId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(produto.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarProdutoInexistente() {
		given()
			.pathParam("produtoId", PRODUTO_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{produtoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		Produto produto1 = new Produto();
		produto1.setNome("Celular 1");
		produtoRepository.save(produto1);
		
		Produto produto2 = new Produto();
		produto2.setNome("Celular 2");
		produtoRepository.save(produto2);
		
		Produto produto3 = new Produto();
		produto3.setNome("Celular 3");
		produtoRepository.save(produto3);
		
		Produto produto4 = new Produto();
		produto4.setNome("Celular 4");
		produtoRepository.save(produto4);
		
		
		contaProduto = (int) produtoRepository.count();
		
	}
}
