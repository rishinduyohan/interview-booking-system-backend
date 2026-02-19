# 📅 Interview Booking System (Spring Boot + PostgreSQL)

A robust backend system designed to manage interview schedules, handle candidate bookings, and ensure data integrity through advanced validation and concurrency control.

## 🚀 Key Features
* **Prevent Double Booking:** Uses database-level unique constraints and application logic to ensure a slot cannot be booked twice.
* **Conflict Detection:** Prevents interviewers from creating overlapping time slots using custom SQL logic.
* **Time Validation:** Validates that no bookings or slots are created for past dates/times.
* **Email Simulation:** Simulates booking confirmations by logging details to the system console using SLF4J.

## 🛠️ Tech Stack
* **Framework:** Spring Boot 3.x
* **Language:** Java 17+
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven

## 📊 Entity Relationship
The system is built around four primary entities:
1.  **Candidate:** Stores applicant details.
2.  **Interviewer:** Stores details of the panel members.
3.  **InterviewSlot:** Managed time blocks for specific interviewers.
4.  **Booking:** The final record connecting a candidate to a specific slot.



---

## 🚦 API Endpoints

### 1. Interviewer Management
* `POST /api/interviewers` - Register a new interviewer in the system.

### 2. Slot Management
* `POST /api/slots/create` - Create a new availability slot.
    * *Logic: Automatically detects if the interviewer has a conflicting slot at the same time.*

### 3. Booking Management
* `POST /api/bookings/book?slotId={id}&resumeLink={link}` - Book an interview.
    * *Requirement: Candidate details must be sent as a JSON object in the Request Body.*

---

## 🔧 Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/rishinduyohan/interview-booking-system.git](https://github.com/rishinduyohan/interview-booking-system-backend.git)
    ```

2.  **Configure PostgreSQL:**
    Update `src/main/resources/application.properties` with your database credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/interview_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

---

## 🧑‍💻 Author
* **Rishindu Peeris**
* Software Engineer | Java & Web Development
* Gemini Certified Educator | Cybersecurity Specialist (Alison)

---