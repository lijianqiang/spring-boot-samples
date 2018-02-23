package com.openplan.server.helper;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.openplan.server.util.StringUtils;



public class HttpRequestHelper {
    
    public static Integer getAsInteger(HttpServletRequest request, String tag) {
        if (request == null) {
            return null;
        }
        try {
            String tagStr = request.getParameter(tag);
            if (StringUtils.isEmpty(tagStr)) {
                return null;
            }
            return Integer.valueOf(tagStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @param defValue
     * @return
     */
    public static int getAsInt(HttpServletRequest request, String tag, int defValue) {
        Integer res = getAsInteger(request, tag);
        if (res == null) {
            return defValue;
        }
        return res;
    }

    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @return
     */
    public static int getAsInt(HttpServletRequest request, String tag) {
        return getAsInt(request, tag, 0);
    }
    
    public static Long getAsLong(HttpServletRequest request, String tag, Long defValue) {
        if (request == null) {
            return defValue;
        }
        try {
            String tagStr = request.getParameter(tag);
            if (StringUtils.isEmpty(tagStr)) {
                return defValue;
            }
            return Long.valueOf(tagStr);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @param defValue
     * @return
     */
    public static Long getAsLong(HttpServletRequest request, String tag) {
        return getAsLong(request, tag, null);
    }

    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @param defValue
     * @return
     */
    public static String getAsString(HttpServletRequest request, String tag, String defValue) {
        if (request == null) {
            return defValue;
        }
        try {
            String tagStr = request.getParameter(tag);
            if (StringUtils.isEmpty(tagStr)) {
                return defValue;
            }
            return tagStr;
        } catch (Exception e) {
            return defValue;
        }
    }
    
    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @return
     */
    public static String getAsString(HttpServletRequest request, String tag) {
        return getAsString(request, tag, null);
    }
    
    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @param defValue
     * @return
     */
    public static Double getAsDouble(HttpServletRequest request, String tag, Double defValue) {
        if (request == null) {
            return defValue;
        }
        try {
            String tagStr = request.getParameter(tag);
            if (StringUtils.isEmpty(tagStr)) {
                return defValue;
            }
            return Double.valueOf(tagStr);
        } catch (Exception e) {
            return defValue;
        }
    }
    
    /**	
     * <p>Description:              </p>
     * <p>Create Time: 2016年3月3日   </p>
     * @author lijianqiang
     * @param request
     * @param tag
     * @return
     */
    public static Double getAsDouble(HttpServletRequest request, String tag) {
        return getAsDouble(request, tag, null);
    }
    
    public static boolean getParamFromCheckbox(HttpServletRequest request, String tag) {
    	String value = getAsString(request, tag, "");
        return "true".equals(value);
    }
    
    public static List<String> getCheckBoxAsList(HttpServletRequest request, String tag) {
    	try {
    		String[] raw = request.getParameterValues(tag);
            return Arrays.asList(raw);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Boolean getAsBoolean(HttpServletRequest request, String tag) {
        String value = getAsString(request, tag, "");
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return "true".equals(value) || "1".equals(value);
    }

}
