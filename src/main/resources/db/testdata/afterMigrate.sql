set foreign_key_checks = 0;

lock tables produto write, cliente write, compra write;

delete from produto;
delete from cliente;
delete from compra;

set foreign_key_checks = 1;

alter table produto auto_increment = 1;
alter table cliente auto_increment = 1;
alter table compra auto_increment = 1;

insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (1, 'Cápsulas Nespresso Café Italle 50 Unidades', 'Os cafés Italle são colhidos por pequenos produtores brasileiros.', 65.08, 35.90, 100, 25, 30, 729.50 );
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (2, 'iPhone 11 Apple 64GB Preto 6,1” 12MP iOS', 'Grave vídeos 4K, faça belos retratos e capture paisagens inteiras com o novo sistema de câmera dupla.', 2699.00, 1735.90, 4, 25, 30, 24077.5); 
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (3, 'Fritadeira Elétrica sem Óleo/Air Fryer Mondial', 'A nova fritadeira elétrica sem Óleo/Air Fryer da Mondial chega para completar seus momentos na cozinha.', 309.43, 225.90, 10, 25, 29, 2088.25);
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (4, 'Pneu Aro 13” 175/70R13 Goodyear 82T Touring', 'Hoje tem tantos modelos que pneus que você deve ficar perdido na hora de escolher um para o seu carro.', 307.91, 200.90, 10, 25, 100, 2675.25);
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (5, 'Smart TV 55” UHD 4K LED Samsung 55CU7700', 'Nova linha para você que sempre gosta de reunir a família e os amigos para assistir algum filme engraçado.', 2849.65, 2555.90, 600, 65, 100, 19093.75);
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (6, 'Cadeira Gamer Otello Preto e Vermelho', 'Para você que é fanático por games, além de ter equipamentos eletrônicos.', 379.71, 280.90, 700, 10, 250, 988.1);
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (7, 'Sanduicheira Amvox AMS 370', 'Prepare deliciosos sanduíches com a Sanduicheira AMS 370 da Amvox.', 77.89, 35.90, 100, 100, 18, 4.199);
insert into produto (id, nome, descricao, preco, valor_custo, margem_lucro, quantidade_produtos_vendidos, quantidade_em_estoque, lucro_total) values (8, 'Fritadeira Elétrica sem óleo', 'A fritadeira elétrica sem óleo/Air Fryer Mondial AFO-12L-BI Oven tem as cores preto e inox.', 743.07, 509.90, 100, 10, 30, 23317);

insert into cliente (id, nome, email, data_cadastro) values (1, 'João da Silva', 'joao.ger@gmail.com', utc_timestamp);
insert into cliente (id, nome, email, data_cadastro) values (2, 'Maria Joaquina', 'maria.vnd@gmail.com', utc_timestamp);
insert into cliente (id, nome, email, data_cadastro) values (3, 'José Souza', 'jose.aux@gmail.com', utc_timestamp);
insert into cliente (id, nome, email, data_cadastro) values (4, 'Sebastião Martins', 'sebastiao.cad@gmail.com', utc_timestamp);
insert into cliente (id, nome, email, data_cadastro) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', utc_timestamp);

insert into compra (id, cliente_id, produto_id, data_criacao, quantidade, valor_total, porcentagem_desconto, valor_com_desconto)
values (1, 1, 6, utc_timestamp, 1, 379.71, 10, 341.73);

insert into compra (id, cliente_id, produto_id, data_criacao, quantidade, valor_total, porcentagem_desconto, valor_com_desconto)
values (2, 2, 3, utc_timestamp, 1, 309.43, 10, 278.48);

insert into compra (id, cliente_id, produto_id, data_criacao, quantidade, valor_total, porcentagem_desconto, valor_com_desconto)
values (3, 3, 1, utc_timestamp, 1, 65.08, 0, 65.08);


insert into compra (id, cliente_id, produto_id, data_criacao, quantidade, valor_total, porcentagem_desconto, valor_com_desconto)
values (4, 4, 4, utc_timestamp, 1, 307.91, 5, 292.51);


insert into compra (id, cliente_id, produto_id, data_criacao, quantidade, valor_total, porcentagem_desconto, valor_com_desconto)
values (5, 5, 8, utc_timestamp, 1, 743.07, 20, 594.45);



unlock tables;