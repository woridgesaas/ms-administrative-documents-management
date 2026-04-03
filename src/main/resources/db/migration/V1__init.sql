-- Users table
CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    CIN        VARCHAR(20) UNIQUE                    NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ
);

-- Document types table
CREATE TABLE document_types
(
    id          UUID PRIMARY KEY,
    category    VARCHAR(50)                           NOT NULL,
    description TEXT,
    created_at  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMPTZ
);

-- Document requests
CREATE TABLE document_requests
(
    id              UUID PRIMARY KEY,
    request_date    TIMESTAMP   NOT NULL,
    status          VARCHAR(20) NOT NULL,
    processing_date TIMESTAMP,
    user_id         UUID        NOT NULL,
    type_id         UUID        NOT NULL,

    CONSTRAINT fk_document_request_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,

    CONSTRAINT fk_document_request_type
        FOREIGN KEY (type_id)
            REFERENCES document_types (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

-- Documents table
CREATE TABLE documents
(
    id              UUID PRIMARY KEY,
    generation_date TIMESTAMP,
    path            TEXT,
    request_id      UUID UNIQUE,

    CONSTRAINT fk_document_request
        FOREIGN KEY (request_id)
            REFERENCES document_requests (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

-- Request histories
CREATE TABLE request_histories
(
    id_history   UUID PRIMARY KEY,
    requested_by UUID,
    approved_by  UUID,
    action_date  TIMESTAMP                             NOT NULL,
    request_id   UUID                                  NOT NULL,
    created_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_requested_by
        FOREIGN KEY (requested_by)
            REFERENCES users (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,

    CONSTRAINT fk_approved_by
        FOREIGN KEY (approved_by)
            REFERENCES users (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,

    CONSTRAINT fk_document_request
        FOREIGN KEY (request_id)
            REFERENCES document_requests (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);