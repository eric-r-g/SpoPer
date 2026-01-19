create materialized view playlist_qtd_albuns as 
	select p.nome, count(Distinct mf.album) as qtd_albuns 
	from playlist p
	INNER JOIN playlist_contem pc on pc.playlist = p.cod
	INNER JOIN faixa f on pc.faixa = f.cod
	INNER JOIN meio_fisico mf on f.disco = mf.cod 
	group by p.nome, p.cod;