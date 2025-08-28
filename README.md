
# Design Doc

```pgsql
          ┌─────────────────────────┐
          │  Order Service (API)    │
          │  Spring Boot Producer   │
          └─────────┬──────────────┘
                    │
                    ▼
              ┌───────────────┐
              │   Kafka       │  topic: order-events
              └─────┬─────────┘
     ┌──────────────┴───────────────┐
     │                              │
     ▼                              ▼
┌──────────────┐              ┌────────────────┐
│ Analytics    │              │ Flink Job      │
│ Consumer     │              │ (Streaming/CEP)│
│ Spring Boot  │              └──────┬─────────┘
└──────┬───────┘                     │
       │ (fast stats)                │ (materialized views / alerts)
       ▼                             ▼
┌──────────────┐               ┌──────────────┐
│   Redis      │ <──(hot)───── │  DynamoDB    │
│ Hash/ZSet    │               │ UserStats/   │
└──────┬───────┘               │ UserAlerts   │
       │                        └──────┬──────┘
       │ (serve)                       │
       ▼                               ▼
┌──────────────────┐           ┌──────────────────┐
│ Analytics REST   │           │ S3 Data Lake     │
│ Spring Boot API  │           │ (歷史查詢)        │
└──────────────────┘           └──────────────────┘

```
