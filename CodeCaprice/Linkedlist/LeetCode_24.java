package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/19/23:31
 * @Description: 24. 两两交换链表中的节点
 * LeetCode链接: https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */

public class LeetCode_24 {
    public static void main(String[] args) {
        // 创建链表
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println("翻转前：" + node1);
        ListNode node = swapPairs(node1);
        System.out.println("翻转后：" + node);

    }

    /**
     * 方法一：直接交换法（不使用虚拟头节点）
     * 时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    private static ListNode swapPairs(ListNode head) {
        // 处理空链表或单节点链表的情况
        if (head == null || head.next == null) return head;
        // 保存最终要返回的新头节点（原链表的第二个节点）
        ListNode newNode = head.next;
        ListNode prevNode = null; // 记录前一组的尾节点
        while (true) {
            // 要交换的两个节点
            ListNode first = head;
            ListNode second = head.next;
            // 执行交换
            first.next = second.next;
            second.next = first;
            // 连接前一组的尾节点
            if (prevNode != null) {
                prevNode.next = second;
            }
            // 更新指针
            prevNode = first; // 当前组的尾节点成为下一组的前驱
            head = first.next; // 移动到下一组

            // 终止条件：剩余节点不足两个
            if (head == null || head.next == null) {
                return newNode;
            }
        }
    }

    /**
     * 方法二：虚拟头节点法（推荐）
     * 时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    private static ListNode swapPairs2(ListNode head) {
        // 创建虚拟头节点，其next指向真实头节点
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy; // 当前指针初始指向虚拟头
        while (cur.next != null && cur.next.next != null) {
            // 要交换的两个节点
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            // 三步完成交换：
            cur.next = node2;        // 步骤1：前驱指向第二个节点
            node1.next = node2.next; // 步骤2：第一个节点指向下一组的头
            node2.next = node1;      // 步骤3：第二个节点指向第一个节点
            // 移动指针到下一组的前驱位置
            cur = cur.next.next;
        }
        return dummy.next; // 返回真实头节点
    }

    /**
     * 方法三：递归法
     * 时间复杂度：O(n) 空间复杂度：O(n)（递归栈空间）
     *
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    public ListNode swapPairs3(ListNode head) {
        // 基线条件：空链表或单节点链表直接返回
        if (head == null || head.next == null) return head;
        // 获取当前组的第二个节点
        ListNode next = head.next;
        // 递归处理后续链表
        ListNode newNode = swapPairs3(next.next);
        // 交换当前两个节点
        next.next = head;
        head.next = newNode;
        return next; // 返回新的头节点
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
