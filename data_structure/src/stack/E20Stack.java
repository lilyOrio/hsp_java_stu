package stack;

import java.util.Deque;
import java.util.LinkedList;

public class E20Stack {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid2("()"));
    }

    public static boolean isValid(String s) {
        ArrayStack<Character> characters = new ArrayStack<>(100);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                characters.push(')');
            } else if (c == '[') {
                characters.push(']');
            } else if (c == '{') {
                characters.push('}');
            } else {
                if (!characters.isEmpty() && c == characters.peek()) {
                    characters.pop();
                } else {
                    return false;
                }
            }
        }
        return characters.isEmpty();//栈如果是空的，说明全部配对成功，返回true
    }

    public static boolean isValid2(String s) {
        Deque<Character> stack = new LinkedList<Character>();
        for(int i = 0; i < s.length();i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else{
                if(!stack.isEmpty() && c == stack.peek()){
                    stack.pop();
                }else {
                    return false;
                }

            }
        }
        return stack.isEmpty();
    }
}
