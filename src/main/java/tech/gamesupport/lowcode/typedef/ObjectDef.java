package tech.gamesupport.lowcode.typedef;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectDef implements TypeDef {

    private final Map<String, ObjectField> fields;

    public ObjectDef(Map<String, ObjectField> fields) {
        this.fields = fields;
    }

    public ObjectDef(List<ObjectField> fields) {
        this.fields = fields.stream().collect(Collectors.toMap(ObjectField::getName, f -> f));
    }

    @Override
    public boolean canAccept(TypeDef other) {
        if (!(other instanceof ObjectDef)) return false;
        ObjectDef o = (ObjectDef) other;
        Set<String> keys = this.fields.keySet();
        for (String key : keys) {
            ObjectField thisField = fields.get(key);
            ObjectField thatField = o.fields.get(key);
            boolean b;
            if (thatField == null) {
                b = thisField.isNullable() || thisField.getDef().canAccept(ValueDefUtils.nullDef());
            } else {
                b = thisField.getDef().canAccept(thatField.getDef());
            }
            if (!b) return false;
        }
        return true;
    }

    @Override
    public String tsLikeString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        fields.values().forEach((v) -> {
            builder.append(v.getName()).append(v.isNullable() ? "?" : "").append(":").append(v.getDef().tsLikeString()).append(";");
        });
        builder.append("}");
        return builder.toString();
    }


}
