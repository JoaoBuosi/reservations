# Reservation API
`docker compose -f compose.yaml up --build` — API em `http://localhost:8082`.
`POST /api/reservations` com `{"resourceName":"Sala A","startsAt":"2026-11-01T10:00:00","endsAt":"2026-11-01T11:00:00"}`.
Retorna HTTP 409 quando detecta sobreposição. `@Version` habilita controle otimista de versão, mas não elimina sozinho corrida entre duas criações simultâneas. Para produção, acrescente constraint/lock no banco (por exemplo, exclusion constraint no PostgreSQL) e teste concorrência. Redis está incluído como base para cache, ainda não usado na lógica.
