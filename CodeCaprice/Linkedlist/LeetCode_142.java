package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/9:34
 * @Description: 142. 环形链表 II
 * LeetCode链接: https://leetcode.cn/problems/linked-list-cycle-ii/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 * 提示：
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 */
public class LeetCode_142 {
    public static void main(String[] args) {
        // 构建测试链表 3->2->0->-4->2 (形成环)
        ListNode node4 = new ListNode(-4);
        ListNode node3 = new ListNode(0, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(3, node2);
        node4.next = node2;
        System.out.println("链表：" + node1);
        System.out.println(detectCycle(node1));
    }

    /**
     * 快慢指针法检测环形链表并找到环入口
     * 时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表头节点
     * @return 环入口节点，若无环则返回null
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        // 第一阶段：检测链表是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;       // 慢指针每次走1步
            fast = fast.next.next;  // 快指针每次走2步
            // 如果相遇说明有环
            if (slow == fast) {
                // 第二阶段：寻找环入口
                ListNode index1 = head;    // 从头节点出发
                ListNode index2 = slow;    // 从相遇点出发
                // 两个指针以相同速度前进，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1; // 返回环入口
            }
        }
        return null; // 无环
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
