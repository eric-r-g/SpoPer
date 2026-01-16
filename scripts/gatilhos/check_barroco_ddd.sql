/*
	O texto fala "inserção" então tou testando só o insert, mas dá pra botar o update,
	só tem que dar uma olhada que o OLD na função pode virar nulo,
	mas nem sei se vou usar o OLD.
	
	Como esse trigger tá em album, 
	se o valor de tipo de gravação ou o periodo do compositor mudar,
	o trigger não vai ativar e daí dá pra quebrar a restrição.
*/

CREATE OR REPLACE FUNCTION check_barroco_ddd() RETURNS TRIGGER AS $check_barroco_ddd$
DECLARE
	is_barroca BOOLEAN = false;
BEGIN
	-- Variáveis importantes:
	-- NEW -> Tupla que está sendo adicionada

	-- Checar se tem faixa barroca

	-- Se sim checar tipo_grav, senão retorna NEW

	-- Se tipo_grav é DDD retorna NEW, senão retorna NULL(OLD pro update)
END
$check_barroco_ddd$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER check_barroco_ddd
BEFORE INSERT ON album
FOR EACH ROW EXECUTE FUNCTION check_barroco_ddd();