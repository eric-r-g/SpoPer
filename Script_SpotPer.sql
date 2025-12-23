CREATE TABLE gravadora (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	endereco varchar(256) NOT NULL,
	telefone varchar(20) NOT NULL,
	endr_homepage varchar(256) NOT NULL
);

-- "Um álbum, com faixas de músicas do período barroco, só pode ser inserido no
-- banco de dados, caso o tipo de gravação seja DDD."
-- "Um álbum não pode ter mais que 64 faixas (músicas)."
-- "No caso de remoção de um álbum do banco de dados, todas as suas faixas
-- devem ser removidas. Lembre-se que faixas podem apresentar, por sua vez,
-- outros relacionamentos"
-- "O preço de compra de um álbum não dever ser superior a três vezes a média
-- do preço de compra de álbuns, com todas as faixas com tipo de gravação
-- DDD."
-- Tem que ver se tem como colocar um check pra quando o tipo do disco for cd, o tipo de gravação
-- ser add ou ddd, e quando não for, ser nulo.
CREATE TABLE album (
	cod int PRIMARY KEY,
	descricao varchar(256) NOT NULL,
	gravadora int REFERENCES gravadora (cod),
	preco_cmpr real NOT NULL, -- Dar uma pesquisada se esse é o melhor tipo mesmo
	data_cmpr date NOT NULL,
	data_grav date NOT NULL,
	
	CHECK(data_grav > date '01-01-2000')

	-- Segunda restrição
	--SELECT count(*) 
	--FROM faixa f, meio_fisico m, album a
	--WHERE m.album=a.cod and f.disco=m.cod
	--GROUP BY a.cod
	--CHECK(select count(*) from faixa f, meio_fisico m where m.)
);

CREATE TABLE meio_fisico (
	cod int PRIMARY KEY,
	album int REFERENCES album(cod),
	tipo varchar(8) NOT NULL
);

CREATE TABLE interprete (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	tipo varchar(64) NOT NULL
);

CREATE TABLE periodo_musical (
	cod int PRIMARY KEY,
	descricao varchar(256) NOT NULL,
	inicio_intervalo date NOT NULL,
	fim_intervalo date NOT NULL
);

CREATE TABLE compositor (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	local_nasc varchar(256) NOT NULL,
	data_nasc date NOT NULL,
	data_morte date,
	periodo_music int REFERENCES periodo_musical(cod)
);

CREATE TABLE tipo_composicao (
	cod int PRIMARY KEY,
	descricao_comp varchar(256) NOT NULL
);

CREATE TABLE faixa (
	cod int PRIMARY KEY,
	pos_album int NOT NULL,
	disco int REFERENCES meio_fisico (cod), -- Talvez mudar o nome dessa coluna
	descricao varchar(256) NOT NULL,
	tipo_grav varchar(3),
	tipo_comp int REFERENCES tipo_composicao (cod)

	-- "Defina um índice primário para a tabela de Faixas sobre o atributo código do
	-- álbum. Defina um índice secundário para a mesma tabela sobre o atributo tipo de
	-- composição. Os dois com taxas de preenchimento máxima"
	--PRIMARY KEY (cod, pos_album, disco)
);

-- Tabelas auxiliares
CREATE TABLE composicao (
	cod int PRIMARY KEY,
	compositor int REFERENCES compositor (cod),
	faixa int REFERENCES faixa (cod)
);
CREATE TABLE interpretacao (
	cod int PRIMARY KEY,
	interprete int REFERENCES interprete (cod),
	faixa int REFERENCES faixa (cod)
);

CREATE TABLE playlist (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	data_criacao date NOT NULL,
	tempo_total int NOT NULL
);

CREATE TABLE playlist_contem (
	cod int PRIMARY KEY,
	playlist int REFERENCES playlist (cod),
	faixa int REFERENCES faixa (cod),
	ult_data_tocada date,
	numero_tocada int NOT NULL DEFAULT 0
);