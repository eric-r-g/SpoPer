CREATE VIEW mostrarPlaylistConcertoBarroco AS
    select *
    from playlist p
    where not exists (
        select *
        from faixa f
		INNER JOIN composicao co on co.faixa = f.cod
		INNER JOIN compositor c on co.compositor = c.cod 
		INNER JOIN periodo_musical pm on c.periodo_music = pm.cod 
		INNER JOIN playlist_contem pc on pc.faixa = f.cod
        where pc.playlist = p.cod 
        and (pm.nome != 'Periodo barroco' OR f.tipo_comp != 1)) -- 1 -> Concerto

