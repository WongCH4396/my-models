package tech.gamesupport.lowcode.logic.custom;

import java.util.ArrayList;
import java.util.List;

public class InstanceLog {

    private String instanceId;
    private final List<Object> subs = new ArrayList<>();

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public List<Object> getSubs() {
        return subs;
    }

    public void addAllMessages(List<Object> messages) {
        subs.addAll(messages);
    }

    @Override
    public String toString() {
        return "InstanceLog(" +
                "instanceId='" + instanceId + '\'' +
                ", subs=" + subs +
                ')';
    }
}

