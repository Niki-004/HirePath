# 📮 Postman API Testing Guide – JobTracker

This document explains how to test the JobTracker backend APIs using Postman.

---

## Base URL
http://localhost:8080

---

## 1. Health Check
GET /health

Response:
```json
{
  "status": "UP"
}
