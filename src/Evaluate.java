import java.util.Stack;

/**
 * P80.�������ʽ��ֵ,֧��+ - * / ()��������֧��int float double����ֵ,֧�ֳ�0��⡣
 * ԭ����������Ҫ��̫���ϸ����������Ż���
 */
public class Evaluate {

	/**
	 * ��������������൱����׺���ʽ��������ֱ�Ӵ���Ҳ����ת�ɺ�׺���ʽ������������
	 */
	public static double calValue(String[] arithmeticExpression) {
		Stack<String> ops = new Stack<String>();// ������ջ
		Stack<Double> vals = new Stack<Double>();// ������ջ
		// ע��forѭ�������󣬲�����ջ���ܻ��в�����û��ִ�У�Ϊ�˴��������������ǿ��԰�ԭʼ���������ʽ���˼���һ�Կո�
		for (String exp : arithmeticExpression) {
			if (exp.equals("+") || exp.equals("-")) {// ��ǰ��������+��-����ôҪ����֮ǰ����(֮���+-*/��������ִ����
				while (ops.isEmpty() == false && ops.peek().equals("(") == false) {
					String preOp = ops.pop();
					if (preOp.equals("*")) {
						vals.push(vals.pop() * vals.pop());
					} else if (preOp.equals("/")) {
						Double temp = vals.pop();
						if (Math.abs(temp) < 1e-30) {// �жϳ�0�쳣
							throw new ArithmeticException("/ by zero");
						}
						vals.push(vals.pop() / temp);
					} else if (preOp.equals("+")) {
						vals.push(vals.pop() + vals.pop());
					} else if (preOp.equals("-")) {
						Double temp = vals.pop();
						vals.push(vals.pop() - temp);
					}
				}
				ops.push(exp);
			} else if (exp.equals("*") || exp.equals("/")) {// ��ǰ��������*��/����ôҪ��֮ǰ��*��/��ִ����
				while (ops.isEmpty() == false && (ops.peek().equals("*") || ops.peek().equals("/"))) {
					String preOp = ops.pop();
					if (preOp.equals("*")) {
						vals.push(vals.pop() * vals.pop());
					} else if (preOp.equals("/")) {
						Double temp = vals.pop();
						if (Math.abs(temp) < 1e-30) {
							throw new ArithmeticException("/ by zero");
						}
						vals.push(vals.pop() / temp);
					}
				}
				ops.push(exp);
			} else if (exp.equals("(")) {// ��ǰ��������(��ֱ��ѹ�������ջ
				ops.push(exp);
			} else if (exp.equals(")")) {// ��ǰ��������)����ôҪ������ƥ���(֮������в�������ִ����
				while (ops.peek().equals("(") == false) {
					String preOp = ops.pop();
					if (preOp.equals("*")) {
						vals.push(vals.pop() * vals.pop());
					} else if (preOp.equals("/")) {
						Double temp = vals.pop();
						if (Math.abs(temp) < 1e-30) {
							throw new ArithmeticException("/ by zero");
						}
						vals.push(vals.pop() / temp);
					} else if (preOp.equals("+")) {
						vals.push(vals.pop() + vals.pop());
					} else if (preOp.equals("-")) {
						Double temp = vals.pop();
						vals.push(vals.pop() - temp);
					}
				}
				ops.pop();
			} else {// �����ǰ���ǲ������Ǿ��ǲ�������ѹ�������ջ
				vals.push(Double.parseDouble(exp));
			}
		}
		return vals.pop();
	}

	public static void main(String[] args) {
		System.out.println(Evaluate.calValue("( 1 + ( 2.0 + 3.0 ) * 4 * -5 )".split(" ")));
		System.out.println(Evaluate.calValue("( 11 + ( 2.0 + 3.0 ) / ( 0 - 0.1 ) * -5 )".split(" ")));
	}
}
