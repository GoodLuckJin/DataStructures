package singleLinkList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单向链表-
 *
 * @ClassName SingleLinkList
 * @Author jjjson
 * @Date 2024/7/28 10:45
 */
public class SingleLinkListDemo {

    public static void main(String[] args) {
        Node<User> node1 = new Node<>(1, new User("马云"));
        Node<User> node2 = new Node<>(2, new User("马化腾"));
        Node<User> node3 = new Node<>(3, new User("刘强东"));
        Node<User> node4 = new Node<>(4, new User("雷军"));

        SingleLinkList singleLinkList = new SingleLinkList();

        //无序添加
        System.out.println();
        System.out.println("没排序链表数据：");
        singleLinkList.add(node1);
        singleLinkList.add(node3);
        singleLinkList.add(node4);
        singleLinkList.add(node2);
        singleLinkList.showList();

        singleLinkList = new SingleLinkList();
        //有序添加
        System.out.println();
        System.out.println("排序后链表数据：");
        singleLinkList.addByOrder(node1);
        singleLinkList.addByOrder(node3);
        singleLinkList.addByOrder(node4);
        singleLinkList.addByOrder(node2);
        singleLinkList.showList();

        //反转连表
        System.out.println("反转连表链表数据：");
        singleLinkList.reversePrint();

        //修改
        System.out.println();
        System.out.println("修改后链表数据：");
        singleLinkList.update(110);
        singleLinkList.update(4);
        singleLinkList.showList();

        //获取倒数第i位Node
        System.out.println();
        System.out.println("获取第i个Node：" + singleLinkList.findLastIndexNode(2));

        System.out.println();
        System.out.println("删除后链表数据：");
        singleLinkList.delete(110);
        singleLinkList.delete(1);
        singleLinkList.delete(4);
        singleLinkList.showList();

        System.out.println();
        System.out.println("单向链表总长度：" + singleLinkList.getLength());

    }


}


class SingleLinkList {
    //头部node
    Node headNode = new Node(0, "");

    public void add(Node node) {
        Node tempNode = headNode;
        while (true) {
            if (null == tempNode.getNext()) {
                break;
            }
            tempNode = tempNode.getNext();
        }
        tempNode.setNext(node);
    }

    /**
     * 按顺序add
     *
     * @Author jjjson
     * @Date 2024/7/28 13:15
     */
    public void addByOrder(Node newNode) {
        Node tempNode = headNode;
        //是否允许添加
        boolean flag = Boolean.FALSE;
        while (true) {
            if (null == tempNode.getNext()) {
                break;
            }
            if (tempNode.getNext().getIndex() > newNode.getIndex()) {
                //找到插入坐标，在tempNode后面
                break;
            } else if (tempNode.getNext().getIndex() == newNode.getIndex()) {
                //插入序号存在冲突
                flag = Boolean.TRUE;
                break;
            }
            //后移坐标
            tempNode = tempNode.getNext();
        }
        if (flag) {
            System.out.println(newNode.getIndex() + "存在相同序号的数据，不能添加");
        } else {
            newNode.setNext(tempNode.getNext());
            tempNode.setNext(newNode);
        }

    }

    public void update(int index) {
        Node<User> tempNode = headNode;
        if (null == tempNode.getNext()) {
            System.out.println("空链表");
        }
        while (true) {
            if (null == tempNode) {
                break;
            }
            if (tempNode.getIndex() == index) {
                tempNode.getT().setName(tempNode.getT().getName() + "_new");
                break;
            }
            tempNode = tempNode.getNext();
        }
    }

    public void delete(int index) {
        Node<User> tempNode = headNode;
        if (null == tempNode.getNext()) {
            System.out.println("空链表");
            return;
        }
        while (true) {
            if (null == tempNode.getNext()) {
                break;
            }
            if (tempNode.getNext().getIndex() == index) {
                tempNode.setNext(tempNode.getNext().getNext());
                //tempNode.setNext(tempNode.getNext());
                break;
            }
            tempNode = tempNode.getNext();
        }
    }

    public int getLength() {
        Node<User> tempNode = headNode;
        if (null == tempNode.getNext()) {
            return 0;
        }
        int length = 0;
        while (tempNode.getNext() != null) {
            length++;
            tempNode = tempNode.getNext();
        }
        return length;
    }

    public Node<User> findLastIndexNode(int i) {
        //得到总size
        int length = this.getLength();
        if (i > length || i <= 0) {
            return null;
        }
        Node<User> curr = headNode.getNext();
        for (int j = 0; j < length - i; j++) {
            curr = curr.getNext();
        }
        return curr;
    }

    public void showList() {
        if (null == headNode.getNext()) {
            System.out.println("链表为空");
            return;
        }
        Node tempNode = headNode.getNext();
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.getNext();
        }
    }

    public   void reversePrint(){
        if(null == headNode.getNext()){
            return;
        }
        Node<User> curr = headNode.getNext();
        Stack<Node<User>> stack =new Stack<>();
        while (curr!=null){
            stack.push(curr);
            curr = curr.getNext();
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }

    }
}


class Node<T> {
    //指针
    private int index;
    //下一个
    private Node next;
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
                '}';
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
}


class User extends Node {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
