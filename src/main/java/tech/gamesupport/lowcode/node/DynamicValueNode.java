package tech.gamesupport.lowcode.node;

import tech.gamesupport.lowcode.typedef.TypeDef;

public class DynamicValueNode implements DynamicNode {

    private final Object value;

    public DynamicValueNode(Object value) {
        this.value = value;
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
        return null;
    }

    @Override
    public boolean isContainer() {
        return false;
    }
}
