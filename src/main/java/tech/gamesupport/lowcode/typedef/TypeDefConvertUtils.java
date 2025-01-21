package tech.gamesupport.lowcode.typedef;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class TypeDefConvertUtils {

    public static TypeDef toTypeDef(Class<?> cls) {
        ValueType valueType = ValueDefUtils.findByClass(cls);
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
