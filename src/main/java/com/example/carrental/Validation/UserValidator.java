package com.example.carrental.Validation;

import com.example.carrental.Dto.RegisterDTO;
import com.example.carrental.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    // Định nghĩa pattern kiểm tra email hợp lệ
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Định nghĩa pattern kiểm tra định dạng ảnh hợp lệ
    private static final String IMAGE_REGEX = "([^\\s]+(\\.(?i)(jpg|png|jpeg|bmp|gif))$)";

    public void validateRegister(RegisterDTO registerDTO) {
     //    Kiểm tra tên người dùng đã tồn tại hay chưa
        checkUserNameExistence(registerDTO.getUserName());

        // Kiểm tra email đã tồn tại hay chưa
        checkEmailExistence(registerDTO.getEmailAddress());

        checkPhoneExistence(registerDTO.getPhoneNumber());

     //    Kiểm tra định dạng email hợp lệ
        validateEmailFormat(registerDTO.getEmailAddress());

        // Kiểm tra các ảnh có đúng định dạng
        validateImageFormat(registerDTO.getProfilePicture(), "Ảnh đại diện không đúng định dạng");
        validateImageFormat(registerDTO.getFrontLicenseImage(), "Ảnh mặt trước của bằng lái xe không đúng định dạng");
        validateImageFormat(registerDTO.getBackLicenseImage(), "Ảnh mặt sau của bằng lái xe không đúng định dạng");
        validateImageFormat(registerDTO.getFrontIdCardImage(), "Ảnh mặt trước của chứng minh thư không đúng định dạng");
        validateImageFormat(registerDTO.getBackIdCardImage(), "Ảnh mặt sau của chứng minh thư không đúng định dạng");
    }

    private void checkUserNameExistence(String userName) {
        if (userRepository.findByUserName(userName).isPresent()) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }
    }

    private void checkEmailExistence(String emailAddress) {
        if (userRepository.findByEmailAddress(emailAddress).isPresent()) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }
    }
    private void checkPhoneExistence(String phone) {
        if (userRepository.findByPhoneNumber(phone).isPresent()) {
            throw new IllegalArgumentException("Số điện thoại đã được sử dụng");
        }
    }

    private void validateEmailFormat(String emailAddress) {
        if (!Pattern.matches(EMAIL_REGEX, emailAddress)) {
            throw new IllegalArgumentException("Địa chỉ email không hợp lệ");
        }
    }

    private void validateImageFormat(String image, String errorMessage) {
        if (image != null && !Pattern.matches(IMAGE_REGEX, image)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
