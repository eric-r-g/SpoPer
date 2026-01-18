CREATE VIEW mostrarPlaylistConcertoBarroco AS
    select *
    from playlist p
    where not exists (
        select f.cod
        from faixa f, composicao co, compositor c, periodo_musical pm
        where co.faixa = f.cod and co.compositor = c.cod and c.periodo_music = pm.cod 

