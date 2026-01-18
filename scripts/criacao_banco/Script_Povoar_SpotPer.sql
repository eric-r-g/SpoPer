-- Script de povoamento
-- ToDo: Descrição melhor se quiser, fiquei com preguiça :P

-- Gravadora
--...........................(cod, nome, endereco, telefone, endr_homepage)
INSERT INTO gravadora VALUES (0, 'Grav_Teste', 'Endr_Teste', '+55 (85)94002-8922', 'www.endrhomepageteste.com.br');
INSERT INTO gravadora VALUES (1, 'Hellcat Records', 'Boston, Massachusetts', '555 555 5555', 'www.hellcatrecords.com');
INSERT INTO gravadora VALUES (2, 'Fox Music', 'Great Victoria Street, Belfast', '421 094 2960', 'www.foxmusic.com');
INSERT INTO gravadora VALUES (3, 'Som Livre', 'Rio de Janeiro', '510 602 8521', 'www.somlivre.com.br');
INSERT INTO gravadora VALUES (4, 'Constelation Records', 'Adelaide, Australia', '841 270 9620', 'www.constelation.com.br');
INSERT INTO gravadora VALUES (5, 'Classic Records', 'Berlim, Alemanha', '000 000 0001', 'www.classicrecords.com.br');

--SELECT * FROM gravadora

-- Interprete
--...........................(cod, nome, tipo)
INSERT INTO interprete VALUES (0, 'Interprete_Teste', 'Tipo_Teste');
INSERT INTO interprete VALUES (1, 'Geese', 'Banda');
INSERT INTO interprete VALUES (2, 'Layne Staley', 'Tenor');
INSERT INTO interprete VALUES (3, 'Chico Buarque e Milton Nascimento', 'Dupla');
INSERT INTO interprete VALUES (4, 'Djavan', 'Tenor');
INSERT INTO interprete VALUES (5, 'Evanescence', 'Banda');
INSERT INTO interprete VALUES (6, 'Cazuza', 'Barítono');
INSERT INTO interprete VALUES (7, 'Orquestra Sinfônica da UFC', 'Orquestra');

--SELECT * FROM interprete

-- Periodo Musical
--...........................(cod, nome, descricao, inicio_intervalo, fim_intervalo)
INSERT INTO periodo_musical VALUES (0, 'Periodo teste', 'Descrição Do periodo Teste', DATE '01-01-2000', DATE '31-12-2000');
INSERT INTO periodo_musical VALUES (1, 'Periodo barroco', 'Descrição Do periodo Barroco', DATE '20-10-1250', DATE '11-09-1700');
INSERT INTO periodo_musical VALUES (2, 'Periodo moderno', 'Descrição Do periodo Moderno', DATE '23-04-1960', DATE '25-01-2026');
INSERT INTO periodo_musical VALUES (3, 'Periodo idade media', 'Descrição Do periodo Idade Média', DATE '01-04-700', DATE '07-09-1300');
INSERT INTO periodo_musical VALUES (4, 'Periodo clássico', 'Descrição Do periodo Clássico', DATE '08-02-1605', DATE '03-11-1850');
INSERT INTO periodo_musical VALUES (5, 'Periodo deo futuro', 'Descrição Do periodo Futuro', DATE '23-07-17776', DATE '25-12-20020');
INSERT INTO periodo_musical VALUES (6, 'Periodo romântico', 'Descrição Do periodo Romântico', DATE '20-10-1760', DATE '11-09-1950');

--SELECT * FROM periodo_musical

