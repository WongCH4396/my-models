package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicValueNode;
import tech.gamesupport.lowcode.typedef.TypeDef;
import tech.gamesupport.lowcode.typedef.TypeDefConvertUtils;

import java.math.BigInteger;

public class IntAddLogic implements ILogic {

    private final TypeDef parameterTypeDef = TypeDefConvertUtils.toTypeDef(AddParameter.class);
    private final TypeDef returnTypeDef = TypeDefConvertUtils.toTypeDef(Long.class);

    @Override
    public void process(LogicContext context) {
        BigInteger num1 = context.getInputNode().getChild("num1").getValue(BigInteger.class);
        BigInteger num2 = context.getInputNode().getChild("num2").getValue(BigInteger.class);
        BigInteger result = num1.add(num2);
        context.writeLog(num1 + " + " + num2 + " = " + result);
        context.setOutputNode(DynamicValueNode.create(result));
    }

    @Override
    public TypeDef getParameterTypeDef() {
        return parameterTypeDef;
    }

    @Override
    public TypeDef getReturnTypeDef() {
        return returnTypeDef;
    }

    static class AddParameter {
        private int num1;
        private int num2;

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public int getNum2() {
            return num2;
        }

        public void setNum2(int num2) {
            this.num2 = num2;
        }
    }

}
