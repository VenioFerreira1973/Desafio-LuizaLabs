create table produto(
	id bigint not null auto_increment,
	nome varchar(60) not null,
	descricao varchar(200),
	preco decimal(10,2) not null,
	valor_custo decimal(10,2) not null,
	margem_lucro decimal(10,2) not null,
	quantidade_produtos_vendidos smallint(6) not null,
	quantidade_em_estoque smallint(6) not null,
	lucro_total decimal(10,2) not null,
	primary key(id)
) engine=InnoDB default charset=utf8;


create table cliente (
	id bigint not null auto_increment, 
	data_cadastro datetime not null, 
	email varchar(80) not null, 
	nome varchar(80) not null,
	primary key(id) 
) engine=InnoDB default charset=utf8;

create table compra(
	id bigint not null auto_increment,
	cliente_id bigint not null,
	produto_id bigint not null,
	data_criacao datetime not null,
	quantidade smallint(6) not null,
	valor_total decimal(10,2),
	porcentagem_desconto decimal(10,2),
	valor_com_desconto decimal(10,2),
	primary key(id),
	unique key uk_compra_cliente_produto (id, produto_id, cliente_id),
	constraint fk_compra_cliente foreign key (cliente_id) references cliente (id),
	constraint fk_compra_produto foreign key (produto_id) references produto (id)
	
) engine=InnoDB default charset=utf8;


