package tech.gamesupport.lowcode.node;

import tech.gamesupport.lowcode.typedef.TypeDef;
import tech.gamesupport.lowcode.typedef.ValueDef;
import tech.gamesupport.lowcode.typedef.ValueDefUtils;
import tech.gamesupport.lowcode.typedef.ValueType;

public class DynamicValueNode implements DynamicNode {

    private final Object value;
    private final TypeDef typeDef;

    public DynamicValueNode(Object value) {
        ValueType valueType = ValueDefUtils.findByValue(value);
        if (valueType == null) {
            throw new IllegalArgumentException("value is not supported, class: " + value.getClass());
        }
        typeDef = new ValueDef(valueType, value == null);
        this.value = value;
    }

    public static DynamicValueNode create(Object value) {
        return new DynamicValueNode(value);
    }


    @Override
    public DynamicNode getChildAt(int index) {
        throw new UnsupportedOperationException("Value node cannot be accessed as an array");
    }

    @Override
    public DynamicNode getChild(String key) {
        throw new UnsupportedOperationException("Value node cannot be accessed as an object");
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public <T> T getValue(Class<T> type) {
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        throw new ClassCastException("value " + value + " is not a " + type);
    }

    @Override
    public TypeDef analyzeTypeDef() {
        return typeDef;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public String toString() {
        return "ValueNode(value = " + value + ")";
    }
}
