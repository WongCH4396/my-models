package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.typedef.TypeDef;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomLogic implements ILogic {

    private final List<String> logicInstanceIds;
    private final TypeDef parameterTypeDef;
    private final TypeDef returnTypeDef;
    private final String finalInstanceId;

    public CustomLogic(List<String> logicInstanceIds, TypeDef parameterTypeDef, TypeDef returnTypeDef, String resultLogicInstanceId) {
        this.logicInstanceIds = logicInstanceIds;
        this.parameterTypeDef = parameterTypeDef;
        this.returnTypeDef = returnTypeDef;
        this.finalInstanceId = resultLogicInstanceId;
    }

    @Override
    public DynamicNode process(DynamicNode node) {
//        LogicContextManager logicContextManager = LogicContextManager.currentManager();

        CustomLogicContext context = new CustomLogicContext(node);
        List<LogicInstance> uncompletedInstances = logicInstanceIds.stream().map(LogicInstanceManager::get).collect(Collectors.toList());
        Set<String> completedInstanceIds = new HashSet<>();

        while (!uncompletedInstances.isEmpty()) {
            LogicInstance logicInstance = uncompletedInstances.stream().filter(instance -> {
                Set<String> dependencies = instance.dependencies();
                return dependencies.isEmpty() || completedInstanceIds.containsAll(dependencies);
            }).findFirst().orElse(null);
            if (logicInstance == null) {
                throw new IllegalStateException("Logic cannot be completed");
            }
            logicInstance.processStep(context);

            completedInstanceIds.add(logicInstance.getInstanceId());
            uncompletedInstances.remove(logicInstance);
        }


        LogicLog logicLog = new LogicLog();
        context.writeLog(logicLog);
        return context.getResultNode(finalInstanceId);
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
