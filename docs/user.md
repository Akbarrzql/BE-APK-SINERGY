# User API Spec

## Register User
- Endpoint: `POST /api/v1/Auth/register`
- Request Body:
```json
{
  "username": "string",
  "email": "string",
  "password": "string"
}
```
- Response Body (Success):
```json
{
  "message": "User registered successfully",
  "userId": "string"
}
```
- Response Body (Error):
- Status Code: 400 Bad Request
```json
{
  "error": "User registration failed",
  "details": "string"
}
```

## Login User
- Endpoint: `POST /api/v1/Auth/login`
- Request Body:
```json
{
  "email": "string",
  "password": "string"
}
```

- Response Body (Success):
```json
{
  "data" : {
    "token": "string",
    "userId": "string",
    "username": "string",
    "email": "string",
    "expiredAt": "string"
  }
}
```

- Response Body (Error):
- Status Code: 401 Unauthorized
```json
{
    "error": "Invalid email or password",
    "details": "string"
}
```

## Get User
- Endpoint: `GET /api/v1/Users/current`
- Headers: Authorization: Bearer {token}
- Response Body (Success):
```json
{
    "data": {
        "userId": "string",
        "username": "string",
        "email": "string"
    }
}
```

- Response Body (Error):
- Status Code: 401 Unauthorized
```json
{
  "error": "Unauthorized",
  "details": "string"
}
```

## Update User
- Endpoint: `PATCH /api/v1/Users/current`
- Headers: Authorization: Bearer {token}
- Request Body:
```json
{
  "username": "string", // put if only want to update username
  "email": "string", // put if only want to update email
  "password": "string" // put if only want to update password
}
```
- Response Body (Success):
```json
{
  "data" : {
    "userId": "string",
    "username": "string",
    "email": "string"
}
```
- Response Body (Error):
- Status Code: 400 Bad Request
- Status Code: 401 Unauthorized
```json
{
  "error": "User update failed",
  "details": "string"
}
```

## Delete User
- Endpoint: `DELETE /api/v1/Auth/logout`
- Headers: Authorization: Bearer {token}
- Response Body (Success):
```json
{
  "data" : {
    "message": "User logged out successfully"
  }
}
```
- Response Body (Error):
- Status Code: 401 Unauthorized
```json
{
  "error": "Unauthorized",
  "details": "string"
}
``` 