package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;

import java.util.*;

public class CustomLogicContext implements ILogicContext {

    private final Map<String, DynamicNode> resultNodeMap = new HashMap<>();

    private LogicLog logicLog = null;
    private Stack<LogicLog> logicLogs = new Stack<>();

    private final DynamicNode node;
    private DynamicNode logicNode;

    public CustomLogicContext(DynamicNode node) {
        this.node = node;
    }

    @Override
    public DynamicNode inputNode() {
        return node;
    }

    public DynamicNode getResultNode(String instanceId) {
        return resultNodeMap.get(instanceId);
    }

    @Override
    public void setLogicNode(DynamicNode node) {
        this.logicNode = node;
    }

    @Override
    public DynamicNode getLogicNode() {
        return logicNode;
    }

    public void setResultNode(String instanceId, DynamicNode resultNode) {
        resultNodeMap.put(instanceId, resultNode);
    }

    @Override
    public LogicLog getLogicLog() {
        return logicLog;
    }

    @Override
    public void setActiveLog(LogicLog log) {
        if (logicLog == null) {
            logicLog = log;
        } else {
            logicLog.addSubLogicLog(log);
            logicLogs.push(logicLog);
            logicLog = log;
        }
    }

    @Override
    public void endActiveLog() {
        if (logicLogs.isEmpty()) {
            logicLog = null;
        }
        logicLog = logicLogs.pop();
    }

    @Override
    public void writeLog(Object message) {
        if (logicLog != null) {
            logicLog.addMessage(message);
        }
    }
}
