package com.openplan.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.domain.model.Placer;
import com.openplan.server.query.ListQueryHelper;
import com.openplan.server.service.PlacerService;

/**
 * @author lijianqiang
 *
 */
@Controller
@RequestMapping("/placers")
public class PlacerController {

    private static final Logger LOG = LoggerFactory.getLogger(PlacerController.class);

    @Autowired
    private PlacerService placerService;

//    @Autowired
//    private DistrictService districtService;

    @RequestMapping(method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Placer> actionListPlacer(HttpServletRequest request) {
        Map<String, Object> params = buildQueryParam(request);
        List<Placer> queryResult = placerService.listByCondition(params);
        return queryResult;
    }

    private Map<String, Object> buildQueryParam(HttpServletRequest request) {
        return ListQueryHelper.buildQueryParam(request, com.openplan.server.enums.PlacerKey.getValueList());
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Placer actionGetById(HttpServletRequest request, @PathVariable Integer id) {
        Placer placer = placerService.getById(id);
        if (placer == null) {
            LOG.error("actionGetById failed");
            return new Placer();
        }
        return placer;
    }

//    @RequestMapping(method = { RequestMethod.POST }, produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String actionCreatePlacer(HttpServletRequest request, @RequestBody String placerJson) {
//        PlacerDTO placerDTO = JsonUtil.fromJson(placerJson, PlacerDTO.class);
//        Placer placer = buildPlacer(placerDTO, true);
//        placer = placerService.insert(placer);
//        if (placer == null) {
//            LOG.error("actionCreatePlacer failed");
//            return ResponseBuilder.fail(1, "save failed");
//        }
//        LOG.info("actionCreatePlacer success");
//        return ResponseBuilder.success(buildPlacerDTO(placer));
//    }
//
//    private Placer buildPlacer(PlacerDTO placerDTO, boolean isNew) {
//        Placer placer = new Placer();
//        copierPlacer.copy(placerDTO, placer, null);
//
//        if (StringUtils.isNotEmpty(placerDTO.getCityName()) && StringUtils.isNotEmpty(placerDTO.getRegionName())) {
//            ZhRegionExtra regionData = districtService.getRegionByCityAndRegionName(placerDTO.getCityName(), placerDTO.getRegionName());
//            placer.setCityNo(regionData.getCityNo());
//            placer.setCityName(regionData.getCityName());
//            placer.setProvinceNo(regionData.getProvinceNo());
//            placer.setRegionNo(regionData.getRegionNo());
//        } else if (StringUtils.isNotEmpty(placerDTO.getCityName())) {
//            ZhCityExtra cityData = districtService.getCityByCityName(placerDTO.getCityName());
//            placer.setCityNo(cityData.getCityNo());
//            placer.setProvinceNo(cityData.getProvinceNo());
//            placer.setRegionNo(cityData.getCityNo());
//        }
//        if (isNew) {
//            placer.setMarker("" + System.currentTimeMillis());
//            placer.setLevel(1);
//            placer.setRented(false);
//        }
//
//        if (placerDTO.getLongitude() != null) {
//            GeoHash geoHash = new GeoHash(Double.valueOf(placerDTO.getLongitude()), Double.valueOf(placerDTO.getLatitude()), 25);
//            placer.setGeohash(geoHash.encodeHash());
//        }
//
//        return placer;
//    }
//
//    @RequestMapping(method = { RequestMethod.PUT }, value = "/{id}", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String actionUpdatePlacerById(HttpServletRequest request, @PathVariable Integer id, @RequestBody PlacerDTO placerDTO) {
//        Placer exist = placerService.getById(id);
//        if (exist == null) {
//            LOG.error("actionUpdatePlacerById:{} failed, not exist", id);
//            return ResponseBuilder.fail(2, "not exist");
//        }
//        Placer placer = buildPlacer(placerDTO, false);
//        placer.setId(id);
//        boolean isOk = placerService.updateById(placer);
//        if (!isOk) {
//            LOG.error("actionUpdatePlacerById:{} failed", id);
//            return ResponseBuilder.fail(2, "update failed");
//        }
//        LOG.info("actionUpdatePlacerById:{} success", id);
//        return ResponseBuilder.success(buildPlacerDTO(placer));
//    }
//
//    @RequestMapping(method = { RequestMethod.DELETE }, value = "/{id}")
//    @ResponseBody
//    public String actionDeletePlacerById(HttpServletRequest request, @PathVariable Integer id) {
//        Placer exist = placerService.getById(id);
//        if (exist == null) {
//            LOG.error("actionDeletePlacerById:{} failed, not exist", id);
//            return ResponseBuilder.fail(3, "not exist");
//        }
//        boolean isDel = placerService.deleteById(exist);
//        if (!isDel) {
//            LOG.error("actionDeletePlacerById:{} failed", id);
//            return ResponseBuilder.fail(4, "delete fail");
//        }
//        LOG.info("actionDeletePlacerById:{} success", id);
//        return ResponseBuilder.success(buildPlacerDTO(exist));
//    }

}
