package tech.gamesupport.lowcode.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LogicContextManager {

    private static final ThreadLocal<LogicContextManager> THREAD_LOCAL_CONTEXT_MANAGER = ThreadLocal.withInitial(LogicContextManager::new);

    public static LogicContextManager currentManager() {
        return THREAD_LOCAL_CONTEXT_MANAGER.get();
    }

    private List<Object> list() {
        return new ArrayList<Object>();
    }


}
