CREATE OR REPLACE FUNCTION check_faixa_num64() RETURNS TRIGGER AS $$
DECLARE
	num_faixas int;
	disco_faixa int;
BEGIN
	LOCK TABLE faixa IN EXCLUSIVE MODE; -- Evitar que a concorrência quebre a restrição

	-- Pegar album especifico da tupla inserida, pra não ter que dar count em todos os albuns
	SELECT m.album INTO disco_faixa FROM meio_fisico m WHERE m.cod = NEW.disco;

	SELECT count(*) INTO num_faixas
	FROM album a, meio_fisico m, faixa f
	WHERE a.cod = m.album and m.cod = f.disco and m.album = disco_faixa
	GROUP BY a.cod;

	IF num_faixas >= 64 THEN
		RAISE EXCEPTION 'Número de faixas em um álbum não pode ser maior que 64'; -- Isso sobe um erro e dá rollback
	END IF;

	RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER check_faixa_num64
BEFORE INSERT OR UPDATE OF disco ON faixa
FOR EACH ROW EXECUTE FUNCTION check_faixa_num64();

-- ========= Testando a quebra da restrição ========= --
--DELETE FROM faixa f WHERE f.cod > 9;

/*
    INSERT INTO faixa VALUES(10, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(11, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(12, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(13, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(14, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(15, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(16, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(17, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(18, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(19, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(20, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(21, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(22, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(23, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(24, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(25, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(26, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(27, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(28, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(29, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(30, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(31, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(32, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(33, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(34, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(35, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(36, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(37, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(38, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(39, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(40, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(41, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(42, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(43, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(44, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(45, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(46, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(47, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(48, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(49, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(50, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(51, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(52, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(53, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(54, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(55, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(56, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(57, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(58, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(59, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(60, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(61, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(62, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(63, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(64, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(65, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(66, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(67, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(68, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(69, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(70, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(71, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(72, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
    INSERT INTO faixa VALUES(73, 'faixa teste', 0, 0, 'Descrição da faixa Teste', 'ADD', 3);
*/