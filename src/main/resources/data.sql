-- ==========================================
-- 1. TABELAS FIXAS (já fornecidas)
-- ==========================================
insert into medida (medida) values ('un');
insert into medida (medida) values ('dúzia');
insert into medida (medida) values ('g');
insert into medida (medida) values ('kg');
insert into medida (medida) values ('mL');
insert into medida (medida) values ('L');
insert into medida (medida) values ('pacote');
insert into medida (medida) values ('sachê');

insert into categoria (categoria) values ('Hortifruti');
insert into categoria (categoria) values ('Açougue');
insert into categoria (categoria) values ('Frios e Laticínios');
insert into categoria (categoria) values ('Congelados');
insert into categoria (categoria) values ('Mercearia');
insert into categoria (categoria) values ('Limpeza');
insert into categoria (categoria) values ('Higiene e Beleza');
insert into categoria (categoria) values ('Bebidas');
insert into categoria (categoria) values ('Utilidades');
insert into categoria (categoria) values ('Padaria');
insert into categoria (categoria) values ('Pets');


-- ==========================================
-- 2. MERCADOS (5 entradas)
-- ==========================================
insert into mercado (nome, link) values ('Koch', 'https://www.koch.com.br');
insert into mercado (nome, link) values ('Supermercado Central', 'https://www.central.com.br');
insert into mercado (nome, link) values ('Compre Bem', 'https://www.comprebem.com.br');
insert into mercado (nome, link) values ('Atacadão Mais', 'https://www.atacadaomais.com.br');
insert into mercado (nome, link) values ('Mercado Bom Preço', 'https://www.bompreco.com.br');


-- ==========================================
-- 3. CLIENTES (20 entradas)
-- ==========================================
insert into cliente (nome, email, senha, telefone) values ('João', 'joao@gmail.com', '1234', '47 9912-3456');
insert into cliente (nome, email, senha, telefone) values ('Maria Silva', 'maria.silva@gmail.com', 'abcd', '47 98877-1122');
insert into cliente (nome, email, senha, telefone) values ('Carlos Souza', 'carlos.souza@outlook.com', 'senha123', '48 99655-4433');
insert into cliente (nome, email, senha, telefone) values ('Ana Paula', 'anapaula@yahoo.com.br', 'ana2026', '47 99111-2233');
insert into cliente (nome, email, senha, telefone) values ('Lucas Oliveira', 'lucas.oli@gmail.com', 'lucas99', '11 98333-5566');
insert into cliente (nome, email, senha, telefone) values ('Beatriz Lima', 'bia.lima@gmail.com', 'beatriz1', '21 97788-9900');
insert into cliente (nome, email, senha, telefone) values ('Marcos Vinicius', 'marcos.v@hotmail.com', 'marcos00', '47 98444-1100');
insert into cliente (nome, email, senha, telefone) values ('Juliana Costa', 'ju.costa@gmail.com', 'jujuba', '41 99222-3344');
insert into cliente (nome, email, senha, telefone) values ('Roberto Dias', 'roberto.dias@bol.com.br', 'dias777', '47 99555-6677');
insert into cliente (nome, email, senha, telefone) values ('Fernanda Rocha', 'fe.rocha@gmail.com', 'rochafer', '51 98111-2233');
insert into cliente (nome, email, senha, telefone) values ('Gabriel Santos', 'gabriel.santos@gmail.com', 'gab123', '31 98822-4455');
insert into cliente (nome, email, senha, telefone) values ('Larissa Mendes', 'larissa.m@outlook.com', 'mendes10', '47 99333-7788');
insert into cliente (nome, email, senha, telefone) values ('Bruno Cardoso', 'bruno.cardoso@gmail.com', 'brunao', '61 98444-5566');
insert into cliente (nome, email, senha, telefone) values ('Camila Martins', 'camila.m@gmail.com', 'camilinha', '47 98999-0011');
insert into cliente (nome, email, senha, telefone) values ('Eduardo Ramos', 'edu.ramos@hotmail.com', 'dudu2026', '85 99111-8899');
insert into cliente (nome, email, senha, telefone) values ('Patrícia Alves', 'paty.alves@gmail.com', 'alvespat', '47 98222-3344');
insert into cliente (nome, email, senha, telefone) values ('Rodrigo Barbosa', 'rodrigo.b@gmail.com', 'digodigo', '41 99666-1122');
insert into cliente (nome, email, senha, telefone) values ('Vanessa Duarte', 'van.duarte@outlook.com', 'vanessa1', '47 98777-4455');
insert into cliente (nome, email, senha, telefone) values ('Thiago Farias', 'thiago.f@gmail.com', 'farias99', '11 99555-2211');
insert into cliente (nome, email, senha, telefone) values ('Mariana Ribeiro', 'mari.ribeiro@gmail.com', 'ribeiro88', '47 98123-4567');


