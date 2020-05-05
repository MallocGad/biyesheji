package com.ht.university.user.repository;

import com.ht.university.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ht
 * @Date: Create in 16:56 2020/1/14
 * @Describe:使用jpa
 * @Last_change:
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
