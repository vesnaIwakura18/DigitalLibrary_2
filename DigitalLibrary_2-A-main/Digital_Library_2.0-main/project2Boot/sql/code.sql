CREATE EXTENSION pg_trgm;

CREATE INDEX trgm_idx_book_title ON BOOK USING GIN(title gin_trgm_ops);