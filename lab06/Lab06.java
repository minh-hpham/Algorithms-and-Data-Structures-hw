package lab06;

import java.util.ArrayList;
import java.util.List;

public class Lab06 {
	
	public static void main(String[] args) {
		//Example of using TreeBuilder. Play around with it...can you make cool trees? 
		TreeBuilder<Character> charTreeBuilder = new TreeBuilder<>();
		BinarySearchNode<Character> root = charTreeBuilder.buildTree(getCharList("u192837"));
		charTreeBuilder.writeDot("uid.dot", root);
		
		// Use TreeBuilder to build an alphabet tree with the smallest height possible.
		TreeBuilder<Character> alphabetTreeBuilder = new TreeBuilder<>();
		String str = "abcdefghijklmnopqrstuvwyz";
		String fix = "mlnkojpiqhrgsfteudvcwbyaz";
		String fix1 = "mfsc";
		root = alphabetTreeBuilder.buildTree(getCharList(fix));
		charTreeBuilder.writeDot("alphabet.dot", root);
		
		TreeTester tester = new TreeTester();
		try{
			tester.testNode();
		} catch (TreeTester.TreeError treeError) {
			System.err.println(treeError.getMessage());
//			BinarySearchNode<Character> root = treeError.getRootNode(); // -- Use this line to debug your BSN. 
		}
		System.out.println("Done");
	}	
	
	private static List<Character> getCharList(String string) {
		List<Character> charList = new ArrayList<>();
		for(char c : string.toCharArray()) {
			charList.add(c);
		}
		return charList;
	}
}
