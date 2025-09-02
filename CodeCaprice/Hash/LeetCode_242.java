package CodeCaprice.Hash;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/10:56
 * @Description: 242. 有效的字母异位词
 * LeetCode链接: https://leetcode.cn/problems/valid-anagram/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 */

public class LeetCode_242 {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    /**
     * 数组计数法 - 判断字母异位词
     *
     * 方法思路：
     * 1. 使用长度为26的数组记录每个字母的出现次数
     * 2. 先统计字符串s中字母出现频率（增加计数）
     * 3. 再减去字符串t中字母出现频率（减少计数）
     * 4. 最后检查数组是否全零
     *
     * 时间复杂度: O(n) - 需要遍历两个字符串各一次
     * 空间复杂度: O(1) - 使用固定大小(26)的计数数组
     *
     * 关键点：
     * - 利用小写字母的ASCII特性（'a'-'z'连续）
     * - 字符到数组索引的映射（c - 'a'）
     * - 先加后减的计数策略
     *
     * 边界条件：
     * - 处理长度不同的字符串（直接返回false）
     * - 仅适用于小写字母场景
     * - 空字符串自动返回true（长度相同且计数全零）
     *
     * @param s 第一个字符串
     * @param t 第二个字符串
     * @return 是否为字母异位词
     */
    private static boolean isAnagram(String s, String t) {
        // 长度不同直接返回false
        if (s.length() != t.length()) {
            return false;
        }
        // 创建26个字母的计数数组（对应a-z）
        int[] record = new int[26];
        // 统计字符串s中各字母出现次数
        for (int i = 0; i < s.length(); i++) {
            // s.charAt(i) - 'a' 将字母转换为0-25的索引
            // 例如 'a' -> 0, 'b' -> 1,..., 'z' -> 25
            record[s.charAt(i) - 'a']++;
        }
        // 减去字符串t中各字母出现次数
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        // 检查所有字母计数是否归零
        for (int count : record) {
            if (count != 0) {
                return false; // 有任何字母计数不为0则不是异位词
            }
        }
        return true; // 所有字母计数均为0则是异位词
    }
}
