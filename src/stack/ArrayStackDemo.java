package stack;

/**
 * 栈
 *
 * @ClassName ArryStackDemo
 * @Author jjjson
 * @Date 2024/8/30 21:44
 */
public class ArrayStackDemo {


    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        for (int i = 1; i <= 11; i++) {
            arrayStack.push(i);
        }
        arrayStack.showList();


        for (int i = 0; i <11 ; i++) {
            System.out.println("取出数据："+arrayStack.pop());
        }
    }

}

class ArrayStack {
    //栈大小
    private int maxSize;
    //栈顶
    private int top = -1;
    //数组模拟栈
    private int[] stack;


    public ArrayStack(int maxSize) {
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
            System.out.printf("输出stack[%d]=%d",i,stack[i]);
        }
    }
}
