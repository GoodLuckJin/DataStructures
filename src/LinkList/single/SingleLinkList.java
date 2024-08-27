package LinkList.single;

import LinkList.Node;
import LinkList.User;

import java.util.Stack;

/**
 * 单链表
 * @ClassName SingleLinkList
 * @Author jjjson
 * @Date 2024/8/25 21:29
 */
public class SingleLinkList <T> {
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
            tempNode.setNext(newNode);
        }

    }

    public void update(int index) {
        Node<User> tempNode = (Node<User>)headNode;
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
        Node<T> tempNode = headNode;
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
