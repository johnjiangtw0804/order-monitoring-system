
# Design

```pgsql
[User Request / Order Service]
          │
          ▼
   (Spring Boot API)
          │
          ▼
   Kafka (order-events topic)
          │
   ┌──────┴──────────────┐
   ▼                     ▼
[Spring Boot Consumer]   [Flink Job]
(簡單統計+Redis存儲)     (進階分析+CEP/SQL)
          │                     │
          ▼                     ▼
       Redis                Redis / Kafka
   (user stats, alerts)     (materialized view, alerts)
          │
          ▼
    Analytics REST API
    (查詢統計 & 告警)
```
