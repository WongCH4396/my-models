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
    private final String instanceId;
    private final String logicId;

    public LogicInstance(String instanceId, String logicId, List<PathGetter> pathGetters) {
        this.instanceId = instanceId;
        this.logicId = logicId;
        this.pathGetters = pathGetters;
    }

    public void processStep(ILogicContext context) {
        LogicLog logicLog = new LogicLog();
        logicLog.setInstanceId(instanceId);
        context.setActiveLog(logicLog);
        DynamicNodeBuilder builder = new DynamicNodeBuilder();
        for (PathGetter pathGetter : pathGetters) {
            DynamicNode current;
            if (pathGetter.isInputArg()) {
                current = context.inputNode();
            } else {
                current = context.getResultNode(pathGetter.getInstanceId());
            }
            DynamicNode dynamicNode = pathGetter.getSourcePath().find(current);
            NodePath targetPath = pathGetter.getTargetPath();
            builder.putNode(targetPath, dynamicNode);
        }
        context.setLogicNode(builder.build());
        DynamicNode process = LogicManager.getLogic(logicId).process(context);
        context.setResultNode(instanceId, process);
        context.endActiveLog();
    }

    public String getInstanceId() {
        return instanceId;
    }

    public Set<String> dependencies() {
        return pathGetters.stream().map(PathGetter::getInstanceId).filter(Objects::nonNull).collect(Collectors.toSet());
    }

}
