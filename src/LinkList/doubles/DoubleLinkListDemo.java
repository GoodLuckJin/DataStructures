package LinkList.doubles;

import LinkList.Node;
import LinkList.User;

import java.util.Objects;
import java.util.Stack;

/**
 * @ClassName DoubleLinkListDemo
 * 双向链表demo
 * @Author jjjson
 * @Date 2024/8/25 21:23
 */
public class DoubleLinkListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        Node<User> node1 = new Node<>(1, new User("马云"));
        Node<User> node2 = new Node<>(2, new User("马化腾"));
        Node<User> node3 = new Node<>(3, new User("刘强东"));
        Node<User> node4 = new Node<>(4, new User("雷军"));

        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.addByOrder(node1);
        doubleLinkList.addByOrder(node4);
        doubleLinkList.addByOrder(node3);
        doubleLinkList.addByOrder(node2);
        //show
        doubleLinkList.showList();
        //修改索引4的数据
        User user = new User("雷军-雷总");
        doubleLinkList.update(4,user);
        System.out.println("修改后链表数据:");
        //show
        doubleLinkList.showList();
        System.out.println("修改后链表数据:");
        //del
        doubleLinkList.delete(2);
        //show
        doubleLinkList.showList();
    }

}
class DoubleLinkList<T>{
    //头部node
    Node<T> headNode = new Node<T>(0, null);

    public void add(Node<T> node) {
        Node<T> tempNode = headNode;
        while (true) {
            if (null == tempNode.getNext()) {
                break;
            }
            tempNode = tempNode.getNext();
        }
        //设置上一个
        node.setPre(tempNode);
        tempNode.setNext(node);
    }

    /**
     * 按顺序add
     *
     * @Author jjjson
     * @Date 2024/7/28 13:15
     */
    public void addByOrder(Node<T> newNode) {
        Node<T> tempNode = headNode;
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
            //设置上一个数据
            newNode.setPre(tempNode);
            tempNode.setNext(newNode);

        }

    }

    public  void  update(int index,User user) {
        Node<User> tempNode = (Node<User>)headNode;
        if (null == tempNode.getNext()) {
            System.out.println("空链表");
        }
        while (true) {
            if (null == tempNode) {
                break;
            }
            if (tempNode.getIndex() == index) {
                tempNode.setT(user);
                break;
            }
            tempNode = tempNode.getNext();
        }
    }

    public void delete(int index) {
        Node<T> tempNode = headNode.getNext();
        while (true) {
            if (null == tempNode) {
                System.out.println("要删除的节点不存在"+index);
                break;
            }
            if (tempNode.getIndex() == index) {
                tempNode.getPre().setNext(tempNode.getNext());
                if(Objects.nonNull(tempNode.getNext())){
                    tempNode.getNext().setPre(tempNode.getPre());
                }
                break;
            }
            tempNode = tempNode.getNext();
        }
    }

    public int getLength() {
        Node<T> tempNode = headNode;
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

    public Node<T> findLastIndexNode(int i) {
        //得到总size
        int length = this.getLength();
        if (i > length || i <= 0) {
            return null;
        }
        Node<T> curr = headNode.getNext();
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
        Node<T> tempNode = headNode.getNext();
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
        Node<T> curr = headNode.getNext();
        Stack<Node<T>> stack =new Stack<>();
        while (curr!=null){
            stack.push(curr);
            curr = curr.getNext();
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }

    }
}

