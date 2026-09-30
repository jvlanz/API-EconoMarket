#! /usr/bin/python

import re

medidas = ['ml', 'l', 'kg', 'g', 'un', 'unidades']
produtos = {}
pmarca = {}

marcas = []
with open('marcas.txt') as fileID:
	line = fileID.readline()
	for marca in line.lower().split(','):
		marca = marca.strip()
		if marca not in marcas:
			marcas.append(marca)

fileID1 = open('produtos-koch2309.csv')
fileID2 = open('produtos-giassi1709.csv')

n = 0
for line in fileID1.readlines()[1:] + fileID2.readlines()[1:]:
	if len(line.split('","'))>5 and 'Eletro' in line.split('","')[5]: continue
	valor = float(line.split('","')[2].strip('"'))
	descricao = line.split('","')[1].strip('"').lower().replace('  ', ' ')
	medida = ''
	valorMedida = ''
	produto = ''
	marca = ''
	for m in marcas:
		if m in descricao: # teste rápido para agilizar o processamento
			if re.match(r'.*\b'+m+r'\b.*', descricao):
				marca = m
				descricao = re.sub(r'\b'+marca+r'\b', '', descricao)  # evitar que marca 88 remova medida 88g
				break
#	if not marca:
#		marca = input(line)
#		if marca:
#			marcas.append(marca)
#		print(len(marcas), sorted(marcas))
	if medida=='unidades':
		medida = 'un'
	marca = marca.replace('´', "'").replace('`', "'")
	descricao = descricao.replace('  ', ' ').replace(' leve mais pague menos', '').replace(' leve mais pague manos', '').replace(' leve pague -', '')
	descricao = descricao.replace('130g', ' 130g').replace('450ml', ' 450ml').replace('700ml', ' 700ml').replace('c/', '').replace('vidro', 'vidro ').replace(' pó', ' pó ').replace('  ', ' ')
	descricao = descricao.replace(' com1', ' 1').replace(' com 1', ' 1').replace(' com 2', ' 2').replace(' com 3', ' 3').replace(' com 4', ' 4').replace(' com 5', ' 5').replace(' com 6', ' 6').replace(' com 7', ' 7').replace(' com 8', ' 8').replace(' com 9', ' 9')
	if 'leve' in descricao and 'pague' in descricao:
		descricao = re.sub(' leve (.*) pague.*', ' \\1', descricao)
	for medida in medidas:
		if re.match('.*?[0-9,\\.]+'+medida+r'\b.*', descricao): # para casos como 310ml
			produto = re.sub('(.*?) [0-9,\\.]+'+medida+r'\b.*', '\\1', descricao)
			valorMedida = re.sub('.*? ([0-9,\\.]+)'+medida+r'\b.*', '\\1', descricao)
			break
		elif re.match('.*?[0-9,\\.]+ '+medida+r'\b.*', descricao): # para 310 ml
			produto = re.sub('(.*?) [0-9,\\.]+ '+medida+r'\b.*', '\\1', descricao)
			valorMedida = re.sub('.*? ([0-9,\\.]+) '+medida+r'\b.*', '\\1', descricao)
			break
		elif re.match('.*? '+medida+r'\b.*', descricao): # para kg, sem valor
			produto = re.sub('(.*?) '+medida+r'\b.*', '\\1', descricao)
			valorMedida = '1'
			break
	if produto=='': # não encontrou nenhuma das unidades previstas
		if re.match('.* [0-9,\\.]+ .*', descricao): # para valor sem unidade (ex. 120 toalhas)
			produto = re.sub('(.*) [0-9,\\.]+ .*', '\\1', descricao)
			valorMedida = re.sub('.* ([0-9,\\.]+) .*', '\\1', descricao)
			medida = re.sub('.* [0-9,\\.]+ ([^ ]*).*', '\\1', descricao)
			medidas.append(medida)
	produto = produto.replace(' pacote', '').replace(' frasco', '').replace(' squeeze', '').replace(' cada', '').replace(' caixa', '').replace(' bandeja', '').replace(' pc', '').replace(' embalagem', '').replace(' pote', '').replace(' pague', '').replace(' sachê', '').replace(' vidro', '').replace(' pet ', ' ')
	produto = produto.replace('sabão barra', 'sabão em barra').replace('refri ', 'refrigerante ').replace('refresco em pó', 'refresco').replace('refresco sabor', 'refresco')
	produto = produto.replace('mozzarella', 'mussarela').replace('mozzarela', 'mussarela').replace('oleo ', 'óleo ')
	produto = produto.replace('lava roupas', 'lava-roupas').replace('lava louça', 'lava-louca').replace('grão de bico', 'grão-de-bico').replace('noz moscada', 'noz-moscada')
	produto = produto.replace('spaghetto', 'spaghetti').replace('espqguete', 'spaghetti').replace('macarrão de ', 'massa ').replace('macarrão', 'massa').replace('macarrao', 'massa')
	produto = produto.replace(' garrafa', '').replace('tipo1', 'tipo 1').replace(' copo', '').replace(' galão', '').replace('uva-passa', 'uva passa').replace('hot dog', 'hot-dog').replace('moveis', 'móveis').replace('lustra móveis', 'lustra-móveis').replace('capim limão', 'capim-limão').replace('laranja pera', 'laranja-pera')
	produto = produto.replace('cafe ', 'café ').replace(' moid', ' moíd').replace(' suin', ' suín').replace('coxao', 'coxão')
	produto = produto.replace('hamburguer', 'hambúrguer').replace('hambúrguer bovino', 'hambúrguer de carne bovina').replace('hambúrguer frango', 'hambúrguer de frango').replace('nhoque batata', 'nhoque de batata')
	produto = produto.replace(' meia lua', '').replace(' meia-lua', '').replace(' em pedaço', '').replace(' fracionado', '').replace(' cunha', '')
	produto = produto.replace('leav-in', 'leave-in').replace('leave in', 'leave-in').replace('leavi-in', 'leave-in')
	produto = produto.replace(' tipo 1', '').replace(' absorve mais molho', '').replace('zero açúcar', 'sem açúcar').replace(' sabor', '')
	if 'vinho' in produto:
		produto = produto.replace('chardonay', 'chardonnay')
		produto = produto.replace(' argentino', '').replace(' brasileiro', '').replace(' chileno', '').replace(' espanhol', '').replace(' francês', '').replace(' italiano', '').replace(' português', '').replace(' uruguaio', '').replace(' sulafricano', '')
	produto = produto.title().replace(' E ', ' e ').replace(' De ', ' de ').replace(' Em ', ' em ').replace(' Com ', ' com ').replace(' Sem ', ' sem ').replace(' Para ', ' para ')
	medida = medida.replace('l', 'L')
	valorMedida = valorMedida.replace(',', '.')
	n += 1
#	if 'santa rita' in line.lower():
#		print(line, marca, produto)
#		print('\n'+line.strip())
#		print('%.2f%%' % (n/25.47), descricao, ' ## ', produto, '#', valorMedida, '#', medida, '#', marca)
	if produto not in produtos:
		produtos[produto] = 0
		pmarca[produto] = marca
	produtos[produto] += 1

#exit()
#print(medidas)
for prod in sorted(produtos):
	print(produtos[prod], prod, '||', pmarca[prod])
print(n, len(produtos))
