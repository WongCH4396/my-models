package tech.gamesupport.lowcode.typedef;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class ValueDefUtils {

    private static final ValueDef NULL_DEF = new ValueDef(ValueType.NULL, true);


    public static ValueDef nullDef() {
        return NULL_DEF;
    }

    private static final Map<Class<?>, ValueType> valueTypes = new HashMap<>();
    static {
        valueTypes.put(Boolean.class, ValueType.BOOL);
        valueTypes.put(BigInteger.class, ValueType.INT);
        valueTypes.put(BigDecimal.class, ValueType.DECIMAL);
        valueTypes.put(String.class, ValueType.STRING);

        valueTypes.put(ZonedDateTime.class, ValueType.DATETIME);
        valueTypes.put(LocalDate.class, ValueType.DATE);
        valueTypes.put(LocalTime.class, ValueType.TIME);
    }

    public static ValueType findByClass(Class<?> clazz) {
        return valueTypes.get(clazz);
    }

    public static ValueType findByValue(Object value) {
        if (value == null) {
            return ValueType.NULL;
        }
        for (Class<?> cls : valueTypes.keySet()) {
            if (cls.isInstance(value)) {
                return valueTypes.get(cls);
            }
        }
        return null;
    }


}
