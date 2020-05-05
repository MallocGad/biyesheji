package com.ht.university.msg;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ht
 * @Date: Create in 15:02 2020/3/11
 * @Describe:控制设备，模拟设备的开启关闭，记录到数据库
 * @Last_change:
 */
public interface ControllDeviceMsgRepository extends JpaRepository<ControllDeviceMsg,Long> {

}
