package tech.gamesupport.lowcode.node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodePath {

    private final List<Object> traces;

    public NodePath(List<Object> traces) {
        this.traces = traces;
    }

    public NodePath goDeep() {
        return new NodePath(traces.subList(1, traces.size()));
    }

    public DynamicNode find(DynamicNode node) {
        DynamicNode current = node;
        for (Object trace : traces) {
            if (trace instanceof String) {
                current = current.getChild((String) trace);
            } else if (trace instanceof Integer) {
                current = current.getChildAt((int) trace);
            }
        }
        return current;
    }

    public static NodePath fromTraces(Object... pathVal) {
        return new NodePath(Arrays.asList(pathVal));
    }

    public static NodePath self() {
        return new NodePath(Collections.emptyList());
    }


    public boolean isSelf() {
        return traces.isEmpty();
    }

    public boolean isIndex() {
        return !traces.isEmpty() && traces.get(0) instanceof Integer;
    }

    public int getIndex() {
        return (Integer) traces.get(0);
    }

    public String getName() {
        return (String) traces.get(0);
    }
}
