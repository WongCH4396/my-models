package tech.gamesupport.lowcode.logic.custom;

public class LogicInstance {

    private final NodeMapping nodeMapping;
    private final String instanceId;
    private final String logicId;

    public LogicInstance(String instanceId, String logicId, NodeMapping nodeMapping) {
        this.instanceId = instanceId;
        this.logicId = logicId;
        this.nodeMapping = nodeMapping;
    }

    public String getLogicId() {
        return logicId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public NodeMapping getNodeMapping() {
        return nodeMapping;
    }
}
