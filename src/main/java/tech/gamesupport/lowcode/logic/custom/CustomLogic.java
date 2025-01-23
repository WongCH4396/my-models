package tech.gamesupport.lowcode.logic.custom;

import tech.gamesupport.lowcode.logic.ILogic;
import tech.gamesupport.lowcode.logic.LogicContext;
import tech.gamesupport.lowcode.logic.LogicManager;
import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.typedef.TypeDef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomLogic implements ILogic {

    private final List<String> logicInstanceIds;
    private final TypeDef parameterTypeDef;
    private final TypeDef returnTypeDef;
    private final NodeMapping resultNodeMapping;

    public CustomLogic(List<String> logicInstanceIds,
                       TypeDef parameterTypeDef,
                       TypeDef returnTypeDef,
                       NodeMapping resultNodeMapping) {
        this.logicInstanceIds = logicInstanceIds;
        this.parameterTypeDef = parameterTypeDef;
        this.returnTypeDef = returnTypeDef;
        this.resultNodeMapping = resultNodeMapping;
    }

    @Override
    public void process(LogicContext logicContext) {

        List<LogicInstance> instances = logicInstanceIds.stream().map(LogicInstanceManager::get).collect(Collectors.toList());

        Map<String, DynamicNode> resultMap = new HashMap<>();
        resultMap.put(null, logicContext.getInputNode());

        while (!instances.isEmpty()) {
            LogicInstance logicInstance = instances.stream().filter(instance -> {
                Set<String> dependencies = instance.getNodeMapping().dependencies();
                return dependencies.isEmpty() || resultMap.keySet().containsAll(dependencies);
            }).findFirst().orElse(null);
            if (logicInstance == null) {
                throw new IllegalStateException("Logic cannot be completed");
            }

            InstanceLog log = new InstanceLog();
            log.setInstanceId(logicInstance.getInstanceId());

            NodeMapping nodeMapping = logicInstance.getNodeMapping();
            DynamicNode inputNode = nodeMapping.mapToNode(resultMap);

            LogicContext subLogicContext = new LogicContext(inputNode);
            String logicId = logicInstance.getLogicId();
            LogicManager.getLogic(logicId).process(subLogicContext); // ???

            resultMap.put(logicInstance.getInstanceId(), subLogicContext.getOutputNode());

            log.addAllMessages(subLogicContext.getLogs());
            logicContext.writeLog(log);

            instances.remove(logicInstance);
        }

        logicContext.setOutputNode(resultNodeMapping.mapToNode(resultMap));
    }

    @Override
    public TypeDef getParameterTypeDef() {
        return parameterTypeDef;
    }

    @Override
    public TypeDef getReturnTypeDef() {
        return returnTypeDef;
    }

    @Override
    public boolean isIntrinsic() {
        return false;
    }
}
