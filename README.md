# 🚖 Spring Boot RideCab App (Uber Clone) 

A backend clone of **Uber** implemented using **Java Spring Boot**.  
This project demonstrates a **real-world ride-hailing application** backend with features like **user onboarding, driver management, ride booking, fare calculation, ride completion, and rating system**.  

---

## 📝 Overview  

This project is a **backend-only implementation** of Uber-like services.  
It handles the **complete lifecycle of a ride**:  

1. User requests a ride  
2. System assigns a driver  
3. Fare is calculated based on distance  
4. Ride is completed and payment processed  
5. Ratings collected from both sides  

It is designed to **simulate production-grade APIs** that can be integrated with a mobile frontend (Android/iOS) or web UI.  

---

## 🏗️ Architecture  

- **Spring Boot Monolith** (can be split into microservices later)  
- RESTful APIs with **Spring MVC**  
- **Service Layer** for business logic  
- **Repository Layer** for database access (Spring Data JPA)  
- **Database**: PostgreSQL (or in-memory H2 for testing)  
- Authentication: JWT (if implemented)  

## 📌 **High-level Flow**:  
User → REST API → Controller → Service → Repository → Database
Driver → REST API → Controller → Service → Repository → Database
---

## 🚀 Features  

- **User Features**
  - Register/Login  
  - Request ride (source → destination)  
  - Track ride status  
  - Pay via wallet or cash  
  - Rate driver after ride  

- **Driver Features**
  - Register/Login  
  - Set availability (online/offline)  
  - Accept/Reject ride requests  
  - Complete rides  
  - Rate passengers  

- **System Features**
  - Automatic driver assignment  
  - Distance-based fare calculation  
  - Wallet-based payment flow  
  - Rating system for both parties  

---

## ⚙️ Tech Stack  

- **Java 17+**  
- **Spring Boot 3.x**  
- **Spring Data JPA (Hibernate)**  
- **PostgreSQL / H2**  
- **Maven** (build tool)  
- **Swagger UI** for API testing  
- **JWT Authentication** (if enabled)  

---

## 🗄️ Database Design  

### Tables  

- **User**  
  - id, name, email, password, walletBalance  

- **Driver**  
  - id, name, vehicleDetails, status (online/offline)  

- **Ride**  
  - id, userId, driverId, source, destination, fare, status (requested/ongoing/completed)  

- **Rating**  
  - id, rideId, userId, driverId, ratingValue, comments  


---

## 📂 Project Structure  

```
src/main/java/com/uber/app/
│── controller/ # REST Controllers (UserController, DriverController, RideController)
│── service/ # Business logic
│── repository/ # JPA Repositories
│── model/ # Entities (User, Driver, Ride, Rating)
│── dto/ # Data Transfer Objects (Request/Response payloads)
│── config/ # Security, Swagger configs
│── exception/ # Custom exception handling
```


---


---

## 🛠️ Getting Started  

### Prerequisites  
- Install **Java 17+**  
- Install **PostgreSQL** (or use in-memory H2 for testing)  
- Install **Maven**  

### Running
./mvnw spring-boot:run
The app runs on:
👉 http://localhost:8080

Swagger UI:
👉 http://localhost:8080/swagger-ui.html

## API Endpoints

### User APIs
| Method | Endpoint                 | Description             |
| ------ | ------------------------ | ----------------------- |
| POST   | `/api/users/register`    | Register a new user     |
| POST   | `/api/users/login`       | Login and get JWT token |
| POST   | `/api/users/requestRide` | Request a ride          |
| GET    | `/api/users/rides/{id}`  | Get ride details        |

### Driver APIs
| Method | Endpoint                   | Description                        |
| ------ | -------------------------- | ---------------------------------- |
| POST   | `/api/drivers/register`    | Register a new driver              |
| POST   | `/api/drivers/login`       | Driver login                       |
| PUT    | `/api/drivers/{id}/status` | Set driver status (online/offline) |
| GET    | `/api/drivers/rides`       | Fetch assigned rides               |

### Ride APIs
| Method | Endpoint                   | Description         |
| ------ | -------------------------- | ------------------- |
| POST   | `/api/rides/{id}/accept`   | Driver accepts ride |
| POST   | `/api/rides/{id}/complete` | Complete ride       |
| GET    | `/api/rides/{id}/fare`     | Get calculated fare |

### Rating APIs
| Method | Endpoint                | Description            |
| ------ | ----------------------- | ---------------------- |
| POST   | `/api/ratings`          | Submit rating for ride |
| GET    | `/api/ratings/{rideId}` | Get ride rating        |


## 🔄 Sample Flow

1. User registers & logs in
→ gets JWT token

2. User requests ride

{
  "userId": 1,
  "source": "MG Road",
  "destination": "Airport"
}


3. System assigns a driver (nearest available)
Response:

{
  "rideId": 101,
  "driverId": 12,
  "fare": 450.0,
  "status": "ASSIGNED"
}


4. Driver accepts & completes ride

5. Payment processed (wallet or cash)

6. Both rate each other

## 🔮 Future Enhancements

🚦 Real-time tracking with WebSockets

💳 Integration with UPI/Stripe/PayPal

📍 Google Maps API for route optimization

🛡️ OAuth2 Authentication

🚘 Surge pricing & pooling logic

  




