import java.util.Arrays;

/**
 * 解决2-SUM问题，时间复杂度O(N*logN)
 */
public class _2Sum {

	/**
	 * 规定输入没有重复
	 */
	public static int count(int[] a) {
		if (a == null || a.length < 2) {
			return 0;
		}
		Arrays.sort(a);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (Arrays.binarySearch(a, a[i] * -1) > i) {// 借助二分查找
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(_2Sum.count(new int[] { 3, -1, 0, 1, 2, 4 }));
	}

}
