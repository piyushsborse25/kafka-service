# 🛒 Kafka Order Notification System

This project demonstrates a **Kafka-based microservices architecture** using Spring Boot. It includes:

- **Order Service** – Sends order messages to Kafka
- **Notification Service** – Listens to Kafka and sends email notifications

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.x
- Apache Kafka
- Gmail SMTP (or Mailtrap)
- Maven

---

## 📦 Microservices

### 1️⃣ `order-service`

- REST API to place orders
- Publishes order data to Kafka topic `orders`

### 2️⃣ `notification-service`

- Kafka consumer for `orders` topic
- Sends email notifications to customers

---

## 🔗 Kafka Topic

```
orders
```
---

## 🚀 How to Run

### ✅ Prerequisites

- Kafka & Zookeeper installed locally and running
- Kafka topic `orders` created
- Java 17+, Maven

### ✅ Steps

1. **Start Zookeeper**
    ```bash
    bin/zookeeper-server-start.sh config/zookeeper.properties
    ```

2. **Start Kafka Broker**
    ```bash
    bin/kafka-server-start.sh config/server.properties
    ```

3. **Create Topic (if not exists)**
    ```bash
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --topic orders --partitions 1 --replication-factor 1
    ```

4. **Start `notification-service`**
    ```bash
    mvn spring-boot:run
    ```

5. **Start `order-service`**
    ```bash
    mvn spring-boot:run
    ```

6. **Send Order Request**

    ```bash
    curl -X POST http://localhost:8081/api/order \
    -H "Content-Type: application/json" \
    -d '{
      "customerEmail": "arihripiyu@gmail.com",
      "items": [
        "Laptop",
        "Wireless Mouse",
        "Artic Fox Bag"
      ],
      "shippingAddress": "Pune"
    }'
    ```

7. ✅ You’ll receive an email at `arihripiyu@gmail.com`.

---

## 📫 Email Configuration

Update your `application.properties` in `notification-service`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=465
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.ssl.enable=true
```

---

## 📁 Folder Structure

```
kafka-microservices/
├── order-service/
│   └── OrderController.java
│   └── OrderProducer.java
│   └── application.properties
├── notification-service/
│   └── OrderConsumer.java
│   └── EmailService.java
│   └── application.properties
```

---

## ✨ Features

- Kafka integration with Spring Boot
- Real-world email notifications
- Decoupled microservices
- JSON-based order messages

---

## 🔐 Security

- Uses **Gmail App Passwords** (not main password)
- Never commit sensitive credentials to GitHub

---

## 👨‍💻 Author

Piyush Borse  
📧 `piyushborse2501@gmail.com`

---
