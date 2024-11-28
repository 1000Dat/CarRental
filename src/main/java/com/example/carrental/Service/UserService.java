package com.example.carrental.Service;

import com.example.carrental.Dto.RegisterDTO;
import com.example.carrental.Entity.UsersEntity;
import com.example.carrental.Enum.Role;
import com.example.carrental.Repository.UserRepository;

import com.example.carrental.Validation.UserValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserValidator userValidator;

    public void create(RegisterDTO registerDTO){
        UsersEntity usersEntity = new UsersEntity();
        userValidator.validateRegister(registerDTO);
        // Chuyển đổi các trường từ RegisterDTO sang UsersEntity
        usersEntity.setUserName(registerDTO.getUserName());
        usersEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));  // Mã hóa mật khẩu
        usersEntity.setEmailAddress(registerDTO.getEmailAddress());
        usersEntity.setRole(Role.USER);
        usersEntity.setLicenseNumber(registerDTO.getLicenseNumber());
        usersEntity.setIdCardNumber(registerDTO.getIdCardNumber());
        usersEntity.setProfilePicture(registerDTO.getProfilePicture());
        usersEntity.setFrontLicenseImage(registerDTO.getFrontLicenseImage());
        usersEntity.setBackLicenseImage(registerDTO.getBackLicenseImage());
        usersEntity.setFrontIdCardImage(registerDTO.getFrontIdCardImage());
        usersEntity.setBackIdCardImage(registerDTO.getBackIdCardImage());
        usersEntity.setAddress(registerDTO.getAddress()); // Địa chỉ nhà
        usersEntity.setPhoneNumber(registerDTO.getPhoneNumber()); // Số điện thoại

        // Cập nhật ngày tạo và ngày cập nhật
        usersEntity.setCreatedDate(LocalDateTime.now());  // Gán ngày tạo
        usersEntity.setUpdatedDate(LocalDateTime.now());  // Gán ngày cập nhật

        // Lưu đối tượng UsersEntity vào cơ sở dữ liệu
        userRepository.save(usersEntity);
    }



}