-- Compositor
--...........................(cod, nome, local_nasc, data_nasc, data_morte, periodo_music)
INSERT INTO compositor VALUES (0, 'Compositor_Teste', 'Local_Nasc_Teste', DATE '01-01-2000', NULL, 0);
INSERT INTO compositor VALUES (1, 'Cameron Winter', 'Brooklyn, NY', DATE '04-03-2002', NULL, 2);
INSERT INTO compositor VALUES (2, 'Tom Jobin', 'Rio de Janeiro', DATE '25-01-1927', DATE '08-12-1994', 2);
INSERT INTO compositor VALUES (3, 'Mozart', 'Salzburgo', DATE '27-01-1756', DATE '05-12-1791', 4);
INSERT INTO compositor VALUES (4, 'Bach', 'Eisenach', DATE '31-03-1685', DATE '28-07-1750', 1);
INSERT INTO compositor VALUES (5, 'Beethoven', 'Bona', DATE '17-12-1770', DATE '26-03-1827', 1);
INSERT INTO compositor VALUES (6, 'Paul McCartney', 'Liverpool', DATE '18-06-1942', NULL, 2);
INSERT INTO compositor VALUES (7, 'Cazuza', 'Rio de Janeiro', DATE '04-04-1958', DATE '07-07-1990', 2);
INSERT INTO compositor VALUES (8, 'Dvorak', 'Nelahozeves', DATE '08-09-1841', DATE '01-05-1904', 6);

--SELECT * FROM Compositor

-- Album
--...........................(cod, nome, descricao, gravadora, preco_cmpr, data_cmpr, data_grav)
INSERT INTO album VALUES (0, 'Album teste','Descrição do album teste', 0, 0.0, DATE '01-01-2000', DATE '01-01-2025');
INSERT INTO album VALUES (1, 'Album MPB', 'Descrição do album MPB', 3, 150.25, DATE '23-09-1991', DATE '08-07-2007');
INSERT INTO album VALUES (2, 'Album clássico', 'Descrição do album Clássico', 5, 300.0, DATE '06-07-1670', DATE '08-09-2016');
INSERT INTO album VALUES (3, 'Album Geese', 'Descrição do album Geese', 1, 250.5, DATE '18-04-2025', DATE '11-09-2025');
INSERT INTO album VALUES (4, 'Album raro dos Beatles', 'Descrição do album Raro do Beatles', 4, 2000.99, DATE '28-02-1971', DATE '05-04-2022');

--SELECT * FROM album 

-- Meio Físico
--...........................(cod, album, tipo)
INSERT INTO meio_fisico VALUES (0, 0, 'CD');
INSERT INTO meio_fisico VALUES (1, 2, 'Vinil');
INSERT INTO meio_fisico VALUES (2, 3, 'Download');
INSERT INTO meio_fisico VALUES (3, 3, 'CD');
INSERT INTO meio_fisico VALUES (4, 4, 'Vinil');
INSERT INTO meio_fisico VALUES (5, 1, 'CD');

--SELECT * FROM meio_fisico

-- Playlist
--...........................(cod, nome, data_criacao, tempo_total)
INSERT INTO playlist VALUES (0, 'Playlist_Teste', DATE '01-01-2000', 90);
INSERT INTO playlist VALUES (1, 'Playlist do Italo', DATE '16-06-2023', 462);
INSERT INTO playlist VALUES (2, 'Playlist do Eric', DATE '30-09-2016', 250);

--SELECT * FROM playlist

-- Tipo de Composição
-- Faltou criatividade
--...........................(cod, descricao_comp)
INSERT INTO tipo_composicao VALUES (0, 'Descrição Sinfonia');
INSERT INTO tipo_composicao VALUES (1, 'Descrição Concerto');
INSERT INTO tipo_composicao VALUES (2, 'Descrição Sonata');
INSERT INTO tipo_composicao VALUES (3, 'Descrição Outros');

--SELECT * FROM tipo_composicao

