package tech.gamesupport.lowcode;

import tech.gamesupport.lowcode.logic.*;
import tech.gamesupport.lowcode.logic.custom.*;
import tech.gamesupport.lowcode.node.*;
import tech.gamesupport.lowcode.typedef.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IntAddLogic addLogic = new IntAddLogic();
        LogicManager.registerLogic("add", addLogic);

        List<NodePathMapping> nodePathMappings = new ArrayList<>();
        NodePathMapping getterA = new NodePathMapping(NodePath.fromTraces("a"), NodePath.fromTraces("num1"));
        NodePathMapping getterB = new NodePathMapping(NodePath.fromTraces("b"), NodePath.fromTraces("num2"));
        nodePathMappings.add(getterA);
        nodePathMappings.add(getterB);
        LogicInstance firstAddInstance = new LogicInstance("first-add",  "add", new NodeMapping(nodePathMappings));

        nodePathMappings = new ArrayList<>();
        NodePathMapping getterC = new NodePathMapping(NodePath.fromTraces("c"), NodePath.fromTraces("num1"));
        NodePathMapping getterPrev = new NodePathMapping("first-add", NodePath.self(), NodePath.fromTraces("num2"));
        nodePathMappings.add(getterC);
        nodePathMappings.add(getterPrev);
        LogicInstance secondAddInstance = new LogicInstance("second-add", "add", new NodeMapping(nodePathMappings));

        ValueDef valueDef = new ValueDef(ValueType.INT, false);

        ObjectField field1 = new ObjectField("a", valueDef, false);
        ObjectField field2 = new ObjectField("b", valueDef, false);
        ObjectField field3 = new ObjectField("c", valueDef, false);

        LogicInstanceManager.put(firstAddInstance);
        LogicInstanceManager.put(secondAddInstance);


        NodeMapping nodeMapping = new NodeMapping(Collections.singletonList(new NodePathMapping("second-add", NodePath.self(), NodePath.self())));

        ObjectDef objectDef = new ObjectDef(Arrays.asList(field1, field2, field3));
        CustomLogic myAddLogic = new CustomLogic(Arrays.asList("first-add", "second-add"), objectDef, TypeDefConvertUtils.toTypeDef(BigInteger.class), nodeMapping);

        LogicManager.registerLogic("three-add", myAddLogic);


        DynamicNodeBuilder builder = new DynamicNodeBuilder();
        builder.putNode(NodePath.fromTraces("a"), new DynamicValueNode(new BigInteger("100")));
        builder.putNode(NodePath.fromTraces("b"), new DynamicValueNode(new BigInteger("200")));
        builder.putNode(NodePath.fromTraces("c"), new DynamicValueNode(new BigInteger("300")));
        builder.putNode(NodePath.fromTraces("d"), new DynamicValueNode(new BigInteger("400")));
        builder.putNode(NodePath.fromTraces("e"), new DynamicValueNode(new BigInteger("500")));
        builder.putNode(NodePath.fromTraces("f"), new DynamicValueNode(new BigInteger("600")));

        nodePathMappings = new ArrayList<>();
        nodePathMappings.add(new NodePathMapping(NodePath.self(), NodePath.self()));
        LogicInstance leftAdd = new LogicInstance("left-add",  "three-add", new NodeMapping(nodePathMappings));


        nodePathMappings = new ArrayList<>();
        nodePathMappings.add(new NodePathMapping(NodePath.fromTraces("d"), NodePath.fromTraces("a")));
        nodePathMappings.add(new NodePathMapping(NodePath.fromTraces("e"), NodePath.fromTraces("b")));
        nodePathMappings.add(new NodePathMapping(NodePath.fromTraces("f"), NodePath.fromTraces("c")));
        LogicInstance rightAdd = new LogicInstance("right-add",  "three-add", new NodeMapping(nodePathMappings));


        nodePathMappings = new ArrayList<>();
        nodePathMappings.add(new NodePathMapping("right-add", NodePath.self(), NodePath.fromTraces("num1")));
        nodePathMappings.add(new NodePathMapping("left-add", NodePath.self(), NodePath.fromTraces("num2")));
        LogicInstance finalAdd = new LogicInstance("final-add",  "add", new NodeMapping(nodePathMappings));

        LogicInstanceManager.put(leftAdd);
        LogicInstanceManager.put(rightAdd);
        LogicInstanceManager.put(finalAdd);

        nodeMapping = new NodeMapping(Collections.singletonList(new NodePathMapping("final-add", NodePath.self(), NodePath.self())));
        CustomLogic myAddLogic2 = new CustomLogic(Arrays.asList("left-add", "right-add", "final-add"), objectDef, TypeDefConvertUtils.toTypeDef(BigInteger.class), nodeMapping);

        InstanceLog instanceLog = new InstanceLog();
        instanceLog.setInstanceId("123123");
        LogicContext context = new LogicContext(builder.build());
        myAddLogic2.process(context);
        System.out.println("process = " + context.getOutputNode());
        System.out.println("logicLog = " + context.getLogs());
    }


}