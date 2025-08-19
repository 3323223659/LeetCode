package CodeCaprice.Array;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/18/21:22
 * @Description: 58. 区间和
 * 链接：https://kamacoder.com/problempage.php?pid=1070
 */

/**
 * 给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
 * 输入：第一行输入为整数数组 Array 的长度 n，接下来 n 行，每行一个整数，表示数组的元素。随后的输入为需要计算总和的区间下标：a，b （b > = a），直至文件结束。
 * 输出：输出每个指定区间内元素的总和。
 * 输入示例
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * 0 1
 * 1 3
 * 输出示例
 * 3
 * 9
 * 提示信息
 * 数据范围：
 * 0 < n <= 100000
 */
public class KamaCoder_58 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数组大小：");
        int size = sc.nextInt();
        int[] vec = new int[size];    // 原始数组
        int[] p = new int[size];      // 前缀和数组
        int sum = 0;                  // 前缀和累加变量
        // 读取数组元素并计算前缀和
        for (int i = 0; i < size; i++) {
            System.out.println("请输入第" + (i + 1) + "个数字：");
            int number = sc.nextInt();
            vec[i] = number;          // 存储原始值
            sum += number;            // 累加当前元素
            p[i] = sum;               // 存储前缀和
        }
        while (true) {
            System.out.println("请输入索引区间(输入左区间按回车再输入右区间,退出输入-1)：");
            int left = sc.nextInt();
            int right = sc.nextInt();
            if (left == -1 || right == -1){
                break;
            }
            try {
                System.out.println(solution(vec, left, right));
                System.out.println(solution2(vec, p, left, right)); // 前缀和实现查找
            } catch (Exception e) {
                System.out.println("输入错误:" + e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * 暴力法计算区间和
     * 时间复杂度：O(n) - 每次查询需要遍历区间所有元素
     *
     * @param vec 原始数组
     * @param left 区间左边界（包含）
     * @param right 区间右边界（包含）
     * @return 区间元素和
     */
    private static int solution(int[] vec, int left, int right) {
        int sum = 0;
        // 遍历区间累加元素
        for (int i = left; i <= right; i++) {
            sum += vec[i];
        }
        return sum;
    }

    /**
     * 前缀和法计算区间和
     * 时间复杂度：O(1) - 通过预计算的前缀和数组快速查询
     *
     * @param vec 原始数组（实际未使用）
     * @param p 前缀和数组（p[i]表示vec[0..i]的和）
     * @param left 区间左边界（包含）
     * @param right 区间右边界（包含）
     * @return 区间元素和
     */
    private static int solution2(int[] vec, int[] p, int left, int right) {
        // 特殊处理左边界为0的情况
        if (left == 0) {
            return p[right];  // 直接返回右边界的前缀和
        }
        // 通用公式：sum[left,right] = p[right] - p[left-1]
        return p[right] - p[left - 1];
    }

}
