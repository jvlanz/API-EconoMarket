import re

medidas = ['ml', 'l', 'kg', 'g', 'un', 'unidades']
marcas = ['!waaw!', '3 corações', '7 belo', '7 montes', '8 galo', '88', '8galo', 'a tal da castanha', 'activia', 'adidas', 'adocyl', 'adria', 'aica', 'ajax', 'algotec', 'alhofree', 'alklin', 'almaromi', 'alpino', 'aminna', 'amstel', 'ana maria', 'andorinha', 'antarctica', 'apti', 'araras', 'araras', 'ares do campo', 'armazém', 'aromas do mar', 'aurora', 'aviação', 'axe', 'baby huggies', 'baggio', 'balardin', 'baly', 'barilla', 'barion', 'barista', 'batavo', 'bauducco', 'becel', 'becker', 'beija flor', 'beija-flor', 'beijubom', 'bellacotton', 'belvita', 'bem brasil', 'benjamim', 'biocrema', 'blumenau', 'bmh gourmet', 'bom ar', 'bom bril', 'bom destino', 'bom jesus', 'bombril', 'bonare', 'bono', 'bortoluzzi', 'bozzano', 'brahma', 'brazilian coffe', 'bretzke', 'brigitta', 'brilhante', 'brilhus', 'bruder alimentos', 'budweiser', 'butter toffees', 'búfalo dourado', 'caboclo', 'café do ponto', 'caldo bom', 'caldão', 'calisul', 'camil', 'campanini', 'campo largo', 'canlção', 'canção', 'carbonell', 'carefree', 'carlos', 'casa & perfume', 'castelo', 'catupiry', 'cebolitos', 'chambinho', 'chamex', 'chamyto', 'chandelle', 'cheetos', 'cheez-it', 'chelli', 'chemim', 'choco leite', 'chocolícia', 'chácara turnes', 'cica', 'cif', 'cisne', 'claybom', 'clear', 'clear men', 'clight', 'closeup', 'club social', 'coala', 'coamo', 'coca-cola', 'cocinero', 'coco do vale', 'colgate', 'comfort', 'concha y toro', 'condor', 'copacol', 'coperalcool', 'copobras', 'coposul', 'copra', 'corn flakes', 'corona', 'corpus', 'costa sul', 'cotton candy', 'cottonbaby', 'crinkle', 'cristalcopo', 'crocantíssimo', 'crokíssimi', 'crokíssimo', 'crunch', 'cruzilia', 'cruzília', 'crystal', 'cultura orgânica', 'cup noodles', 'da boa', 'da colônia', 'da márcia', 'daboa', 'dacolônia', 'damasco', 'danette', 'danone', 'danoninho', 'danubio', 'daoli', 'dauvan', 'del valle', 'delicia', 'delícia', 'destac', 'devile', 'dihellen', 'dipães', 'disner', 'doces caseiros sorocaba', 'dois frades', 'dois irmaos', 'dom bruno', 'dom fiorelo', 'dona benta', 'dona dida', 'dori', 'doriana', 'doritos', 'dove', 'downy', 'doçura do vale', 'dr. oetker', 'duessmann', 'duetto', 'duracell', 'duryea', 'duçula', 'elefante', 'elegê', 'elite', 'elma chips', 'elte', 'eqlibri panetini', 'esfrebom', 'estrela do vale', 'excelsior', 'fabiany', 'faixa azul', 'famiglia venturelli', 'fazenda', 'fazenda toca', 'fazenda trevisan', 'feliz', 'fernandes', 'finish', 'finish power', 'fit food', 'fleischmann', 'fleishmann', 'fofo', 'forno de minas', 'francis', 'free lar', 'freecô', 'friolar', 'friskies', 'fugini', 'fys', 'gaboardi', 'galak', 'gallo', 'galo', 'gantarctica', 'garibaldi', 'garota', 'garoto', 'garotão', 'gatorade', 'germani', 'giassi', 'gillette', 'gina', 'girando sol', 'gomes da costa', 'gotas de mel', 'gotinha de mel', 'gran legado', 'gran mestri', 'granado', 'grand hotel', 'grand mantiqueira', 'grelhaditos', 'guimarães', 'gusman', 'h2oh!', 'harpic', 'heineken', 'heinig', 'heinz', "hellmann's", 'hellmann´s', 'hemmer', 'henner', "hershey's", 'hershey´s', 'hoffmann', 'holandês', 'hort silva', 'horta zanolli', 'hortalicas silva', 'hortaliças silva', 'hortazanolli', 'ideale', 'iguaçu', 'imperatriz', 'inspira', 'intimus', 'isabela', 'itaipava', 'jasmine', 'jimo', 'johnson & johnson', "johnson's baby", 'johnson´s baby', 'joice', 'kalassi', 'kanty', "kellog's", "kellog's", "kellog's", "kellogg's", 'kellogg´s', 'kenko', 'kero coco', 'ki-carvão', 'kiarroz', 'kifritas', 'kinder', 'kinder bueno', 'kipipoka', 'kitano', 'kitkat', 'kleenex', 'knorr', 'kuat', "l'or", 'la española', 'la nonna', 'la pastina', 'la serenissima', 'lac lélo', 'lacta', 'lar', "lay's", 'leite niño', 'leitíssimo', 'lembeck', 'levit', 'leão', 'limpol', 'limpol', 'linea', 'linguinette arrighi', 'lipton', 'listerine', 'liza', 'lohn bier', 'lollo', 'lux', 'lysoform', 'l´or', "m&m's", 'mac tost', 'macedo', 'macedo iqf', 'machado', 'macrophytus', 'macrovita', 'mactost', 'maggi', 'magro', 'maguary', 'maille', 'maispura', 'mandiok', 'marilan', 'marquespan', 'max clean', 'max.decor', 'maxim', 'maxin', 'mccain', 'melitta', 'men bijou', 'mendorato', 'mentos', 'michelob ultra', 'mili', 'milka', 'mimmo', 'minamel', 'mineirinho', 'minueto', 'molico', 'mon bijou', 'monange', 'monde', 'monster', 'moça', 'mu-mu', 'muky', 'mãe luzia', 'mãe terra', 'nat', 'nat verde', 'negresco', 'nescafé', 'nescau', 'nesfit', 'nestlé', 'neumann', 'neve', 'ninho', 'nissin', 'nita', 'nivea', 'nordeste', 'nossa senhora do caravagio', 'not creme', 'not milk', 'notmilk', 'noviça', 'nude.', 'nutella', 'nutry', 'oderich', 'ofs', 'ola', 'olé', 'omo', 'oq', 'oral-b', 'oreo', 'orfeu', 'orquidea', 'orquídea', 'palmasul', 'pampers', 'pamplona', 'pantene', 'papapá', 'papapá biscotti', 'parati', 'parmalat', 'parmézzo', 'parmíssimo', 'passatempo', 'passe bem', 'pato', 'peccin', 'pedigree', 'pepsi', 'percicoti', 'perdigão', 'peroba', 'pescador', 'phebo', 'philadelphia', 'pilatti', 'pilão', 'pingo doce', 'pinho bril', 'pinho sol', 'pipicat', 'piracanjuba', 'pirakids', 'piraque', 'piraquê', 'planalto', 'polenghi', 'poliflor', 'polpanorte', 'pomarola', 'pomodoro', 'popcorners', 'porção certa rocha', 'poteitos', 'powerade', 'ppipicat', 'prat´s', 'prenda', 'prestígio', 'primalta', 'pringles', 'protex', 'président', 'président', 'pullman', 'pure fresh', 'pureza', 'purezinha', 'puro latte', 'qboa', 'quaker', 'qualimar', 'quality fries', 'qualy', 'quero', 'radium', 'rampineli', 'rampinelli', 'rap10', 'rayovac', 'rebeka', 'red bull', 'refinatto', 'rei redinha', 'renata', 'rexona', 'rinaldi', 'risovita', 'ritter', 'rocha', 'rocha alimentos', 'rolo feliz', 'rosani', 'roseflor', 'royal', 'royalpack', 'sabor a mi', 'sabor açoriano', 'sadia', 'safira', 'sakura', 'salsaretti', 'salton', 'salubi', 'san patrizio', 'sani-sol', 'sanjo', 'sanol dog', 'santa edwiges', 'santa massa', 'santa rita', 'sazón', 'schmitt', 'schweppes', 'schär', 'scotch-brite', 'scott', 'seara', 'seda', 'sempre livre', 'sensodyne', 'seven boys', 'sinhá', 'sitio schulter', 'slice', 'smirnoff', 'soberana', 'sococo', 'soft air', 'sol', 'sorriso', 'soya', 'sprite', 'st. dalfour', 'stella artois', 'strawplast', 'stylus', 'suflair', 'sulfrios', 'sun gold zespri', 'suq', 'surf', 'surpresa', 'suspirolândia', 'suvalan', 'sítio n.s. caravaggio', 'sítio santo expedito', 'sônia', 'tang', 'texas burger', 'thabrulai', 'tio joão', 'tio urbano', 'tirol', 'toblerone', 'toddy', 'torcida', 'tostines', 'tostitos', 'trakinas', 'trevisan', 'trident', 'trink', 'triunfo', 'tubarão', 'tupã', 'turma do pinicão', 'twinings', 'twix', 'uniagro', 'unik', 'união', 'união', 'urbano', 'utilis', 'vale fértil', 'vanish', 'veja', 'veneza', 'verd fácil', 'verde campo', 'versátil', 'vida macia', 'vida veg', 'vigor', 'vim', 'virtus', 'visconti', 'vital', 'vitao', 'vitapan', 'vitreo', 'viva livre', 'vivaris', 'weinmann', 'whiskas', 'wick', 'wickbold', 'wyda', 'yakult', 'yaí', 'yogurte 100', 'yoki', 'yopro', 'ypê', 'zavaski', 'zero cal', 'zezé', 'zilse', 'zizo', 'água da serra', 'águia', 'áurea', 'ávila']

