package tech.gamesupport.lowcode;

import tech.gamesupport.lowcode.typedef.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        ObjectField num1 = new ObjectField("num1", new ValueDef(ValueType.INT, false), false);
//        ObjectField num2 = new ObjectField("num2", new ValueDef(ValueType.INT, false), false);
//        ObjectDef def = new ObjectDef(Arrays.asList(num1, num2));
//
//
//        ObjectDef def2 = new ObjectDef(Arrays.asList(new ObjectField("data", def, false)));
//
//        ObjectDef def3 = new ObjectDef(Arrays.asList(new ObjectField("data2", def, true)));
//
//        System.out.println("def.tsLikeString() = " + def.tsLikeString());
//        System.out.println("def2.tsLikeString() = " + def2.tsLikeString());
//        System.out.println("def2.canAccept(def3) = " + def2.canAccept(def3));
//        System.out.println("def3.canAccept(def2) = " + def3.canAccept(def2));
        TypeDef typeDef = TypeDefConvertUtils.toTypeDef(AddExample.class);
        System.out.println("typeDef = " + typeDef.tsLikeString());
    }

    static final class AddExample {
        int num1;
        Integer num2;

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public Integer getNum2() {
            return num2;
        }

        public void setNum2(Integer num2) {
            this.num2 = num2;
        }
    }

}