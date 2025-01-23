package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.node.DynamicNullNode;

import java.util.ArrayList;
import java.util.List;

public class LogicContext {

    private final DynamicNode inputNode;
    private DynamicNode outputNode = DynamicNullNode.getInstance();
    private final List<Object> logs = new ArrayList<>();

    public LogicContext(DynamicNode inputNode) {
        this.inputNode = inputNode;
    }

    public void writeLog(Object message){
        logs.add(message);
    }

    public DynamicNode getInputNode() {
        return inputNode;
    }

    public DynamicNode getOutputNode() {
        return outputNode;
    }

    public void setOutputNode(DynamicNode outputNode) {
        this.outputNode = outputNode;
    }

    public List<Object> getLogs() {
        return logs;
    }
}
