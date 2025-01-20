package tech.gamesupport.lowcode.node;

import java.util.ArrayList;
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

    public static NodePath fromString(String pathVal) {
        List<Object> pathList = new ArrayList<>();
        // Remove the surrounding brackets if present
        if (pathVal.startsWith("[") && pathVal.endsWith("]")) {
            pathVal = pathVal.substring(1, pathVal.length() - 1);
        }
        // Split by dot while considering array-like notations
        String[] parts = pathVal.split("\\.");
        for (String part : parts) {
            // Check if the part is a number (array index)
            try {
                int index = Integer.parseInt(part.trim());
                pathList.add(index);
            } catch (NumberFormatException e) {
                // Not a number, treat it as a string
                pathList.add(part.trim());
            }
        }
        return new NodePath(pathList);
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
