package tech.gamesupport.lowcode.typedef;

public class ValueDefUtils {

    private static final ValueDef NULL_DEF = new ValueDef(ValueType.NULL, true);


    public static ValueDef nullDef() {
        return NULL_DEF;
    }

}
