# Lời thuyết minh Video 2

Nguyễn Văn Minh - BIT240157. Giọng nam tổng hợp tiếng Việt, NamMinh; nhịp đọc -3%, ngắt nghỉ theo câu. Tên và MSSV hiển thị trong hình; lời đọc tập trung giải thích mã nguồn.

## 1. Tổng quan bài làm (00:00)

Chào thầy. Trong video này, em xin trình bày cấu trúc và cách hoạt động của ứng dụng quản lý món ăn. Tên sinh viên, mã sinh viên và tên project được thể hiện ngay trên màn hình. Em chọn Android Native Java, kết hợp cơ sở dữ liệu SQLite để lưu trực tiếp trên thiết bị. Cách làm này giúp các thao tác quản lý món ăn vẫn sử dụng được khi không có Internet. Sau đây, em sẽ đi từ tổ chức project và mô hình dữ liệu, đến luồng xử lý chức năng, kiểm tra dữ liệu và giao diện đa ngôn ngữ.

## 2. Cấu trúc project và công nghệ (00:34)

Đầu tiên là cách tổ chức mã nguồn. Nhóm model chứa ba lớp: loại món, món ăn và ảnh. Nhóm data quản lý cơ sở dữ liệu, quy tắc đặt tên và lỗi nghiệp vụ. Main Activity xây dựng các màn hình; lớp Texts cung cấp nhãn tiếng Anh và tiếng Việt. Image Loader phụ trách tải ảnh và bộ nhớ đệm. Nhóm sync chứa hàng đợi cùng bộ đồng bộ mở rộng. Ở file cấu hình đang hiển thị, mã ứng dụng mang mã sinh viên, phiên bản Android tối thiểu là tám và mã nguồn dùng Java mười bảy. Ứng dụng không phụ thuộc vào thư viện giao diện bên ngoài.

## 3. Model và quan hệ dữ liệu (01:13)

Về dữ liệu, loại món có ba thuộc tính là mã định danh, tên và mô tả. Món ăn có mã định danh, tên, giá, thời gian chế biến, trạng thái đang bán, mô tả, mã loại và danh sách ảnh. Trong đó, mã loại tạo quan hệ nhiều món thuộc về một loại. Mỗi món lại có thể chứa nhiều ảnh; một ảnh gồm đường dẫn và cờ đánh dấu ảnh đại diện. Giá được lưu bằng số nguyên kiểu long, còn thời gian chế biến là số phút nguyên. Hàm kiểm tra nhanh dựa trên mốc mười lăm phút, vì vậy không cần lưu thêm một cột phân loại dư thừa.

## 4. MSSV trong project, bảng và cột (01:50)

Tiếp theo là yêu cầu về mã sinh viên. Trên màn hình, hằng số P chứa đúng tiền tố của bài, có dấu gạch dưới ở cuối. Các tên bảng loại món, món ăn, ảnh và hàng đợi đều được ghép từ hằng số này. Tên cột, chẳng hạn mã định danh, tên, giá, thời gian chế biến và khóa ngoại, cũng được ghép theo cùng cách. File settings của Gradle đặt tên project theo mẫu mã sinh viên, gạch dưới, Food Manager. Tên cơ sở dữ liệu được đặt trong hàm khởi tạo lớp quản lý dữ liệu. Như vậy, tiền tố được áp dụng vào bảng và cột thật khi lưu trữ, chứ không chỉ xuất hiện trong chú thích.

## 5. SQLite: khóa ngoại và ràng buộc (02:31)

Khi mở cơ sở dữ liệu lần đầu, ứng dụng tạo các bảng và dữ liệu mẫu. Quan hệ giữa món ăn và loại món được bảo vệ bằng khóa ngoại: loại đang được sử dụng không thể bị xóa. Khi xóa một món, các ảnh đi kèm được xóa theo để không để lại dữ liệu mồ côi. Ràng buộc duy nhất trên loại món và tên đã chuẩn hóa giúp chặn hai món trùng tên trong cùng loại. Một chỉ mục duy nhất có điều kiện tiếp tục bảo đảm mỗi món chỉ có tối đa một ảnh đại diện. Ngoài việc kiểm tra ở giao diện, cơ sở dữ liệu còn kiểm tra giá và thời gian phải lớn hơn không. Dữ liệu ban đầu có ba loại và sáu món ăn.