-- ==========================================
-- 4. MARCAS (Algumas marcas para associar)
-- ==========================================
insert into marca (marca) values ('Friolac');
insert into marca (marca) values ('Sadia');
insert into marca (marca) values ('Nestlé');
insert into marca (marca) values ('Perdigão');
insert into marca (marca) values ('Ypê');
insert into marca (marca) values ('Omo');
insert into marca (marca) values ('Coca-Cola');
insert into marca (marca) values ('Amstel');
insert into marca (marca) values ('Pilão');
insert into marca (marca) values ('Camil');


-- ==========================================
-- 5. PRODUTOS (100 entradas distribuídas)
-- ==========================================
-- Exemplo já existente
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('queijo fatiado', 3, 3, 150, 'queijo.jpg');

-- Hortifruti (Cat 1)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Maçã Gala', 1, 4, 1, 'maca.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Banana Nanica', 1, 4, 1, 'banana.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Tomate Ítala', 1, 4, 1, 'tomate.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Batata Inglesa', 1, 4, 1, 'batata.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Cebola Branca', 1, 4, 1, 'cebola.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Alface Crespa', 1, 1, 1, 'alface.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Cenoura', 1, 4, 1, 'cenoura.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Mamão Formosa', 1, 4, 1, 'mamao.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Abacaxi Pérola', 1, 1, 1, 'abacaxi.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Limão Tahiti', 1, 4, 1, 'limao.jpg');

-- Açougue (Cat 2)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Carne Bovina Alcatra', 2, 4, 1, 'alcatra.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Peito de Frango', 2, 4, 1, 'frango.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Carne Moída Bovina', 2, 4, 1, 'carne_moida.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Linguiça Toscana', 2, 4, 1, 'linguica.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Costelinha Suína', 2, 4, 1, 'costelinha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Filé Mignon', 2, 4, 1, 'file_mignon.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Coxa e Sobrecoxa', 2, 4, 1, 'coxa_sobrecoxa.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Picanha Bovina', 2, 4, 1, 'picanha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Bisteca Suína', 2, 4, 1, 'bisteca.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Coração de Frango', 2, 3, 500, 'coracao.jpg');

-- Frios e Laticínios (Cat 3)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Leite Integral', 3, 6, 1, 'leite.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Mussarela Peça', 3, 4, 1, 'mussarela.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Presunto Cozido', 3, 3, 200, 'presunto.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Iogurte Natural', 3, 3, 170, 'iogurte.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Manteiga com Sal', 3, 3, 200, 'manteiga.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Requeijão Cremoso', 3, 3, 250, 'requeijao.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Queijo Prato', 3, 3, 150, 'queijo_prato.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Leite Condensado', 3, 3, 395, 'leite_condensado.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Creme de Leite', 3, 3, 200, 'creme_leite.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Parmesão Ralado', 3, 3, 50, 'parmesao.jpg');

