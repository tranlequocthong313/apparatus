# Dashboard Quản lý Thiết bị (Spring MVC)

**Dashboard Quản lý Thiết bị** là một ứng dụng web được phát triển bằng Spring MVC, giúp quản lý thông tin các thiết bị trong một hệ thống. Ứng dụng này cung cấp các tính năng như thêm, sửa, xóa, và xem thông tin chi tiết của các thiết bị, đồng thời hỗ trợ phân quyền người dùng và quản lý lịch sử hoạt động.

## Tính năng chính

- **Quản lý thiết bị**: Thêm, sửa, xóa, và xem thông tin chi tiết của các thiết bị.
- **Quản lý người dùng**: Phân quyền người dùng (admin, user) và quản lý thông tin người dùng.
- **Tìm kiếm và phân trang**: Hỗ trợ tìm kiếm thiết bị và phân trang dữ liệu.
- **Lịch sử hoạt động**: Theo dõi lịch sử thao tác của người dùng trên hệ thống.
- **Báo cáo và thống kê**: Xem báo cáo thống kê về trạng thái và hoạt động của thiết bị.

## Công nghệ sử dụng

- **Backend**: Spring MVC, Spring Boot, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript, Bootstrap
- **Database**: MySQL (hoặc H2 cho môi trường phát triển)
- **Authentication**: Spring Security (JWT hoặc Session-based)
- **Build Tool**: Maven

## Cài đặt và chạy dự án

### Yêu cầu hệ thống

- Java 11 trở lên
- Maven 3.x
- MySQL (hoặc H2 cho môi trường phát triển)

### Các bước cài đặt

1. **Clone dự án**:
   ```bash
   git clone https://github.com/tranlequocthong313/apparatus.git
   cd apparatus
   ```

2. **Cấu hình cơ sở dữ liệu**:
   - Tạo một cơ sở dữ liệu MySQL (hoặc sử dụng H2 cho môi trường phát triển).
   - Cập nhật file `application.properties` hoặc `application.yml` với thông tin kết nối cơ sở dữ liệu:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
     spring.datasource.username=your_db_user
     spring.datasource.password=your_db_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build và chạy dự án**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Truy cập ứng dụng**:
   - Mở trình duyệt và truy cập vào địa chỉ: `http://localhost:8080`

### Cấu hình môi trường

Tạo file `application.properties` hoặc `application.yml` trong thư mục `src/main/resources` và thêm các cấu hình cần thiết:

```properties
# Cấu hình cơ sở dữ liệu
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

# Cấu hình Spring Security
spring.security.user.name=admin
spring.security.user.password=admin123

# Cấu hình JWT (nếu sử dụng)
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## Cấu trúc thư mục

```
apparatus/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── apparatus/
│   │   │   │   │   │   ├── config/          # Cấu hình Spring (Security, Web, v.v.)
│   │   │   │   │   │   ├── controller/      # Các controller xử lý request
│   │   │   │   │   │   ├── model/          # Các entity (JPA)
│   │   │   │   │   │   ├── repository/     # Các repository (Spring Data JPA)
│   │   │   │   │   │   ├── service/        # Các service xử lý logic nghiệp vụ
│   │   │   │   │   │   ├── util/           # Các tiện ích (JWT, v.v.)
│   │   │   │   │   │   └── ApparatusApplication.java # Main class
│   │   ├── resources/
│   │   │   ├── static/      # CSS, JS, hình ảnh
│   │   │   ├── templates/   # Các file Thymeleaf (HTML)
│   │   │   └── application.properties # Cấu hình ứng dụng
│   └── test/                # Các bài test
├── pom.xml                  # File cấu hình Maven
└── README.md                # Tài liệu hướng dẫn
```

## Các API Endpoints

Dưới đây là một số API endpoints chính:

- **Authentication**:
  - `POST /api/auth/login` - Đăng nhập và nhận JWT token (nếu sử dụng JWT).
  - `POST /api/auth/logout` - Đăng xuất.

- **Quản lý thiết bị**:
  - `GET /api/devices` - Lấy danh sách thiết bị (có phân trang).
  - `GET /api/devices/{id}` - Lấy thông tin chi tiết của một thiết bị.
  - `POST /api/devices` - Thêm thiết bị mới.
  - `PUT /api/devices/{id}` - Cập nhật thông tin thiết bị.
  - `DELETE /api/devices/{id}` - Xóa thiết bị.

- **Quản lý người dùng**:
  - `GET /api/users` - Lấy danh sách người dùng.
  - `POST /api/users` - Thêm người dùng mới.
  - `PUT /api/users/{id}` - Cập nhật thông tin người dùng.
  - `DELETE /api/users/{id}` - Xóa người dùng.

- **Lịch sử hoạt động**:
  - `GET /api/activities` - Lấy danh sách lịch sử hoạt động.

## Đóng góp

Nếu bạn muốn đóng góp vào dự án, vui lòng làm theo các bước sau:

1. Fork dự án
2. Tạo branch mới (`git checkout -b feature/YourFeatureName`)
3. Commit các thay đổi (`git commit -m 'Add some feature'`)
4. Push lên branch (`git push origin feature/YourFeatureName`)
5. Mở một Pull Request

## Liên hệ

Nếu bạn có bất kỳ câu hỏi hoặc góp ý nào, vui lòng liên hệ:

- **Tên**: Trần Lê Quốc Thông
- **Email**: tranlequocthong313@gmail.com
- **GitHub**: [tranlequocthong313](https://github.com/tranlequocthong313)
