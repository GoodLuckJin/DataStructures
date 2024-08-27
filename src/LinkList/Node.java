package LinkList;

import java.util.Objects;

/**
 * @ClassName SingleNode
 * @Description TODO
 * @Author jjjson
 * @Date 2024/8/25 21:30
 */
public class Node<T> {
    //指针
    private int index;
    //下一个节点
    private Node<T> next;
    //前一个节点（双链表使用）
    private Node<T> pre;
    //数据变量
    private T t;

    public Node(int index, T t) {
        this.index = index;
        this.t = t;
    }

    public Node() {
        super();
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", t=" + t +
                '}'+"  pre : {"+pre.getT()+"}"+"  next : {"+ (Objects.nonNull(next)?next.getT():null)+"}";
    }

    public int getIndex() {
        return index;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public Node<T> getPre() {
        return pre;
    }

    public void setPre(Node<T> pre) {
        this.pre = pre;
    }

    public void setT(T t) {
        this.t = t;
    }
}
