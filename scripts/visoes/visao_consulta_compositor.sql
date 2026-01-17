-- Peguei a sem repetições
CREATE VIEW mostrarCompMaisPlaylists AS
    select *
    from compositor c
    where
        -- sub consulta para saber quantas musicas aquele compositor tem
        (select count(DISTINCT f.cod) 
        from compositor c1, composicao co, faixa f, playlist_contem pc
        where c1.cod = co.compositor and f.cod = co.faixa and f.cod = pc.faixa and c.cod = c1.cod) >= all
        -- sub consulta para saber quantas musicas todos os compositor tem
        (select count(DISTINCT f.cod) 
        from compositor c1, composicao co, faixa f, playlist_contem pc
        where c1.cod = co.compositor and f.cod = co.faixa and f.cod = pc.faixa
        group by c1.cod);