-- Congelados (Cat 4)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pizza de Frango', 4, 1, 1, 'pizza.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Lasanha à Bolonhesa', 4, 7, 1, 'lasanha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pão de Queijo', 4, 7, 1, 'pao_queijo.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Batata Frita Congelada', 4, 7, 1, 'batata_frita.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Hamburguer Bovino', 4, 7, 1, 'hamburguer.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Nuggets de Frango', 4, 7, 1, 'nuggets.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Seleta de Legumes', 4, 7, 1, 'seleta.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Ervilha Congelada', 4, 7, 1, 'ervilha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Torta de Palmito', 4, 1, 1, 'torta.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Prato Feito Congelado', 4, 1, 1, 'prato_feito.jpg');

-- Mercearia (Cat 5)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Arroz Branco 5kg', 5, 7, 5, 'arroz.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Feijão Preto 1kg', 5, 7, 1, 'feijao.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Óleo de Soja', 5, 6, 900, 'oleo.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Açúcar Cristal', 5, 7, 1, 'acucar.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Café em Pó', 5, 7, 500, 'cafe.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Macarrão Espaguete', 5, 7, 500, 'macarrao.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Farinha de Trigo', 5, 7, 1, 'farinha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Sal Refinado', 5, 7, 1, 'sal.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Molho de Tomate', 5, 8, 340, 'molho.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Atum em Lata', 5, 1, 170, 'atum.jpg');

-- Limpeza (Cat 6)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Sabão em Pó', 6, 7, 1, 'sabao_po.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Amaciante de Roupas', 6, 6, 2, 'amaciante.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Detergentes Líquido', 6, 1, 500, 'detergente.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Desinfetante', 6, 6, 2, 'desinfetante.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Água Sanitária', 6, 6, 2, 'agua_sanitaria.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Esponja de Aço', 6, 7, 1, 'esponja_aco.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Limpador Multiuso', 6, 1, 500, 'multiuso.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Saco de Lixo 50L', 6, 7, 1, 'saco_lixo.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Flanela de Limpeza', 6, 1, 1, 'flanela.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Papel Toalha', 6, 7, 2, 'papel_toalha.jpg');

-- Higiene e Beleza (Cat 7)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Papel Higiênico 12un', 7, 7, 12, 'papel_higienico.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pasta de Dente', 7, 1, 90, 'pasta_dente.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Sabonete em Barra', 7, 1, 90, 'sabonete.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Shampoo', 7, 6, 350, 'shampoo.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Condicionador', 7, 6, 350, 'condicionador.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Desodorante Aerosol', 7, 1, 150, 'desodorante.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Aparelho de Barbear', 7, 1, 2, 'barbear.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Fio Dental', 7, 1, 50, 'fio_dental.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Absorvente Higiênico', 7, 7, 8, 'absorvente.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Algodão Bola', 7, 7, 1, 'algodao.jpg');

-- Bebidas (Cat 8)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Refrigerante Cola 2L', 8, 6, 2, 'reri_cola.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Cerveja Lata', 8, 1, 350, 'cerveja.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Água Mineral sem Gás', 8, 6, 500, 'agua.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Suco de Laranja Caixinha', 8, 6, 1, 'suco.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Energético', 8, 1, 473, 'energetico.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Vinho Tinto Seco', 8, 6, 750, 'vinho.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Água Tônica', 8, 1, 350, 'tonica.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Chá Gelado', 8, 6, 1, 'cha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Refrigerante Guaraná', 8, 6, 2, 'guarana.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Isotônico', 8, 6, 500, 'isotonico.jpg');

-- Utilidades (Cat 9)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pilhas AA', 9, 7, 4, 'pilha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Lâmpada LED', 9, 1, 1, 'lampada.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Fita Isolante', 9, 1, 1, 'fita.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Fósforos de Segurança', 9, 7, 10, 'fosforo.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Vela Branca', 9, 7, 8, 'vela.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pano de Chão', 9, 1, 1, 'pano_chao.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pregador de Roupas', 9, 7, 12, 'pregador.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Saco Plástico Zip', 9, 7, 20, 'saco_zip.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Papel Alumínio', 9, 7, 1, 'aluminio.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Filme Plástico', 9, 7, 1, 'filme.jpg');

