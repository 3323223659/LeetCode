package HOT100;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/09/21:49
 * @Description: 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */

public class hot7 {
    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int result = Solution.trap(height);
        System.out.println(result);
    }

    static class Solution {

        /**
         * 官方题解 1：动态规划
         * 方法思路：
         * 1. 预处理两个数组：
         *    - leftMax[i] 表示i位置左侧的最大高度
         *    - rightMax[i] 表示i位置右侧的最大高度
         * 2. 计算每个位置的积水量：
         *    - 取左右两侧最大高度的较小值
         *    - 减去当前柱子的高度
         * 3. 累加所有位置的积水量
         *
         * 时间复杂度: O(n)
         * 空间复杂度: O(n)
         *
         * @param height 柱子高度数组
         * @return 能接的雨水总量
        */
        public static int trap(int[] height) {
            int n = height.length;
            if (n == 0) {  // 空数组特殊情况处理
                return 0;
            }

            // 初始化左最大高度数组
            int[] leftMax = new int[n];
            leftMax[0] = height[0];  // 第一个元素的左最大值就是自身
            for (int i = 1; i < n; ++i) {  // 从左向右计算
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);  // 当前最大值是前一个最大值和当前值的较大者
            }

            // 初始化右最大高度数组
            int[] rightMax = new int[n];
            rightMax[n - 1] = height[n - 1];  // 最后一个元素的右最大值就是自身
            for (int i = n - 2; i >= 0; --i) {  // 从右向左计算
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);  // 当前最大值是后一个最大值和当前值的较大者
            }

            // 计算总雨水量
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                // 每个位置的积水量等于左右最小最大高度减去当前高度
                ans += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
            return ans;
        }

        /**
         * 官方题解 2：单调栈
         * 方法思路：
         * 1. 维护一个单调递减栈
         * 2. 遍历数组时：
         *    - 当当前高度大于栈顶高度时，计算积水区域
         *    - 积水区域的宽度为当前索引与栈顶下个元素的间距
         *    - 积水高度为左右边界较小值减去底部高度
         * 3. 将当前索引入栈
         *
         * 时间复杂度: O(n) - 每个元素最多入栈出栈一次
         * 空间复杂度: O(n) - 最坏情况下栈大小为n
         *
         * 关键点：
         * - 单调栈维护递减序列
         * - 积水区域计算需要三个柱子（左、中、右）
         * - 栈中存储的是索引而非高度
         *
         * @param height 柱子高度数组
         * @return 能接的雨水总量
         */
        public static int trap2(int[] height) {
            int ans = 0;  // 总雨水量
            Deque<Integer> stack = new LinkedList<Integer>();  // 单调栈（存储索引）
            int n = height.length;

            for (int i = 0; i < n; ++i) {  // 遍历所有柱子
                // 当当前高度大于栈顶高度时，说明可能形成凹槽
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int top = stack.pop();  // 弹出栈顶（凹槽底部）
                    if (stack.isEmpty()) {  // 栈为空说明没有左边界
                        break;
                    }
                    int left = stack.peek();  // 新的栈顶作为左边界
                    int currWidth = i - left - 1;  // 计算积水宽度
                    int currHeight = Math.min(height[left], height[i]) - height[top];  // 计算积水高度
                    ans += currWidth * currHeight;  // 累加当前凹槽的积水量
                }
                stack.push(i);  // 将当前索引入栈
            }
            return ans;
        }

        /**
         * 官方题解 3：双指针
         * 方法思路：
         * 1. 使用左右指针从两端向中间移动
         * 2. 维护左右两侧的最大高度
         * 3. 每次移动较小高度的指针
         * 4. 计算当前位置的积水量并累加
         *
         * 时间复杂度: O(n) - 一次遍历
         * 空间复杂度: O(1) - 只需要常数空间
         *
         * 关键点：
         * - 左右指针交替移动策略
         * - 实时更新左右最大高度
         * - 积水量由较小侧的最大高度决定
         *
         * @param height 柱子高度数组
         * @return 能接的雨水总量
         */
        public static int trap3(int[] height) {
            int ans = 0; // 初始化雨水总量为0

            // 初始化双指针：left指向数组起始，right指向数组末尾
            int left = 0, right = height.length - 1;

            // 初始化左右最大高度为0
            int leftMax = 0, rightMax = 0;

            // 双指针遍历数组
            while (left < right) {
                // 更新左指针所在位置的左侧最大高度
                leftMax = Math.max(leftMax, height[left]);
                // 更新右指针所在位置的右侧最大高度
                rightMax = Math.max(rightMax, height[right]);

                // 比较左右指针当前高度
                if (height[left] < height[right]) {
                    // 左指针高度较小，计算左指针位置的积水量
                    // 积水量 = 左侧最大高度 - 当前高度
                    ans += leftMax - height[left];
                    // 左指针右移
                    ++left;
                } else {
                    // 右指针高度较小，计算右指针位置的积水量
                    // 积水量 = 右侧最大高度 - 当前高度
                    ans += rightMax - height[right];
                    // 右指针左移
                    --right;
                }
            }

            // 返回最终计算的雨水总量
            return ans;
        }

    }
}
