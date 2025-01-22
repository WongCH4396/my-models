package tech.gamesupport.lowcode.logic;

import java.util.ArrayList;
import java.util.List;

public class LogicLog {

    private String instanceId;
    private final List<Object> subs = new ArrayList<>();

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void addSubLogicLog(LogicLog subLog) {
        subs.add(subLog);
    }

    public void addMessage(Object message) {
        subs.add(message);
    }

    public String getInstanceId() {
        return instanceId;
    }

    public List<Object> getSubs() {
        return subs;
    }
}

