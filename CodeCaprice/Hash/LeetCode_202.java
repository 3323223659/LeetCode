package CodeCaprice.Hash;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/11:11
 * @Description: 202. 快乐数
 * LeetCode链接: https://leetcode.cn/problems/happy-number/
 * 笔记链接：https://www.programmercarl.com
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * 示例 1：
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 * 输入：n = 2
 * 输出：false
 */
public class LeetCode_202 {
    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy(n));
    }

    /**
     * 哈希表法 - 判断快乐数
     * 方法思路：
     * 1. 使用HashSet记录计算过程中出现的所有数字
     * 2. 循环计算数字各位平方和直到结果为1（快乐数）或出现重复数字（非快乐数）
     * 3. 通过集合检测机制避免无限循环
     *
     * 时间复杂度: O(log n) - 每次迭代数字位数减少，收敛速度快
     * 空间复杂度: O(log n) - 需要存储计算过程中的中间数字
     *
     * 数学原理：
     * - 根据数学证明，任何数最终都会进入循环（鸽巢原理）
     * - 快乐数最终会收敛到1的循环
     * - 非快乐数会进入其他数字的循环（如4→16→37→58→89→145→42→20→4）
     *
     * 关键点：
     * - 使用集合检测循环是核心机制
     * - 平方和计算需要正确处理每位数字
     * - 边界条件1直接返回true
     *
     * 边界条件：
     * - 处理输入为1的情况
     * - 处理大数计算（题目保证输入为正整数）
     *
     * @param n 待判断的正整数
     * @return 如果是快乐数返回true，否则返回false
     */
    public static boolean isHappy(int n) {
        // 使用HashSet记录已经出现过的数字，用于检测循环
        Set<Integer> seenNumbers = new HashSet<>();
        // 主循环：当数字不为1且未出现重复时继续计算
        while (n != 1 && !seenNumbers.contains(n)) {
            seenNumbers.add(n);  // 记录当前数字
            // 计算各位数字的平方和
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;   // 获取个位数字
                sum += digit * digit; // 累加平方值
                n /= 10;             // 移除已处理的个位
            }
            n = sum;  // 将平方和作为新的待处理数字
        }
        // 最终判断：如果n变为1则是快乐数
        return n == 1;
    }
}
