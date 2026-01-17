select g.nome
from gravadora g
INNER JOIN album a on a.gravadora = g.cod
INNER JOIN faixa f on f.disco = a.cod
INNER JOIN playlist_contem pc on pc.faixa = f.cod
INNER JOIN composicao cc on cc.faixa = f.cod
INNER JOIN compositor ct on cc.compositor = ct.cod
where ct.nome = 'Dvorack'
group by g.nome
having count(DISTINCT pc.playlist) >= all (
    select count(DISTINCT pc2.playlist)
    from gravadora g2
    INNER JOIN album a2 on a2.gravadora = g2.cod
    INNER JOIN faixa f2 on f2.disco = a2.cod
    INNER JOIN playlist_contem pc2 on pc2.faixa = f2.cod
    INNER JOIN composicao cc2 on cc2.faixa = f2.cod
    INNER JOIN compositor ct2 on cc2.compositor = ct2.cod
    where ct2.nome = 'Dvorack'
    group by g2.nome
);