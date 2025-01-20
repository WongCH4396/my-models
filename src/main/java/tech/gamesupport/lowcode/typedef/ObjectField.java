package tech.gamesupport.lowcode.typedef;

public class ObjectField {

    private final String name;
    private final TypeDef def;
    private final boolean nullable;

    public ObjectField(String name, TypeDef def, boolean nullable) {
        this.name = name;
        this.def = def;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public TypeDef getDef() {
        return def;
    }

    public boolean isNullable() {
        return nullable;
    }
}
