package HOT100;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/23/12:31
 * @Description: 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */

public class hot2 {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = Solution.groupAnagrams1(strs);
        System.out.println(lists);
    }

    static class Solution {

        /**
         * 自实现Map实现 - 通过字符串排序作为键分组
         *
         * 方法思路：
         * 1. 创建HashMap存储分组结果，键为排序后的字符串，值为原始字符串列表
         * 2. 对每个字符串进行字符排序生成标准键
         * 3. 根据键将字符串分组存储
         *
         * 时间复杂度: O(n*klogk) - n是字符串数量，k是字符串平均长度
         * 空间复杂度: O(n*k) - 需要存储所有字符串和排序后的键
         *
         * @param strs 输入字符串数组
         * @return 分组后的字母异位词列表
         */
        public static List<List<String>> groupAnagrams2(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                // 将字符串转换为字符数组并排序
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                // 排序后的字符串作为分组键
                String key = new String(charArray);

                // 如果键不存在则创建新列表
                if (!map.containsKey(key)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(key, list);
                } else {
                    // 键存在则添加到对应列表
                    map.get(key).add(str);
                }
            }
            // 返回所有分组结果
            return new ArrayList<>(map.values());
        }

        /**
         * 官方Map实现 - 使用getOrDefault简化代码
         *
         * 改进点：
         * 1. 使用getOrDefault方法简化列表获取逻辑
         * 2. 代码更简洁，但功能与自定义实现相同
         *
         * 时间复杂度: O(n*klogk)
         * 空间复杂度: O(n*k)
         *
         * @param strs 输入字符串数组
         * @return 分组后的字母异位词列表
         */
        public static List<List<String>> groupAnagrams1(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                // 使用getOrDefault简化列表获取逻辑
                // 从map中获取该key对应的列表，如果不存在则创建一个新列表
                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<>(map.values());
        }

        /**
         * 官方计数法实现 - 通过字符频率统计作为键
         *
         * 方法思路：
         * 1. 统计每个字符串中字符出现频率
         * 2. 将频率统计转换为标准键格式（如a1b2c3）
         * 3. 使用频率键进行分组
         *
         * 时间复杂度: O(n*(k+26)) - 优于排序法，特别是长字符串场景
         * 空间复杂度: O(n*(k+26)) - 需要存储频率键
         *
         * @param strs 输入字符串数组
         * @return 分组后的字母异位词列表
         */
        public List<List<String>> groupAnagrams3(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                // 初始化26字母计数数组
                int[] counts = new int[26];
                int length = str.length();
                // 统计每个字符出现次数
                for (int i = 0; i < length; i++) {
                    counts[str.charAt(i) - 'a']++;
                }

                // 构建频率键字符串,将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 26; i++) {
                    if (counts[i] != 0) {
                        sb.append((char) ('a' + i)); // 字母
                        sb.append(counts[i]);        // 出现次数
                    }
                }
                String key = sb.toString();

                // 分组存储,从map中获取该key对应的列表，如果不存在则创建一个新列表
                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<>(map.values());
        }


    }

}
