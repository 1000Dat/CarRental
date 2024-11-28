package com.example.carrental.Entity;

import com.example.carrental.Enum.Role;
import com.example.carrental.Enum.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users",schema = "car_rental")
@Data
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;



    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    @Email(message = "Email không đúng định dạng")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@example\\.com$", message = "Email phải kết thúc bằng @example.com")
    private String emailAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true)
    private String licenseNumber;

    @Column(nullable = true)
    private String idCardNumber;

    @Column(nullable = true)
    private String idLicense;;

    @Column(nullable = true)
    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt trước của bằng lái xe phải có đuôi là .jpg, .jpeg hoặc .png")
    private String frontLicenseImage; // Ảnh mặt trước bằng lái xe

    @Column(nullable = true)
    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt sau của bằng lái xe phải có đuôi là .jpg, .jpeg hoặc .png")
    private String backLicenseImage; // Ảnh mặt sau bằng lái xe

    @Column(nullable = true)
    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt trước của chứng minh thư phải có đuôi là .jpg, .jpeg hoặc .png")
    private String frontIdCardImage; // Ảnh mặt trước chứng minh thư

    @Column(nullable = true)
    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh mặt sau của chứng minh thư phải có đuôi là .jpg, .jpeg hoặc .png")
    private String backIdCardImage; // Ảnh mặt sau chứng minh thư

    @Column(nullable = true)
    @Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Ảnh đại diện phải có đuôi là .jpg, .jpeg hoặc .png")
    private String profilePicture; // Ảnh đại diện

    @Column(nullable = true)
    private String address; // Địa chỉ nhà

    @Column(name = "phone_number" ,nullable = true,unique = true)
    private String phoneNumber; // Số điện thoại

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate; // Ngày tạo

    @Column(nullable = true)
    private LocalDateTime updatedDate; // Ngày cập nhật


    // Thêm phương thức khởi tạo cho ngày tạo
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    // Thêm phương thức để cập nhật ngày cập nhật
    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    private UserStatus status;


}
