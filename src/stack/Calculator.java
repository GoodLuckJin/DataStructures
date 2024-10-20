package stack;

/**
 * 计算器
 *
 * @ClassName Calculator
 * @Author jjjson
 * @Date 2024/10/20 20:46
 */
public class Calculator {
    public static void main1(String[] args) {
        char a = '1';
        System.out.println(a - 48);
    }

    public static void main(String[] args) {
        String expression = "70+2*6-4";
        //创建两个栈，数栈，符号栈
        CalculatorStack numStack = new CalculatorStack(10);
        CalculatorStack operStack = new CalculatorStack(10);
        //定义需要相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        //扫描的结果存放
        char ch = ' ';
        //拼接字段
        StringBuilder keepNum = new StringBuilder();
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            //是否计算符
            if (operStack.isOper(ch)) {
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果当前符号操作优先级小于等于占中的操作符，需要从数栈中pop出两个数
                    //从符号栈中pop一个符号，进行运算，将结果入数栈，然后将单钱操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.call(num1, num2, oper);
                        //把运算的结构入数栈
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数栈，则入数栈
                //char 1的十进制=49 ascii码
                //numStack.push(ch - 48);
                //1.处理数时需要需要判断是否是多位数
                //2.判断后几位是否是计算法，否则需要拼接数字
                keepNum.append(ch);
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        keepNum = new StringBuilder();
                    }
                }

            }
            //判断是否最好一条数据，跳出循环
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.call(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d ", expression, numStack.pop());
    }

}


class CalculatorStack {
    //栈大小
    private int maxSize;
    //栈顶
    private int top = -1;
    //数组模拟栈
    private int[] stack;


    public CalculatorStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /*
     * 判断栈满
     */
    public boolean ifFull() {
        return maxSize - 1 == top;
    }

    /*
     *判断栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /*
     * 添加数据
     */
    public void push(int value) {
        if (ifFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    /*
     * 取出数据
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void showList() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("输出stack[%d]=%d", i, stack[i]);
        }
    }

    /**
     * 判断符号优先级
     *
     * @param oper
     * @return int
     * @Author jjjson
     * @Date 2024/10/20 20:52
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if ('+' == oper) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否一个运算符
     *
     * @param val
     * @return boolean
     * @Author jjjson
     * @Date 2024/10/20 20:54
     */
    public boolean isOper(char val) {
        return val == '+' || val == '*' || val == '/' || val == '-';
    }

    /**
     * 计算逻辑
     *
     * @param num1
     * @param num2
     * @param oper
     * @return int
     * @Author jjjson
     * @Date 2024/10/20 21:02
     */
    public int call(int num1, int num2, int oper) {
        //存储计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                return num2 / num1;
            default:
                break;
        }
        return res;
    }

    /**
     * 返回当前栈顶的值是不是真正的pop
     *
     * @return int
     * @Author jjjson
     * @Date 2024/10/20 21:14
     */
    public int peek() {
        return stack[top];
    }
}
