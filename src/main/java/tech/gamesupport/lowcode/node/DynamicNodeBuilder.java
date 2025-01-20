package tech.gamesupport.lowcode.node;

import java.util.*;

public class DynamicNodeBuilder {

    private DynamicNode root;
    private final List<DynamicNodeBuilder> array = new ArrayList<>();
    private final Map<String, DynamicNodeBuilder> map = new HashMap<>();

    private String type;


    public DynamicNodeBuilder() {

    }


    public void putNode(NodePath targetPath, DynamicNode dynamicNode) {
        String prevType = type;
        if (targetPath.isSelf()) {
            type = "immutable";
        } else if (targetPath.isIndex()) {
            type = "array";
        } else {
            type = "object";
        }
        if (prevType != null) {
            if (prevType.equals("immutable")) {
                throw new IllegalArgumentException("path has been occupied");
            }
            if (!prevType.equals(type)) {
                throw new IllegalArgumentException("path is not allowed");
            }
        }
        DynamicNodeBuilder subBuilder = new DynamicNodeBuilder();
        subBuilder.putNode(targetPath.goDeep(), dynamicNode);
        if (type.equals("immutable")) {
            root = dynamicNode;
        } else if (type.equals("array")) {
            array.set(targetPath.getIndex(), subBuilder);
        } else {
            map.put(targetPath.getName(), subBuilder);
        }

    }

    public DynamicNode build() {
        if (Objects.equals(type, "immutable")) {
            return root;
        } else if (type.equals("array")) {
            DynamicNode[] nodes = array.stream().map(DynamicNodeBuilder::build).toArray(DynamicNode[]::new);
            return new DynamicArrayNode(nodes);
        } else {
            Map<String, DynamicNode> nodeMap = new HashMap<>();
            map.forEach((k, v) -> nodeMap.put(k, v.build()));
            return new DynamicObjectNode(nodeMap);
        }
    }
}
