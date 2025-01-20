package tech.gamesupport.lowcode.typedef;

public class ArrayDef implements TypeDef {

    private final TypeDef elementType;

    public ArrayDef(TypeDef elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean canAccept(TypeDef other) {
        if (!(other instanceof ArrayDef)) {
            return false;
        }
        ArrayDef arrDef = (ArrayDef) other;
        return this.elementType.canAccept(arrDef.elementType);
    }

    @Override
    public String tsLikeString() {
        String s = elementType.tsLikeString();
        if (s.contains("| null")) {
            s = "(" + s + ")";
        }
        return s + "[]";
    }
}
