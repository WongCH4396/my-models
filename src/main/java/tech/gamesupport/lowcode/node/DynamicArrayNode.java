package tech.gamesupport.lowcode.node;

import tech.gamesupport.lowcode.typedef.TypeDef;

public class DynamicArrayNode implements DynamicNode {

    private final DynamicNode[] array;

    public DynamicArrayNode(DynamicNode[] array) {
        this.array = array;
    }

    @Override
    public DynamicNode getChildAt(int index) {
        if (index < 0 || index >= array.length) {
            return DynamicNullNode.getInstance();
        }
        return array[index];
    }

    @Override
    public DynamicNode getChild(String key) {
        return DynamicNullNode.getInstance();
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("object doesn't have value");
    }

    @Override
    public <T> T getValue(Class<T> type) {
        throw new UnsupportedOperationException("object doesn't have value");
    }

    @Override
    public TypeDef analyzeTypeDef() {
        return null;
    }

    @Override
    public boolean isContainer() {
        return true;
    }
}
