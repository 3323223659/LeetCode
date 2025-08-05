package HOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/23/12:24
 * @Description: 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */

public class hot5 {

    public static void main(String[] args) {
        Solution.maxArea1(new int[]{1,8,6,2,5,4,8,3,7});
    }

    static class Solution {

        /**
         * 双指针法 - 计算能盛最多水的容器
         *
         * 方法思路：
         * 1. 初始化两个指针，left指向数组开头，right指向数组末尾
         * 2. 计算当前指针位置形成的容器面积
         * 3. 移动高度较小的指针，因为只有这样才能可能获得更大的面积
         * 4. 重复上述过程直到指针相遇
         *
         * 时间复杂度: O(n) - 只需一次遍历
         * 空间复杂度: O(1) - 只使用了固定大小的额外空间
         *
         * 关键点：
         * - 容器的面积由较短的线和两线距离决定
         * - 移动较短的线才有可能增加面积
         * - 保持记录最大面积
         *
         * @param height 表示线段高度的数组
         * @return 能盛放的最大水量
         */
        public static int maxArea1(int[] height) {
            int left = 0; // 左指针
            int right = height.length - 1; // 右指针
            int maxArea = 0; // 记录最大面积

            while (left < right) {
                // 计算当前容器的面积
                int minHeight = Math.min(height[left], height[right]);
                int area = (right - left) * minHeight;

                // 更新最大面积
                if (area > maxArea) {
                    maxArea = area;
                }

                // 移动较短的线
                if (height[left] < height[right]) {
                    left++; // 左指针右移
                } else {
                    right--; // 右指针左移
                }
            }

            return maxArea;
        }

        /**
         * 官方双指针法 - 更简洁的实现
         *
         * 方法思路与自定义实现相同，但代码更简洁：
         * 1. 使用Math.max和Math.min简化比较逻辑
         * 2. 直接更新ans变量
         * 3. 移动指针的条件判断更简洁
         *
         * 时间复杂度: O(n)
         * 空间复杂度: O(1)
         *
         * @param height 表示线段高度的数组
         * @return 能盛放的最大水量
         */
        public static int maxArea2(int[] height) {
            int l = 0; // 左指针
            int r = height.length - 1; // 右指针
            int ans = 0; // 记录最大面积

            while (l < r) {
                // 计算当前面积并更新最大值
                int area = Math.min(height[l], height[r]) * (r - l);
                ans = Math.max(ans, area);

                // 移动较短的线
                if (height[l] <= height[r]) {
                    ++l; // 左指针右移
                } else {
                    --r; // 右指针左移
                }
            }

            return ans;
        }

    }
}
