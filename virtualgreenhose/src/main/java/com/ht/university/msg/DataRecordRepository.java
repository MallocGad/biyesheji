package com.ht.university.msg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 19:01 2020/3/11
 * @Describe:
 * @Last_change:
 */
public interface DataRecordRepository extends JpaRepository<DataRecord,Long> {
   }
