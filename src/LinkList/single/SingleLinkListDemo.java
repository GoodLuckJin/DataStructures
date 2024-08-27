package LinkList.single;

import LinkList.Node;
import LinkList.User;

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

