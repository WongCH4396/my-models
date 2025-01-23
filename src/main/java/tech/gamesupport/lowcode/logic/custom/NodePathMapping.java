package tech.gamesupport.lowcode.logic.custom;

import tech.gamesupport.lowcode.node.NodePath;

public class NodePathMapping {

    private final String instanceId;
    private final NodePath sourcePath;
    private final NodePath targetPath;

    public NodePathMapping(String instanceId, NodePath sourcePath, NodePath targetPath) {
        this.instanceId = instanceId;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }

    public NodePathMapping(NodePath sourcePath, NodePath targetPath) {
        this.instanceId = null;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
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
