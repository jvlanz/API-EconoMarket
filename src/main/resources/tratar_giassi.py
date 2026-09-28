import re

medidas = ['ml', 'l', 'kg', 'g', 'un', 'unidades']

marcas = []
with open('marcas.txt') as fileID:
	line = fileID.readline()
	for marca in line.lower().split(', '):
		if marca not in marcas:
			marcas.append(marca)

#marcas = ['51', 'mór', 'sadia', 'sadia speciale', 'sadia bio', 'duçula', 'président', 'heinig', 'coamo', 'coca-cola', 'sprite', 'giassi', 'tio jõao', 'tio urbano', 'urbano', 'caldo bom', 'caldão', 'kiarroz', 'camil', 'mãe luzia', 'yoki', 'quero', 'barilla', 'galo', 'isabela', 'renata', 'adria', 'limpol', 'dona benta', 'orquidea', 'parati', 'germani', 'nordeste', 'fleischmann', 'dr. oetker', 'royal', 'quaker', 'nestlé', 'união', 'melitta', 'pilão', '3 corações', 'caboclo', 'café do ponto', 'baggio', 'orfeu', 'bom jesus', 'nescau', 'toddy', "hershey's", 'matte leão', 'leão', 'twinings', 'danoninho', 'ninho', 'piracanjuba', 'elegê', 'parmalat', 'batavo', 'aurora', 'danone', 'activia', 'yopro', 'vigor', 'polenghi', 'seara', 'catupiry', 'lacta', 'garoto', 'kinder', 'milka', 'club social', 'bauducco', 'oreo', 'gillete', 'bono', 'negresco', 'passatempo', 'trakinas', 'piraquê', 'marilan', 'jasmine sou sweet', 'jasmine pouch', 'jasmine', 'schar', 'pullman', 'wickbold', 'seven boys', 'visconti', 'nutella', 'vital', 'rap10', 'santa edwiges', 'pepsi', 'antarctica', 'fanta', 'schweppes', 'kuat', 'del valle', 'tang', 'maguary', 'natural one', 'campo largo', 'vanish', 'crystal', 'monster', 'red bull', 'baly', 'perdigão', 'disner', 'finish', 'duetto', '!waaw!', '7 belo', '7 montes', '8 galo', '88', '8galo', 'a tal da castanha', 'adidas', 'adocyl', 'aica', 'ajax', 'algotec', 'alhofree', 'alklin', 'almaromi', 'alpino', 'aminna', 'amstel', 'ana maria', 'andorinha', 'apti', 'araras', 'ares do campo', 'armazém', 'aromas do mar', 'aviação', 'axe', 'baby huggies', 'balardin', 'barion', 'barista', 'becel', 'becker', 'beija flor', 'beija-flor', 'beijubom', 'bellacotton', 'belvita', 'bem brasil', 'benjamim', 'biocrema', 'blumenau', 'bmh gourmet', 'bom ar', 'bom bril', 'bom destino', 'bombril', 'bonare', 'bortoluzzi', 'bozzano', 'brahma', 'brazilian coffe', 'bretzke', 'brigitta', 'brilhante', 'brilhus', 'bruder alimentos', 'budweiser', 'butter toffees', 'búfalo dourado', 'calisul', 'campanini', 'canlção', 'canção', 'carbonell', 'carefree', 'carlos', 'casa & perfume', 'castelo', 'cebolitos', 'chambinho', 'chamex', 'chamyto', 'chandelle', 'cheetos', 'cheez-it', 'chelli', 'chemim', 'choco leite', 'chocolícia', 'chácara turnes', 'cica', 'cif', 'cisne', 'claybom', 'clear', 'clear men', 'clight', 'closeup', 'coala', 'cocinero', 'coco do vale', 'colgate', 'comfort', 'concha y toro', 'condor', 'copacol', 'coperalcool', 'copobras', 'coposul', 'copra', 'corn flakes', 'corona', 'corpus', 'costa sul', 'cotton candy', 'cottonbaby', 'crinkle', 'cristalcopo', 'crocantíssimo', 'crokíssimi', 'crokíssimo', 'crunch', 'cruzilia', 'cruzília', 'cultura orgânica', 'cup noodles', 'da boa', 'da colônia', 'da márcia', 'daboa', 'dacolônia', 'damasco', 'danette', 'danubio', 'daoli', 'dauvan', 'delicia', 'delícia', 'destac', 'devile', 'dihellen', 'dipães', 'doces caseiros sorocaba', 'dois frades', 'dois irmaos', 'dom bruno', 'dom fiorelo', 'dona dida', 'dori', 'doriana', 'doritos', 'dove', 'downy', 'doçura do vale', 'duessmann', 'duracell', 'duryea', 'elefante', 'elite', 'elma chips', 'elte', 'eqlibri panetini', 'esfrebom', 'estrela do vale', 'excelsior', 'fabiany', 'faixa azul', 'famiglia venturelli', 'fazenda', 'fazenda toca', 'fazenda trevisan', 'feliz', 'fernandes', 'finish power', 'fit food', 'fleishmann', 'fofo', 'forno de minas', 'francis', 'free lar', 'freecô', 'friolar', 'friskies', 'fugini', 'fys', 'gaboardi', 'galak', 'gallo', 'gantarctica', 'garibaldi', 'garota', 'garotão', 'gatorade', 'gillette', 'gina', 'girando sol', 'gomes da costa', 'gotas de mel', 'gotinha de mel', 'gran legado', 'gran mestri', 'granado', 'grand hotel', 'grand mantiqueira', 'grelhaditos', 'guimarães', 'gusman', 'h2oh!', 'harpic', 'heineken', 'heinz', "hellmann's", 'hellmann´s', 'hemmer', 'henner', 'hershey´s', 'hoffmann', 'holandês', 'hort silva', 'horta zanolli', 'hortalicas silva', 'hortaliças silva', 'hortazanolli', 'ideale', 'iguaçu', 'imperatriz', 'inspira', 'intimus', 'itaipava', 'jimo', 'johnson & johnson', "johnson's baby", 'johnson´s baby', 'joice', 'kalassi', 'kanty', "kellog's", "kellogg's", 'kellogg´s', 'kenko', 'kero coco', 'ki-carvão', 'kifritas', 'kinder bueno', 'kipipoka', 'kitano', 'kitkat', 'kleenex', 'knorr', 'l´or', "l'or", 'la española', 'la nonna', 'la pastina', 'la serenissima', 'lac lélo', 'lar', "lay's", 'leite niño', 'leitíssimo', 'lembeck', 'levit', 'linea', 'linguinette arrighi', 'lipton', 'listerine', 'liza', 'lohn bier', 'lollo', 'lux', 'lysoform', "m&m's", 'mac tost', 'macedo', 'macedo iqf', 'machado', 'macrophytus', 'macrovita', 'mactost', 'maggi', 'magro', 'maille', 'maispura', 'mandiok', 'marquespan', 'max clean', 'max.decor', 'maxim', 'maxin', 'mccain', 'men bijou', 'mendorato', 'mentos', 'michelob ultra', 'mili', 'mimmo', 'minamel', 'mineirinho', 'minueto', 'molico', 'mon bijou', 'monange', 'monde', 'moça', 'mu-mu', 'muky', 'mãe terra', 'nat', 'nat verde', 'nescafé', 'nesfit', 'neumann', 'neve', 'nissin', 'nita', 'nivea', 'nossa senhora do caravagio', 'not creme', 'not milk', 'notmilk', 'noviça', 'nude.', 'nutry', 'oderich', 'ofs', 'ola', 'olé', 'omo', 'oq', 'oral-b', 'orquídea', 'palmasul', 'pampers', 'pamplona', 'pantene', 'papapá', 'papapá biscotti', 'parmézzo', 'parmíssimo', 'passe bem', 'pato', 'peccin', 'pedigree', 'percicoti', 'peroba', 'pescador', 'phebo', 'philadelphia', 'pilatti', 'pingo doce', 'pinho bril', 'pinho sol', 'pipicat', 'pirakids', 'piraque', 'planalto', 'poliflor', 'polpanorte', 'pomarola', 'pomodoro', 'popcorners', 'porção certa rocha', 'poteitos', 'powerade', 'ppipicat', 'prat´s', 'prenda', 'prestígio', 'primalta', 'pringles', 'protex', 'pure fresh', 'pureza', 'purezinha', 'puro latte', 'qboa', 'qualimar', 'quality fries', 'qualy', 'radium', 'rampineli', 'rampinelli', 'rayovac', 'rebeka', 'refinatto', 'rei redinha', 'rexona', 'rinaldi', 'risovita', 'ritter', 'rocha', 'rocha alimentos', 'rolo feliz', 'rosani', 'roseflor', 'royalpack', 'sabor a mi', 'sabor açoriano', 'safira', 'sakura', 'salsaretti', 'salton', 'salubi', 'san patrizio', 'sani-sol', 'sanjo', 'sanol dog', 'santa massa', 'santa rita', 'sazón', 'schmitt', 'schär', 'scotch-brite', 'scott', 'seda', 'sempre livre', 'sensodyne', 'sinhá', 'sitio schulter', 'slice', 'smirnoff', 'soberana', 'sococo', 'soft air', 'sol', 'sorriso', 'soya', 'st. dalfour', 'stella artois', 'strawplast', 'stylus', 'suflair', 'sulfrios', 'sun gold zespri', 'suq', 'surf', 'surpresa', 'suspirolândia', 'suvalan', 'sítio n.s. caravaggio', 'sítio santo expedito', 'sônia', 'texas burger', 'thabrulai', 'tio joão', 'tirol', 'toblerone', 'torcida', 'tostines', 'tostitos', 'trevisan', 'trident', 'trink', 'triunfo', 'tubarão', 'tupã', 'turma do pinicão', 'twix', 'uniagro', 'unik', 'utilis', 'vale fértil', 'veja', 'veneza', 'verd fácil', 'verde campo', 'versátil', 'vida macia', 'vida veg', 'vim', 'virtus', 'vitao', 'vitapan', 'vitreo', 'viva livre', 'vivaris', 'weinmann', 'whiskas', 'wick', 'wyda', 'yakult', 'yaí', 'yogurte 100', 'ypê', 'zavaski', 'zero cal', 'zezé', 'zilse', 'zizo', 'água da serra', 'águia', 'áurea', 'ávila', 'grape gold', 'colônia holandesa naturalle', 'vila nova', 'dover roll', 'divertidas', 'grape sweet', 'palmolive', 'da´guarda', 'fino nature', 'yorgus', 'coqueiro', 'ferreira', 'sukita', 'natural tea', 'blue diamond', 'pita bread', 'elseve', 'dohler', 'tortuguita', 'balariam', 'paderrí', 'pinati', 'pinat', 'supercoffe', 'nuts harts', 'banana brasil', 'supino', 'sésamo real', 'moving', 'chocowheyfer', 'crush bar', 'bendu', 'dr. peanut', 'nature food', 'doctor wafer', 'integralmedica', 'bold', 'protobar', 'nutrata', 'max titanium', 'pinc bar', 'probiótica', 'ss pets', 'kelcat', 'monello', 'dreamies', 'birbo', 'keldog', 'purina', 'mucilon', 'allnutri', 'nutren', 'nuby', 'cajovil', 'kuka', 'panine', 'ana júlia', 'tra la la', 'trá lá lá', 'johnsons baby', 'huggies', 'plasutil', 'ponjita', 'anjinho', 'hipoglós', 'doble', 'piquitucho', 'maribella', 'pauli', 'superpan', 'boccone', 'schirmann', 'koch', 'paderri', 'nutrella', 'lievito', 'rap 10', 'mentus', 'pulman', 'ta pronto', '3coracoes', 'copag', 'hot wheels', 'regina', 'lorenzetti', 'hello kitty', 'faber castell', 'bic', 'turma da mônica', 'natuá', 'perboni', 'forelle', 'pramesa', 'do vale', 'pura vida', 'verde fácil', 'benja', 'la molisana', 'mallmann', 'laurentino', 'oriental brasil', 'santa cucina', 'são francisco', 'sao francisco', 'deville', 'raiar', 'carminatti', ' nienow', 'pra mim', 'karui', 'gauer', 'sprouts', 'feltrin', 'do quintal', 'mel buss', 'baldoni', 'eurocompany', 'número 10', 'zinho', 'minga', 'são joão', 'sulbeef', 'raça britânica']

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
