# 🧠 Architecture Decision Records

Use an ADR to capture decisions that materially affect API contracts, identity, data, infrastructure, security, or operations.

## Naming convention
`NNNN-short-decision-title.md`
Example: `0001-use-versioned-database-migrations.md`.

## Lifecycle

~~~mermaid
stateDiagram-v2
    [*] --> Proposed
    Proposed --> Accepted
    Proposed --> Rejected
    Accepted --> Superseded
    Rejected --> Proposed
~~~

## Rules
- Keep one decision per file.
- Record context and alternatives, not only the final choice.
- Include security and migration impact.
- Do not rewrite accepted decisions silently; supersede with a new ADR.
- Link implementation PRs and tests.
