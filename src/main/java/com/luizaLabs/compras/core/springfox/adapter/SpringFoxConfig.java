package com.luizaLabs.compras.core.springfox.adapter;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luizaLabs.compras.api.exceptionHandler.Problem;
import com.luizaLabs.compras.api.model.model.ClienteModel;
import com.luizaLabs.compras.api.model.model.CompraResumoModel;
import com.luizaLabs.compras.api.model.model.ProdutoModel;
import com.luizaLabs.compras.api.openapi.model.ClientesModelOpenApi;
import com.luizaLabs.compras.api.openapi.model.ComprasResumoModelOpenApi;
import com.luizaLabs.compras.api.openapi.model.LinksModelOpenApi;
import com.luizaLabs.compras.api.openapi.model.PageableModelOpenApi;
import com.luizaLabs.compras.api.openapi.model.ProdutosModelOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {

		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.luizaLabs.compras.api"))
				.paths(PathSelectors.ant("/**"))
				.build().useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.globalResponseMessage(RequestMethod.PATCH, globalPatchResponseMessages())
				.ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
						Resource.class, File.class, InputStream.class)
				.additionalModels(typeResolver.resolve(Problem.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, CompraResumoModel.class), ComprasResumoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, ProdutoModel.class), ProdutosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, ClienteModel.class), ClientesModelOpenApi.class))
				.apiInfo(apiInfo()).tags(new Tag("Compras", "Gerencia compras"), 
						new Tag("Clientes", "Gerencia clientes"), 
						new Tag("Produtos", "Gerencia rodutos"));
	}
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}

	private List<ResponseMessage> globalPatchResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro Interno do servidor").responseModel(new ModelRef("Problema")).build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).responseModel(new ModelRef("Problema"))
						.message("Requisição inválida").build());
	}

	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro Interno do servidor").responseModel(new ModelRef("Problema")).build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).responseModel(new ModelRef("Problema"))
						.message("Requisição inválida").build());
	}

	private List<ResponseMessage> globalPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro Interno do servidor").responseModel(new ModelRef("Problema")).build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).message("Requisição inválida")
						.responseModel(new ModelRef("Problema")).build(),
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Requisição recusada porque o corpo está em um formato não suportado").build());
	}

	private List<ResponseMessage> globalPostResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro Interno do servidor").responseModel(new ModelRef("Problema")).build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).message("Requisição inválida")
						.responseModel(new ModelRef("Problema")).build(),
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Requisição recusada porque o corpo está em um formato não suportado").build());
	}

	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro Interno do servidor").responseModel(new ModelRef("Problema")).build(),

				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor.").build());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API de compras Magalu").description("API desenvolvida para o desafio técnico backend")
				.version("1.0").contact(new Contact("Vênio Ferreira", "https:/venio.com", "venioferreira@gmail.com")).build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
