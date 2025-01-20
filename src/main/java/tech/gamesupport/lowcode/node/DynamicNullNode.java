package tech.gamesupport.lowcode.node;

import tech.gamesupport.lowcode.typedef.TypeDef;
import tech.gamesupport.lowcode.typedef.ValueDefUtils;

public class DynamicNullNode implements DynamicNode {

    private static final DynamicNullNode INSTANCE = new DynamicNullNode();

    private DynamicNullNode() {
    }

    public static DynamicNullNode getInstance() {
        return INSTANCE;
    }


    @Override
    public DynamicNode getChildAt(int index) {
        throw new UnsupportedOperationException("null node cannot be accessed as an array");
    }

    @Override
    public DynamicNode getChild(String key) {
        throw new UnsupportedOperationException("null node cannot be accessed as an object");
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public <T> T getValue(Class<T> type) {
        if (type.isPrimitive()) {
            throw new IllegalArgumentException("type must not be primitive");
        }
        return null;
    }

    @Override
    public TypeDef analyzeTypeDef() {
        return ValueDefUtils.nullDef();
    }

    @Override
    public boolean isContainer() {
        return false;
    }
}
