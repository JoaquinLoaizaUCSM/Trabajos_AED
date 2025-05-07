package Ejercicio4;


import actividad1.ExceptionIsEmpty;
import actividad1.StackArray;

public class estaBalanceado {

    public static boolean isBalanced(String S) {

        StackArray<Character> stack = new StackArray<>(S.length());
        
        for (char c : S.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {

                stack.push(c);

            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top;
                
                try {
                    top = stack.pop();
                } catch (ExceptionIsEmpty e) {
                    return false;
                }
                
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
                        
    /**
     * Checks if the opening and closing brackets form a valid pair.
     * 
     * @param opening The opening bracket.
     * @param closing The closing bracket.
     * @return true if the brackets match, false otherwise.
     */
    private static boolean isMatchingPair(char opening, char closing) {
        if (opening == '(' && closing == ')') return true;
        if (opening == '[' && closing == ']') return true;
        if (opening == '{' && closing == '}') return true;
        return false;
    }

    
    public static void main(String[] args) {
        // Test cases
        System.out.println(isBalanced("()()()[()]()")); // true
        System.out.println(isBalanced("((()))[]"));     // true
        System.out.println(isBalanced("([])[](]"));     // false
        System.out.println(isBalanced("([{)]}"));       // false
        System.out.println(isBalanced("["));            // false
        System.out.println(isBalanced("[][][]{{{}}}")); // true
        System.out.println(isBalanced("[][][]{{{}}}")); // true
        System.out.println(isBalanced(")"));            // false
        System.out.println(isBalanced("("));            // false
        System.out.println(isBalanced("{[]("));         // false
        System.out.println(isBalanced("({[]})"));       // true
        System.out.println(isBalanced("([)]"));         // false
    }
}