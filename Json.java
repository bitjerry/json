//@Time: 2021/10/24 12:10
//@Author: Mr.lin
//@File: Json.java
package com.google.webserve.Json;

import java.lang.reflect.Array;
import java.util.*;

public class Json extends LinkedHashMap<String, Object>{
    private static StringBuilder getVar(Object var){
        StringBuilder vars = null;
        if (var == null) {
            vars = new StringBuilder();
            vars.append("null");
        }
        else if (var instanceof String){
            vars = new StringBuilder();
            vars.append("\"");
            vars.append(var);
            vars.append("\"");
        }
        else if (var.getClass().isPrimitive() || Number.class.isAssignableFrom(var.getClass()) || var instanceof Boolean){
            vars = new StringBuilder();
            vars.append(var);
        }
        return vars;
    }
    private static StringBuilder getArray(Object array){
        StringBuilder arrays = null;
        if (array != null){
            if (List.class.isAssignableFrom(array.getClass()))
                array = ((List<?>) array).toArray();
            if (array.getClass().isArray()){
                arrays = new StringBuilder();
                int len = Array.getLength(array);
                arrays.append("[");
                for (int i=0; i<len; i++){
                    Object par = Array.get(array, i);
                    StringBuilder sb;
                    if ((sb = getArray(par)) != null || (sb = getJson(par)) != null || (sb = getVar(par)) != null)
                        arrays.append(sb);
                    if (i != len -1) arrays.append(", ");
                }
                arrays.append("]");
            }
        }
        return arrays;
    }
    public static StringBuilder getJson(Object json){
        StringBuilder jsonString = null;
        if (json != null && Map.class.isAssignableFrom(json.getClass())){
            jsonString = new StringBuilder();
            jsonString.append("{");
            int len = ((Map<?, ?>) json).size();
            int i = 0;
            Iterator<?> keys = ((Map<?, ?>) json).keySet().iterator();
            while (keys.hasNext()){
                Object key = keys.next();
                jsonString.append("\"");
                jsonString.append(key);
                jsonString.append("\": ");
                Object par = ((Map<?, ?>) json).get(key);
                StringBuilder pardeal;
                if ((pardeal = getArray(par))!= null || (pardeal = getJson(par))!= null || (pardeal = getVar(par))!= null)
                    jsonString.append(pardeal);
                if (i++ != len-1) jsonString.append(", ");
            }
            jsonString.append("}");
        }
        return jsonString;
    }
    @Override
    public String toString() {
        return Json.getJson(this).toString();
    }
}