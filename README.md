# VIT Semester Result Portal

Responsive Spring Boot and MySQL application for preparing one-semester results across four courses.

## Setup

1. Create or start a MySQL server locally.
2. Update `spring.datasource.username` and `spring.datasource.password` in `src/main/resources/application.properties`.
3. Run `mvn spring-boot:run`.
4. Open `http://localhost:8080`.
mvn -s .mvn/settings.xml spring-boot:run

The database `vit_results` and the `student_results` table are created automatically. MSE marks contribute 30%; ESE marks contribute 70%. Grades: S (90+), A (80+), B (70+), C (60+), D (50+), E (40+), F (below 40).
