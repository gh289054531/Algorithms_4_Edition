import java.util.Stack;

/**
 * P80.算术表达式求值,支持+ - * / ()操作符，支持int float double型数值,支持除0检测。
 * 原书代码对括号要求太过严格，这里有所优化。
 */
public class Evaluate {

	/**
	 * 这里的输入数组相当于中缀表达式，这里是直接处理，也可以转成后缀表达式处理计算更方便
	 */
	public static double calValue(String[] arithmeticExpression) {
		Stack<String> ops = new Stack<String>();// 操作符栈
		Stack<Double> vals = new Stack<Double>();// 操作数栈
		// 注意for循环结束后，操作符栈可能还有操作符没有执行，为了处理这个情况，我们可以把原始的算术表达式两端加上一对空格
		for (String exp : arithmeticExpression) {
			if (exp.equals("+") || exp.equals("-")) {// 当前操作符是+或-，那么要把它之前除了(之外的+-*/操作符都执行了
				while (ops.isEmpty() == false && ops.peek().equals("(") == false) {
					String preOp = ops.pop();
					if (preOp.equals("*")) {
						vals.push(vals.pop() * vals.pop());
					} else if (preOp.equals("/")) {
						Double temp = vals.pop();
						if (Math.abs(temp) < 1e-30) {// 判断除0异常
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
			} else if (exp.equals("*") || exp.equals("/")) {// 当前操作符是*或/，那么要把之前的*和/都执行了
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
			} else if (exp.equals("(")) {// 当前操作符是(，直接压入操作符栈
				ops.push(exp);
			} else if (exp.equals(")")) {// 当前操作符是)，那么要把与它匹配的(之间的所有操作符都执行了
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
			} else {// 如果当前不是操作符那就是操作数，压入操作数栈
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
