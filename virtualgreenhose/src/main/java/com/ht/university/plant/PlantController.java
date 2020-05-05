package com.ht.university.plant;

import com.ht.university.common.result.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 10:23 2020/4/4
 * @Describe:
 * @Last_change:
 */
@RestController
@RequestMapping("api-plant/")
public class PlantController {
    @Autowired
    private PlantService service;

    /**
     * 新增植物种子信息
     * @param param
     * @return
     */
    @RequestMapping("add-plant")
    public ResultBody addPlant(@RequestBody PlantVo param){

        Plant plant = new Plant();
        return getResultBody(param, plant);
    }

    /**
     * 删除
     * @param param
     * @return
     */
    @RequestMapping("delete-plant")
    public ResultBody deletePlant(@RequestBody PlantVo param){
        service.deletePlant(param.getPlantId());
        return ResultBody.success();
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping("find-all-plant")
    public ResultBody findAll(){
        List<Plant> plants = service.selectPlant();
        return ResultBody.success(plants);
    }

    /**
     * 编辑
     * @param param
     * @return
     */
    @RequestMapping("edit-plant")
    public ResultBody editHouse(@RequestBody PlantVo param){

        Plant plant = new Plant();
        plant.setPlantId(param.getPlantId());
        return getResultBody(param, plant);
    }

    /**
     * 编辑和查询的重合代码
     * @param param
     * @param plant
     * @return
     */
    private ResultBody getResultBody( PlantVo param, Plant plant) {
        plant.setPlantComment(param.getPlantComment());
        plant.setPlant_maxLx(param.getPlant_maxLx());
        plant.setPlant_minLx(param.getPlant_minLx());
        plant.setPlantMaxOC(param.getPlantMaxOC());
        plant.setPlantMinOC(param.getPlantMinOC());
        plant.setPlant_maxPH(param.getPlant_maxPH());
        plant.setPlant_minPH(param.getPlant_minPH());
        plant.setPlant_maxPpm(param.getPlant_maxPpm());
        plant.setPlant_minPpm(param.getPlant_minPpm());
        plant.setPlant_maxRH(param.getPlant_maxRH());
        plant.setPlant_minRH(param.getPlant_minRH());

        service.addPlant(plant);
        return ResultBody.success();
    }
}
