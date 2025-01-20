package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.typedef.TypeDef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomLogic implements ILogic {

    private final List<CustomLogicStep> steps;
    private final TypeDef parameterTypeDef;
    private final TypeDef returnTypeDef;

    public CustomLogic(List<CustomLogicStep> steps, TypeDef parameterTypeDef, TypeDef returnTypeDef) {
        this.steps = steps;
        this.parameterTypeDef = parameterTypeDef;
        this.returnTypeDef = returnTypeDef;
    }

    @Override
    public DynamicNode process(DynamicNode node) {
        Map<Integer, DynamicNode> resultMap = new HashMap<>();
        resultMap.put(-1, node);
        for (CustomLogicStep step : steps) {
            int stepId = step.getStepId();
            ILogic logic = step.getLogic();
            DynamicNode stepParameter = step.buildArg(resultMap);
            DynamicNode stepResult = logic.process(stepParameter);
            resultMap.put(stepId, stepResult);
        }
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
