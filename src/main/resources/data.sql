-- ==========================================
-- 1. TABELAS FIXAS (já fornecidas)
-- ==========================================

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
-- insert into cliente (nome, email, senha, telefone) values ('Anônimo', 'anon@email.com', '1234', '00 0000-0000');
-- insert into cliente (nome, email, senha, telefone) values ('João', 'joao@gmail.com', '1234', '47 9912-3456');
-- insert into cliente (nome, email, senha, telefone) values ('Maria Silva', 'maria.silva@gmail.com', 'abcd', '47 98877-1122');
-- insert into cliente (nome, email, senha, telefone) values ('Carlos Souza', 'carlos.souza@outlook.com', 'senha123', '48 99655-4433');
-- insert into cliente (nome, email, senha, telefone) values ('Ana Paula', 'anapaula@yahoo.com.br', 'ana2026', '47 99111-2233');
-- insert into cliente (nome, email, senha, telefone) values ('Lucas Oliveira', 'lucas.oli@gmail.com', 'lucas99', '11 98333-5566');
-- insert into cliente (nome, email, senha, telefone) values ('Beatriz Lima', 'bia.lima@gmail.com', 'beatriz1', '21 97788-9900');
-- insert into cliente (nome, email, senha, telefone) values ('Marcos Vinicius', 'marcos.v@hotmail.com', 'marcos00', '47 98444-1100');
-- insert into cliente (nome, email, senha, telefone) values ('Juliana Costa', 'ju.costa@gmail.com', 'jujuba', '41 99222-3344');
-- insert into cliente (nome, email, senha, telefone) values ('Roberto Dias', 'roberto.dias@bol.com.br', 'dias777', '47 99555-6677');
-- insert into cliente (nome, email, senha, telefone) values ('Fernanda Rocha', 'fe.rocha@gmail.com', 'rochafer', '51 98111-2233');
-- insert into cliente (nome, email, senha, telefone) values ('Gabriel Santos', 'gabriel.santos@gmail.com', 'gab123', '31 98822-4455');
-- insert into cliente (nome, email, senha, telefone) values ('Larissa Mendes', 'larissa.m@outlook.com', 'mendes10', '47 99333-7788');
-- insert into cliente (nome, email, senha, telefone) values ('Bruno Cardoso', 'bruno.cardoso@gmail.com', 'brunao', '61 98444-5566');
-- insert into cliente (nome, email, senha, telefone) values ('Camila Martins', 'camila.m@gmail.com', 'camilinha', '47 98999-0011');
-- insert into cliente (nome, email, senha, telefone) values ('Eduardo Ramos', 'edu.ramos@hotmail.com', 'dudu2026', '85 99111-8899');
-- insert into cliente (nome, email, senha, telefone) values ('Patrícia Alves', 'paty.alves@gmail.com', 'alvespat', '47 98222-3344');
-- insert into cliente (nome, email, senha, telefone) values ('Rodrigo Barbosa', 'rodrigo.b@gmail.com', 'digodigo', '41 99666-1122');
-- insert into cliente (nome, email, senha, telefone) values ('Vanessa Duarte', 'van.duarte@outlook.com', 'vanessa1', '47 98777-4455');
-- insert into cliente (nome, email, senha, telefone) values ('Thiago Farias', 'thiago.f@gmail.com', 'farias99', '11 99555-2211');
-- insert into cliente (nome, email, senha, telefone) values ('Mariana Ribeiro', 'mari.ribeiro@gmail.com', 'ribeiro88', '47 98123-4567');


-- ==========================================
-- 4. MARCAS (Algumas marcas para associar)
-- ==========================================
insert into marca (marca) values ('');    -- para produtos sem marca, ex. hortifruti
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
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Queijo Fatiado', 3, 'g', 150, 10.99, 16.99, 'queijo_fatiado.jpg');

