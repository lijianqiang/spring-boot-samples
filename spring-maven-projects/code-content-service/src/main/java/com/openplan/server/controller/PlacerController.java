package com.openplan.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.entity.model.Placer;
import com.openplan.server.entity.model.ZhCityExtra;
import com.openplan.server.entity.model.ZhRegionExtra;
import com.openplan.server.github.geohash.GeoHash;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.placer.PlacerDTO;
import com.openplan.server.module.placer.PlacerKey;
import com.openplan.server.module.placer.PlacerListDTO;
import com.openplan.server.query.helper.ListQueryHelper;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.DistrictService;
import com.openplan.server.service.PlacerService;
import com.openplan.server.tool.BeanCopierTool;
import com.openplan.server.util.JsonUtil;
import com.openplan.server.util.StringUtils;

import net.sf.cglib.beans.BeanCopier;

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

    @Autowired
    private DistrictService districtService;

    private final BeanCopier copierPlacerDTO = BeanCopierTool.buildCopier(new Placer(), new PlacerDTO());

    private final BeanCopier copierPlacer = BeanCopierTool.buildCopier(new PlacerDTO(), new Placer());

    private final static String[] QUERY_KEYS = { PlacerKey.CITY_NO, PlacerKey.ENABLE, PlacerKey.LEVEL, PlacerKey.NAME, PlacerKey.PROVINCE_NO,
            PlacerKey.REGION_NO };

    @RequestMapping(method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionListPlacer(HttpServletRequest request) {
        Map<String, Object> params = buildQueryParam(request);
        ConditionQueryResult<Placer> queryResult = placerService.listByCondition(params);
        PlacerListDTO buildPagingDTO = buildPlacerListDTO(queryResult);
        if (LOG.isInfoEnabled()) {
            LOG.info("json:{}", JsonUtil.toJson(queryResult));
        }
        return ResponseBuilder.success(buildPagingDTO);
    }

    private PlacerListDTO buildPlacerListDTO(ConditionQueryResult<Placer> queryResult) {
        PlacerListDTO resultDTO = new PlacerListDTO();
        resultDTO.setPage(queryResult.getPage());
        resultDTO.setTotal(queryResult.getTotal());
        resultDTO.setParams(queryResult.getParams());
        List<Placer> rows = queryResult.getRows();
        int size = rows != null ? rows.size() : 0;
        if (queryResult.getTotal() > 0 && size > 0) {
            List<PlacerDTO> listDTO = new ArrayList<PlacerDTO>(size + 1);
            long start = System.nanoTime();
            long startms = System.currentTimeMillis();
            for (Placer model : rows) {
                listDTO.add(buildPlacerDTO(model));
            }
            LOG.info("property copy time:{}ns, {}ms", (System.nanoTime() - start), (System.currentTimeMillis() - startms));
            resultDTO.setRows(listDTO);
        }

        return resultDTO;
    }

    private PlacerDTO buildPlacerDTO(Placer model) {
        PlacerDTO qrcodeDTO = new PlacerDTO();
        copierPlacerDTO.copy(model, qrcodeDTO, null);
        return qrcodeDTO;
    }

    private Map<String, Object> buildQueryParam(HttpServletRequest request) {
        return ListQueryHelper.buildQueryParam(request, QUERY_KEYS);
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionGetById(HttpServletRequest request, @PathVariable Integer id) {
        Placer placer = placerService.getById(id);
        if (placer == null) {
            LOG.error("actionGetById failed");
            return ResponseBuilder.fail(1, "actionGetById failed");
        }
        return ResponseBuilder.success(buildPlacerDTO(placer));
    }

    @RequestMapping(method = { RequestMethod.POST }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionCreatePlacer(HttpServletRequest request, @RequestBody String placerJson) {
        PlacerDTO placerDTO = JsonUtil.fromJson(placerJson, PlacerDTO.class);
        Placer placer = buildPlacer(placerDTO, true);
        placer = placerService.insert(placer);
        if (placer == null) {
            LOG.error("actionCreatePlacer failed");
            return ResponseBuilder.fail(1, "save failed");
        }
        LOG.info("actionCreatePlacer success");
        return ResponseBuilder.success(buildPlacerDTO(placer));
    }

    private Placer buildPlacer(PlacerDTO placerDTO, boolean isNew) {
        Placer placer = new Placer();
        copierPlacer.copy(placerDTO, placer, null);

        if (StringUtils.isNotEmpty(placerDTO.getCityName()) && StringUtils.isNotEmpty(placerDTO.getRegionName())) {
            ZhRegionExtra regionData = districtService.getRegionByCityAndRegionName(placerDTO.getCityName(), placerDTO.getRegionName());
            placer.setCityNo(regionData.getCityNo());
            placer.setCityName(regionData.getCityName());
            placer.setProvinceNo(regionData.getProvinceNo());
            placer.setRegionNo(regionData.getRegionNo());
        } else if (StringUtils.isNotEmpty(placerDTO.getCityName())) {
            ZhCityExtra cityData = districtService.getCityByCityName(placerDTO.getCityName());
            placer.setCityNo(cityData.getCityNo());
            placer.setProvinceNo(cityData.getProvinceNo());
            placer.setRegionNo(cityData.getCityNo());
        }
        if (isNew) {
            placer.setMarker("" + System.currentTimeMillis());
            placer.setLevel(1);
            placer.setRented(false);
        }

        if (placerDTO.getLongitude() != null) {
            GeoHash geoHash = new GeoHash(Double.valueOf(placerDTO.getLongitude()), Double.valueOf(placerDTO.getLatitude()), 25);
            placer.setGeohash(geoHash.encodeHash());
        }

        return placer;
    }

    @RequestMapping(method = { RequestMethod.PUT }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionUpdatePlacerById(HttpServletRequest request, @PathVariable Integer id, @RequestBody PlacerDTO placerDTO) {
        Placer exist = placerService.getById(id);
        if (exist == null) {
            LOG.error("actionUpdatePlacerById:{} failed, not exist", id);
            return ResponseBuilder.fail(2, "not exist");
        }
        Placer placer = buildPlacer(placerDTO, false);
        placer.setId(id);
        boolean isOk = placerService.updateById(placer);
        if (!isOk) {
            LOG.error("actionUpdatePlacerById:{} failed", id);
            return ResponseBuilder.fail(2, "update failed");
        }
        LOG.info("actionUpdatePlacerById:{} success", id);
        return ResponseBuilder.success(buildPlacerDTO(placer));
    }

    @RequestMapping(method = { RequestMethod.DELETE }, value = "/{id}")
    @ResponseBody
    public String actionDeletePlacerById(HttpServletRequest request, @PathVariable Integer id) {
        Placer exist = placerService.getById(id);
        if (exist == null) {
            LOG.error("actionDeletePlacerById:{} failed, not exist", id);
            return ResponseBuilder.fail(3, "not exist");
        }
        boolean isDel = placerService.deleteById(exist);
        if (!isDel) {
            LOG.error("actionDeletePlacerById:{} failed", id);
            return ResponseBuilder.fail(4, "delete fail");
        }
        LOG.info("actionDeletePlacerById:{} success", id);
        return ResponseBuilder.success(buildPlacerDTO(exist));
    }

}
