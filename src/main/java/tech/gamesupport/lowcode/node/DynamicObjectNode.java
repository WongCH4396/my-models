package tech.gamesupport.lowcode.node;

import tech.gamesupport.lowcode.typedef.TypeDef;

import java.util.Map;

public class DynamicObjectNode implements DynamicNode {

    private final Map<String, DynamicNode> map;

    public DynamicObjectNode(Map<String, DynamicNode> map) {
        this.map = map;
    }

    @Override
    public DynamicNode getChildAt(int index) {
        return map.getOrDefault(String.valueOf(index), DynamicNullNode.getInstance());
    }

    @Override
    public DynamicNode getChild(String key) {
        return map.getOrDefault(key, DynamicNullNode.getInstance());
    }

    @Override
    public int size() {
        return map.size();
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
