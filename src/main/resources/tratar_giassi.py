import re

medidas = ['ml', 'l', 'kg', 'g', 'un']
marcas = ['sadia', 'maggi', 'milka', 'friolar', 'tirol', 'lac lélo', 'duçula', 'giassi', 'heinig', 'maxim', 'maxin', 'président', 'coamo', 'união', 'piracanjuba', 'limpol', 'melitta', 'armazém', 'araras', 'bombril', 'dois irmaos', 'duetto', 'yoki', 'mili', 'elefante', 'fabiany', 'free lar', 'doçura do vale', 'coca-cola', 'pepsi', 'zizo', 'vivaris', 'oderich', 'bauducco', 'rocha alimentos', 'união', 'tio urbano', 'chácara turnes', 'neve', 'seven boys', 'perdigão', 'parmíssimo', 'président', 'soya', 'qualy', '8galo', 'moça', 'omo', 'scotch-brite', 'brilhus', 'limpol', 'qboa', 'orquídea', 'bonare', 'araras', 'marquespan', 'prat´s', 'nat verde', 'girando sol', 'suq', 'nescau', 'lacta', 'radium', 'famiglia venturelli', 'forno de minas', 'parati', 'vigor', 'mccain', 'cruzília', '']
with open('produtos-giassi1709.csv', encoding="utf8") as fileID:
	primeiraLinha = True
	for line in fileID.readlines():
#		print(line.strip())
		if primeiraLinha:
			primeiraLinha = False
			continue
		valor = float(line.split('","')[2].strip('"'))
		descricao = line.split('","')[1].strip('"').lower()
		medida = ''
		valorMedida = ''
		produto = ''
		marca = ''
		for marca in marcas:
			if marca in descricao:
				descricao = re.sub(marca, '', descricao)
				break
		if marca=='':
			input(line)
		for medida in medidas:
			if re.match('.*?[0-9]+'+medida+'\b.*', descricao): # para casos como 310ml
				produto = re.sub('(.*?) [0-9]+'+medida+'\b.*', '\\1', descricao)
				valorMedida = re.sub('.*? ([0-9]+)'+medida+'\b.*', '\\1', descricao)
				break
			elif re.match('.*?[0-9]+ '+medida+'\b.*', descricao): # para 310 ml
				produto = re.sub('(.*?) [0-9]+ '+medida+'\b.*', '\\1', descricao)
				valorMedida = re.sub('.*? ([0-9]+) '+medida+'\b.*', '\\1', descricao)
				break
			elif re.match('.*? '+medida+'\b.*', descricao): # para kg, sem valor
				produto = re.sub('(.*?) '+medida+'\b.*', '\\1', descricao)
				valorMedida = 1
				break
		print(descricao, ' ## ', produto, '#', valorMedida, '#', medida, marca)