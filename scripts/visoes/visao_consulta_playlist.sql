CREATE VIEW mostrarPlaylistConcertoBarroco AS
    select *
    from playlist p
    where not exists (
        select *
        from faixa f, composicao co, compositor c, periodo_musical pm, playlist_contem pc
        where pc.playlist = p.cod and pc.faixa = f.cod and co.faixa = f.cod and co.compositor = c.cod and c.periodo_music = pm.cod 
        and (pm.nome != 'Periodo barroco' OR f.tipo_comp != 1)) -- 1 -> Concerto

