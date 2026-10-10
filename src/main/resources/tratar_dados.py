#! /usr/bin/python

import re, sys

medidas = ['ml', 'l', 'kg', 'gr', 'g', 'un', 'unidades', 'unidade']
produtos = {}
pmarca = {}
traducoes = {}

def eprint(arg): print(arg, file=sys.stderr)

marcas = []
#maxlen = 0
with open('marcas.txt') as fileID:
	for marca in fileID.readlines():
		marca = marca.strip().lower()
		if marca and marca not in marcas:
			marcas.append(marca)
#			if len(marca)>maxlen: maxlen = len(marca)
#marcas = sorted(marcas)
#while maxlen>0:
#	for marca in marcas:
#		if len(marca)==maxlen:
#			print(marca)
#	maxlen -= 1
#exit()

with open('dicionario_produtos.txt') as fileID:
	for line in fileID.readlines():
		if line.strip() and line[0]!='#':
			traducoes[line.split('|')[0].lower()] = line.split('|')[1].strip('\n').lower()

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
	if 'leve' in descricao and 'pague' in descricao:
		descricao = re.sub(' leve (.*) pague [^ ]+', ' \\1', descricao)
	descricao = descricao.replace('  ', ' ')+' '
	for key in traducoes:
		descricao = descricao.replace(key, traducoes[key])
	descricao = descricao.strip().replace('  ', ' ')
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
		else:
			produto = descricao
			valorMedida = '1'
			medida = 'un'

	produto = produto.title().replace(' E ', ' e ').replace(' Da ', ' da ').replace(' De ', ' de ').replace(' Do ', ' do ').replace(' Em ', ' em ').replace(' Com ', ' com ').replace(' Sem ', ' sem ').replace(' Para ', ' para ')

	medida = medida.replace('l', 'L').replace('gr','g').replace('unidades', 'un').replace('unidade', 'un')
	valorMedida = valorMedida.replace(',', '.')
	n += 1
	if produto not in produtos:
		produtos[produto] = 0
		pmarca[produto] = marca
	produtos[produto] += 1

	if not produto.strip():
		eprint(produto + ' | ' + line.strip())

#exit()
#print(medidas)
for prod in sorted(produtos):
	print(produtos[prod], prod, '||', pmarca[prod])
eprint('%d %d' % (n, len(produtos)))
