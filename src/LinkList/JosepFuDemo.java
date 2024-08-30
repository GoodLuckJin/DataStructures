package LinkList;

/**
 * 约瑟夫-环形链表
 *
 * @ClassName JosepFuDemo
 * @Author jjjson
 * @Date 2024/8/28 21:44
 */
public class JosepFuDemo {

    public static void main(String[] args) {
        CircleSingeLinkList circleSingeLinkList = new CircleSingeLinkList();
        circleSingeLinkList.addBoy(10);
        System.out.println("队列列表：");
        circleSingeLinkList.showBoy();
        System.out.println("开始报数：");
        circleSingeLinkList.countBoy(1, 4, 10);
    }

}

//创建单向环形链表
class CircleSingeLinkList {
    //开始节点
    private Boy first = new Boy(-1);

    /**
     * add 环形链表
     *
     * @param nums
     * @Author jjjson
     * @Date 2024/8/28 22:05
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
        }
        //辅助变量
        Boy currBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //第一个节点做环形处理
            if (i == 1) {
                first = boy;
                //第一个节点将下一个节点只想自己
                first.setNext(first);
                //将第一个节点赋值局部变量,用于中间变量使用
                currBoy = first;
                continue;
            }
            //后续节点处理
            //将上个节点的下一个节点指向新节点
            currBoy.setNext(boy);
            //将新节点的下个节点指向第一个节点，构成环形链表
            boy.setNext(first);
            //将新节点的数据赋值中间变量
            currBoy = boy;
        }
    }

    /**
     * 输出链表
     *
     * @Author jjjson
     * @Date 2024/8/28 22:05
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("链表没有数据");
            return;
        }
        Boy currBoy = first;
        while (true) {
            System.out.println("链表编号：" + currBoy.getNo());
            if (currBoy.getNext() == first) {
                break;
            }
            currBoy = currBoy.getNext();
        }
    }

    /**
     * 根据用户输入，计算出链表出圈的顺序
     *
     * @param startNo 表示从第几个人开始数数
     * @param countNo 表示每次数几下
     * @param nums    表示圈中的人数
     * @Author jjjson
     * @Date 2024/8/28 22:17
     */
    public void countBoy(int startNo, int countNo, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            //需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
            helper = helper.getNext();
        }
        //报数开始前，先让first和helper移动 k - 1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 报数开始时，让first和helper指针同时 的移动 m  - 1 次, 然后first指向人被杀死，出圈
        // 这里是一个循环操作，直至圈中只有一个节点
        while (true) {
            // 当helper == first时，说明圈中只有一个节点
            if (first == helper) {
                break;
            }
            // 让 first 和 helper 指针同时移动 countNum - 1
            for (int i = 0; i <countNo-1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("第%d号人被出圈\n", first.getNo());
            // 出圈操作
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的人的编号是：%d \n", first.getNo());
    }

}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}