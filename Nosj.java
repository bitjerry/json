//@Time: 2021/10/29 21:04
//@Author: Mr.lin
//@File: Nosj.java
package com.google.webserve.Json;

import java.util.ArrayList;

public class Nosj {
    private static class Node{
        private Node pre;
        private String key;
        private Object obj;
    }
    private static StringBuilder put(StringBuilder key, StringBuilder value, Object obj, Object values) {
        StringBuilder s = key;
        if (values != null) {
            if (values instanceof StringBuilder) {
                String v = values.toString();
                int v_length = v.length();
                if (v_length > 0) {
                    if (v.charAt(0) == '"' && v.charAt(v_length - 1) == '"')
                        values = v.substring(1, v_length - 1);
                    else {
                        try {
                            values = Integer.parseInt(v);
                        } catch (Exception a) {
                            try {
                                values = Double.parseDouble(v);
                            } catch (Exception b) {
                                if ("true".equalsIgnoreCase(v)) values = Boolean.TRUE;
                                else if ("false".equalsIgnoreCase(v)) values = Boolean.FALSE;
                                else if ("null".equalsIgnoreCase(v)) values = null;
                                else values = v;
                            }
                        }
                    }
                }
            }
            if (obj instanceof Json) {
                if (key.length() > 0) {
                    String k = key.toString();
                    int k_length = k.length();
                    if (k.charAt(0) == '"' && k.charAt(k_length - 1) == '"') k = k.substring(1, k_length - 1);
                    ((Json) obj).put(k, values);
                }
                s = key;
            } else if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(values);
                s = value;
            }
        }
        key.setLength(0);
        value.setLength(0);
        return s;
    }
    public static Json parseJson(String jsonString){
        char[] cs = jsonString.toCharArray();
        int cs_len = cs.length;
        if (cs_len > 0 && cs[0] == '{' && cs[cs_len-1] == '}') {
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();
            StringBuilder s = key;
            Node p = new Node();
            try {
                for (int i = 0; i < cs_len; i++) {
                    int s_length = s.length();
                    boolean isclose = (s_length > 0 && (s.charAt(0) != '"' || (s_length > 1 && s.charAt(s_length-2) != '\\' && s.charAt(s_length-1) == '"')));
                    boolean si = (s_length == 0 || isclose);
                    if (si && cs[i] <= 32) continue;
                    if (si){
                        if (cs[i] == '{') {
                            Node jn = new Node();
                            jn.pre = p;
                            jn.obj = new Json();
                            put(key, value, p.obj, jn.obj);
                            p = jn;
                            s = key;
                            continue;
                        }
                        else if (cs[i] == '}') {
                            s = put(key, value, p.obj, value);
                            if (p.pre.pre == null) break;
                            else p = p.pre;
                            continue;
                        }
                        else if (cs[i] == '[') {
                            Node jn = new Node();
                            jn.pre = p;
                            jn.obj = new ArrayList<>();
                            if (key.length() > 0) jn.key = String.valueOf(key);
                            p = jn;
                            s = value;
                            continue;
                        }
                        else if (cs[i] == ']') {
                            Object n = p.obj;
                            if (value.length() > 0) put(key,value,n,value);
                            if (p.key != null) key.append(p.key);
                            p = p.pre;
                            s = put(key,value,p.obj,((ArrayList) n).toArray());
                            continue;
                        }
                    }
                    if (isclose){
                        if (cs[i] == ':') {
                            s = value;
                            continue;
                        }
                        else if (cs[i] == ',' && s == value) {
                            s = put(key, value, p.obj, value);
                            continue;
                        }
                    }
                    if (cs[i] != ',' || s_length > 0) s.append(cs[i]);
                }
            }catch (Exception e){
            }
            return (Json) p.obj;
        }
        return null;
    }
}