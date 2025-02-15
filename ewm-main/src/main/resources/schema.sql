DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS compilations CASCADE;
DROP TABLE IF EXISTS compilation_event CASCADE;
DROP TABLE IF EXISTS requests CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS locations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    lat FLOAT NOT NULL,
    lon FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS events (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    annotation VARCHAR NOT NULL,
    category_id BIGINT NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR NOT NULL,
    confirmed_requests INTEGER,
    event_date TIMESTAMP NOT NULL,
    initiator_id BIGINT NOT NULL,
    location_id BIGINT NOT NULL,
    paid BOOL DEFAULT FALSE NOT NULL,
    participant_limit INTEGER DEFAULT 0,
    published_on TIMESTAMP,
    request_moderation BOOL DEFAULT TRUE NOT NULL,
    state VARCHAR,
    title VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (initiator_id) REFERENCES users(id),
    FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE IF NOT EXISTS requests (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    event_id BIGINT NOT NULL,
    requester_id BIGINT NOT NULL,
    request_status VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (requester_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS compilations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    pinned BOOL NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compilation_events (
    compilation_id BIGINT,
    event_id BIGINT,
    FOREIGN KEY (compilation_id) REFERENCES compilations(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
);

CREATE TABLE IF NOT EXISTS comments (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    text VARCHAR(1000) NOT NULL,
    event_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);