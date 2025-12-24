select * 
from album a
where a.preco_cmpr > 
	(select avg(alb.preco_cmpr) 
	 from album alb)