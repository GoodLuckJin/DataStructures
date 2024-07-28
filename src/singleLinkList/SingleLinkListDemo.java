package singleLinkList;

import java.util.ArrayList;
import java.util.List;

/**
 * 单向链表-
 * @ClassName SingleLinkList
 * @Author zhao jin
 * @Date 2024/7/28 10:45
 */
public class SingleLinkListDemo {

    public static void main(String[] args) {
            Node<User> node1 = new Node<>(1,new User("马云"));
            Node<User> node2 = new Node<>(2,new User("马化腾"));
            Node<User> node3 = new Node<>(3,new User("刘强东"));
            Node<User> node4 = new Node<>(4,new User("雷军"));

        SingleLinkList singleLinkList = new SingleLinkList();
        singleLinkList.add(node1);
        singleLinkList.add(node2);
        singleLinkList.add(node3);
        singleLinkList.add(node4);
        singleLinkList.showList();
    }


}
class SingleLinkList{
    //头部node
    Node headNode = new Node(0,"");

    public void add(Node node){
        Node tempNode =headNode;
        while (true){
            if(null == tempNode.getNext()){
                break;
            }
            tempNode=tempNode.getNext();
        }
        tempNode.setNext(node);
    }
    public void showList(){
        if(null == headNode.getNext()){
            System.out.println("链表为空");
            return;
        }
        Node tempNode =headNode.getNext();
        while (true){
            if(tempNode==null){
                break;
            }
            System.out.println(tempNode);
            tempNode=tempNode.getNext();
        }
    }
}


class  Node<T>{
    //指针
    private int index;
    //下一个
    private Node next;
    //数据变量
    private T t;

    public Node(int index,T t){
        this.index=index;
        this.t=t;
    }

    public Node(){
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
}


class User extends Node {
    private String name;

    public User(String name){
        this.name=name;
    }
    public User(){
        super();
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