## 6. Thêm, sửa, xóa và luồng lưu dữ liệu (03:11)

Luồng ghi dữ liệu bắt đầu từ form thêm hoặc sửa. Giao diện lấy các giá trị người dùng nhập, rồi gọi hàm lưu món. Món mới được cấp một mã định danh ổn định. Với thao tác sửa, chương trình kiểm tra món đó còn tồn tại trước khi cập nhật. Sau khi dữ liệu hợp lệ, thông tin món, danh sách ảnh và thay đổi trong hàng đợi được ghi trong cùng một giao dịch. Có lỗi thì hoàn tác toàn bộ; thành công thì màn hình chi tiết đọc lại bản đã lưu. Ở chiều đọc, chương trình truy vấn món theo mã, lấy thêm các ảnh liên quan và ánh xạ kết quả thành model. Thao tác xóa luôn yêu cầu xác nhận trước khi gọi lớp dữ liệu.

## 7. Validation và thông báo lỗi (03:52)

Phần kiểm tra dữ liệu được đặt tập trung trong lớp quản lý cơ sở dữ liệu. Tên không được rỗng, giá và thời gian phải dương, còn loại món phải tồn tại. Khi kiểm tra trùng tên, chương trình cắt và gom khoảng trắng, chuẩn hóa ký tự Unicode rồi chuyển chữ thường. Nhờ vậy, khác biệt về hoa thường hoặc khoảng trắng không tạo ra hai tên tưởng như khác nhau. Cùng một tên ở hai loại khác nhau vẫn được phép. Các đường dẫn ảnh phải hợp lệ và không được trùng trong một món. Khi gặp lỗi, chương trình trả về khóa thông báo; giao diện dùng khóa đó để hiện đúng ngôn ngữ đang chọn, thay vì lưu dữ liệu sai.

## 8. Tìm kiếm, bộ lọc và sắp xếp (04:31)

Ở màn hình danh sách, người dùng có thể phối hợp tìm tên, loại món, trạng thái đang bán và khoảng giá. Các điều kiện loại, trạng thái và giá được đưa vào truy vấn cơ sở dữ liệu bằng tham số. Tên được chuẩn hóa rồi kiểm tra có chứa phần người dùng nhập hay không. Vì không dùng ký tự đại diện cho bước này, dấu phần trăm trong tên vẫn được hiểu là ký tự thông thường. Biến sắp xếp quyết định giá tăng, giá giảm hoặc thời gian chế biến tăng. Trạng thái bộ lọc được lưu trong tùy chọn ứng dụng, nên quay lại từ chi tiết hay form sửa vẫn giữ các lựa chọn. Món ngừng bán chỉ bị loại khi bật điều kiện đang bán; nó vẫn có trong danh sách quản lý chung.

## 9. Ảnh đại diện và mốc 15 phút (05:13)

Với ảnh, form cho phép thêm nhiều đường dẫn và xóa riêng từng ảnh. Màn hình chi tiết hiển thị bộ sưu tập; khi đổi ảnh đại diện, chương trình bật cờ cho ảnh được chọn và tắt cờ ở những ảnh còn lại. Các ảnh mẫu có bản đóng gói trong ứng dụng để xem được khi mất mạng. Ảnh người dùng thêm được lưu bộ nhớ đệm sau khi tải thành công; ảnh trống hoặc tải lỗi có hình thay thế. Về thời gian chế biến, hàm đang hiển thị trả về nhanh khi số phút nhỏ hơn hoặc bằng mười lăm. Nếu lớn hơn mốc đó thì hiển thị thông thường. Đây là giá trị tính từ dữ liệu hiện có, nên luôn cập nhật đúng khi người dùng sửa thời gian.

