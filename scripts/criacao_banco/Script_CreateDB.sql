CREATE TABLESPACE primary
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
    TABLESPACE = primary;
