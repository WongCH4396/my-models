package tech.gamesupport.lowcode.logic.argpath;

import tech.gamesupport.lowcode.node.NodePath;

public class PathGetter {

    private final int source;
    private final NodePath sourcePath;
    private final NodePath targetPath;

    public PathGetter(int source, NodePath sourcePath, NodePath targetPath) {
        this.source = source;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }


    public int getSource() {
        return source;
    }

    public NodePath getSourcePath() {
        return sourcePath;
    }

    public NodePath getTargetPath() {
        return targetPath;
    }
}
