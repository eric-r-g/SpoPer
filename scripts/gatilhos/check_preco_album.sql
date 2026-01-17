CREATE OR REPLACE FUNCTION check_preco_album() RETURNS TRIGGER AS $$
DECLARE
	media_preco int;
BEGIN

	SELECT AVG(preco_cmpr) INTO media_preco FROM (
		SELECT a.preco_cmpr
		FROM faixa f, meio_fisico m, album a
		WHERE f.disco = m.cod and m.album = a.cod and
			not exists (SELECT * FROM faixa f2, meio_fisico m2 
						WHERE (f2.tipo_grav != 'DDD' OR f2.tipo_grav IS NULL) AND f2.disco = m2.cod AND m2.album = a.cod)
		GROUP BY a.cod
	);

	IF NEW.preco_cmpr > 3*media_preco THEN
		RAISE EXCEPTION 'Preço do álbum não pode ser maior que três vezes a média 
		dos albuns com todas as faixas tendo tipo de gravação DDD';
	END IF;

	RETURN NEW;
END
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER check_preco_album
BEFORE INSERT OR UPDATE OF preco_cmpr ON album
FOR EACH ROW EXECUTE FUNCTION check_preco_album();

-- ========= Testando a quebra da restrição ========= --
--DELETE FROM faixa f WHERE f.cod > 9;

/*
    -- Albuns que tem todos os tipos DDD pra testar, a média fica mais ou menos 755
    INSERT INTO album VALUES (5, 'Album DDD', 'Descrição do album ddd', 0, 505.38, DATE '28-02-1971', DATE '05-04-2022');
    INSERT INTO album VALUES (6, 'Album DDD2', 'Descrição do album ddd2', 0, 1005.98, DATE '28-02-1971', DATE '05-04-2022');

    INSERT INTO meio_fisico VALUES (6, 5, 'CD');
    INSERT INTO meio_fisico VALUES (7, 6, 'CD');

    INSERT INTO faixa VALUES (10, 'faixa DDD1', 0, 6, 'Descrição da faixa DDD1', 'DDD', 3);
    INSERT INTO faixa VALUES (11, 'faixa DDD2', 0, 6, 'Descrição da faixa DDD2', 'DDD', 3);
    INSERT INTO faixa VALUES (12, 'faixa DDD3', 0, 7, 'Descrição da faixa DDD3', 'DDD', 3);
    INSERT INTO faixa VALUES (13, 'faixa DDD4', 0, 7, 'Descrição da faixa DDD4', 'DDD', 3);
    INSERT INTO faixa VALUES (14, 'faixa DDD5', 0, 8, 'Descrição da faixa DDD5', 'DDD', 3);

    -- Quebra de restrição:
    INSERT INTO album VALUES (7, 'Album Caro', 'Descrição do album caro', 0, 2300, DATE '28-02-1971', DATE '05-04-2022');
*/