-- Hortifruti (Cat 1)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Maçã Gala', 1, 'kg', 1, 7.99, 12.99, 'maca.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Banana Nanica', 1, 'kg', 1, 3.99, 5.99, 'banana.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Tomate Ítala', 1, 'kg', 1, 6.99, 12.99, 'tomate.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Batata Inglesa', 1, 'kg', 1, 3.99, 6.99, 'batata.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Cebola Branca', 1, 'kg', 1, 5.99, 8.99, 'cebola.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Alface Crespa', 1, 'un', 1, 4.99, 6.99, 'alface.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Cenoura', 1, 'kg', 1, 4.99, 6.99, 'cenoura.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Mamão Formosa', 1, 'kg', 1, 3.99, 5.99, 'mamao.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Abacaxi Pérola', 1, 'kg', 1, 6.99, 9.99, 'abacaxi.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Limão Tahiti', 1, 'kg', 1, 2.99, 4.99, 'limao.jpg');

-- Açougue (Cat 2)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Carne Bovina Alcatra', 2, 'kg', 1, 39.99, 59.99, 'alcatra.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Peito de Frango', 2, 'kg', 1, 29.99, 39.99, 'frango.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Carne Moída Bovina', 2, 'kg', 1, 29.99, 39.99, 'carne_moida.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Linguiça Toscana', 2, 'kg', 1, 19.99, 39.99, 'linguica.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Costelinha Suína', 2, 'kg', 1, 29.99, 39.99, 'costelinha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Filé Mignon', 2, 'kg', 1, 49.99, 89.99, 'file_mignon.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Coxa e Sobrecoxa', 2, 'kg', 1, 29.99, 39.99, 'coxa_sobrecoxa.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Picanha Bovina', 2, 'kg', 1, 49.99, 79.99, 'picanha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Bisteca Suína', 2, 'kg', 1, 29.99, 59.99, 'bisteca.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Coração de Frango', 2, 'g', 500, 9.99, 19.99, 'coracao.jpg');

-- Frios e Laticínios (Cat 3)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Leite Integral', 3, 'L', 1, 3.99, 5.99, 'leite.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Mussarela Peça', 3, 'g', 500, 24.99, 34.99, 'mussarela.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Presunto Cozido', 3, 'g', 200, 22.99, 29.99, 'presunto.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Iogurte Natural', 3, 'g', 170, 2.99, 3.45, 'iogurte.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Manteiga com Sal', 3, 'g', 200, 4.99, 9.99, 'manteiga.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Requeijão Cremoso', 3, 'mL', 250, 4.99, 9.99, 'requeijao.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Queijo Prato', 3, 'g', 150, 5.99, 8.99, 'queijo_prato.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Leite Condensado', 3, 'mL', 395, 4.99, 8.99, 'leite_condensado.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Creme de Leite', 3, 'mL', 200, 4.59, 6.89, 'creme_leite.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Parmesão Ralado', 3, 'g', 150, 5.89, 9.87, 'parmesao.jpg');

-- Congelados (Cat 4)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pizza de Frango', 4, 'un', 1, 12.99, 16.99, 'pizza.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Lasanha à Bolonhesa', 4, 'un', 1, 8.99, 15.99, 'lasanha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pão de Queijo', 4, 'g', 50, 6.99, 8.99, 'pao_queijo.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Batata Frita Congelada', 4, 'kg', 1, 8.99, 15.99, 'batata_frita.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Hamburguer Bovino', 4, 'g', 100, 4.99, 6.99, 'hamburguer.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Nuggets de Frango', 4, 'kg', 1, 8.99, 16.99, 'nuggets.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Seleta de Legumes', 4, 'g', 250, 3.99, 5.99, 'seleta.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Ervilha Congelada', 4, 'g', 500, 4.99, 6.99, 'ervilha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Torta de Palmito', 4, 'un', 1, 9.99, 17.99, 'torta.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Prato Feito Congelado', 4, 'g', 400, 18.99, 28.98, 'prato_feito.jpg');

