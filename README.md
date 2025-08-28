
# Design Doc

```pgsql
                ┌───────────────────┐
                │   Producers       │
                │  (Orders, Events) │
                └─────────┬─────────┘
                          │
                          ▼
                   ┌────────────┐
                   │   Kafka    │
                   │ (Event Bus)│
                   └─────┬──────┘
                         │
                         ▼
                  ┌──────────────┐
                  │    Flink     │
                  │ (Dedup + Agg)│
                  └──┬─────────┬─┘
                     │         │
       ┌─────────────┘         └─────────────┐
       ▼                                     ▼
┌──────────────┐                     ┌─────────────────┐
│   DynamoDB   │                     │     Redis       │
│ (Source of   │                     │ (Cache Layer /  │
│   Record)    │                     │  Fast Lookup)   │
└──────┬───────┘                     └────────┬────────┘
       │                                      │
       ▼                                      ▼
    BI / ETL                              Dashboard / API
  (S3, BigQuery)                         (ultra-low latency)
```
