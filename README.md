# Bài kiểm tra giữa kỳ - Quản lý món ăn

**Sinh viên:** Nguyễn Văn Minh  
**MSSV:** BIT240157  
**Project:** BIT240157_FoodManager

| Nội dung | Tệp |
|---|---|
| Mã nguồn Android Java | [BIT240157_FoodManager](BIT240157_FoodManager) |
| Mã nguồn dạng ZIP | [BIT240157_MaNguon.zip](BIT240157_MaNguon.zip) |
| APK cài thử | [BIT240157_FoodManager.apk](BIT240157_FoodManager.apk) |
| Hướng dẫn | [BIT240157_HuongDan.pdf](BIT240157_HuongDan.pdf) |
| Video 1 - demo thao tác, không thuyết minh | [BIT240157_Video1_Demo.mp4](BIT240157_Video1_Demo.mp4) |

Không kèm Video 2 thuyết minh.

## Chức năng và chạy thử

Android Native Java, SQLite, giao diện hồng. Có 3 loại và 6 món mẫu. CRUD món/loại, tìm kiếm và bộ lọc kết hợp, sắp xếp, gallery và ảnh đại diện, kiểm tra ràng buộc, chuyển English/Tiếng Việt và lưu offline. Tên project, namespace, database, bảng/cột và thông tin hiển thị dùng BIT240157.

Mở thư mục project bằng Android Studio, cài SDK Platform 34, chọn Gradle JDK tương thích rồi chạy module app. Bản bàn giao dùng JBR 25, Gradle 9.6.0 và AGP 9.4.1. APK yêu cầu Android 8.0 trở lên.

[README chi tiết](BIT240157_FoodManager/README.md) - [Kết quả kiểm tra](BIT240157_FoodManager/docs/KIEM_TRA.md).

## MockAPI

**Chưa có hai endpoint riêng của bản BIT240157.** SQLite và phần cơ bản dùng được ngay. Mã nguồn có bộ tự đồng bộ/outbox; cấu hình hai URL resource HTTPS trong Settings theo [schema](BIT240157_FoodManager/docs/MOCKAPI_SCHEMA.json) rồi kiểm tra dịch vụ thật. Video này thể hiện lưu offline, chưa chứng minh tự đồng bộ lên MockAPI. Không dùng chung endpoint của bài khác.

## Video 1

Video quay lại trực tiếp ứng dụng Nguyễn Văn Minh - BIT240157, không có giọng đọc. Bao gồm danh sách, chi tiết và ảnh, đổi ngôn ngữ, thêm/sửa/xóa, bộ lọc, lỗi nhập liệu, quản lý loại và lưu offline. Không đổi tên hay che thông tin trong video của người khác.

Để nộp chính thức, cần đối chiếu đề và hoàn thiện phần MockAPI cùng các nội dung video còn được yêu cầu. Việc đăng GitHub không thay thế nộp LMS.
