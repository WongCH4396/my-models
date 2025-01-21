package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomLogicContext {

    private final DynamicNode inputNode;
    private final Map<String, DynamicNode> resultNodeMap = new HashMap<>();
    private final List<LogicLog> logs = new ArrayList<>();

    public CustomLogicContext(DynamicNode inputNode) {
        this.inputNode = inputNode;
    }

    public void writeLog(LogicLog log) {
        logs.add(log);
    }

    public List<LogicLog> getLogs() {
        return logs;
    }

    public DynamicNode getResultNode(String logicInstanceId) {
        return resultNodeMap.get(logicInstanceId);
    }

    public DynamicNode getInputNode() {
        return inputNode;
    }

    public void putResultNode(String instanceId, DynamicNode process) {
        resultNodeMap.put(instanceId, process);
    }
}
