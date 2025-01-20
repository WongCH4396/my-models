package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.logic.argpath.PathGetter;
import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.node.DynamicNodeBuilder;
import tech.gamesupport.lowcode.node.NodePath;

import java.util.List;
import java.util.Map;

public class ParameterMapper {

    private List<PathGetter> pathGetters;

    public DynamicNode buildParameterNode(Map<Integer, DynamicNode> nodeMap) {
        DynamicNodeBuilder builder = new DynamicNodeBuilder();

        for (PathGetter pathGetter : pathGetters) {
            DynamicNode current = nodeMap.get(pathGetter.getSource());
            DynamicNode dynamicNode = pathGetter.getSourcePath().find(current);

            NodePath targetPath = pathGetter.getTargetPath();
            builder.putNode(targetPath, dynamicNode);

        }
        return builder.build();
    }

}