-- Mercearia (Cat 5)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Arroz Branco 5kg', 5, 'kg', 5, 12.99, 16.99, 'arroz.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Feijão Preto 1kg', 5, 'kg', 1, 7.99, 9.99, 'feijao.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Óleo de Soja', 5, 'mL', 900, 6.99, 10.99, 'oleo.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Açúcar Cristal', 5, 'kg', 1, 5.99, 7.99, 'acucar.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Café em Pó', 5, 'g', 500, 49.99, 78.99, 'cafe.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Macarrão Espaguete', 5, 'g', 500, 3.99, 6.99, 'macarrao.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Farinha de Trigo', 5, 'kg', 1, 2.99, 4.99, 'farinha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Sal Refinado', 5, 'kg', 1, 1.99, 2.49, 'sal.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Molho de Tomate', 5, 'mL', 340, 2.99, 3.99, 'molho.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Atum em Lata', 5, 'g', 170, 4.99, 7.99, 'atum.jpg');

-- Limpeza (Cat 6)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Sabão em Pó', 6, 'kg', 1, 8.99, 12.99, 'sabao_po.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Amaciante de Roupas', 6, 'L', 2, 24.99, 32.99, 'amaciante.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Detergente Líquido', 6, 'mL', 500, 2.99, 3.45, 'detergente.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Desinfetante', 6, 'mL', 200, 4.99, 8.99, 'desinfetante.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Água Sanitária', 6, 'L', 2, 1.99, 2.49, 'agua_sanitaria.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Esponja de Aço', 6, 'un', 1, 1.49, 1.99, 'esponja_aco.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Limpador Multiuso', 6, 'mL', 500, 1.99, 3.99, 'desinfetante.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Saco de Lixo 50L', 6, 'un', 20, 2.99, 4.98, 'saco_lixo.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Flanela de Limpeza', 6, 'un', 3, 4.99, 5.99, 'flanela.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Papel Toalha', 6, 'un', 2, 6.99, 8.99, 'papel_toalha.jpg');

-- Higiene e Beleza (Cat 7)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Papel Higiênico 12un', 7, 'un', 12, 16.99, 18.99, 'papel_higienico.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pasta de Dente', 7, 'g', 90, 2.99, 3.02, 'pasta_dente.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Sabonete em Barra', 7, 'g', 90, 2.45, 3.21, 'sabonete.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Shampoo', 7, 'mL', 350, 13.99, 25.99, 'shampoo.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Condicionador', 7, 'mL', 350, 10.99, 34.98, 'condicionador.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Desodorante Aerosol', 7, 'mL', 150, 8.99, 14.99, 'desodorante.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Aparelho de Barbear', 7, 'un', 2, 25.99, 32.99, 'navalha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Fio Dental', 7, 'm', 50, 2.99, 4.99, 'fio_dental.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Absorvente Íntimo', 7, 'un', 8, 7.99, 14.99, 'absorvente.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Algodão Bola', 7, 'pacote', 1, 8.99, 10.99, 'algodao.jpg');

-- Bebidas (Cat 8)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Refrigerante Cola 2L', 8, 'L', 2, 13.99, 25.99, 'refri_lata.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Cerveja Lata', 8, 'mL', 350, 3.99, 8.99, 'cerveja.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Água Mineral sem Gás', 8, 'mL', 500, 1.49, 1.89, 'agua.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Suco de Laranja Caixinha', 8, 'L', 1, 5.99, 8.99, 'suco.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Energético', 8, 'mL', 473, 12.99, 17.99, 'energetico.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Vinho Tinto Seco', 8, 'mL', 750, 17.50, 99.99, 'vinho.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Água Tônica', 8, 'mL', 350, 3.99, 5.99, 'tonica.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Chá Gelado', 8, 'L', 1, 13.99, 18.99, 'cha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Refrigerante Guaraná', 8, 'mL', 350, 2.99, 4.50, 'refri_lata.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Isotônico', 8, 'mL', 500, 4.99, 7.65, 'isotonico.jpg');

