package com.openplan.server.tool;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

/**
 * http://blog.csdn.net/jianhua0902/article/details/8155368
 * http://www.open-open.com/lib/view/open1404998048200.html
 * 
 * @author lijianqiang
 *
 */
public class BeanCopierTool {
    
    private static Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<String, BeanCopier>();
    
    public static BeanCopier buildCopier(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = BEAN_COPIER_MAP.get(beanKey);
        if (copier == null) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_MAP.put(beanKey, copier);
        } 
        return copier;
    }

    /**
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        BeanCopier copier = buildCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.getName().toString() + class2.getName().toString();
    }

}
