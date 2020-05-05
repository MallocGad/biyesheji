package com.ht.university.house.repository;

import com.ht.university.house.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ht
 * @Date: Create in 14:37 2020/3/5
 * @Describe:
 * @Last_change:
 */
public interface HouseRepository extends JpaRepository<House,Long> {

}
