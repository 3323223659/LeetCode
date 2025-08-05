package HOT100;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/23/12:24
 * @Description: 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */

public class hot4 {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        Solution.moveZeroes3(nums);
    }

    static class Solution {

        /**
         * 自实现 - 通过记录0的位置并覆盖移动非零元素
         *
         * 方法思路：
         * 1. 先记录所有0的位置
         * 2. 根据0的位置移动非零元素向前覆盖
         * 3. 最后在数组末尾补0
         *
         * 时间复杂度: O(n²) - 最坏情况下需要多次移动元素
         * 空间复杂度: O(k) - 需要存储所有0的位置(k为0的个数)
         *
         * 特点：
         * - 不是真正的"移动"0，而是通过覆盖方式实现
         * - 保持了非零元素的相对顺序
         * - 需要额外的空间存储0的位置
         *
         * @param nums 输入数组
         */
        public static void moveZeroes1(int[] nums) {
            List<Integer> list = new ArrayList<>(); // 存储所有0的位置
            int length = nums.length;
            int slow = 0; // 记录已处理的0的个数

            // 第一次遍历：记录所有0的位置
            for (int i = 0; i < length; i++) {
                if (nums[i] == 0){
                    list.add(i);
                }
            }

            // 如果没有0直接返回
            if (list.isEmpty()) {
                return;
            }

            // 处理每个0的位置
            while (slow < list.size()) {
                int currentZeroPos = list.get(slow);
                int nextZeroPos = (slow + 1 < list.size()) ? list.get(slow + 1) : length;

                // 移动非零元素向前覆盖
                for (int i = currentZeroPos; i < nextZeroPos - 1; i++) {
                    if (i + 1 < length) {
                        nums[i - slow] = nums[i + 1];
                    }
                }
                slow++;
            }

            // 在数组末尾补0
            for (int i = 0; i < slow; i++) {
                nums[length - 1 - i] = 0;
            }
        }

        /**
         * 网友双指针法 - 更高效的覆盖实现
         *
         * 方法思路：
         * 1. 使用双指针，一个记录当前非零元素应放置的位置
         * 2. 遍历数组，将非零元素移动到前面
         * 3. 最后在剩余位置补0
         *
         * 时间复杂度: O(n) - 只需两次遍历
         * 空间复杂度: O(1) - 不需要额外空间
         *
         * 特点：
         * - 比第一种方法更高效
         * - 仍然是覆盖而非交换
         * - 保持了非零元素的相对顺序
         *
         * @param nums 输入数组
         */
        public static void moveZeroes2(int[] nums) {
            int indexNow = 0; // 记录非零元素应放置的位置
            int indexNum = 0; // 遍历指针
            int m = nums.length;

            // 第一次遍历：移动所有非零元素到前面
            while(indexNum < m){
                if(nums[indexNum] != 0) {
                    nums[indexNow++] = nums[indexNum];
                }
                ++indexNum;
            }

            // 第二次遍历：在剩余位置补0
            for(int i = indexNow; i < m; i++){
                nums[i] = 0;
            }
        }

        /**
         * 官方双指针法 - 通过交换实现真正的移动
         *
         * 方法思路：
         * 1. 使用两个指针，left指向已处理序列的尾部，right遍历数组
         * 2. 当right遇到非零元素时，与left位置交换
         * 3. 这样可以在一次遍历中完成0的移动
         *
         * 时间复杂度: O(n) - 只需一次遍历
         * 空间复杂度: O(1) - 不需要额外空间
         *
         * 特点：
         * - 真正实现了"移动"而非覆盖
         * - 保持了非零元素的相对顺序
         * - 最优雅高效的实现方式
         *
         * @param nums 输入数组
         */
        public static void moveZeroes3(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        /**
         * 交换数组中两个位置的元素
         * @param nums 数组
         * @param left 第一个位置
         * @param right 第二个位置
         */
        public static void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

    }
}
