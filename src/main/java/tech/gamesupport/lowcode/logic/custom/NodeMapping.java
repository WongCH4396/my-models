package tech.gamesupport.lowcode.logic.custom;

import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.node.DynamicNodeBuilder;
import tech.gamesupport.lowcode.node.NodePath;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeMapping {

    private final List<NodePathMapping> pathMappings;

    public NodeMapping(List<NodePathMapping> pathMappings) {
        this.pathMappings = pathMappings;
    }

    public DynamicNode mapToNode(Map<String, DynamicNode> nodeMap) {
        DynamicNodeBuilder builder = new DynamicNodeBuilder();
        for (NodePathMapping nodePathMapping : pathMappings) {
            DynamicNode current = nodeMap.get(nodePathMapping.getInstanceId());
            DynamicNode dynamicNode = nodePathMapping.getSourcePath().find(current);
            NodePath targetPath = nodePathMapping.getTargetPath();
            builder.putNode(targetPath, dynamicNode);
        }
        return builder.build();
    }

    public Set<String> dependencies() {
        return pathMappings.stream().map(NodePathMapping::getInstanceId).filter(Objects::nonNull).collect(Collectors.toSet());
    }


}
