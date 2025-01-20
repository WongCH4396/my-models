package tech.gamesupport.lowcode.typedef;

public interface TypeDef {

    /**
     * check if current type definition can accept the argument type definition. for example
     *
     * {
     *     a: string
     * }
     *
     * can accept
     *
     * {
     *     a: string,
     *     b: string,
     *     c: string
     * }
     *
     * but not {
     *     b: string
     * } required parameter 'a' is missing
     *
     * nor {
     *     a: int
     * } (type mismatched)
     *
     * @param other
     * @return
     */
    boolean canAccept(TypeDef other);

    String tsLikeString();

    @Override
    String toString();

}
