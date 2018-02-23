package com.openplan.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.entity.model.ZhCityExtra;
import com.openplan.server.entity.model.ZhProvince;
import com.openplan.server.entity.model.ZhRegionExtra;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.district.ZhCityDTO;
import com.openplan.server.module.district.ZhCityListDTO;
import com.openplan.server.module.district.ZhProvinceDTO;
import com.openplan.server.module.district.ZhProvinceListDTO;
import com.openplan.server.module.district.ZhRegionDTO;
import com.openplan.server.module.district.ZhRegionListDTO;
import com.openplan.server.service.DistrictService;

@Controller
@RequestMapping("/district")
public class DistrictController {

    private static final Logger LOG = LoggerFactory.getLogger(DistrictController.class);

    @Autowired
    private DistrictService districtService;

    @RequestMapping(method = { RequestMethod.GET })
    @ResponseBody
    public String getAction(HttpServletRequest request) {
        LOG.info("getAction");
        return "行政区域";
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces")
    @ResponseBody
    public String actionListProvince(HttpServletRequest request) {
        LOG.info("actionListProvince");
        List<ZhProvince> provinces = districtService.listProvinceAll();
        if (provinces == null || provinces.isEmpty()) {
            LOG.error("actionListProvince provinces empty");
            return ResponseBuilder.fail(10001, "provinces empty");
        }
        List<ZhProvinceDTO> provinceDTOList = new ArrayList<ZhProvinceDTO>(provinces.size());

        for (ZhProvince model : provinces) {
            provinceDTOList.add(buildProvinceDTO(model));
        }

        return ResponseBuilder.success(new ZhProvinceListDTO(provinceDTOList));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces/{provinceNo}")
    @ResponseBody
    public String actionGetProvinceByNo(HttpServletRequest request, @PathVariable Integer provinceNo) {
        LOG.info("actionGetProvinceByNo");
        ZhProvince province = districtService.getProvinceByProvinceNo(provinceNo);
        if (province == null) {
            LOG.error("actionGetProvinceByNo provinceNo not exists");
            return ResponseBuilder.fail(10002, "provinceNo not exists");
        }
        return ResponseBuilder.success(buildProvinceDTO(province));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces/{provinceNo}/citys")
    @ResponseBody
    public String actionListCityByProvinceNo(HttpServletRequest request, @PathVariable Integer provinceNo) {
        LOG.info("actionListCityByProvinceNo");
        List<ZhCityExtra> citys = districtService.listCityByProvinceNo(provinceNo);
        if (citys == null || citys.isEmpty()) {
            LOG.error("actionListCityByProvinceNo provinceNo not exists");
            return ResponseBuilder.fail(10003, "provinceNo not exists");
        }
        List<ZhCityDTO> cityListDTO = new ArrayList<ZhCityDTO>(citys.size());

        for (ZhCityExtra model : citys) {
            cityListDTO.add(buildCityDTO(model));
        }

        return ResponseBuilder.success(new ZhCityListDTO(cityListDTO));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/citys")
    @ResponseBody
    public String actionListCity(HttpServletRequest request) {
        LOG.info("actionListCity");
        List<ZhCityExtra> citys = districtService.listCityAll();
        if (citys == null || citys.isEmpty()) {
            LOG.error("actionListCity citys empty");
            return ResponseBuilder.fail(10004, "citys empty");
        }
        List<ZhCityDTO> cityListDTO = new ArrayList<ZhCityDTO>(citys.size());

        for (ZhCityExtra model : citys) {
            cityListDTO.add(buildCityDTO(model));
        }

        return ResponseBuilder.success(new ZhCityListDTO(cityListDTO));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/citys/{cityNo}")
    @ResponseBody
    public String actionGetCityByCityNo(HttpServletRequest request, @PathVariable Integer cityNo) {
        ZhCityExtra city = districtService.getCityByCityNo(cityNo);
        if (city == null) {
            LOG.error("actionGetCityByCityNo cityNo not exists");
            return ResponseBuilder.fail(10005, "cityNo not exists");
        }
        return ResponseBuilder.success(buildCityDTO(city));
    }
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/citys/{cityName}")
    @ResponseBody
    public String actionGetCityByCityName(HttpServletRequest request, @PathVariable String cityName) {
        ZhCityExtra city = districtService.getCityByCityName(cityName);
        if (city == null) {
            LOG.error("actionGetCityByCityNo cityNo not exists");
            return ResponseBuilder.fail(10005, "cityNo not exists");
        }
        return ResponseBuilder.success(buildCityDTO(city));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces/{provinceNo}/regions")
    @ResponseBody
    public String actionListRegionByProvinceNo(HttpServletRequest request, @PathVariable Integer provinceNo) {
        LOG.info("actionListRegionByProvinceNo");
        List<ZhRegionExtra> regions = districtService.listRegionByProvinceNo(provinceNo);
        if (regions == null || regions.isEmpty()) {
            LOG.error("actionListRegionByProvinceNo provinceNo not exists");
            return ResponseBuilder.fail(10006, "provinceNo not exists");
        }
        List<ZhRegionDTO> regionListDTO = new ArrayList<ZhRegionDTO>(regions.size());

        for (ZhRegionExtra model : regions) {
            regionListDTO.add(buildRegionDTO(model));
        }

        return ResponseBuilder.success(new ZhRegionListDTO(regionListDTO));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/citys/{cityNo}/regions")
    @ResponseBody
    public String actionListRegionByCityNo(HttpServletRequest request, @PathVariable Integer cityNo) {
        LOG.info("actionListRegionByCityNo");
        List<ZhRegionExtra> regions = districtService.listRegionByCityNo(cityNo);
        if (regions == null || regions.isEmpty()) {
            LOG.error("actionListRegionByCityNo cityNo not exists");
            return ResponseBuilder.fail(10007, "cityNo not exists");
        }
        List<ZhRegionDTO> regionListDTO = new ArrayList<ZhRegionDTO>(regions.size());

        for (ZhRegionExtra model : regions) {
            regionListDTO.add(buildRegionDTO(model));
        }

        return ResponseBuilder.success(new ZhRegionListDTO(regionListDTO));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/regions")
    @ResponseBody
    public String actionListRegion(HttpServletRequest request) {
        LOG.info("actionListRegion");
        List<ZhRegionExtra> regions = districtService.listRegionAll();
        if (regions == null || regions.isEmpty()) {
            LOG.error("actionListRegion regions not exists");
            return ResponseBuilder.fail(10008, "regions not exists");
        }
        List<ZhRegionDTO> regionListDTO = new ArrayList<ZhRegionDTO>(regions.size());

        for (ZhRegionExtra model : regions) {
            regionListDTO.add(buildRegionDTO(model));
        }

        return ResponseBuilder.success(new ZhRegionListDTO(regionListDTO));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/regions/{regionNo}")
    @ResponseBody
    public String actionGetRegionByRegionNo(HttpServletRequest request, @PathVariable Integer regionNo) {
        LOG.info("actionGetRegionByRegionNo");
        ZhRegionExtra region = districtService.getRegionByRegionNo(regionNo);
        if (region == null) {
            LOG.error("actionGetRegionByRegionNo region not exists");
            return ResponseBuilder.fail(10009, "region not exists");
        }
        return ResponseBuilder.success(buildRegionDTO(region));
    }

    private ZhProvinceDTO buildProvinceDTO(ZhProvince model) {
        ZhProvinceDTO dto = new ZhProvinceDTO();
        dto.setProvinceName(model.getProvinceName());
        dto.setProvinceNo(model.getProvinceNo());
        return dto;
    }

    private ZhCityDTO buildCityDTO(ZhCityExtra model) {
        ZhCityDTO dto = new ZhCityDTO();
        dto.setCityName(model.getCityName());
        dto.setCityNo(model.getCityNo());
        dto.setFirstLetter(model.getFirstLetter());
        dto.setProvinceNo(model.getProvinceNo());
        dto.setProvinceName(model.getProvinceName());
        return dto;
    }

    private ZhRegionDTO buildRegionDTO(ZhRegionExtra model) {
        ZhRegionDTO dto = new ZhRegionDTO();
        dto.setCityNo(model.getCityNo());
        dto.setRegionName(model.getRegionName());
        dto.setRegionNo(model.getRegionNo());
        dto.setCityName(model.getCityName());
        dto.setProvinceNo(model.getProvinceNo());
        return dto;
    }

}
