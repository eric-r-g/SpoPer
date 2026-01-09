create view playlist_qtd_albuns as 
	select p.nome, count(Distinct MF.album) as qtd_albuns 
	from playlist p, playlist_contem pc, faixa f, meio_fisico mf 
	where pc.playlist = p.cod and pc.faixa = f.cod and f.disco = mf.cod 
	group by p.nome;