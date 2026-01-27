
# Architecture Overview

OpenJudgement follows a modular monolith architecture with clear internal boundaries
and event-driven communication between core modules.

The system prioritizes:
- Scoped anonymity
- Deterministic case state transitions
- Safety-first moderation
- Open-source extensibility via plugins

## Core Modules

- Case Management
- Identity & Anonymity
- Role & Permission Enforcement
- Content Management
- Moderation
- Voting (future)

External integrations and experimental features must be implemented as plugins.
