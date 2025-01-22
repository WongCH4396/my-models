package tech.gamesupport.lowcode.logic;

import tech.gamesupport.lowcode.node.DynamicNode;
import tech.gamesupport.lowcode.typedef.TypeDef;

public interface ILogic {

    DynamicNode process(ILogicContext context);

    TypeDef getParameterTypeDef();

    TypeDef getReturnTypeDef();

    default boolean isIntrinsic() {
        return true;
    }

}
