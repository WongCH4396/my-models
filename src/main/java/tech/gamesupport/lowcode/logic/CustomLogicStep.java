package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.logic.argpath.PathGetter;
import tech.gamesupport.lowcode.node.DynamicNode;

import java.util.List;
import java.util.Map;

public class CustomLogicStep {

    private int stepId;
    private ILogic logic;
    private ParameterMapper parameterMapper;

    public DynamicNode buildArg(Map<Integer, DynamicNode> resultMap) {
        return parameterMapper.buildParameterNode(resultMap);
    }
}
