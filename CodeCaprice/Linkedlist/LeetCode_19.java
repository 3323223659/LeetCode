package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/20/11:43
 * @Description: 删除链表的倒数第 N 个结点
 * LeetCode链接: https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class LeetCode_19 {
    public static void main(String[] args) {
        // 创建链表
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println("删除前：" + node1);
        ListNode node = removeNthFromEnd(node1, 2);
        System.out.println("删除后：" + node);

    }

    /**
     * 方法一：快慢指针法
     * 时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表头节点
     * @param n 要删除的倒数第n个节点
     * @return 删除后的链表头节点
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        // 处理空链表或单节点链表的特殊情况
        if (head == null || head.next == null) {
            return null;
        }
        // 创建虚拟头节点，其next指向真实头节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 初始化快慢指针
        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;
        // 快指针先移动n+1步，使快慢指针间隔n个节点
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }
        // 同步移动快慢指针，直到快指针到达链表末尾
        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }
        // 此时slowIndex指向待删除节点的前一个节点,执行删除操作
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next; // 返回真实头节点
    }

    /**
     * 方法二：递归法
     * 时间复杂度：O(n) 空间复杂度：O(n)（递归栈空间）
     *
     * @param head 链表头节点
     * @param n 要删除的倒数第n个节点
     * @return 删除后的链表头节点
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 创建哑节点处理头节点删除的特殊情况
        ListNode dummy = new ListNode(-1, head);
        // 递归找到并且删除节点
        remove(dummy, n);
        return dummy.next;
    }

    /**
     * 递归辅助方法
     * @param p 当前节点
     * @param n 要删除的倒数位置
     * @return 当前节点到链表末尾的距离
     */
    public static int remove(ListNode p, int n) {
        // 递归终止条件：到达链表末尾
        if (p == null) {
            return 0;
        }
        // 递归深入下一节点
        int distanceFromEnd = remove(p.next, n);
        // 如果当前节点是倒数第n个节点的前驱，执行删除
        if (distanceFromEnd == n) {
            p.next = p.next.next;
        }
        // 返回当前节点到末尾的距离+1
        return distanceFromEnd + 1;
    }

    /**
     * 链表节点定义
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}  // 空构造函数
        ListNode(int val) {  // 只有值的构造函数
            this.val = val;
        }
        ListNode(int val, ListNode next) {  // 包含值和next指针的构造函数
            this.val = val;
            this.next = next;
        }
        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
