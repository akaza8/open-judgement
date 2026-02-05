CREATE TABLE case_roles (
  id UUID PRIMARY KEY,
  case_id UUID NOT NULL,
  anonymous_id UUID NOT NULL,
  role VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  UNIQUE (case_id, role)
);

CREATE TABLE invitation_tokens (
  id UUID PRIMARY KEY,
  case_id UUID NOT NULL,
  token TEXT NOT NULL,
  expires_at TIMESTAMP NOT NULL,
  used BOOLEAN NOT NULL
);
