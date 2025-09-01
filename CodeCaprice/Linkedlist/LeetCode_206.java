package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/19/21:56
 * @Description: 206. 反转链表
 * LeetCode链接: https://leetcode.cn/problems/reverse-linked-list/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 */

public class LeetCode_206 {
    public static void main(String[] args) {
        // 创建链表
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println("翻转前：" + node1);
        ListNode node = new Solution().reverseList2(node1);
        System.out.println("翻转后：" + node);
    }

    private static class Solution {

        /**
         * 方法一：双指针迭代法反转链表
         *
         * 方法思路：
         * 1. 使用两个指针pre和cur，pre初始为null，cur初始指向头节点
         * 2. 遍历链表，每次将cur.next指向pre实现反转
         * 3. 移动pre和cur指针到下一个位置
         * 4. 当cur为null时，pre就是新链表的头节点
         *
         * 时间复杂度: O(n) - 只需一次遍历
         * 空间复杂度: O(1) - 只使用了固定数量的指针
         *
         * 关键点：
         * - 需要临时保存cur.next防止断链
         * - 指针移动顺序：先反转，再移动pre，最后移动cur
         * - 终止条件：cur == null
         *
         * @param head 链表头节点
         * @return 反转后的链表头节点
         */
        private static ListNode reverseList(ListNode head) {
            ListNode pre = null;    // 前驱节点初始化为null
            ListNode cur = head;    // 当前节点从头节点开始
            while (cur != null) {
                ListNode next = cur.next; // 临时保存下一个节点
                cur.next = pre;     // 反转指针方向
                pre = cur;          // 前驱指针后移
                cur = next;         // 当前指针后移
            }
            return pre; // 最后pre会成为新链表的头节点
        }

        /**
         * 方法二：递归实现链表反转
         *
         * 方法思路：
         * 1. 递归终止条件：当前节点为null，返回前驱节点
         * 2. 保存下一个节点防止断链
         * 3. 反转当前节点的指针方向
         * 4. 递归处理下一个节点
         *
         * 时间复杂度: O(n) - 需要递归n层
         * 空间复杂度: O(n) - 递归栈空间
         *
         * 关键点：
         * - 递归终止条件处理
         * - 指针反转顺序与迭代法一致
         * - 递归调用时更新pre和cur
         *
         * @param head 链表头节点
         * @return 反转后的链表头节点
         */
        private static ListNode reverseList2(ListNode head) {
            return reverse(null, head); // 初始化pre和cur开始递归
        }

        /**
         * 递归辅助方法
         * @param pre 前一个节点
         * @param cur 当前节点
         * @return 反转后的链表头节点
         */
        private static ListNode reverse(ListNode pre, ListNode cur) {
            if (cur == null) {
                return pre; // 递归终止条件，返回新链表头节点
            }
            ListNode temp = cur.next; // 临时保存下一个节点
            cur.next = pre;           // 反转指针方向
//            pre = cur;
//            cur = temp;
            return reverse(cur, temp); // 递归处理下一个节点(上面两步加上更加清晰)
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
