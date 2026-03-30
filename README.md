# 🔐 Spring Boot JWT Security

## 📌 Description
Application Spring Boot sécurisée avec **JWT (JSON Web Token)** pour gérer l’authentification et l’accès aux API.

## ⚙️ Technologies
- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Maven

## 🚀 Fonctionnalités
- Authentification avec JWT
- Génération de token après login
- Accès sécurisé aux endpoints
- Gestion des rôles (USER / ADMIN)

## 🔑 Authentification
### Login :
POST `/api/auth/login`

Body :
```json
{
  "username": "user",
  "password": "1234"
}

