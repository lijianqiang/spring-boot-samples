package com.openplan.server.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.entity.model.CategoryItem;
import com.openplan.server.entity.model.ConsumerCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testInsertCategoryItem() {
        CategoryItem item = new CategoryItem();
        item.setName("自助餐");
        item.setIntro("自助餐");
        item.setParentId(1);
        
        CategoryItem res = categoryService.insertCategoryItem(item);
        assertTrue("*************** insert failed", (res != null));
        
        
        item.setName("小吃快餐");
        item.setIntro("小吃快餐");
        item.setParentId(1);
        
        res = categoryService.insertCategoryItem(item);
        assertTrue("*************** insert failed", (res != null));
        
        item.setName("粤港菜");
        item.setIntro("粤港菜");
        item.setParentId(1);
        
        res = categoryService.insertCategoryItem(item);
        assertTrue("*************** insert failed", (res != null));
        
        item.setName("电影");
        item.setIntro("粤港菜");
        item.setParentId(2);
        
        res = categoryService.insertCategoryItem(item);
        assertTrue("*************** insert failed", (res != null));
    }

    @Test
    public void testInsertConsumerCategory() {
        ConsumerCategory category = new ConsumerCategory();
        category.setName("美食");
        category.setConsumerType(101);
        category.setIntro("美食1001101");
        
        ConsumerCategory res = categoryService.insertConsumerCategory(category);
        assertTrue("*************** insert failed", (res != null));
        
        category.setName("休闲娱乐");
        category.setConsumerType(102);
        category.setIntro("休闲娱乐20011102");
        
        res = categoryService.insertConsumerCategory(category);
        assertTrue("*************** insert failed", (res != null));
        
    }

}
