CREATE OR REPLACE FUNCTION albuns_comp(comp varchar(50)) RETURNS TABLE(album int)
AS $$
SELECT a.cod FROM compositor cpositor, composicao cposicao, faixa f, meio_fisico m, album a
WHERE cpositor.nome ILIKE '%' || comp || '%'  AND cpositor.cod = cposicao.compositor AND 
cposicao.faixa = f.cod AND f.disco = m.cod AND m.album = a.cod
GROUP BY a.cod -- Pra não ter multiplas saidas do mesmo album
$$ LANGUAGE SQL