-- Padaria (Cat 10)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pão Francês', 10, 4, 1, 'pao_frances.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pão de Forma', 10, 7, 1, 'pao_forma.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Bolo de Chocolate', 10, 1, 1, 'bolo.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Rosquinha Doce', 10, 7, 1, 'rosquinha.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pão Integral', 10, 7, 1, 'pao_integral.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Sonho de Creme', 10, 1, 1, 'sonho.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Broa de Milho', 10, 3, 100, 'broa.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Torrada Integrativa', 10, 7, 1, 'torrada.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Cuca de Banana', 10, 3, 500, 'cuca.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Pão de Hamburguer', 10, 7, 1, 'pao_hamburguer.jpg');

-- Pets (Cat 11)
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Ração Cão Adulto', 11, 4, 1, 'racao_cao.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Ração Gato Filhote', 11, 7, 1, 'racao_gato.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Sachê para Gato', 11, 8, 85, 'sache_gato.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Tapete Higiênico', 11, 7, 30, 'tapete_higi.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Areia para Gato', 11, 7, 4, 'areia_gato.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Biscoito para Cão', 11, 7, 1, 'biscoito_pet.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Shampoo Pet', 11, 6, 500, 'shampoo_pet.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Osso de Brinquedo', 11, 1, 1, 'osso.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Antipulgas', 11, 1, 1, 'antipulgas.jpg');
insert into produto (descricao, id_categoria, id_medida, valor_medida, figura) values ('Ossinho para Cães', 11, 7, 1, 'ossinho.jpg');


-- ==========================================
-- 6. RELACIONAMENTO PRODUTO-MARCA (Exemplos)
-- ==========================================
insert into produto_marca (id_produto, id_marca) values (1, 1);
insert into produto_marca (id_produto, id_marca) values (2, 2);
insert into produto_marca (id_produto, id_marca) values (21, 3);
insert into produto_marca (id_produto, id_marca) values (41, 10);
insert into produto_marca (id_produto, id_marca) values (51, 5);
insert into produto_marca (id_produto, id_marca) values (52, 6);
insert into produto_marca (id_produto, id_marca) values (71, 7);
insert into produto_marca (id_produto, id_marca) values (72, 8);
insert into produto_marca (id_produto, id_marca) values (45, 9);
insert into produto_marca (id_produto, id_marca) values (11, 4);


-- ==========================================
-- 7. LISTA DE COMPRAS
-- ==========================================
insert into lista_compra (id_produto_marca, id_mercado) values (1, 1);
insert into lista_compra (id_produto_marca, id_mercado) values (2, 1);
insert into lista_compra (id_produto_marca, id_mercado) values (3, 2);
insert into lista_compra (id_produto_marca, id_mercado) values (4, 3);
insert into lista_compra (id_produto_marca, id_mercado) values (5, 4);


-- ==========================================
-- 8. PREÇOS
-- ==========================================
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 1, 14.99, '2026-08-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 1, 7.50, '2026-08-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 8.90, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 3, 24.50, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (5, 4, 3.99, '2026-08-24');


-- ==========================================
-- 9. COMPRAS
-- ==========================================
insert into compra (id_cliente, id_lista_compra, data) values (1, 1, '2026-08-24');
insert into compra (id_cliente, id_lista_compra, data) values (2, 2, '2026-08-24');
insert into compra (id_cliente, id_lista_compra, data) values (3, 3, '2026-08-25');
insert into compra (id_cliente, id_lista_compra, data) values (4, 4, '2026-08-25');
insert into compra (id_cliente, id_lista_compra, data) values (5, 5, '2026-08-25');

