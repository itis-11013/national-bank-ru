CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into roles(id, created_at, updated_at, name)
values ((SELECT uuid_generate_v1()), current_timestamp, current_timestamp, 'ADMIN'),
       ((SELECT uuid_generate_v1()), current_timestamp, current_timestamp, 'USER');
