package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.NodePath;

public class PathGetter {

    private final boolean isInputArg;
    private final String instanceId;
    private final NodePath sourcePath;
    private final NodePath targetPath;

    public PathGetter(String instanceId, NodePath sourcePath, NodePath targetPath) {
        this.instanceId = instanceId;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        isInputArg = false;
    }

    public PathGetter(NodePath sourcePath, NodePath targetPath) {
        this.instanceId = null;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        isInputArg = true;
    }

    public boolean isInputArg() {
        return isInputArg;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public NodePath getSourcePath() {
        return sourcePath;
    }

    public NodePath getTargetPath() {
        return targetPath;
    }
}
