package tech.gamesupport.lowcode.node;

import tech.gamesupport.lowcode.typedef.TypeDef;

/**
 * Node should be immutable
 */
public interface DynamicNode {

    DynamicNode getChildAt(int index);

    DynamicNode getChild(String key);

    int size();

    Object getValue();

    <T> T getValue(Class<T> type);

    TypeDef analyzeTypeDef();

    boolean isContainer();

}
