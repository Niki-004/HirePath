# HirePath – Error Handling Strategy

This document describes how errors are handled in the HirePath backend.

---

## Goals

- Return clear and meaningful API errors
- Use proper HTTP status codes
- Avoid exposing internal details
- Keep frontend behavior predictable

---

## HTTP Status Codes Used

| Status Code | Meaning |
|------------|--------|
| 200 | Successful request |
| 201 | Resource created |
| 204 | Resource deleted |
| 400 | Bad request |
| 404 | Resource not found |
| 500 | Internal server error |

---

## Controller-Level Errors

Spring’s `ResponseStatusException` is used for common failures.

Example:

```java
throw new ResponseStatusException(
    HttpStatus.NOT_FOUND,
    "Job not found"
);
