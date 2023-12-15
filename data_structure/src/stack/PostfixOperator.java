package stack;

import java.util.LinkedList;

/**
 * 将中缀运算转换成后缀运算
 */
public class PostfixOperator {
    /*
    遇到数字就添加到新字符串中
    遇到运算符就 1.如果栈内没有运算符，或者栈顶的运算符优先级没有本运算符高，本运算符就入栈；
               2.如果栈内有运算符，并且优先级比本运算符的优先级高或者是平级的，就将栈内运算符都出栈完添加到新字符串中，
                 再将本字符串入栈；
     */
    static int priority(char c) {
        switch (c) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                throw new IllegalArgumentException("运算符不合法~");
        }
    }

    static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '*':
                case '/':
                case '+':
                case '-':
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                    break;
                default:
                    sb.append(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(infixToSuffix("a+b-c"));
        System.out.println(infixToSuffix("a+b*c"));
    }

}