-- Utilidades (Cat 9)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pilhas AA', 9, 'un', 4, 14.99, 19.99, 'pilha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Lâmpada LED', 9, 'un', 1, 24.99, 28.99, 'lampada.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Fita Isolante', 9, 'un', 1, 1.99, 2.99, 'fita.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Fósforos de Segurança', 9, 'un', 10, 2.99, 5.43, 'fosforo.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Vela Branca', 9, 'un', 8, 1.89, 2.45, 'vela.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pano de Chão', 9, 'un', 1, 2.99, 3.45, 'pano_chao.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pregador de Roupas', 9, 'un', 12, 0.89, 1.99, 'pregador.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Saco Plástico Zip', 9, 'un', 20, 3.99, 6.55, 'saco_zip.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Papel Alumínio', 9, 'un', 1, 6.99, 12.21, 'aluminio.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Filme Plástico', 9, 'un', 1, 4.99, 6.54, 'filme.jpg');

-- Padaria (Cat 10)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pão Francês', 10, 'g', 50, 6.99, 7.99, 'pao_frances.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pão de Forma', 10, 'g', 450, 7.99, 9.99, 'pao_forma.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Bolo de Chocolate', 10, 'kg', 1, 32.99, 45.99, 'bolo.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Rosquinha Doce', 10, 'g', 50, 5.99, 8.77, 'rosquinha.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pão Integral', 10, 'g', 100, 8.99, 15.99, 'pao_integral.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Sonho de Creme', 10, 'g', 50, 5.99, 8.79, 'sonho.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Broa de Milho', 10, 'g', 100, 6.99, 10.99, 'broa.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Torrada Integral', 10, 'g', 100, 10.99, 13.99, 'torrada.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Cuca de Banana', 10, 'g', 500, 17.99, 24.99, 'cuca.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Pão de Hamburguer', 10, 'g', 100, 10.99, 13.99, 'pao_hamburguer.jpg');

-- Pets (Cat 11)
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Ração Cão Adulto', 11, 'kg', 1, 45.99, 99.99, 'racao_cao.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Ração Gato Filhote', 11, 'kg', 1, 49.99, 120.99, 'racao_gato.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Sachê para Gato', 11, 'sache', 1, 4.99, 6.99, 'sache_gato.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Tapete Higiênico', 11, 'un', 30, 20.99, 29.99, 'tapete_higi.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Areia para Gato', 11, 'kg', 4, 10.99, 14.99, 'areia_gato.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Biscoito para Cão', 11, 'un', 1, 2.99, 4.99, 'biscoito_pet.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Shampoo Pet', 11, 'mL', 500, 15.99, 20.99, 'shampoo_pet.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Osso de Brinquedo', 11, 'un', 1, 3.99, 6.99, 'osso.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Antipulgas', 11, 'mL', 100, 15.99, 19.99, 'antipulgas.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Ossinho para Cães', 11, 'un', 1, 7.99, 13.99, 'ossinho.jpg');
insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Ração Cão Filhote', 11, 'kg', 1, 45.99, 99.99, 'racao_cao.jpg');


-- ==========================================
-- 6. RELACIONAMENTO PRODUTO-MARCA (Exemplos)
-- ==========================================
insert into produto_marca (id_produto, id_marca) values (1, 2);
insert into produto_marca (id_produto, id_marca) values (2, 3);
insert into produto_marca (id_produto, id_marca) values (21, 4);
insert into produto_marca (id_produto, id_marca) values (41, 11);
insert into produto_marca (id_produto, id_marca) values (51, 6);
insert into produto_marca (id_produto, id_marca) values (52, 7);
insert into produto_marca (id_produto, id_marca) values (71, 8);
insert into produto_marca (id_produto, id_marca) values (72, 9);
insert into produto_marca (id_produto, id_marca) values (45, 10);
insert into produto_marca (id_produto, id_marca) values (11, 5);

-- Inserções para a tabela 'produto_marca' (300 registros)

