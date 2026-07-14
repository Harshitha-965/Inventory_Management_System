# 📦 Inventory Management System

A full-stack inventory management application for tracking products, categories, and suppliers with secure, RESTful APIs.

## 🎯 Overview

Inventory Management System enables efficient product catalog management through a relational schema and layered backend architecture, supporting CRUD operations, pagination, filtering, and secure access control.

## ⚙️ Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React |
| Backend | Spring Boot (Java) |
| Database | MySQL (Spring Data JPA) |
| Authentication | JWT |
| Tools | Postman, Git, GitHub |

## 🧩 Core Features

- Product, Category, and Supplier management with relational mapping
- Layered Controller-Service-Repository architecture
- DTO-based request/response handling
- Centralized exception handling
- JWT-based authentication
- Pagination, filtering, and input validation across endpoints

## 🛠️ Setup Instructions

**1. Clone the repository**
```bash
git clone https://github.com/Harshitha-965/InventoryManagementSystem.git
cd InventoryManagementSystem
```

**2. Backend setup**
```bash
cd backend
# Configure application.properties with your local MySQL credentials
mvn spring-boot:run
```

**3. Frontend setup**
```bash
cd frontend
npm install
npm run dev
```

## 🧭 API Endpoints (Sample)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/login` | User login |
| GET | `/api/products` | Fetch paginated product list |
| POST | `/api/products` | Add a new product |
| PUT | `/api/products/{id}` | Update product details |
| DELETE | `/api/products/{id}` | Delete a product |
| GET | `/api/categories` | Fetch categories |
| GET | `/api/suppliers` | Fetch suppliers |


## 👨‍💻 Developed By

Harshitha K G
harshithakg09@gmail.com | [LinkedIn](https://www.linkedin.com/in/harshithakg09)
