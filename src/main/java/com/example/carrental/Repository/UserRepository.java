package com.example.carrental.Repository;

import com.example.carrental.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity,Long> {
    // Tìm kiếm người dùng theo tên
    Optional<UsersEntity> findByUserName(String userName);

    // Tìm kiếm người dùng theo email
    Optional<UsersEntity> findByEmailAddress(String emailAddress);

    Optional<UsersEntity>findByPhoneNumber (String phone);

//
//    // Kiểm tra xem tên người dùng có tồn tại trong cơ sở dữ liệu không
//    boolean existsByuserName(String username);
//
//    // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu không
//    boolean existsByemailAddress(String email);
//
//    // Lấy thông tin người dùng bằng username (nếu có)
//    Optional<UsersEntity> findByuserName(String username);
//
//    // Lấy thông tin người dùng bằng email (nếu có)

}
