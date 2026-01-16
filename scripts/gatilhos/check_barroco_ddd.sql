/*
	O texto fala "inserção" então tou testando só o insert, mas dá pra botar o update,
	só tem que dar uma olhada que o OLD na função pode virar nulo,
	mas nem sei se vou usar o OLD.
	
	Como esse trigger tá em album, 
	se o valor de tipo de gravação ou o periodo do compositor mudar,
	o trigger não vai ativar e daí dá pra quebrar a restrição.

	Uma forma bem mais garantida de fz isso é adicionar esse trigger em faixa,
	se tipo_comp da faixa é barroca, garantir que o disco dela é DDD.
	O problema é que isso não é exatamente o que ele tá falando no item.
*/

CREATE OR REPLACE FUNCTION check_barroco_ddd() RETURNS TRIGGER AS $check_barroco_ddd$
DECLARE
	is_barroca BOOLEAN = false;
BEGIN
	-- Checar se tem faixa barroca

	-- Se sim checar tipo_grav, senão retorna NEW

	-- Se tipo_grav é DDD retorna NEW, senão retorna NULL(OLD pro update)
END
$check_barroco_ddd$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER check_barroco_ddd
BEFORE INSERT ON album
FOR EACH ROW EXECUTE FUNCTION check_barroco_ddd();