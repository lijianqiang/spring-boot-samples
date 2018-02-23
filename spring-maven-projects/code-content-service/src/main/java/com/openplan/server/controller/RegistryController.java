package com.openplan.server.controller;

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

import com.openplan.server.entity.model.PlacerRegistry;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.registry.PlacerRegistryDTO;
import com.openplan.server.service.PlacerRegistryService;
import com.openplan.server.util.UuidUtil;

@Controller
@RequestMapping("/registries")
public class RegistryController {
    
    private static final Logger LOG = LoggerFactory.getLogger(RegistryController.class);
    
    @Autowired
    private PlacerRegistryService placerRegistryService;

    @RequestMapping(method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionListMenu(HttpServletRequest request) {
        LOG.debug("hello world");
        return "";
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionSave(HttpServletRequest request, @RequestBody PlacerRegistryDTO corporationDTO) {
        PlacerRegistry model = buildPlacerRegistry(corporationDTO, true);
        model = placerRegistryService.insert(model);
        if (model == null) {
            return ResponseBuilder.fail(1, "save failed");
        }
        
        return ResponseBuilder.success(buildPlacerRegistryDTO(model));
    }

    private PlacerRegistryDTO buildPlacerRegistryDTO(PlacerRegistry model) {
        PlacerRegistryDTO dto = new PlacerRegistryDTO();
        dto.setCorporationId(model.getCorporationId());
        dto.setEnable(model.getEnable());
        dto.setPlacerId(model.getPlacerId());
        dto.setId(model.getId());
        dto.setUnid(model.getUnid());
        dto.setUpdateAt(model.getUpdateAt());
        return dto;
    }

    private PlacerRegistry buildPlacerRegistry(PlacerRegistryDTO registryDTO, boolean isNew) {
        PlacerRegistry model = new PlacerRegistry();
        model.setCorporationId(registryDTO.getCorporationId());
        model.setEnable(registryDTO.getEnable());
        model.setPlacerId(registryDTO.getPlacerId());
        if (isNew) {
            String uuid = UuidUtil.getUuid();
            model.setUnid(uuid);
        }
        return model;
    }
    
    @RequestMapping(method = { RequestMethod.DELETE }, value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionDeleteById(HttpServletRequest request, @PathVariable Integer id) {
        PlacerRegistry exist = placerRegistryService.getById(id);
        if (exist == null) {
            return ResponseBuilder.fail(3, "not exist");
        }
        boolean isDel = placerRegistryService.delete(exist);
        if (!isDel) {
            return ResponseBuilder.fail(4, "delete fail");
        }
        return ResponseBuilder.success(buildPlacerRegistryDTO(exist));
    }

}