insert into produto_marca (id_produto, id_marca) values (1, 4);
insert into produto_marca (id_produto, id_marca) values (2, 2);
insert into produto_marca (id_produto, id_marca) values (3, 2);
insert into produto_marca (id_produto, id_marca) values (4, 3);
insert into produto_marca (id_produto, id_marca) values (5, 3);
insert into produto_marca (id_produto, id_marca) values (6, 3);
insert into produto_marca (id_produto, id_marca) values (7, 4);
insert into produto_marca (id_produto, id_marca) values (8, 4);
insert into produto_marca (id_produto, id_marca) values (9, 4);
insert into produto_marca (id_produto, id_marca) values (10, 5);
insert into produto_marca (id_produto, id_marca) values (11, 5);
insert into produto_marca (id_produto, id_marca) values (12, 5);
insert into produto_marca (id_produto, id_marca) values (13, 6);
insert into produto_marca (id_produto, id_marca) values (14, 6);
insert into produto_marca (id_produto, id_marca) values (15, 6);
insert into produto_marca (id_produto, id_marca) values (16, 7);
insert into produto_marca (id_produto, id_marca) values (17, 7);
insert into produto_marca (id_produto, id_marca) values (18, 7);
insert into produto_marca (id_produto, id_marca) values (19, 8);
insert into produto_marca (id_produto, id_marca) values (20, 8);
insert into produto_marca (id_produto, id_marca) values (21, 8);
insert into produto_marca (id_produto, id_marca) values (22, 9);
insert into produto_marca (id_produto, id_marca) values (23, 9);
insert into produto_marca (id_produto, id_marca) values (24, 9);
insert into produto_marca (id_produto, id_marca) values (25, 10);
insert into produto_marca (id_produto, id_marca) values (26, 10);
insert into produto_marca (id_produto, id_marca) values (27, 10);
insert into produto_marca (id_produto, id_marca) values (28, 11);
insert into produto_marca (id_produto, id_marca) values (29, 11);
insert into produto_marca (id_produto, id_marca) values (30, 11);

insert into produto_marca (id_produto, id_marca) values (1, 3);
insert into produto_marca (id_produto, id_marca) values (1, 5);


-- ==========================================
-- 7. LISTA DE COMPRAS
-- ==========================================
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (1, 1, 1);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (1, 2, 10);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (1, 3, 3);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (1, 4, 1);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (1, 5, 2);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (1, 6, 1);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (2, 3, 1);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (3, 4, 1);
-- insert into lista_compra (id_cliente, id_produto, quantidade) values (4, 5, 1);


-- ==========================================
-- 8. PREÇOS
-- ==========================================
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 1, 14.99, '2026-08-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 1, 7.50, '2026-08-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 8.90, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 3, 24.50, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (5, 4, 3.99, '2026-08-24');

insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 2, 34.02, '2026-08-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 19.34, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 22.86, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 4, 38.31, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 5, 23.32, '2026-07-29');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 35.86, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 1, 13.97, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 1, 19.46, '2026-08-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 2, 14.13, '2026-08-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 30.50, '2026-07-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 3, 44.57, '2026-08-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 3, 24.31, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 31.81, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 5, 27.60, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 23.18, '2026-08-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 32.83, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 48.64, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 4, 31.06, '2026-08-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 20.47, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 19.64, '2026-08-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 7.82, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 3, 33.68, '2026-08-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 35.80, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (18, 5, 41.13, '2026-07-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 4, 19.78, '2026-08-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (6, 5, 12.39, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 3, 33.40, '2026-07-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 5, 15.68, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 18.00, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 3, 44.80, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 4, 30.65, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 49.30, '2026-07-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 47.96, '2026-07-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 1, 35.91, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 4, 25.13, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 2, 17.51, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 4, 11.23, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 15.11, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 4, 43.14, '2026-07-13');
insert into preco (id_produto_marca, id_mercado, preco, data) values (9, 2, 44.52, '2026-07-24');
insert into preco (id_produto_marca, id_mercado, preco, data) values (6, 5, 23.36, '2026-08-03');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 25.59, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 5, 29.80, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (11, 4, 7.82, '2026-07-03');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 5, 42.44, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 26.24, '2026-08-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 20.91, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (18, 5, 33.68, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 4, 45.31, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 14.18, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 3, 24.23, '2026-07-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (31, 3, 25.30, '2026-08-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 2, 26.31, '2026-07-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 10.36, '2026-07-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 4, 21.05, '2026-08-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 3, 40.23, '2026-08-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 2, 45.45, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 3, 23.36, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 42.06, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 41.56, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 1, 39.06, '2026-07-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 38.35, '2026-07-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 2, 36.98, '2026-07-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 1, 38.56, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 4, 17.56, '2026-08-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (32, 4, 16.51, '2026-07-26');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 41.65, '2026-07-30');
insert into preco (id_produto_marca, id_mercado, preco, data) values (9, 2, 23.75, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 1, 14.73, '2026-07-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 33.19, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 22.46, '2026-07-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 2, 35.80, '2026-08-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 2, 7.39, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 3, 29.56, '2026-07-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (6, 5, 41.48, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 17.70, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 2, 29.98, '2026-08-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 3, 30.06, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 14.18, '2026-08-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 29.83, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 3, 10.82, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (18, 5, 23.33, '2026-07-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 41.97, '2026-07-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 25.07, '2026-08-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 14.19, '2026-08-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 45.48, '2026-07-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 2, 42.06, '2026-08-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (18, 5, 33.56, '2026-08-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 23.23, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 1, 48.78, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 3, 11.23, '2026-08-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 24.36, '2026-07-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 3, 31.86, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 49.33, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 23.16, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 3, 18.06, '2026-08-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 30.69, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 3, 12.01, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 16.51, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 2, 25.29, '2026-07-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 20.30, '2026-08-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 4, 7.37, '2026-08-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 34.61, '2026-07-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 33.72, '2026-08-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 5, 23.95, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 31.42, '2026-07-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 5, 13.91, '2026-07-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (6, 5, 12.04, '2026-07-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (7, 4, 24.28, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 22.15, '2026-08-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 1, 33.15, '2026-07-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 21.05, '2026-07-24');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 7.55, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 14.16, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 4, 6.78, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 5, 41.69, '2026-08-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (7, 4, 49.33, '2026-07-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 5, 12.39, '2026-07-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 12.59, '2026-07-29');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 2, 7.82, '2026-07-29');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 40.59, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (7, 4, 30.56, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 4, 26.54, '2026-07-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 39.51, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 33.91, '2026-07-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 4, 34.66, '2026-08-03');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 27.60, '2026-08-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 4, 21.07, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 23.36, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 18.23, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 5, 23.15, '2026-07-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 15.69, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 1, 10.97, '2026-08-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 2, 36.31, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 1, 48.91, '2026-07-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 38.35, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 1, 49.38, '2026-08-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 4, 29.56, '2026-08-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 4, 37.05, '2026-08-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 24.38, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 1, 19.38, '2026-07-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 22.36, '2026-08-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 5, 41.56, '2026-07-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 2, 31.06, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (18, 5, 20.32, '2026-07-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 3, 21.05, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 2, 42.48, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 31.43, '2026-07-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 3, 25.10, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 49.52, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 2, 17.51, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 37.42, '2026-08-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 46.12, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 2, 32.74, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 31.97, '2026-08-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 4, 49.03, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 46.12, '2026-08-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 37.12, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (26, 4, 34.69, '2026-07-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (7, 3, 36.63, '2026-07-13');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 11.23, '2026-07-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 1, 26.68, '2026-07-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 4, 21.05, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 25.10, '2026-07-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 1, 14.86, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 3, 25.21, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 35.32, '2026-07-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 21.31, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 2, 40.56, '2026-08-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (9, 1, 14.93, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (6, 5, 33.15, '2026-07-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 46.12, '2026-08-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 1, 33.36, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 23.36, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 3, 15.68, '2026-08-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (11, 4, 18.23, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 4, 22.06, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 23.97, '2026-08-03');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 2, 29.56, '2026-08-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 5, 23.95, '2026-08-12');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 43.19, '2026-08-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 5, 35.86, '2026-08-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 31.06, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 24.31, '2026-08-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 33.72, '2026-07-29');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 16.51, '2026-07-27');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 23.31, '2026-08-13');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 3, 20.32, '2026-08-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 45.41, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 47.96, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 3, 31.18, '2026-07-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (9, 2, 17.65, '2026-07-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 41.56, '2026-08-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 18.06, '2026-08-13');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 30.56, '2026-08-09');
insert into preco (id_produto_marca, id_mercado, preco, data) values (14, 3, 44.91, '2026-07-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 19.38, '2026-07-30');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 4, 18.15, '2026-07-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 1, 38.31, '2026-07-03');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 4, 48.91, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 5, 23.36, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (7, 4, 10.97, '2026-07-26');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 23.36, '2026-08-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 11.23, '2026-08-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 2, 13.91, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 2, 25.07, '2026-07-29');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 14.16, '2026-08-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 21.05, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 27.60, '2026-07-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 11.23, '2026-07-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 4, 41.56, '2026-08-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 4, 49.30, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 2, 26.54, '2026-08-15');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 14.65, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 19.38, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 2, 45.41, '2026-08-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 35.86, '2026-08-24');
insert into preco (id_produto_marca, id_mercado, preco, data) values (24, 5, 23.18, '2026-07-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 1, 23.36, '2026-08-24');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 44.91, '2026-08-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 1, 20.32, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (12, 4, 12.01, '2026-07-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (23, 1, 26.68, '2026-08-02');
insert into preco (id_produto_marca, id_mercado, preco, data) values (1, 1, 42.48, '2026-08-04');
insert into preco (id_produto_marca, id_mercado, preco, data) values (19, 1, 15.69, '2026-07-31');
insert into preco (id_produto_marca, id_mercado, preco, data) values (4, 1, 32.74, '2026-08-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 3, 26.31, '2026-08-23');
insert into preco (id_produto_marca, id_mercado, preco, data) values (11, 4, 46.12, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (20, 2, 33.72, '2026-08-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 5, 42.06, '2026-07-16');
insert into preco (id_produto_marca, id_mercado, preco, data) values (7, 4, 25.10, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (13, 2, 44.20, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 4, 30.06, '2026-07-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 2, 45.45, '2026-07-14');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 3, 11.23, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 4, 21.05, '2026-08-13');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 4, 46.12, '2026-07-06');
insert into preco (id_produto_marca, id_mercado, preco, data) values (25, 4, 37.42, '2026-07-05');
insert into preco (id_produto_marca, id_mercado, preco, data) values (28, 4, 24.36, '2026-07-26');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 31.06, '2026-08-24');
insert into preco (id_produto_marca, id_mercado, preco, data) values (11, 3, 34.02, '2026-08-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 40.59, '2026-07-21');
insert into preco (id_produto_marca, id_mercado, preco, data) values (22, 1, 23.36, '2026-07-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 2, 13.91, '2026-07-28');
insert into preco (id_produto_marca, id_mercado, preco, data) values (21, 3, 20.91, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 23.36, '2026-07-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (30, 2, 25.13, '2026-07-11');
insert into preco (id_produto_marca, id_mercado, preco, data) values (9, 2, 33.15, '2026-07-08');
insert into preco (id_produto_marca, id_mercado, preco, data) values (18, 5, 23.31, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (2, 2, 33.15, '2026-07-13');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 5, 14.86, '2026-08-22');
insert into preco (id_produto_marca, id_mercado, preco, data) values (29, 3, 10.82, '2026-07-01');
insert into preco (id_produto_marca, id_mercado, preco, data) values (17, 2, 30.56, '2026-07-07');
insert into preco (id_produto_marca, id_mercado, preco, data) values (8, 4, 33.19, '2026-08-25');
insert into preco (id_produto_marca, id_mercado, preco, data) values (27, 4, 30.69, '2026-07-19');
insert into preco (id_produto_marca, id_mercado, preco, data) values (11, 4, 25.07, '2026-07-17');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 2, 15.68, '2026-07-18');
insert into preco (id_produto_marca, id_mercado, preco, data) values (10, 4, 22.06, '2026-07-10');
insert into preco (id_produto_marca, id_mercado, preco, data) values (16, 3, 20.91, '2026-08-20');
insert into preco (id_produto_marca, id_mercado, preco, data) values (3, 2, 43.19, '2026-07-27');

