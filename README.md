# QuanProject

- Database Sửa Có sửa lại -> Có thêm 1 trường amountOfPeople (số lượng người ngồi) - kiểu: int

- Khi đăng nhập, sigup thì luôn chuyển về chuyển qua Servlet - HomeServlet để hiện số lượng bàn, số bàn đã đặt, số bàn chưa đặt, số bàn đang 
![image](https://user-images.githubusercontent.com/83583888/198874237-b7551565-658b-42a2-9456-23d63d2e33f0.png)

- Sau khi login tk thì sẽ set giá trị là có kiểu là Account ko dùng getName() nữa
![image](https://user-images.githubusercontent.com/83583888/198874277-7e8d5398-09b4-499b-8a46-b798c331bd2a.png)

- Lấy các module sửa lại các func sau đừng lấy chạy dự án -> Dự án dùng tomcat 10 - JDK 18 -> sẽ dùng thư viện jarkata
+ LoginServlet
+ RegisterServlet -> Lưu ý phần auto increasement id là dùng size của mảng + 1 nên nếu xóa 1 phần tử ở giữa trong db nó sẽ báo lỗi
+ AuthFilter
+ CancelBillController
+ BookingServle
+ TableServlet
+ Entity có sửa lại nên check lại đặt biệt là Tables, TablesType và Reservation
+ DAO Thì có thêm vài hàm 
+ Ở WebPage nên lấy folder PageError hoặc có thể lên mạng lấy cũng được -> dùng bên filter nếu có ai đó vào trang
+ Check lại web.xml
