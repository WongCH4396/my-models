package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.node.DynamicNodeBuilder;
import tech.gamesupport.lowcode.node.NodePath;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicInstance {

    private final List<PathGetter> pathGetters;
    private final ILogic logic;
    private final String instanceId;

    public LogicInstance(String instanceId, ILogic logic, List<PathGetter> pathGetters) {
        this.pathGetters = pathGetters;
        this.logic = logic;
        this.instanceId = instanceId;
    }

    public void processStep(CustomLogicContext context) {
        DynamicNodeBuilder builder = new DynamicNodeBuilder();
        for (PathGetter pathGetter : pathGetters) {
            DynamicNode current;
            if (pathGetter.isInputArg()) {
                current = context.getInputNode();
            } else {
                current = context.getResultNode(pathGetter.getInstanceId());
            }
            DynamicNode dynamicNode = pathGetter.getSourcePath().find(current);
            NodePath targetPath = pathGetter.getTargetPath();
            builder.putNode(targetPath, dynamicNode);
        }
        DynamicNode build = builder.build();
        DynamicNode process = logic.process(build);
        context.putResultNode(instanceId, process);
    }

    public DynamicNode process(DynamicNode node) {
        return logic.process(node);
    }

    public String getInstanceId() {
        return instanceId;
    }

    public Set<String> dependencies() {
        return pathGetters.stream().map(PathGetter::getInstanceId).filter(Objects::nonNull).collect(Collectors.toSet());
    }

}
