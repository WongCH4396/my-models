package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.typedef.TypeDef;

public interface ILogic {

    void process(LogicContext context);

    TypeDef getParameterTypeDef();

    TypeDef getReturnTypeDef();

    default boolean isIntrinsic() {
        return true;
    }

}
