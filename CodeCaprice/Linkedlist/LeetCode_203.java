package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/19/13:30
 * @Description: 203. 移除链表元素
 * LeetCode链接: https://leetcode.cn/problems/remove-linked-list-elements/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * 提示：
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */
public class LeetCode_203 {
    public static void main(String[] args) {
        // 构建测试链表 1->2->6->3->4->5->6
        ListNode node6 = new ListNode(5);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(6, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        System.out.println("删除前：" + head.toString());
        ListNode result = Solution.removeElements2(head, 6);
        System.out.println("删除后：" + result.toString());
    }

    private static class Solution {
        /**
         * 方法一：原链表删除法
         * 时间复杂度：O(n) 空间复杂度：O(1)
         *
         * @param head 链表头节点
         * @param val 要删除的目标值
         * @return 删除后的链表头节点
         */
        private static ListNode removeElements(ListNode head, int val) {
            // 处理头节点就是目标值的情况（可能连续多个）
            while (head != null && head.val == val) {
                head = head.next; // 直接跳过当前头节点
            }
            // 遍历链表删除非头节点的目标值
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next; // 删除cur.next节点
                } else {
                    cur = cur.next; // 只有不删除时才移动指针
                }
            }
            return head;
        }


        /**
         * 方法二：虚拟头节点法（推荐）
         * 时间复杂度：O(n) 空间复杂度：O(1)
         *
         * @param head 链表头节点
         * @param val 要删除的目标值
         * @return 删除后的链表头节点
         */
        private static ListNode removeElements2(ListNode head, int val) {
            if (head == null) return null;
            // 创建虚拟头节点，其next指向真实头节点
            ListNode dummy = new ListNode(-1, head);
            ListNode cur = dummy; // 从虚拟头节点开始遍历
            while (cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next; // 删除操作
                    // 注意：删除后不移动cur，因为新的next可能也需要删除
                } else {
                    cur = cur.next; // 移动指针
                }
            }
            return dummy.next; // 返回真实的头节点
        }
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
