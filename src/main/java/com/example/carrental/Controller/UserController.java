package com.example.carrental.Controller;



import com.example.carrental.Dto.RegisterDTO;
import com.example.carrental.Service.UserService;
import com.example.carrental.Validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;


    // Đăng ký người dùng mới


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        try {

            // Tiến hành lưu người dùng vào cơ sở dữ liệu
            userService.create(registerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (IllegalArgumentException ex) {
            // Trả về thông báo lỗi với mã trạng thái 400 (Bad Request)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            // Trả về lỗi chung 500 nếu có vấn đề không mong muốn
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + ex.getMessage());
        }
    }

}