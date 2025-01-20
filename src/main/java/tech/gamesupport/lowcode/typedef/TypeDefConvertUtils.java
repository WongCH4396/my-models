package tech.gamesupport.lowcode.typedef;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;

public class TypeDefConvertUtils {

    private static final Map<Class<?>, ValueType> valueTypeMap = new HashMap<>();

    static {
        valueTypeMap.put(Boolean.class, ValueType.BOOL);
        valueTypeMap.put(Integer.class, ValueType.INT);
        valueTypeMap.put(Long.class, ValueType.INT);
        valueTypeMap.put(Short.class, ValueType.INT);
        valueTypeMap.put(BigDecimal.class, ValueType.DECIMAL);
        valueTypeMap.put(Double.class, ValueType.DECIMAL);
        valueTypeMap.put(String.class, ValueType.STRING);
        valueTypeMap.put(ZonedDateTime.class, ValueType.DATETIME);
        valueTypeMap.put(Date.class, ValueType.DATETIME);
        valueTypeMap.put(LocalDate.class, ValueType.DATE);
        valueTypeMap.put(LocalTime.class, ValueType.TIME);
        valueTypeMap.put(Object.class, ValueType.ANY);
        valueTypeMap.put(boolean.class, ValueType.BOOL);
        valueTypeMap.put(byte.class, ValueType.INT);
        valueTypeMap.put(double.class, ValueType.DECIMAL);
        valueTypeMap.put(float.class, ValueType.DECIMAL);
        valueTypeMap.put(int.class, ValueType.INT);
        valueTypeMap.put(long.class, ValueType.INT);
        valueTypeMap.put(short.class, ValueType.INT);
    }


    public static TypeDef toTypeDef(Class<?> cls) {
        ValueType valueType = valueTypeMap.get(cls);
        if (valueType != null) {
            return new ValueDef(valueType, !cls.isPrimitive());
        }
        if (cls.isArray()) {
            return toArrayDef(cls.getComponentType());
        }
        return toObjectDef(cls);
    }

    public static ArrayDef toArrayDef(Class<?> elementCls) {
        return new ArrayDef(toTypeDef(elementCls));
    }

    public static ObjectDef toObjectDef(Class<?> cls) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(cls);
        List<ObjectField> fields = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals("class")) {
                continue;
            }
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            TypeDef typeDef = toTypeDef(propertyType);
            String name = propertyDescriptor.getName();
            ObjectField field = new ObjectField(name, typeDef, false);
            fields.add(field);
        }
        return new ObjectDef(fields);
    }

}
