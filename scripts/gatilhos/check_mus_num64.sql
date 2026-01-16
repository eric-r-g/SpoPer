CREATE OR REPLACE FUNCTION check_mus_num64() RETURNS TRIGGER AS $check_mus_num64$
DECLARE
	is_barroca BOOLEAN = false;
BEGIN
	-- Variáveis importantes:

	-- Checar quantidade de faixas do album
	-- Ficar de olho que album pode ter mais de um meio fisico,
	-- então tem que somar de todos eles.
END
$check_mus_num64$ LANGUAGE plpgsql;

-- Tem que ser por statement pq a quantidade de musicas adicionadas importa
-- o problema é descobrir como dar rollback

CREATE OR REPLACE TRIGGER check_mus_num64
BEFORE INSERT OR UPDATE OF disco ON faixa
FOR EACH STATEMENT EXECUTE FUNCTION check_mus_num64();