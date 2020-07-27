create table users
(
    id       UUID    default random_uuid() not null primary key,
    username varchar_ignorecase(50)        not null unique,
    password varchar(500)                  not null,
    enabled  boolean default true          not null,
    locked   boolean default false         not null,
    expired  boolean default false         not null
);

create table authorities
(
    id        UUID default random_uuid() not null primary key,
    username  varchar_ignorecase(50)     not null,
    authority varchar_ignorecase(50)     not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

CREATE TABLE tasks
(
    id          UUID default random_uuid() not null primary key,
    user_id     UUID                       NOT NULL,
    title       VARCHAR(100)               NOT NULL,
    description VARCHAR(255)               NOT NULL,
    due         TIMESTAMP,

    CONSTRAINT FK_TASK_USER FOREIGN KEY (user_id) REFERENCES users on delete cascade
);
CREATE INDEX idx_tasks_title ON tasks (title);