## 10. Đa ngôn ngữ và duy trì lựa chọn (05:53)

Giao diện mặc định dùng tiếng Anh. Khi chọn tiếng Việt, cờ ngôn ngữ được lưu lại và dùng chung cho danh sách, chi tiết, thêm, sửa cùng các màn hình phụ. Lớp Texts lưu từng cặp bản dịch, còn hàm get chọn nội dung theo cờ hiện tại. Việc dịch áp dụng cả nhãn, nút, trạng thái, hộp thoại và thông báo kiểm tra dữ liệu. Khi đổi ngôn ngữ ngay trong form, chương trình thu thập bản nháp trước khi dựng lại giao diện để giữ nội dung đang nhập. Tên và mô tả món do người dùng tạo không tự dịch, đúng với yêu cầu của đề. Lựa chọn ngôn ngữ cũng được giữ sau khi mở lại ứng dụng.

## 11. Lưu offline và hàng đợi thay đổi (06:32)

Phần hoạt động offline đã được thực hiện bằng cách luôn đọc và ghi trên SQLite. Khi người dùng thêm, sửa hoặc xóa, thay đổi được lưu ngay trên thiết bị; việc không có mạng không ngăn thao tác đó. Đồng thời, hàm queue ghi loại đối tượng, mã định danh, thao tác và phiên bản thay đổi vào bảng hàng đợi. Mỗi đối tượng chỉ giữ một mục hiện hành, vì vậy sửa liên tiếp không làm hàng đợi phình ra thành nhiều bản trùng. Video một đã minh họa tắt mạng, thêm món, sửa giá thành bốn mươi nghìn rồi mở lại ứng dụng. Dữ liệu vẫn còn. Đây là minh chứng lưu local; nó chưa phải minh chứng đồng bộ lên máy chủ.

## 12. MockAPI: cơ chế và phần chưa cấu hình (07:12)

Về phần Mock API, em xin phân biệt rõ mã đã chuẩn bị và chức năng đã kiểm chứng. Hai địa chỉ tài nguyên trong cấu hình hiện để trống, nên bộ đồng bộ dừng ở chế độ local. Khi có hai địa chỉ riêng và hợp lệ, cơ chế dự kiến là theo dõi thay đổi kết nối, đọc dữ liệu máy chủ và tự gửi hàng đợi. Mã định danh ổn định được dùng để tìm bản ghi trước khi tạo, còn phiên bản thay đổi giúp không xóa nhầm lần sửa mới khi yêu cầu cũ hoàn tất. Tuy nhiên, bản này chưa cấu hình và chưa kiểm chứng đồng bộ với dịch vụ thật. Vì vậy, video chỉ xác nhận phần lưu offline đã chạy, không trình bày phần tự đồng bộ là hoàn thành.

## 13. Kiểm chứng và tổng kết bài làm (07:53)

Cuối cùng là phần kiểm chứng. Bản ứng dụng đã qua sáu mươi bảy mục kiểm tra dữ liệu và mười một mục kiểm tra giao diện trên emulator. Số mục dữ liệu có tính riêng từng tên bảng, từng cột khi kiểm tra tiền tố, nên không đồng nghĩa với sáu mươi bảy chức năng. Các kiểm tra còn lại bao gồm thêm, sửa, xóa, ràng buộc, tìm kiếm, bộ lọc, sắp xếp, ảnh và chuyển ngôn ngữ. Video một minh họa thao tác thực tế; video này giải thích cấu trúc và các đoạn mã xử lý tương ứng. Mã nguồn cùng bản cài đặt được lưu trên repository của bài. Phần tự đồng bộ với dịch vụ thật vẫn cần cấu hình và kiểm chứng riêng. Em xin kết thúc phần trình bày tại đây. Em cảm ơn thầy.
