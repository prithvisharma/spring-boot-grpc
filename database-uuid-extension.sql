\c  <name of the database>
CREATE EXTENSION IF NOT EXISTS “uuid-ossp”;

SELECT uuid_generate_v4();