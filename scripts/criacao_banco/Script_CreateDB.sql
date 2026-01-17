--É necessário rodar cada um desse comandos de forma separada.
CREATE TABLESPACE ts_primary
LOCATION '/dados/primary'; --aqui é o caminho absoluto, então falta o inicio

CREATE TABLESPACE ts_one
LOCATION '/dados/tsOne';

CREATE TABLESPACE ts_two
LOCATION '/dados/tsTwo';

CREATE DATABASE "SpotPer"
    WITH
    OWNER = "SpotPer"
    TEMPLATE = template0
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False
    TABLESPACE = ts_primary;
