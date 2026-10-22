# BIT240157_FoodManager

**Sinh viên: Nguyễn Văn Minh - MSSV: BIT240157**

Ứng dụng Android Native Java quản lý món ăn, SQLite, giao diện hồng. Android 8.0/API 26 trở lên. Tên project, namespace, database, bảng/cột và thông tin hiển thị dùng BIT240157. Mã ứng dụng: `vn.edu.bit240157.foodmanager`.

## Chạy ứng dụng

1. Mở thư mục project này trong Android Studio.
2. Cài SDK Platform 34, chọn Gradle JDK tương thích. Bản biên dịch trên máy bàn giao dùng Android Studio JBR 25, Gradle 9.6.0 và AGP 9.4.1.
3. Chờ Gradle Sync rồi Run module app trên điện thoại hoặc emulator Android 8.0 trở lên.
4. Có thể cài trực tiếp APK đi kèm. APK debug phục vụ học tập.

Biên dịch: `gradlew.bat assembleDebug`. Android Studio tự tạo local.properties cho SDK của máy. Không đưa local.properties, build/, .gradle/ hoặc .idea/ lên Git; .gitignore đã có sẵn.

## Chức năng

- Dữ liệu mẫu: 3 loại, 6 món; ảnh mẫu có bản đóng gói để xem offline.
- Danh sách, chi tiết, thêm, sửa và xóa món có xác nhận. Quản lý loại; chặn xóa loại đang dùng.
- Tên bắt buộc, giá và thời gian là số nguyên dương, chọn loại hợp lệ. Chặn tên trùng trong cùng loại sau chuẩn hóa.
- Tìm một phần tên; phối hợp loại, trạng thái đang bán và khoảng giá. Sắp xếp tên, giá tăng/giảm, thời gian tăng. Giữ bộ lọc khi quay lại.
- Nhiều URL ảnh, tối đa một ảnh đại diện; xử lý ảnh trống hoặc lỗi.
- Quick khi thời gian <= 15 phút; Regular khi > 15 phút. Món ngừng bán vẫn có trong quản lý, bị loại khi chỉ lọc đang bán.
- Mặc định English, đổi VI/EN và lưu lựa chọn. Dữ liệu người dùng nhập không tự dịch.
- SQLite lưu offline; outbox ghi thay đổi cùng transaction. Có bộ tự đồng bộ MockAPI sau khi cấu hình tài nguyên riêng.

## Cấu trúc

`app/src/main/java/vn/edu/bit240157/foodmanager/`

- MainActivity: danh sách, chi tiết, form, loại món, cài đặt.
- Texts, ImageLoader: nhãn song ngữ; ảnh, cache và ảnh thay thế.
- model/: DishCategory, Dish, DishImage.
- data/Contract: nguồn tiền tố `BIT240157_`; FoodRepository: schema, dữ liệu mẫu, CRUD, ràng buộc và outbox.
- sync/SyncConfig, SyncEngine: cấu hình, theo dõi mạng, đẩy/đọc dữ liệu và thử lại.
- androidTest/.../FoodTestRunner: kiểm thử dữ liệu và giao diện; không nằm trong APK người dùng.

## Dữ liệu

Database `BIT240157_FoodManager.db`. Bảng: `BIT240157_DishCategory`, `BIT240157_Dish`, `BIT240157_DishImage`, `BIT240157_Outbox`. Tất cả cột do ứng dụng định nghĩa có tiền tố `BIT240157_`.

Quan hệ loại 1-N món 1-N ảnh. Khóa ngoại chặn xóa loại đang dùng; xóa món xóa ảnh liên quan. Chỉ mục UNIQUE(CategoryId, NameKey) chặn tên trùng cùng loại; partial unique index chặn nhiều ảnh đại diện. Giá dùng long để tránh sai số.

## MockAPI riêng

**Bản này chưa cấu hình endpoint MockAPI.** Mọi chức năng SQLite dùng được ngay. Không có dữ liệu hay tài khoản dịch vụ của sinh viên khác trong gói.

1. Tạo project MockAPI của mình, tạo hai resource `BIT240157_DishCategory` và `BIT240157_Dish`.
2. Định nghĩa trường theo `docs/MOCKAPI_SCHEMA.json`. Trường id của dịch vụ do MockAPI tự quản lý; Id nghiệp vụ là BIT240157_Id. DishImage trên API là mảng ảnh nằm trong món.
3. Trong ứng dụng, Settings/Cài đặt, dán hai URL HTTPS đầy đủ của hai resource rồi lưu. Không dán URL trang quản trị project.
4. Ứng dụng tự đồng bộ khi có mạng, không cần nút đồng bộ. Khi đang mở ứng dụng, tắt mạng, thêm và sửa món, bật lại mạng; kiểm tra API có đúng một UUID và giá trị mới.

Worker đơn luồng, GET ánh xạ UUID sang id dịch vụ, PUT nếu đã có, POST nếu chưa. Upsert loại trước món; xóa món trước loại. Revision giữ thay đổi mới nếu người dùng sửa trong lúc đang gửi. GET lại để nhập dữ liệu hợp lệ; ưu tiên local còn pending. Có thử lại 30 giây khi app mở. Chưa chạy thử dịch vụ thật cho bản này vì chưa có endpoint riêng.

Giới hạn: worker chạy khi ứng dụng mở; chưa có dịch vụ nền sau khi đóng app, hợp nhất xung đột nhiều thiết bị hoặc kéo xóa thực hiện trực tiếp trên website. Xóa từ ứng dụng được đẩy bằng outbox.

## Kiểm thử

Chạy trên thiết bị thử có dữ liệu mới (3 loại/6 món), tránh dùng dữ liệu cá nhân:

```text
gradlew.bat assembleDebug assembleDebugAndroidTest
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb install -r app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
adb shell am instrument -w -e mode test vn.edu.bit240157.foodmanager.test/vn.edu.bit240157.foodmanager.FoodTestRunner
adb shell am instrument -w -e mode ui vn.edu.bit240157.foodmanager.test/vn.edu.bit240157.foodmanager.FoodTestRunner
```

Kết quả kiểm tra bản bàn giao ghi tại docs/KIEM_TRA.md. Kiểm tra tiền tố đếm từng bảng/cột riêng, không tương ứng số chức năng.

## Gói bàn giao và tự đăng Git

Gói có mã nguồn, APK và hướng dẫn, **không kèm hai video theo yêu cầu**. Các yêu cầu video của đề, nếu vẫn áp dụng khi nộp chính thức, cần chuẩn bị riêng. Repository: https://github.com/minh1801/Baikiemtragiuaky.

Giải nén mã nguồn rồi tải các file trong project lên repository riêng. Giữ gradle wrapper, source, assets và docs. Không tải build/cache/local.properties. Có thể đặt APK và hướng dẫn cạnh thư mục source và thêm liên kết trong README của repository.
