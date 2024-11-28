package com.example.carrental.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterDTO {

    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String emailAddress;

    private String licenseNumber;

    private String idCardNumber;

    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh đại diện phải có đuôi là .jpg, .jpeg hoặc .png")
    private String profilePicture;

    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt trước của bằng lái xe phải có đuôi là .jpg, .jpeg hoặc .png")
    private String frontLicenseImage;

    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt sau của bằng lái xe phải có đuôi là .jpg, .jpeg hoặc .png")
    private String backLicenseImage;

    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt trước của chứng minh thư phải có đuôi là .jpg, .jpeg hoặc .png")
    private String frontIdCardImage;

    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt sau của chứng minh thư phải có đuôi là .jpg, .jpeg hoặc .png")
    private String backIdCardImage;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address; // Địa chỉ nhà

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(\\+84|0)[3|5|7|8|9]\\d{8}$", message = "Số điện thoại không hợp lệ") // Ví dụ định dạng số điện thoại Việt Nam
    private String phoneNumber; // Số điện thoại
    private LocalDateTime createdAt; // Ngày tạo tài khoản

    private LocalDateTime updatedAt; // Ngày cập nhật tài khoản
}
