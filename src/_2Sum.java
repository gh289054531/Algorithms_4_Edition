import java.util.Arrays;

/**
 * ���2-SUM���⣬ʱ�临�Ӷ�O(N*logN)
 */
public class _2Sum {

	/**
	 * �涨����û���ظ�
	 */
	public static int count(int[] a) {
		if (a == null || a.length < 2) {
			return 0;
		}
		Arrays.sort(a);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (Arrays.binarySearch(a, a[i] * -1) > i) {// �������ֲ���
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