n = 0
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
		for m in marcas:
			if ' '+m in descricao:
				marca = m
				descricao = re.sub(' '+marca+r'\b', '', descricao)  # evitar que marca 88 remova medida 88g
				break
#		if not marca:
#			marca = input(line)
#			if marca:
#				marcas.append(marca)
#			print(len(marcas), sorted(marcas))
		if marca=='unidades':
			marca = 'un'
		marca = marca.replace('´', "'").replace('`', "'")
		descricao = descricao.replace(' leve mais pague menos', '')
		if 'leve' in descricao and 'pague' in descricao:
			descricao = re.sub(' leve (.*) pague.*', '\\1', descricao)
		for medida in medidas:
			if re.match('.*?[0-9,]+'+medida+r'\b.*', descricao): # para casos como 310ml
				produto = re.sub('(.*?) [0-9,]+'+medida+r'\b.*', '\\1', descricao)
				valorMedida = re.sub('.*? ([0-9,]+)'+medida+r'\b.*', '\\1', descricao)
				break
			elif re.match('.*?[0-9,]+ '+medida+r'\b.*', descricao): # para 310 ml
				produto = re.sub('(.*?) [0-9,]+ '+medida+r'\b.*', '\\1', descricao)
				valorMedida = re.sub('.*? ([0-9,]+) '+medida+r'\b.*', '\\1', descricao)
				break
			elif re.match('.*? '+medida+r'\b.*', descricao): # para kg, sem valor
				produto = re.sub('(.*?) '+medida+r'\b.*', '\\1', descricao)
				valorMedida = '1'
				break
		if produto=='': # não encontrou nenhuma das unidades previstas
			if re.match('.* [0-9]+ .*', descricao): # para valor sem unidade (ex. 120 toalhas)
				produto = re.sub('(.*) [0-9]+ .*', '\\1', descricao)
				valorMedida = re.sub('.* ([0-9]+) .*', '\\1', descricao)
				medida = re.sub('.* [0-9]+ ([^ ]*).*', '\\1', descricao)
				medidas.append(medida)
		produto = produto.replace(' pacote', '').replace(' frasco', '').replace(' squeeze', '').replace(' cada', '').replace(' caixa', '').replace(' bandeja', '')
		produto = produto.title().replace(' E ', ' e ').replace(' De ', ' de ')
		medida = medida.replace('l', 'L')
		valorMedida = valorMedida.replace(',', '.')
		n += 1
		print('\n'+line.strip())
		print('%.2f%%' % (n/25.47), descricao, ' ## ', produto, '#', valorMedida, '#', medida, '#', marca)

print(medidas)
