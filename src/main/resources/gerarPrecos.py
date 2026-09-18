#! /usr/bin/python3

# 2026-09-18

# insert into mercado (nome, link) values ('Koch', 'https://www.koch.com.br');
# insert into marca (marca) values ('Sadia');
# insert into produto (descricao, id_categoria, medida, valor_medida, preco_min, preco_max, figura) values ('Maçã Gala', 1, 'kg', 1, 7.99, 12.99, 'maca.jpg');
# insert into produto_marca (id_produto, id_marca) values (21, 4);
# insert into preco (id_produto_marca, id_mercado, preco, data) values (15, 3, 31.81, '2026-08-07');

import random

mercados = []
marcas = []
idProduto = 0
idProdutoMarca = 0

with open('data.sql') as fileID:
	for line in fileID.readlines():
		if 'insert into mercado' in line:
			mercados.append(line.split()[6].strip("'(,"))
		if 'insert into marca' in line:
			marcas.append(line.split()[5].strip("'();"))
		if 'insert into produto ' in line:
			idProduto += 1
			precoMin = float(line.split(',')[10].strip(','))
			precoMax = float(line.split(',')[11].strip(','))
			faixa = precoMax-precoMin
			for n in range(3): # escolher 3 marcas por produto
				idProdutoMarca += 1
				idMarca = random.randint(1,len(marcas))
				print('insert into produto_marca (id_produto, id_marca) values ('+str(idProduto)+', '+str(idMarca)+');')
				for idMercado in range(len(mercados)):
					preco = '%.2f' % (precoMin + faixa*random.random())
					print('insert into preco (id_produto_marca, id_mercado, preco, data) values ('+str(idProdutoMarca)+', '+str(idMercado+1)+', '+preco+', \'2026-09-18\');')

