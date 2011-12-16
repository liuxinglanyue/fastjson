package com.alibaba.json.bvt.parser.deser;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class StackTraceElementDeserializerTest extends TestCase {

    public void test_stack() throws Exception {
        Assert.assertNull(JSON.parseObject("null", StackTraceElement.class));
        Assert.assertNull(JSON.parseArray("null", StackTraceElement.class));
        Assert.assertNull(JSON.parseArray("[null]", StackTraceElement.class).get(0));
        Assert.assertNull(JSON.parseObject("{\"value\":null}", VO.class).getValue());
        Assert.assertNull(JSON.parseObject("{\"className\":\"int\",\"methodName\":\"parseInt\"}",
                                           StackTraceElement.class).getFileName());
        
        Assert.assertEquals(StackTraceElement.class, ((StackTraceElement) JSON.parse("{\"@type\":\"java.lang.StackTraceElement\",\"className\":\"int\",\"methodName\":\"parseInt\"}")).getClass());
    }

    public void test_stack_error() throws Exception {
        Exception error = null;
        try {
            JSON.parseObject("{}", StackTraceElement.class);
        } catch (JSONException ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }
    
    public void test_stack_error_1() throws Exception {
        Exception error = null;
        try {
            JSON.parseObject("[]", StackTraceElement.class);
        } catch (JSONException ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }
    
    public void test_stack_error_2() throws Exception {
        Exception error = null;
        try {
            JSON.parseObject("{\"className\":null,\"methodName\":null,\"fileName\":null,\"lineNumber\":null,\"@type\":\"xxx\"}", StackTraceElement.class);
        } catch (JSONException ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    public static class VO {

        private StackTraceElement value;

        public StackTraceElement getValue() {
            return value;
        }

        public void setValue(StackTraceElement value) {
            this.value = value;
        }

    }
}