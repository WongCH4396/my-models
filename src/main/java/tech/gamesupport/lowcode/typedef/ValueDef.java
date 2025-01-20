package tech.gamesupport.lowcode.typedef;

import java.util.Locale;

public class ValueDef implements TypeDef {

    private final ValueType valueType;
    private final boolean nullable;

    public ValueDef(ValueType valueType, boolean nullable) {
        this.valueType = valueType;
        this.nullable = nullable;
    }

    @Override
    public boolean canAccept(TypeDef other) {
        if (!(other instanceof ValueDef)) {
            return false;
        }
        ValueDef v = (ValueDef) other;
        if (this.valueType == ValueType.ANY) {
            return true;
        }
        if (this.valueType == v.valueType) {
            return this.nullable || !v.nullable;
        }
        if (v.valueType == ValueType.NULL) {
            return this.nullable;
        }
        return false;
    }

    @Override
    public String tsLikeString() {
        if (valueType == ValueType.ANY) {
            return "any";
        }
        if (valueType == ValueType.NULL) {
            return "null";
        }
        return valueType.name().toLowerCase(Locale.ROOT) + (nullable ? " | null" : "");
    }
}
