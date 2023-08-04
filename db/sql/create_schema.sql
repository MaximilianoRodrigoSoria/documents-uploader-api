CREATE TABLE documents
(
    "id"           bigserial NOT NULL,
    file_name      varchar(60),
    hash_sha_256    varchar(100),
    hash_sha_512    varchar(100),
    account_id  bigint NOT NULL,
    last_upload timestamp

);

