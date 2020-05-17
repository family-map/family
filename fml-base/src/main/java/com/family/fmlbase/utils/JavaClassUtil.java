package com.family.fmlbase.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class JavaClassUtil {
    public static boolean isJavaClass(Class<?> clazz) {
        boolean isBaseClass = false;
        if (clazz.isArray()) {
            isBaseClass = false;
        } else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz
                .getPackage().getName().equals("java.lang") || clazz
                .getPackage().getName().equals("java.math") || clazz
                .getPackage().getName().equals("java.util")) {
            isBaseClass = true;
        }
        return isBaseClass;
    }

    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception exception) {
            }
        }
        return null;
    }

    static class A {
        private String a;

        private Integer b;

        private BigDecimal c;

        private Long d;

        public void setA(String a) {
            this.a = a;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public void setC(BigDecimal c) {
            this.c = c;
        }

        public void setD(Long d) {
            this.d = d;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof A))
                return false;
            A other = (A) o;
            if (!other.canEqual(this))
                return false;
            Object this$a = getA(), other$a = other.getA();
            if ((this$a == null) ? (other$a != null) : !this$a.equals(other$a))
                return false;
            Object this$b = getB(), other$b = other.getB();
            if ((this$b == null) ? (other$b != null) : !this$b.equals(other$b))
                return false;
            Object this$c = getC(), other$c = other.getC();
            if ((this$c == null) ? (other$c != null) : !this$c.equals(other$c))
                return false;
            Object this$d = getD(), other$d = other.getD();
            return !((this$d == null) ? (other$d != null) : !this$d.equals(other$d));
        }

        protected boolean canEqual(Object other) {
            return other instanceof A;
        }

        public int hashCode() {
            int PRIME = 59,
                    result = 1;
            Object $a = getA();
            result = result * 59 + (($a == null) ? 43 : $a.hashCode());
            Object $b = getB();
            result = result * 59 + (($b == null) ? 43 : $b.hashCode());
            Object $c = getC();
            result = result * 59 + (($c == null) ? 43 : $c.hashCode());
            Object $d = getD();
            return result * 59 + (($d == null) ? 43 : $d.hashCode());
        }

        public String toString() {
            return "JavaClassUtil.A(a=" + getA() + ", b=" + getB() + ", c=" + getC() + ", d=" + getD() + ")";
        }

        public String getA() {
            return this.a;
        }

        public Integer getB() {
            return this.b;
        }

        public BigDecimal getC() {
            return this.c;
        }

        public Long getD() {
            return this.d;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        setFieldValue(a, "a", Integer.valueOf(1));
        System.out.println(a);
    }

    private static Boolean parameterTypeName(Object object, String name) {
        String simple = object.getClass().getSimpleName().toLowerCase();
        return parameterTypeName(simple, name);
    }

    private static Boolean parameterTypeName(String type1, String type2) {
        return Boolean.valueOf(type1.toLowerCase().startsWith(type2.toLowerCase()));
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null)
            return;
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasField(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        return (field != null);
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null)
            return null;
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
