<<<<<<< HEAD:scripts/criação do banco/Script_SpotPerUser.sql
CREATE ROLE "SpotPer" WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	NOINHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1
	PASSWORD 'xxxxxx';

CREATE TABLESPACE primary
LOCATION '/dados/primary';

CREATE TABLESPACE ts_one
LOCATION '/dados/tsOne';

CREATE TABLESPACE ts_two
LOCATION '/dados/tsTwo';


=======
>>>>>>> f36aec813f70edd6696a24ace8344cdec8b97472:scripts/criacao_banco/Script_CreateDB.sql
CREATE DATABASE "SpotPer"
    WITH
    OWNER = "SpotPer"
    TEMPLATE = template0
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False
    TABLESPACE = primary;
