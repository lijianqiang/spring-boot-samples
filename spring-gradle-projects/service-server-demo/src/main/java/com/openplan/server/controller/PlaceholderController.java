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

import com.openplan.server.domain.model.Placeholder;
import com.openplan.server.enums.PlaceholderKey;
import com.openplan.server.query.ListQueryHelper;
import com.openplan.server.service.PlaceholderService;


@Controller
@RequestMapping("/placeholders")
public class PlaceholderController {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceholderController.class);

    @Autowired
    private PlaceholderService placeholderService;

//    @Autowired
//    private PlacerService placerService;

//    private final BeanCopier copierToDTO = BeanCopierTool.buildCopier(new Placeholder(), new PlaceholderDTO());
//
//    private final BeanCopier copierFromDTO = BeanCopierTool.buildCopier(new PlaceholderDTO(), new Placeholder());

    @RequestMapping(method = { RequestMethod.GET }, value = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Placeholder> actionList(HttpServletRequest request) {
        LOG.debug("hello world");
        Map<String, Object> params = buildQueryParam(request);
        List<Placeholder> result = placeholderService.listByCondition(params);
        return result;
    }

    private Map<String, Object> buildQueryParam(HttpServletRequest request) {
        return ListQueryHelper.buildQueryParam(request, PlaceholderKey.getValueList());
    }

//    private PlaceholderListDTO buildListDTO(ConditionQueryResult<Placeholder> queryResult) {
//        PlaceholderListDTO pagingDTO = new PlaceholderListDTO();
//        pagingDTO.setPage(queryResult.getPage());
//        pagingDTO.setTotal(queryResult.getTotal());
//        pagingDTO.setParams(queryResult.getParams());
//        List<Placeholder> rows = queryResult.getRows();
//        int size = rows != null ? rows.size() : 0;
//        if (queryResult.getTotal() > 0 && size > 0) {
//            List<PlaceholderDTO> dtos = new ArrayList<PlaceholderDTO>(size + 1);
//            for (Placeholder qrcode : rows) {
//                dtos.add(buildQrcodeDTO(qrcode));
//            }
//            pagingDTO.setRows(dtos);
//        }
//
//        return pagingDTO;
//    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Placeholder actionGetById(HttpServletRequest request, @PathVariable Integer id) {
        Placeholder qrcode = placeholderService.getById(id);
//        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
        return qrcode;
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/unid/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Placeholder actionGetByUnid(HttpServletRequest request, @PathVariable Integer id) {
        Placeholder qrcode = placeholderService.getById(id);
//        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
        return qrcode;
    }

//    @RequestMapping(method = { RequestMethod.POST }, value = "", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String actionSave(HttpServletRequest request, @RequestBody PlaceholderDTO qrcodeDTO) {
//        Placer placer = placerService.getById(qrcodeDTO.getPlacerId());
//        if (placer == null) {
//            return ResponseBuilder.fail(1, "save failed, placer null");
//        }
//        qrcodeDTO.setProvinceNo(placer.getProvinceNo());
//        qrcodeDTO.setCityNo(placer.getCityNo());
//        qrcodeDTO.setRegionNo(placer.getRegionNo());
//        Placeholder qrcode = buildQrcode(qrcodeDTO, true);
//        qrcode = placeholderService.insert(qrcode);
//        if (qrcode == null) {
//            return ResponseBuilder.fail(1, "save failed");
//        }
//
//        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
//    }

//    private PlaceholderDTO buildQrcodeDTO(Placeholder qrcode) {
//        PlaceholderDTO qrcodeDTO = new PlaceholderDTO();
//        copierToDTO.copy(qrcode, qrcodeDTO, null);
//        return qrcodeDTO;
//    }

//    private Placeholder buildQrcode(PlaceholderDTO qrcodeDTO, boolean isNew) {
//        Placeholder qrcode = new Placeholder();
//        copierFromDTO.copy(qrcodeDTO, qrcode, null);
//        if (isNew) {
//            String uuid = UuidUtil.getUuid();
//            qrcode.setUnid(uuid);
//        }
//        if (qrcodeDTO.getLongitude() != null) {
//            GeoHash geoHash = new GeoHash(Double.valueOf(qrcodeDTO.getLongitude()), Double.valueOf(qrcodeDTO.getLatitude()), 25);
//            qrcode.setGeohash(geoHash.encodeHash());
//        }
//        return qrcode;
//    }
//
//    @RequestMapping(method = { RequestMethod.PUT }, value = "/{id}", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String actionUpdateById(HttpServletRequest request, @PathVariable Integer id, @RequestBody PlaceholderDTO qrcodeDTO) {
//        Placeholder exist = placeholderService.getById(id);
//        if (exist == null) {
//            return ResponseBuilder.fail(2, "not exist");
//        }
//        Placeholder qrcode = buildQrcode(qrcodeDTO, false);
//        boolean isOk = placeholderService.updateById(qrcode);
//        if (!isOk) {
//            return ResponseBuilder.fail(2, "update failed");
//        }
//        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
//    }
//
//    @RequestMapping(method = { RequestMethod.DELETE }, value = "/{id}", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String actionDeleteById(HttpServletRequest request, @PathVariable Integer id) {
//        Placeholder exist = placeholderService.getById(id);
//        if (exist == null) {
//            return ResponseBuilder.fail(3, "not exist");
//        }
//        boolean isDel = placeholderService.deleteById(exist);
//        if (!isDel) {
//            return ResponseBuilder.fail(4, "delete fail");
//        }
//        return ResponseBuilder.success(buildQrcodeDTO(exist));
//    }

}
