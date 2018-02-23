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
import com.openplan.server.entity.model.Qrcoder;
import com.openplan.server.github.geohash.GeoHash;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.qrcode.QrcoderDTO;
import com.openplan.server.module.qrcode.QrcoderKey;
import com.openplan.server.module.qrcode.QrcoderListDTO;
import com.openplan.server.query.helper.ListQueryHelper;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.PlacerService;
import com.openplan.server.service.QrcoderService;
import com.openplan.server.tool.BeanCopierTool;
import com.openplan.server.util.UuidUtil;

import net.sf.cglib.beans.BeanCopier;

@Controller
@RequestMapping("/qrcoders")
public class QrcoderController {

    private static final Logger LOG = LoggerFactory.getLogger(QrcoderController.class);

    @Autowired
    private QrcoderService qrcoderService;

    @Autowired
    private PlacerService placerService;

    private final BeanCopier copierToDTO = BeanCopierTool.buildCopier(new Qrcoder(), new QrcoderDTO());

    private final BeanCopier copierFromDTO = BeanCopierTool.buildCopier(new QrcoderDTO(), new Qrcoder());

    private final static String[] QUERY_KEYS = { QrcoderKey.CITY_NO, QrcoderKey.ENABLE, QrcoderKey.NAME, QrcoderKey.PLACER_ID, QrcoderKey.PROVINCE_NO,
            QrcoderKey.REGION_NO };

    @RequestMapping(method = { RequestMethod.GET }, value = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionList(HttpServletRequest request) {
        LOG.debug("hello world");
        Map<String, Object> params = buildQueryParam(request);
        ConditionQueryResult<Qrcoder> queryResult = qrcoderService.listByCondition(params);
        QrcoderListDTO buildListDTO = buildListDTO(queryResult);
        return ResponseBuilder.success(buildListDTO);
    }

    private Map<String, Object> buildQueryParam(HttpServletRequest request) {
        return ListQueryHelper.buildQueryParam(request, QUERY_KEYS);
    }

    private QrcoderListDTO buildListDTO(ConditionQueryResult<Qrcoder> queryResult) {
        QrcoderListDTO pagingDTO = new QrcoderListDTO();
        pagingDTO.setPage(queryResult.getPage());
        pagingDTO.setTotal(queryResult.getTotal());
        pagingDTO.setParams(queryResult.getParams());
        List<Qrcoder> rows = queryResult.getRows();
        int size = rows != null ? rows.size() : 0;
        if (queryResult.getTotal() > 0 && size > 0) {
            List<QrcoderDTO> dtos = new ArrayList<QrcoderDTO>(size + 1);
            for (Qrcoder qrcode : rows) {
                dtos.add(buildQrcodeDTO(qrcode));
            }
            pagingDTO.setRows(dtos);
        }

        return pagingDTO;
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionGetById(HttpServletRequest request, @PathVariable Integer id) {
        Qrcoder qrcode = qrcoderService.getById(id);
        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/unid/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionGetByUnid(HttpServletRequest request, @PathVariable Integer id) {
        Qrcoder qrcode = qrcoderService.getById(id);
        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
    }

    @RequestMapping(method = { RequestMethod.POST }, value = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionSave(HttpServletRequest request, @RequestBody QrcoderDTO qrcodeDTO) {
        Placer placer = placerService.getById(qrcodeDTO.getPlacerId());
        if (placer == null) {
            return ResponseBuilder.fail(1, "save failed, placer null");
        }
        qrcodeDTO.setProvinceNo(placer.getProvinceNo());
        qrcodeDTO.setCityNo(placer.getCityNo());
        qrcodeDTO.setRegionNo(placer.getRegionNo());
        Qrcoder qrcode = buildQrcode(qrcodeDTO, true);
        qrcode = qrcoderService.insert(qrcode);
        if (qrcode == null) {
            return ResponseBuilder.fail(1, "save failed");
        }

        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
    }

    private QrcoderDTO buildQrcodeDTO(Qrcoder qrcode) {
        QrcoderDTO qrcodeDTO = new QrcoderDTO();
        copierToDTO.copy(qrcode, qrcodeDTO, null);
        return qrcodeDTO;
    }

    private Qrcoder buildQrcode(QrcoderDTO qrcodeDTO, boolean isNew) {
        Qrcoder qrcode = new Qrcoder();
        copierFromDTO.copy(qrcodeDTO, qrcode, null);
        if (isNew) {
            String uuid = UuidUtil.getUuid();
            qrcode.setUnid(uuid);
        }
        if (qrcodeDTO.getLongitude() != null) {
            GeoHash geoHash = new GeoHash(Double.valueOf(qrcodeDTO.getLongitude()), Double.valueOf(qrcodeDTO.getLatitude()), 25);
            qrcode.setGeohash(geoHash.encodeHash());
        }
        return qrcode;
    }

    @RequestMapping(method = { RequestMethod.PUT }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionUpdateById(HttpServletRequest request, @PathVariable Integer id, @RequestBody QrcoderDTO qrcodeDTO) {
        Qrcoder exist = qrcoderService.getById(id);
        if (exist == null) {
            return ResponseBuilder.fail(2, "not exist");
        }
        Qrcoder qrcode = buildQrcode(qrcodeDTO, false);
        boolean isOk = qrcoderService.updateById(qrcode);
        if (!isOk) {
            return ResponseBuilder.fail(2, "update failed");
        }
        return ResponseBuilder.success(buildQrcodeDTO(qrcode));
    }

    @RequestMapping(method = { RequestMethod.DELETE }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionDeleteById(HttpServletRequest request, @PathVariable Integer id) {
        Qrcoder exist = qrcoderService.getById(id);
        if (exist == null) {
            return ResponseBuilder.fail(3, "not exist");
        }
        boolean isDel = qrcoderService.deleteById(exist);
        if (!isDel) {
            return ResponseBuilder.fail(4, "delete fail");
        }
        return ResponseBuilder.success(buildQrcodeDTO(exist));
    }

}
