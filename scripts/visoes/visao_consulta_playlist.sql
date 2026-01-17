CREATE VIEW mostrarPlaylistConcertoBarroco AS
    select *
    from playlist p
    where not exists (
        select f.cod
        from faixa f, composicao co, compositor c
        where co.faixa = f.cod and co.compositor = c.cod 
        -- essa linha tem que alterar depois para ter os valores exatos
        and c.periodo_music = 1 and f.tipo_grav = 'Concerto'
        and not exists (
            select 1
            from playlist_contem pc
            where 
                pc.faixa = f.cod and pc.playlist = p.cod
            )
        );
