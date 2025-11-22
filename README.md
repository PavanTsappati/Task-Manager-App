# Task Manager App (Spring Boot + Vanilla JS)

A modern and functional **Task Management Application** built using **Spring Boot, Java, HTML, CSS, and JavaScript.**  
The app supports secure authentication, CRUD operations, search, pagination, and a detailed audit log tracking system.

---

## 🚀 Features

✔ Create, View, Edit, and Delete tasks  
✔ Search tasks by title or description  
✔ Pagination support (5 tasks per page)  
✔ Audit Log showing detailed changes  
✔ Only updated fields logged in edit mode  
✔ Color-coded activity logs  
✔ Protected API routes using **Basic Authentication**  
✔ Clean and responsive UI  
✔ Validation on both frontend & backend  

---

## 🛠 Tech Stack

| Layer | Technologies |
|-------|-------------|
| Backend | Spring Boot, Spring Security, JPA/Hibernate |
| Frontend | HTML, CSS, JavaScript |
| Database | H2  |
| Build Tool | Maven |
| Version Control | Git & GitHub |

---

## 🔐 Authentication

All protected endpoints require Basic Auth.

| Username | Password |
|----------|----------|
| `admin` | `password123` |

---

## 📂 Project Structure

```
src/
 └── main
      ├── java/com.qc.taskmanager
      │      ├── config
      │      ├── controller
      │      ├── model
      │      ├── repository
      │      └── service
      └── resources
            ├── static/
            │     ├── login.html
            │     ├── tasks.html
            │     ├── logs.html
            │     ├── style.css
            │     └── script.js
            └── application.properties
```

---

## ▶️ Running the Application

### 1️⃣ Clone the project
```
git clone https://github.com/PavanTsappati/Task-Manager-App.git
cd Task-Manager-App
```

### 2️⃣ Run via Maven
```
mvn spring-boot:run
```

### 3️⃣ Open the App

```
http://localhost:8080/login.html
```

---

## 📊 Audit Log Example

| Timestamp | Action | Task ID | Updated Fields |
|-----------|--------|---------|---------------|
| 2025-11-22 | Create Task | 1 | `{title, description}` |
| 2025-11-22 | Update Task | 1 | `{description}` |
| 2025-11-22 | Delete Task | 1 | `null` |

---

## 🧪 Validations

- Title (required, max 100 chars)  
- Description (required, max 500 chars)  

---

## 🔮 Possible Future Enhancements

- JWT Authentication  
- Role-based users (Admin/User)
- Dark/Light theme toggle
- Docker configuration support  

---

## 👨‍💻 Author

**Pavan Tsappati**  
Backend Developer — Java • Spring Boot  
📧 Email: _(optional)_  
🔗 GitHub: https://github.com/PavanTsappati  

---

## ⭐ If this project helped or impressed you — consider giving it a ⭐ on GitHub!

