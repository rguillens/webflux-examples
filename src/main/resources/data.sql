
--Blogs
insert into
    blogs(created_at, updated_at, name)
    values (current_timestamp, current_timestamp, 'Reactive Blog');

--Posts
insert into
    posts(created_at, updated_at, author, title, summary, content, published, published_at, blog_id)
    values (
        current_timestamp,
        current_timestamp,
        'rguillens',
        'Hello Reactive Query Derivation',
        'This is a test of Reactive support for Spring Data Repositories Query Derivation...',
        '# Hello Query Derivation  ' ||
            'This is a test of Reactive support for Spring Data Repositories Query Derivation.   ' ||
            'We can now achieve queries like `findByFirstnameContaining()` without specifying   ' ||
            'the corresponding `SQL`.',
        true,
        current_timestamp,
        1);

insert into
    posts(created_at, updated_at, author, title, summary, content, published, published_at, blog_id)
    values (
        current_timestamp,
        current_timestamp,
        'rguillens',
        'Hello Reactive Web Stack',
        'This is a test of Reactive support in Spring WebFlux...',
        '# Hello Reactive Web Stack  ' ||
            'This is a test of Reactive support support in Spring WebFlux.   ' ||
            'We can now use `Flux` and `Mono` in `@RestController` requests and responses,   ' ||
            'use *Accept* HTTP Header `text/event-stream` to receive non-blocking stream    ' ||
            'of data, or `application/json` for a request/response normal behavior.',
        true,
        current_timestamp,
        1);

insert into
    posts(created_at, updated_at, author, title, summary, content, published, published_at, blog_id)
    values (
        current_timestamp,
        current_timestamp,
        'rguillens',
        'Hello Reactive H2 Configuration',
        'This is a test of Reactive support for H2 in-memory database...',
        '# Hello Reactive H2 Configuration  ' ||
            'This is a test of Reactive support for H2 in-memory database.   ' ||
            'We can now achieve even database schema creation and population,   ' ||
            'check `R2dbcConfig` class for more details.',
        true,
        current_timestamp,
        1);

insert into
    posts(created_at, updated_at, author, title, summary, content, published, published_at, blog_id)
    values (
        current_timestamp,
        current_timestamp,
        'rguillens',
        'Hello Reactive Query Derivation',
        'This is a test of Reactive support for Spring Data Repositories Query Derivation...',
        '# Hello Query Derivation  ' ||
            'This is a test of Reactive support for Spring Data Repositories Query Derivation.   ' ||
            'We can now achieve queries like `findByFirstnameContaining()` without specifying   ' ||
            'the corresponding `SQL`. The library `spring-data-r2dbc` version `1.1.0.RELEASE`   ' ||
            'was not supported in the SpringBoot R2DBC Starter at the time of writing this post. ',
        true,
        current_timestamp,
        1);
