-- É necessário rodar cada um desse comandos de forma separada.
-- Cada  caminho de criação deve ser absoluto e ter as devidas permissões
-- Por fim, é necessário já existe as pastas devidas.

CREATE TABLESPACE ts_primary
LOCATION '/dados/primary'; 

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
