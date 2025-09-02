package CodeCaprice.Hash;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/13:43
 * @Description: 383. 赎金信
 * LeetCode链接: https://leetcode.cn/problems/ransom-note/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * 提示：
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 */
public class LeetCode_383 {
    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    /**
     * 赎金信构造判断算法
     * 功能说明：
     * 判断ransomNote字符串是否能由magazine字符串中的字符构成。
     * magazine字符串中的每个字符只能在ransomNote中使用一次。
     *
     * 实现原理：
     * 1. 使用长度为26的数组统计magazine中各字母的出现次数
     * 2. 遍历ransomNote消耗统计数组中的字符
     * 3. 当出现字符不足时立即返回false
     *
     * 时间复杂度分析：O(m+n) 其中m=magazine长度，n=ransomNote长度
     * 空间复杂度分析：O(1)（固定大小的计数数组）
     *
     * 字符处理：
     * - 仅处理小写字母（ASCII码范围97-122）
     * - 大小写敏感（输入需统一大小写）
     *
     * @param ransomNote 要构造的字符串
     * @param magazine   可用的字符来源字符串
     * @return 如果可以构造返回true，否则返回false
     * @throws NullPointerException 如果任一输入为null
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        // 快速失败：如果目标字符串比来源字符串长，必然无法构造
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // 初始化字母计数数组（对应a-z）
        int[] magazineCount = new int[26]; // 索引0-25对应a-z
        // 统计magazine中各字母出现次数
        for (char c : magazine.toCharArray()) {
            magazineCount[c - 'a']++; // 'a'的ASCII码为97，转换为0-25的索引
        }
        // 检查ransomNote中的每个字符是否可用
        for (char c : ransomNote.toCharArray()) {
            // 先判断后递减（包含等于0的情况）
            if (magazineCount[c - 'a']-- <= 0) {
                return false; // 字符不足或不存在
            }
        }
        return true; // 所有字符检查通过
    }
}