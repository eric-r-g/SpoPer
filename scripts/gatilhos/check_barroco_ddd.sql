/*
	Isso aqui ainda é quebrável com update no tipo_grav de faixa
	ou no periodo do compositor, mas foi a forma que mais faz sentido
	na minha opinião, já que o periodo de um compositor não deve mudar
	e nem o tipo de gravação de uma faixa.
*/

CREATE OR REPLACE FUNCTION check_barroco_ddd() RETURNS TRIGGER AS $$
DECLARE
	tipo_grav varchar;
	periodo_music varchar;
BEGIN
	SELECT f.tipo_grav, p.nome INTO tipo_grav, periodo_music
	FROM faixa f, compositor c, periodo_musical p
	WHERE f.cod = NEW.faixa AND NEW.compositor = c.cod AND c.periodo_music = p.cod;

	-- Vale fazer um teste aqui, eu acho que esse teste vvv retorna verdadeiro se tipo_grav é nulo
	IF periodo_music ILIKE '%barroco%' AND NOT tipo_grav = 'DDD' THEN
		RAISE EXCEPTION 'Não pode haver album com faixa do periodo barroco e tipo de gravação diferente de DDD';
	END IF;

	RETURN NEW;
END
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER check_barroco_ddd
BEFORE INSERT OR UPDATE ON composicao
FOR EACH ROW EXECUTE FUNCTION check_barroco_ddd();

-- ========= Testando a quebra da restrição ========= --
-- Pra essa é só tentar fazer beethoven ser compositor da faixa clássica 02, que é ADD
--INSERT INTO composicao VALUES (14, 5, 2);

-- Quebras que não vão ser identificadas:
/*
    UPDATE faixa 
    SET tipo_grav = 'ADD'
    WHERE cod = 1
*/
