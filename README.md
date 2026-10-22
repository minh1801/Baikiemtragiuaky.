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
| Video 2 - thuyết minh mã nguồn, giọng nam | [BIT240157_Video2_ThuyetMinh.mp4](BIT240157_Video2_ThuyetMinh.mp4) |
| Phụ đề Video 2 | [BIT240157_Video2_PhuDe.srt](BIT240157_Video2_PhuDe.srt) |

Đã có Video 1 demo thao tác và Video 2 thuyết minh mã nguồn. Video 2 sử dụng giọng nam tổng hợp tiếng Việt.

## Chức năng và chạy thử

Android Native Java, SQLite, giao diện hồng. Có 3 loại và 6 món mẫu. CRUD món/loại, tìm kiếm và bộ lọc kết hợp, sắp xếp, gallery và ảnh đại diện, kiểm tra ràng buộc, chuyển English/Tiếng Việt và lưu offline. Tên project, namespace, database, bảng/cột và thông tin hiển thị dùng BIT240157.

Mở thư mục project bằng Android Studio, cài SDK Platform 34, chọn Gradle JDK tương thích rồi chạy module app. Bản bàn giao dùng JBR 25, Gradle 9.6.0 và AGP 9.4.1. APK yêu cầu Android 8.0 trở lên.

[README chi tiết](BIT240157_FoodManager/README.md) - [Kết quả kiểm tra](BIT240157_FoodManager/docs/KIEM_TRA.md).

## MockAPI

**Chưa có hai endpoint riêng của bản BIT240157.** SQLite và phần cơ bản dùng được ngay. Mã nguồn có bộ tự đồng bộ/outbox; cấu hình hai URL resource HTTPS trong Settings theo [schema](BIT240157_FoodManager/docs/MOCKAPI_SCHEMA.json) rồi kiểm tra dịch vụ thật. Video 1 thể hiện lưu offline; Video 2 giải thích mã và nêu rõ MockAPI chưa cấu hình. Hai video chưa chứng minh tự đồng bộ lên dịch vụ thật. Không dùng chung endpoint của bài khác.

## Video 1

- 00:00: ảnh, ngôn ngữ và thêm món.
- 01:10: lọc kết hợp, sửa/xóa và quản lý loại.
- 02:43: lưu offline, cài đặt và mở lại ứng dụng.

Video dài 03:48, quay lại trực tiếp ứng dụng Nguyễn Văn Minh - BIT240157, không có giọng đọc và không có track âm thanh. Bao gồm danh sách, chi tiết và ảnh, đổi ngôn ngữ, thêm/sửa/xóa, bộ lọc, lỗi nhập liệu, quản lý loại và lưu offline. 

## Video 2

Video dài **08:39**, thuyết minh bằng giọng nam tiếng Việt NamMinh, có ngắt nghỉ theo câu. Nội dung gồm cấu trúc project, model và quan hệ, tiền tố MSSV trong project/bảng/cột, SQLite, luồng lưu/đọc dữ liệu, CRUD, validation, bộ lọc, ảnh, đa ngôn ngữ, lưu offline và phạm vi MockAPI chưa cấu hình. Hình ảnh trích mã nguồn thật của BIT240157, có số dòng.

- [Mốc thời gian 13 phần](BIT240157_FoodManager/docs/VIDEO2_MOC_THOI_GIAN.md)
- [Lời thuyết minh](BIT240157_FoodManager/docs/VIDEO2_LOI_THUYET_MINH.md)
- [Phụ đề SRT](BIT240157_Video2_PhuDe.srt)

Việc đăng GitHub không thay thế nộp LMS. Theo đề, Video 2 cần nộp trên LMS chậm nhất ngày 23/10/2026. Phần MockAPI vẫn cần hai endpoint riêng và kiểm chứng đồng bộ thực tế.

