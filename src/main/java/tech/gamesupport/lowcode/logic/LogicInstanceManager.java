package tech.gamesupport.lowcode.logic;

import java.util.HashMap;
import java.util.Map;

public class LogicInstanceManager {

    private static final Map<String, LogicInstance> logicInstanceMap = new HashMap<>();

    public static void put(LogicInstance logicInstance) {
        logicInstanceMap.put(logicInstance.getInstanceId(), logicInstance);
    }

    public static LogicInstance get(String logicInstanceId) {
        return logicInstanceMap.get(logicInstanceId);
    }

}
