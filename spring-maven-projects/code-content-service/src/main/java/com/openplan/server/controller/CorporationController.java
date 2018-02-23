package com.openplan.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.openplan.server.constant.HttpConstant;
import com.openplan.server.entity.model.Corporation;
import com.openplan.server.github.geohash.GeoHash;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.corporation.CorporationDTO;
import com.openplan.server.module.corporation.CorporationKey;
import com.openplan.server.module.corporation.CorporationListDTO;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.CorporationService;
import com.openplan.server.tool.BeanCopierTool;
import com.openplan.server.tool.HttpRequestHelper;
import com.openplan.server.util.JsonUtil;
import com.openplan.server.util.UuidUtil;

import net.sf.cglib.beans.BeanCopier;

@Controller
@RequestMapping("/corporations")
public class CorporationController {

    private static final Logger LOG = LoggerFactory.getLogger(CorporationController.class);

    @Autowired
    private CorporationService corporationService;

    private final BeanCopier copierToDTO = BeanCopierTool.buildCopier(new Corporation(), new CorporationDTO());
    
    private final BeanCopier copierToModel = BeanCopierTool.buildCopier(new CorporationDTO(), new Corporation());

    @RequestMapping(method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionListCondition(HttpServletRequest request) {
        Map<String, Object> params = buildQueryParam(request);
        ConditionQueryResult<Corporation> queryResult = corporationService.listByCondition(params);
        CorporationListDTO buildListDTO = buildCorporationListDTO(queryResult);
        if (LOG.isInfoEnabled()) {
            LOG.info("json:{}", JsonUtil.toJson(queryResult));
        }
        return ResponseBuilder.success(buildListDTO);
    }

    private CorporationListDTO buildCorporationListDTO(ConditionQueryResult<Corporation> queryResult) {
        CorporationListDTO resultDTO = new CorporationListDTO();
        resultDTO.setPage(queryResult.getPage());
        resultDTO.setTotal(queryResult.getTotal());
        resultDTO.setParams(queryResult.getParams());
        List<Corporation> rows = queryResult.getRows();
        int size = rows != null ? rows.size() : 0;
        if (queryResult.getTotal() > 0 && size > 0) {
            resultDTO.setRows(buildCorporationDTOs(rows));
        }

        return resultDTO;
    }

    private List<CorporationDTO> buildCorporationDTOs(List<Corporation> rows) {
        long start = System.nanoTime();
        long startms = System.currentTimeMillis();
        int size = rows != null ? rows.size() : 0;
        List<CorporationDTO> listDTO = new ArrayList<CorporationDTO>(size + 1);
        if (size > 0) {
            for (Corporation model : rows) {
                listDTO.add(buildCorporationDTO(model));
            }
        }
        LOG.info("property copy time:{}ns, {}ms", (System.nanoTime() - start), (System.currentTimeMillis() - startms));
        return listDTO;
    }

    private CorporationDTO buildCorporationDTO(Corporation model) {
        CorporationDTO corporationDTO = new CorporationDTO();
        copierToDTO.copy(model, corporationDTO, null);
        return corporationDTO;
    }

    private Map<String, Object> buildQueryParam(HttpServletRequest request) {
        Map<String, Object> conditions = new HashMap<String, Object>();
        Integer limit = HttpRequestHelper.getAsInteger(request, HttpConstant.LIMIT_KEY);
        if (limit != null) {
            conditions.put(HttpConstant.LIMIT_KEY, limit);
        }
        Integer page = HttpRequestHelper.getAsInteger(request, HttpConstant.PAGE_KEY);
        if (page != null) {
            conditions.put(HttpConstant.PAGE_KEY, page);
        }
        String order = HttpRequestHelper.getAsString(request, HttpConstant.ORDER_KEY, null);
        if (order != null) {
            conditions.put(HttpConstant.ORDER_KEY, order);
        }
        String sort = HttpRequestHelper.getAsString(request, HttpConstant.SORT_KEY, null);
        if (sort != null) {
            conditions.put(HttpConstant.SORT_KEY, sort);
        }
        Boolean enable = HttpRequestHelper.getAsBoolean(request, CorporationKey.ENABLE);
        if (enable != null) {
            conditions.put(CorporationKey.ENABLE, enable);
        }
        Integer placerId = HttpRequestHelper.getAsInteger(request, CorporationKey.PLACER_ID);
        if (placerId != null) {
            conditions.put(CorporationKey.PLACER_ID, placerId);
        }
        Integer provinceNo = HttpRequestHelper.getAsInteger(request, CorporationKey.PROVINCE_NO);
        if (provinceNo != null) {
            conditions.put(CorporationKey.PROVINCE_NO, provinceNo);
        }
        Integer cityNo = HttpRequestHelper.getAsInteger(request, CorporationKey.CITY_NO);
        if (cityNo != null) {
            conditions.put(CorporationKey.CITY_NO, cityNo);
        }
        Integer regionNo = HttpRequestHelper.getAsInteger(request, CorporationKey.REGION_NO);
        if (regionNo != null) {
            conditions.put(CorporationKey.REGION_NO, regionNo);
        }
        String name = HttpRequestHelper.getAsString(request, CorporationKey.NAME, null);
        if (name != null) {
            conditions.put(CorporationKey.NAME, name);
        }
        return conditions;
    }
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionListById(HttpServletRequest request, @PathVariable Integer id) {
        Corporation model = corporationService.getById(id);
        if (model == null) {
            LOG.error("actionListById empty");
            return ResponseBuilder.fail(1, "actionListByPlacerId empty");
        }
        CorporationDTO resultDTO = buildCorporationDTO(model);

        return ResponseBuilder.success(resultDTO);
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionSave(HttpServletRequest request, @RequestBody CorporationDTO corporationDTO) {
        Corporation model = buildCorporation(corporationDTO, true);
        model = corporationService.insert(model);
        if (model == null) {
            return ResponseBuilder.fail(1, "save failed");
        }
        
        return ResponseBuilder.success(buildCorporationDTO(model));
    }

    private Corporation buildCorporation(CorporationDTO corporationDTO, boolean isNew) {
        Corporation model = new Corporation();
        copierToModel.copy(corporationDTO, model, null);
        if (isNew) {
            String uuid = UuidUtil.getUuid();
            model.setUnid(uuid);
        }
        if (corporationDTO.getLongitude() != null) {
            GeoHash geoHash = new GeoHash(Double.valueOf(corporationDTO.getLongitude()), Double.valueOf(corporationDTO.getLatitude()), 25);
            model.setGeohash(geoHash.encodeHash());
        }
        return model;
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/placers/{placerId}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionListByPlacerId(HttpServletRequest request, @PathVariable Integer placerId) {
        List<Corporation> rows = corporationService.listByPlacerId(placerId);
        if (rows == null || rows.isEmpty()) {
            LOG.error("actionListByPlacerId empty");
            return ResponseBuilder.fail(1, "actionListByPlacerId empty");
        }
        CorporationListDTO resultDTO = new CorporationListDTO();
        resultDTO.setPage(1);
        resultDTO.setTotal(rows.size());
        resultDTO.setRows(buildCorporationDTOs(rows));

        return ResponseBuilder.success(resultDTO);
    }
    
    @RequestMapping(method = { RequestMethod.PUT }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionUpdateById(HttpServletRequest request, @PathVariable Integer id, @RequestBody CorporationDTO corporationDTO) {
        Corporation exist = corporationService.getById(id);
        if (exist == null) {
            return ResponseBuilder.fail(2, "not exist");
        }
        Corporation corporation = buildCorporation(corporationDTO, false);
        boolean isOk = corporationService.update(corporation);
        if (!isOk) {
            return ResponseBuilder.fail(2, "update failed");
        }
        return ResponseBuilder.success(buildCorporationDTO(corporation));
    }
    
    
    @RequestMapping(method = { RequestMethod.DELETE }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionDeleteById(HttpServletRequest request, @PathVariable Integer id) {
        Corporation exist = corporationService.getById(id);
        if (exist == null) {
            return ResponseBuilder.fail(3, "not exist");
        }
        boolean isDel = corporationService.delete(exist);
        if (!isDel) {
            return ResponseBuilder.fail(4, "delete fail");
        }
        return ResponseBuilder.success(buildCorporationDTO(exist));
    }

}