-- Faixa
-- Tou desconsiderando pos_album por enquanto, discutir isso depois, junto com o bgl da chave primaria
--...........................(cod, nome, pos_album, disco, descricao, tipo_grav, tipo_comp)
INSERT INTO faixa VALUES (0, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
INSERT INTO faixa VALUES (1, 'faixa classica 01', 0, 1, 'Descrição da faixa Clássica01', NULL, 0);
INSERT INTO faixa VALUES (2, 'faixa classica 02', 1, 1, 'Descrição da faixa Clássica02', NULL, 2);
INSERT INTO faixa VALUES (3, 'faixa geese 01', 0, 2, 'Descrição da faixa Geese01', NULL, 3);
INSERT INTO faixa VALUES (4, 'faixa geese 02', 1, 3, 'Descrição da faixa Geese02', 'DDD', 3);
INSERT INTO faixa VALUES (5, 'faixa geese 03', 2, 3, 'Descrição da faixa Geese03', 'DDD', 3);
INSERT INTO faixa VALUES (6, 'faixa beatles 01', 0, 4, 'Descrição da faixa Beatles01', NULL, 3);
INSERT INTO faixa VALUES (7, 'faixa beatles 02', 1, 4, 'Descrição da faixa Beatles02', NULL, 3);
INSERT INTO faixa VALUES (8, 'faixa mpb01', 0, 5, 'Descrição da faixa MPB01', 'ADD', 3);
INSERT INTO faixa VALUES (9, 'faixa mpb02', 1, 5, 'Descrição da faixa MPB02', 'DDD', 3);
INSERT INTO faixa VALUES (10, 'faixa classica 03', 2, 1, 'Descrição da faixa Clássica 03', NULL, 1);

--SELECT * FROM faixa

-- Playlist Contem
-- Dá pra inicializar com ult_data_tocada e numero tocado diferente se quiser
--...........................(playlist, faixa, ult_data_tocada, numero_tocada)
INSERT INTO playlist_contem VALUES (0, 0, NULL, 0);
INSERT INTO playlist_contem VALUES (1, 3, NULL, 0);
INSERT INTO playlist_contem VALUES (1, 4, NULL, 0);
INSERT INTO playlist_contem VALUES (1, 5, NULL, 0);
INSERT INTO playlist_contem VALUES (1, 9, NULL, 0);
INSERT INTO playlist_contem VALUES (2, 8, NULL, 0);
INSERT INTO playlist_contem VALUES (2, 9, NULL, 0);
INSERT INTO playlist_contem VALUES (2, 1, NULL, 0);
INSERT INTO playlist_contem VALUES (2, 2, NULL, 0);
INSERT INTO playlist_contem VALUES (2, 6, NULL, 0);

--SELECT * FROM playlist_contem

-- Composição
--...........................(compositor, faixa)
INSERT INTO composicao VALUES (0, 0);
INSERT INTO composicao VALUES (3, 1);
INSERT INTO composicao VALUES (3, 2);
INSERT INTO composicao VALUES (4, 1);
INSERT INTO composicao VALUES (6, 6);
INSERT INTO composicao VALUES (6, 7);
INSERT INTO composicao VALUES (2, 8);
INSERT INTO composicao VALUES (2, 9);
INSERT INTO composicao VALUES (7, 9);
INSERT INTO composicao VALUES (1, 4);
INSERT INTO composicao VALUES (1, 5);
INSERT INTO composicao VALUES (1, 6);
INSERT INTO composicao VALUES (8, 1);
INSERT INTO composicao VALUES (8, 2);
INSERT INTO composicao VALUES (5, 10);

--SELECT * FROM composicao

-- Interpretação
--...........................(interprete, faixa)
INSERT INTO interpretacao VALUES (0, 0);
INSERT INTO interpretacao VALUES (7, 1);
INSERT INTO interpretacao VALUES (7, 2);
INSERT INTO interpretacao VALUES (1, 3);
INSERT INTO interpretacao VALUES (1, 4);
INSERT INTO interpretacao VALUES (1, 5);
INSERT INTO interpretacao VALUES (5, 6); -- O Evanescence tá tocando beatles, talvez isso seja confuso nos testes
INSERT INTO interpretacao VALUES (5, 7);
INSERT INTO interpretacao VALUES (3, 8);
INSERT INTO interpretacao VALUES (4, 8);
INSERT INTO interpretacao VALUES (3, 9);
INSERT INTO interpretacao VALUES (6, 9);

--SELECT * FROM interpretacao