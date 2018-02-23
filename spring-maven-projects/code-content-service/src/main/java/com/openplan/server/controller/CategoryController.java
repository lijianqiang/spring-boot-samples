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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.entity.extra.CategoryItemExtra;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.category.CategoryDTO;
import com.openplan.server.module.category.CategoryItemDTO;
import com.openplan.server.module.category.CategoryListDTO;
import com.openplan.server.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    /**
     * Member Description
     */
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionListMenu(HttpServletRequest request) {
        LOG.debug("hello world");
        List<CategoryItemExtra> extraList = categoryService.listCategoryItemExtraAll();
        CategoryListDTO buildListDTO = buildCategoryListDTO(extraList);
        return ResponseBuilder.success(buildListDTO);
    }

    private CategoryListDTO buildCategoryListDTO(List<CategoryItemExtra> extraList) {
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        int total = extraList != null ? extraList.size() : 0;
        categoryListDTO.setPage(1);
        categoryListDTO.setTotal(total);
        if (total < 1) {
            return categoryListDTO;
        }
        
        Map<Integer, CategoryDTO> parents = new HashMap<Integer, CategoryDTO>(total);
        for (CategoryItemExtra extra : extraList) {
            CategoryDTO categoryDTO = parents.get(extra.getParentId());
            if (categoryDTO == null) {
                categoryDTO = new CategoryDTO();
                categoryDTO.setConsumerType(extra.getConsumerType());
                categoryDTO.setId(extra.getParentId());
                categoryDTO.setName(extra.getParentName());
                categoryDTO.setItems(new ArrayList<CategoryItemDTO>(total));
            }
            List<CategoryItemDTO> items = categoryDTO.getItems();
            items.add(buildItemDTO(extra));
            parents.put(extra.getParentId(), categoryDTO);
        }
        categoryListDTO.setRows(new ArrayList<CategoryDTO>(parents.values()));
        return categoryListDTO;
    }

    private CategoryItemDTO buildItemDTO(CategoryItemExtra extra) {
        CategoryItemDTO itemDTO = new CategoryItemDTO();
        itemDTO.setId(extra.getId());
        itemDTO.setName(extra.getName());
        itemDTO.setParentId(extra.getParentId());
        itemDTO.setParentName(extra.getParentName());
        itemDTO.setUpdateAt(extra.getUpdateAt());
        return itemDTO;
    }

}
