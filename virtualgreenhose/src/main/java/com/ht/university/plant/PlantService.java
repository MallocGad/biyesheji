package com.ht.university.plant;

import com.ht.university.house.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 10:23 2020/4/4
 * @Describe:
 * @Last_change:
 */
@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private HouseMapper mapper;
    /**
     * 查询所有种子信息
     * @return
     */
    public List<Plant> selectPlant(){
        List<Plant> list = plantRepository.findAll();
        return list;
    }

    /**
     * 新增和修改
     * @param plant
     */
    public void addPlant(Plant plant){
        plantRepository.save(plant);
    }

    /**
     * 删除
     * @param id
     */
    @Transient
    public void deletePlant(Long id){
        plantRepository.deleteById(id);
        //删除植物后 对应温室的plantid应该设置为null
        mapper.setNullByPlantId(id);
    }

}
