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
        LogicManager.registerLogic("add", addLogic);

        List<PathGetter> pathGetters = new ArrayList<>();
        PathGetter getterA = new PathGetter(NodePath.fromTraces("a"), NodePath.fromTraces("num1"));
        PathGetter getterB = new PathGetter(NodePath.fromTraces("b"), NodePath.fromTraces("num2"));
        pathGetters.add(getterA);
        pathGetters.add(getterB);
        LogicInstance firstAddInstance = new LogicInstance("first-add",  "add", pathGetters);

        pathGetters = new ArrayList<>();
        PathGetter getterC = new PathGetter(NodePath.fromTraces("c"), NodePath.fromTraces("num1"));
        PathGetter getterPrev = new PathGetter("first-add", NodePath.self(), NodePath.fromTraces("num2"));
        pathGetters.add(getterC);
        pathGetters.add(getterPrev);
        LogicInstance secondAddInstance = new LogicInstance("second-add", "add", pathGetters);

        ValueDef valueDef = new ValueDef(ValueType.INT, false);

        ObjectField field1 = new ObjectField("a", valueDef, false);
        ObjectField field2 = new ObjectField("b", valueDef, false);
        ObjectField field3 = new ObjectField("c", valueDef, false);

        LogicInstanceManager.put(firstAddInstance);
        LogicInstanceManager.put(secondAddInstance);


        ObjectDef objectDef = new ObjectDef(Arrays.asList(field1, field2, field3));
        CustomLogic myAddLogic = new CustomLogic(Arrays.asList("first-add", "second-add"), objectDef, TypeDefConvertUtils.toTypeDef(BigInteger.class), "second-add");


        LogicManager.registerLogic("three-add", myAddLogic);


        DynamicNodeBuilder builder = new DynamicNodeBuilder();
        builder.putNode(NodePath.fromTraces("a"), new DynamicValueNode(new BigInteger("100")));
        builder.putNode(NodePath.fromTraces("b"), new DynamicValueNode(new BigInteger("200")));
        builder.putNode(NodePath.fromTraces("c"), new DynamicValueNode(new BigInteger("300")));
        builder.putNode(NodePath.fromTraces("d"), new DynamicValueNode(new BigInteger("400")));
        builder.putNode(NodePath.fromTraces("e"), new DynamicValueNode(new BigInteger("500")));
        builder.putNode(NodePath.fromTraces("f"), new DynamicValueNode(new BigInteger("600")));

        pathGetters = new ArrayList<>();
        pathGetters.add(new PathGetter(NodePath.self(), NodePath.self()));
        LogicInstance leftAdd = new LogicInstance("left-add",  "three-add", pathGetters);


        pathGetters = new ArrayList<>();
        pathGetters.add(new PathGetter(NodePath.fromTraces("d"), NodePath.fromTraces("a")));
        pathGetters.add(new PathGetter(NodePath.fromTraces("e"), NodePath.fromTraces("b")));
        pathGetters.add(new PathGetter(NodePath.fromTraces("f"), NodePath.fromTraces("c")));
        LogicInstance rightAdd = new LogicInstance("right-add",  "three-add", pathGetters);


        pathGetters = new ArrayList<>();
        pathGetters.add(new PathGetter("right-add", NodePath.self(), NodePath.fromTraces("num1")));
        pathGetters.add(new PathGetter("left-add", NodePath.self(), NodePath.fromTraces("num2")));
        LogicInstance finalAdd = new LogicInstance("final-add",  "add", pathGetters);

        LogicInstanceManager.put(leftAdd);
        LogicInstanceManager.put(rightAdd);
        LogicInstanceManager.put(finalAdd);

        CustomLogic myAddLogic2 = new CustomLogic(Arrays.asList("left-add", "right-add", "final-add"), objectDef, TypeDefConvertUtils.toTypeDef(BigInteger.class), "final-add");

        CustomLogicContext context = new CustomLogicContext(builder.build());
        LogicLog logicLog = new LogicLog();
        logicLog.setInstanceId("123123");
        context.setActiveLog(logicLog);
        System.out.println("process = " + myAddLogic2.process(context).getValue(BigInteger.class));
        System.out.println("logicLog = " + logicLog);
    }


}