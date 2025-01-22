package tech.gamesupport.lowcode.logic;

import java.util.HashMap;
import java.util.Map;

public class LogicManager {

    private static final Map<String, ILogic> logicMap = new HashMap<String, ILogic>();

    public static void registerLogic(String logicId, ILogic logic) {
        logicMap.put(logicId, logic);
    }

    public static ILogic getLogic(String logicId) {
        return logicMap.get(logicId);
    }

}
