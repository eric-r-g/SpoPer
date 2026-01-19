-- Sem repetições
CREATE VIEW mostrarCompMaisPlaylists AS
    select c.nome
    from compositor c
    where
        (select count(DISTINCT f.cod) 
        from compositor c1
        INNER JOIN composicao co on c1.cod = co.compositor
        INNER JOIN faixa f on f.cod = co.faixa
        INNER JOIN playlist_contem pc on f.cod = pc.faixa
        where c.cod = c1.cod) >= all 
        (select count(DISTINCT f.cod) 
        from compositor c1
        INNER JOIN composicao co on c1.cod = co.compositor
        INNER JOIN faixa f on f.cod = co.faixa
        INNER JOIN playlist_contem pc on f.cod = pc.faixa
        GROUP BY c1.cod)