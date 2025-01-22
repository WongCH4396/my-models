package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;

public interface ILogicContext {

    DynamicNode inputNode();

    LogicLog getLogicLog();

    void setActiveLog(LogicLog log);

    void endActiveLog();

    void writeLog(Object message);

    void setResultNode(String instanceId, DynamicNode node);

    DynamicNode getResultNode(String instanceId);

    void setLogicNode(DynamicNode build);

    DynamicNode getLogicNode();

}
