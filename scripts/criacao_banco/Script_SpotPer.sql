CREATE TABLE gravadora (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	endereco varchar(256) NOT NULL,
	telefone varchar(20) NOT NULL,
	endr_homepage varchar(256) NOT NULL
) TABLESPACE ts_one;

CREATE TABLE album (
	cod int PRIMARY KEY,
    nome varchar(64) NOT NULL,
	descricao varchar(256) NOT NULL,
	gravadora int REFERENCES gravadora (cod),
	preco_cmpr real NOT NULL,
	data_cmpr date NOT NULL,
	data_grav date NOT NULL,
	
	CHECK(data_grav > date '01-01-2000')
<<<<<<< HEAD:scripts/criação do banco/Script_SpotPer.sql

	-- Segunda restrição
	--SELECT count(*) 
	--FROM faixa f, meio_fisico m, album a
	--WHERE m.album=a.cod and f.disco=m.cod
	--GROUP BY a.cod
	--CHECK(select count(*) from faixa f, meio_fisico m where m.)
) TABLESPACE ts_one;
=======
);
>>>>>>> f36aec813f70edd6696a24ace8344cdec8b97472:scripts/criacao_banco/Script_SpotPer.sql

CREATE TABLE meio_fisico (
	cod int PRIMARY KEY,
	album int REFERENCES album(cod) ON DELETE CASCADE,
	tipo varchar(8) NOT NULL
) TABLESPACE ts_one;

CREATE TABLE interprete (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	tipo varchar(64) NOT NULL
) TABLESPACE ts_one;

CREATE TABLE periodo_musical (
	cod int PRIMARY KEY,
    nome varchar(64) NOT NULL,
	descricao varchar(256) NOT NULL,
	inicio_intervalo date NOT NULL,
	fim_intervalo date NOT NULL
) TABLESPACE ts_one;

CREATE TABLE compositor (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	local_nasc varchar(256) NOT NULL,
	data_nasc date NOT NULL,
	data_morte date,
	periodo_music int REFERENCES periodo_musical(cod)
) TABLESPACE ts_one;

CREATE TABLE tipo_composicao (
	cod int PRIMARY KEY,
	descricao_comp varchar(256) NOT NULL
) TABLESPACE ts_one;

CREATE TABLE faixa (
	cod int PRIMARY KEY,
    nome varchar(64) NOT NULL,
	pos_album int NOT NULL,
	disco int REFERENCES meio_fisico (cod) ON DELETE CASCADE, -- Talvez mudar o nome dessa coluna
	descricao varchar(256) NOT NULL,
	tipo_grav varchar(3),
	tipo_comp int REFERENCES tipo_composicao (cod)
<<<<<<< HEAD:scripts/criação do banco/Script_SpotPer.sql

	-- "Defina um índice primário para a tabela de Faixas sobre o atributo código do
	-- álbum. Defina um índice secundário para a mesma tabela sobre o atributo tipo de
	-- composição. Os dois com taxas de preenchimento máxima"
	--PRIMARY KEY (cod, pos_album, disco)
) TABLESPACE ts_two;
=======
);
>>>>>>> f36aec813f70edd6696a24ace8344cdec8b97472:scripts/criacao_banco/Script_SpotPer.sql

-- Tabelas auxiliares
CREATE TABLE composicao (
	cod int PRIMARY KEY,
	compositor int REFERENCES compositor (cod),
<<<<<<< HEAD:scripts/criação do banco/Script_SpotPer.sql
	faixa int REFERENCES faixa (cod)
) TABLESPACE ts_one;

CREATE TABLE interpretacao (
	cod int PRIMARY KEY,
	interprete int REFERENCES interprete (cod),
	faixa int REFERENCES faixa (cod)
) TABLESPACE ts_one;
=======
	faixa int REFERENCES faixa (cod) ON DELETE CASCADE
);
CREATE TABLE interpretacao (
	cod int PRIMARY KEY,
	interprete int REFERENCES interprete (cod),
	faixa int REFERENCES faixa (cod) ON DELETE CASCADE
);
>>>>>>> f36aec813f70edd6696a24ace8344cdec8b97472:scripts/criacao_banco/Script_SpotPer.sql

CREATE TABLE playlist (
	cod int PRIMARY KEY,
	nome varchar(64) NOT NULL,
	data_criacao date NOT NULL,
	tempo_total int NOT NULL
) TABLESPACE ts_two;

CREATE TABLE playlist_contem (
	cod int PRIMARY KEY,
	playlist int REFERENCES playlist (cod),
	faixa int REFERENCES faixa (cod) ON DELETE CASCADE,
	ult_data_tocada date,
	numero_tocada int NOT NULL DEFAULT 0
) TABLESPACE ts_two;
