package tech.gamesupport.lowcode;

import tech.gamesupport.lowcode.logic.*;
import tech.gamesupport.lowcode.logic.PathGetter;
import tech.gamesupport.lowcode.node.*;
import tech.gamesupport.lowcode.typedef.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IntAddLogic addLogic = new IntAddLogic();
        List<PathGetter> pathGetters = new ArrayList<>();
        PathGetter getterA = new PathGetter(NodePath.fromTraces("a"), NodePath.fromTraces("num1"));
        PathGetter getterB = new PathGetter(NodePath.fromTraces("b"), NodePath.fromTraces("num2"));
        pathGetters.add(getterA);
        pathGetters.add(getterB);
        LogicInstance firstAddInstance = new LogicInstance("first-add",  addLogic, pathGetters);

        pathGetters = new ArrayList<>();
        PathGetter getterC = new PathGetter(NodePath.fromTraces("c"), NodePath.fromTraces("num1"));
        PathGetter getterPrev = new PathGetter("first-add", NodePath.self(), NodePath.fromTraces("num2"));
        pathGetters.add(getterC);
        pathGetters.add(getterPrev);
        LogicInstance secondAddInstance = new LogicInstance("second-add", addLogic, pathGetters);

        ValueDef valueDef = new ValueDef(ValueType.INT, false);

        ObjectField field1 = new ObjectField("a", valueDef, false);
        ObjectField field2 = new ObjectField("b", valueDef, false);
        ObjectField field3 = new ObjectField("c", valueDef, false);

        LogicInstanceManager.put(firstAddInstance);
        LogicInstanceManager.put(secondAddInstance);


        ObjectDef objectDef = new ObjectDef(Arrays.asList(field1, field2, field3));
        CustomLogic myAddLogic = new CustomLogic(Arrays.asList("first-add", "second-add"), objectDef, TypeDefConvertUtils.toTypeDef(BigInteger.class), "second-add");
        DynamicNodeBuilder builder = new DynamicNodeBuilder();
        builder.putNode(NodePath.fromTraces("a"), new DynamicValueNode(new BigInteger("100")));
        builder.putNode(NodePath.fromTraces("b"), new DynamicValueNode(new BigInteger("200")));
        builder.putNode(NodePath.fromTraces("c"), new DynamicValueNode(new BigInteger("300")));
        DynamicNode input = builder.build();
        DynamicNode output = myAddLogic.process(input);

        System.out.println("process = " + output);
        BigInteger value = output.getValue(BigInteger.class);
        System.out.println("value = " + value);
    }


}