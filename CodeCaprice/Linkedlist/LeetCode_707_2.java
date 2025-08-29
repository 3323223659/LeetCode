package CodeCaprice.Linkedlist;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/19/20:36
 * @Description: 707. 设计链表
 * LeetCode链接: https://leetcode.cn/problems/design-linked-list/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
 * 实现 MyLinkedList 类：
 * MyLinkedList() 初始化 MyLinkedList 对象。
 * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
 * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
 * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
 * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
 * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
 * 示例：
 * 输入
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * 输出
 * [null, null, null, null, 2, null, 3]
 * 解释
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
 * myLinkedList.get(1);              // 返回 2
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
 * myLinkedList.get(1);              // 返回 3*
 * 提示：
 * 0 <= index, val <= 1000
 * 请不要使用内置的 LinkedList 库。
 * 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。
 */
public class LeetCode_707_2 {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        System.out.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        System.out.println(myLinkedList.get(1));
    }

    // 双链表实现
    private static class MyLinkedList {
        // size存储链表元素的个数
        private int size;
        // 记录头结点、尾结点
        private ListNode head,tile;
        public MyLinkedList() {
            head = new ListNode();
            tile = head;
        }

        /**
         * 获取链表中第index个节点的值（索引从0开始）
         * @param index 节点索引
         * @return 节点值，无效索引返回-1
         */
        public int get(int index) {
            if (index < 0 || index >= size) return -1; // 索引校验
            if (index == 0) return head.val;           // 直接返回头节点
            if (index == size - 1) return tile.val;
            ListNode cur = head;
            // 遍历到目标节点
            for (int i = 0; i < index; i++) {
                if (cur.next != null) {
                    cur = cur.next;
                }
            }
            return cur.val;
        }

        /**
         * 在链表头部插入节点
         * @param val 要插入的值
         */
        public void addAtHead(int val) {
            if (size == 0) {
                head.val = val; // 空链表直接赋值
            } else {
                head.prev = new ListNode(val, head); // 新节点指向原头节点，原头节点的prev指向新节点
                head = head.prev; // 头指针指向新节点
            }
            size++;
        }

        /**
         * 在链表尾部追加节点
         * @param val 要插入的值
         */
        public void addAtTail(int val) {
            // 空链表
            if (size == 0) {
                addAtHead(val);
                return;
            }
            tile.next = new ListNode(val, null, tile); // 在末尾添加新节点
            tile = tile.next;
            size++;
        }

        /**
         * 在指定索引处插入节点
         * @param index 插入位置索引
         * @param val 要插入的值
         */
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) return; // 无效索引
            // 处理特殊插入位置
            if (index == size) {
                addAtTail(val);
                return;
            }
            if (index == 0) {
                addAtHead(val);
                return;
            }
            ListNode cur = head;
            // 移动到插入位置的节点
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            // 插入新节点
            ListNode newNode = new ListNode(val, cur, cur.prev);
            cur.prev.next = newNode;
            cur.prev = newNode;
            size++;
        }

        /**
         * 删除指定索引处的节点
         * @param index 要删除的节点索引
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return; // 无效索引
            // 处理仅有一个节点的情况
            if (size == 1) {
                head = new ListNode();
                tile = head;
                size--;
                return;
            }
            // 删除头节点
            if (index == 0) {
                head = head.next;
                head.prev = null;
                size--;
                return;
            }
            // 处理删除尾节点
            if (index == size - 1) {
                tile = tile.prev;
                tile.next = null;
                size--;
                return;
            }
            ListNode cur = head;
            // 移动到删除位置的节点
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            // 跳过待删除节点
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            size--;
        }

        /**
         * 链表节点内部类
         */
        public static class ListNode {
            int val;
            ListNode next;
            ListNode prev;
            ListNode() {}  // 空构造
            ListNode(int val) {  // 值构造
                this.val = val;
            }
            ListNode(int val, ListNode next) {  // 值和next指针构造
                this.val = val;
                this.next = next;
            }
            ListNode(int val, ListNode next, ListNode prev) { // 值、next指针和prev指针构造
                this.val = val;
                this.next = next;
                this.prev = prev;
            }
            @Override
            public String toString() {
                return "ListNode{" +
                        "val=" + val +
                        ", next=" + next +
                        ", prev=" + prev +
                        '}';
            }
        }
    }
}
