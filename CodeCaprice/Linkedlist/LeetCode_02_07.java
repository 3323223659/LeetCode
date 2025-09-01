package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/24/0:00
 * @Description: 02.07. 链表相交(不只是值相等)
 * LeetCode链接: https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 图示两个链表在节点 c1 开始相交：
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 */
public class LeetCode_02_07 {

    public static void main(String[] args) {
        // 创建链表A
        ListNode nodeA5 = new ListNode(4);
        ListNode nodeA4 = new ListNode(2, nodeA5);
        ListNode nodeA3 = new ListNode(1, nodeA4);
        ListNode nodeA2 = new ListNode(9, nodeA3);
        ListNode nodeA1 = new ListNode(0, nodeA2);
        System.out.println("链表A：" + nodeA1);
        // 创建链表B
        ListNode nodeB3 = new ListNode(4);
        ListNode nodeB2 = new ListNode(2, nodeB3);
        ListNode nodeB1 = new ListNode(3, nodeB2);
        System.out.println("链表B：" + nodeB1);
        ListNode node = getIntersectionNode1(nodeA1, nodeB1);
        System.out.println("相交节点" + node);

    }

    /**
     * 方法一：长度差同步法
     * 时间复杂度：O(m+n) 空间复杂度：O(1)
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 相交节点，若无则返回null
     */
    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;
        // 计算链表A、B的长度
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }
        // 重置指针
        curA = headA;
        curB = headB;
        // 确保curA指向较长的链表
        if (lenB > lenA) {
            // 交换长度 swap(lenA, lenB)
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            // 交换指针 swap(curA, curB)
            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }
        // 计算长度差
        int gap = lenA - lenB;
        // 移动长链表指针到与短链表对齐的位置
        while (gap-- > 0) {
            curA = curA.next;
        }
        // 同步移动两个指针寻找交点
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null; // 无交点
    }

    /**
     * 方法二：双指针循环法（浪漫相遇法）
     * 时间复杂度：O(m+n) 空间复杂度：O(1)
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 相交节点，若无则返回null
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        // 两个指针同时移动，到达末尾后切换到对方链表
        while (p1 != p2) {
            // p1 走一步，如果走到 A 链表末尾，转到 B 链表
            if (p1 == null) p1 = headB;
            else p1 = p1.next;
            // p2 走一步，如果走到 B 链表末尾，转到 A 链表
            if (p2 == null) p2 = headA;
            else p2 = p2.next;
        }
        return p1; // 返回相遇点（可能为null）
    }

    /**
     * 链表节点定义
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }  // 空构造函数

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
