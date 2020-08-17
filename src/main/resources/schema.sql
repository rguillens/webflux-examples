
create table blogs
(
    id         identity,
    created_at datetime     not null,
    updated_at datetime     not null,
    name       varchar(255) not null,
    primary key (id)
);

create table posts
(
    id           identity,
    created_at   datetime      not null,
    updated_at   datetime      not null,
    author       varchar(255)  not null,
    title        varchar(255)  not null,
    summary      varchar(512)  not null,
    content      varchar(4000) not null,
    published    boolean       not null,
    published_at datetime,
    blog_id      int8          not null,
    primary key (id)
);

create table comments
(
    id         identity,
    created_at datetime      not null,
    updated_at datetime      not null,
    author    varchar(255)   not null,
    content    varchar(4000) not null,
    post_id    int8          not null,
    primary key (id)
);

alter table posts
    add constraint fk_posts_blog foreign key (blog_id) references blogs;
alter table comments
    add constraint fk_comments_post foreign key (post_id) references posts;
