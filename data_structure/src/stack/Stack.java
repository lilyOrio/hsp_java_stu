package stack;

//栈的实现接口
public interface Stack<E> {
    //向栈顶端添加元素
    boolean push(E val);
    //向栈的顶端移除元素
    E pop();
    //返回栈顶端元素，不弹出
    E peek();
    //判空
    boolean isEmpty();
    //判满
    boolean isFull();
}
