package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	BinarySearchTree<Integer> tree;
	@Before
	public void setUp() throws Exception {
		tree = new BinarySearchTree<Integer>();
	}

	@Test
	public void testBinarySearchTree() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
	}
	@Test(expected = NullPointerException.class)
	public void testAddNullItem()
	{
		tree.add(null);
	}
	@Test
	public void testAddTryDuplicate() {
		assertTrue(tree.add(7));
		assertFalse(tree.add(7));
	}
	
	@Test
	public void testAddNoDuplicates(){
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(8));
		assertTrue(tree.add(20));
		assertTrue(tree.add(1));
		assertTrue(tree.add(4));
		
		assertEquals(6, tree.size());
		tree.writeDot("addNoDuplicatesTest.dot");
	}
	
	@Test
	public void testAddTryDuplicatesForBiggerTree()
	{
		assertTrue(tree.add(4));
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(20));
		assertTrue(tree.add(8));
		assertTrue(tree.add(1));
		// try to add a duplicate element
		assertFalse(tree.add(1));
		
		assertEquals(6, tree.size());
		tree.writeDot("addWithDuplicatesTest.dot");
	}
	@Test(expected = NullPointerException.class)
	public void testAddAllNullItem()
	{		
		tree.addAll(null);
		
	}
	@Test
	public void testAddAllWhenAllDuplicates() {
		ArrayList<Integer> collection = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
		{
			collection.add(i);
			tree.add(i);
		}
		
		assertFalse(tree.addAll(collection));
		assertEquals(10, tree.size());
		tree.writeDot("addAllWhenAllDuplicatesTest.dot");
	}
	
	@Test
	public void testAddAllWhenNoDuplicates()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		for (int i = 2; i < 21; i+=2)
		{
			collection.add(i);
			tree.add(i - 1);
		}
		
		assertEquals(10, tree.size());
		assertTrue(tree.addAll(collection));
		assertEquals(20, tree.size());
		tree.writeDot("addAllWhenNoDuplicatesTest.dot");
	}

	@Test
	public void testClearOnEmpty() {
		// shouldn't throw an exception
		tree.clear();
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
	}
	
	@Test 
	public void testClearOnNonEmpty()
	{
		assertTrue(tree.add(4));
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(20));
		assertTrue(tree.add(8));
		assertTrue(tree.add(1));
		
		assertEquals(6, tree.size());
		
		tree.clear();
		
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty()); 
	}
	@Test(expected = NullPointerException.class)
	public void testContainsNull()
	{
		tree.contains(null);
	}
	@Test
	public void testContainsOnEmpty()
	{
		// shouldn't throw an exception
		assertFalse(tree.contains(3));
	}
	@Test
	public void testContainsNotInTree() {
		assertTrue(tree.add(4));
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(20));
		assertTrue(tree.add(8));
		assertTrue(tree.add(1));
		
		assertFalse(tree.contains(21));
	}

	@Test
	public void testContainsInTree()
	{
		assertTrue(tree.add(4));
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(20));
		assertTrue(tree.add(8));
		assertTrue(tree.add(1));
		
		// at the root
		assertTrue(tree.contains(4));
		// at an inner-node
		assertTrue(tree.contains(7));
		// at a leaf
		assertTrue(tree.contains(20));
	}
	
	@Test
	public void testContainsAllEmpty() {
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		
		// shouldn't throw an exception
		assertFalse(tree.containsAll(collection));
	}
	@Test(expected = NullPointerException.class)
	public void testContainsAllNull()
	{
		tree.containsAll(null);
	}
	@Test
	public void testContainsAllNonEmpty()
	{
		tree.add(5);
		tree.add(7);
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(5);
		collection.add(7);
		assertTrue(tree.containsAll(collection));
	}
	@Test
	public void testContainsAllNonEmptyHasItemDupplicatesInCollecction()
	{
		tree.add(5);
		tree.add(7);
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(5);
		assertFalse(tree.containsAll(collection));
	}

	@Test(expected = NoSuchElementException.class)
	public void testFirstEmpty()
	{
		tree.first();
	}
	@Test
	public void testFirst() {
		tree.add(5);
		tree.add(4);
		assertEquals(4, (int) tree.first());
		tree.add(1);
		tree.add(9);
		assertEquals(1,(int)tree.first());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tree.isEmpty());
		
		assertTrue(tree.add(4));
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(20));
		assertTrue(tree.add(8));
		assertTrue(tree.add(1));
		
		assertFalse(tree.isEmpty());
		tree.clear();
		assertTrue(tree.isEmpty());
	}
	@Test(expected = NoSuchElementException.class)
	public void testLastEmpty()
	{
		tree.add(5);
		tree.add(4);
		tree.add(1);
		tree.add(9);
		
		tree.clear();
		
		tree.last();
	}
	@Test
	public void testLast() {
		tree.add(5);
		tree.add(4);
		assertEquals(5, (int) tree.last());
		tree.add(1);
		tree.add(9);
		assertEquals(9,(int)tree.last());
		
	}
	@Test(expected = NullPointerException.class)
	public void testRemoveNullItem()
	{
		tree.remove(null);
	}
	
	@Test
	public void testRemoveWithLeaf()
	{
		tree.add(4);
		tree.add(5);
		tree.add(6);
		
		assertTrue(tree.remove(6));
		
		tree.writeDot("treeRemoveWithLeafTest.dot");
	}
	
	@Test
	public void testRemoveWithOneChild()
	{
		tree.add(4);
		tree.add(5);
		tree.add(6);
		
		assertTrue(tree.remove(5));
		
		tree.writeDot("treeRemoveWithOneChild.dot");
	}
	@Test
	public void testRemoveNodeWithTwoChildren() {
		tree.add(5);
		tree.add(4);
		tree.add(1);
		tree.add(9);
		
		assertTrue(tree.remove(5));
		assertFalse(tree.remove(10));
		
		tree.writeDot("treeRemoveWithTwoChildrenTest.dot");
	}
	@Test(expected = NullPointerException.class)
	public void testRemoveAllNullItem()
	{
		tree.removeAll(null);
	}
	@Test
	public void testRemoveAll() {
		ArrayList<Integer> collection = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
		{
			collection.add(i);
			tree.add(i);
		}
		
		tree.removeAll(collection);
		assertTrue(tree.isEmpty());
		
		
		
	}

	@Test
	public void testSize() {
		tree.add(5);
		tree.add(4);
		tree.add(1);
		tree.add(9);
		
		assertEquals(4, tree.size());
		tree.clear();
		assertEquals(0, tree.size());
	}

	@Test
	public void testToArrayEmpty()
	{
		// shouldn't throw an exception
		tree.toArrayList();
	}
	@Test
	public void testToArrayListNonEmpty() {
		tree.add(5);
		tree.add(4);
		tree.add(1);
		tree.add(9);
		
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(9);
		ArrayList<Integer> arrList = tree.toArrayList();
		
		assertEquals(arrayList.get(0), arrList.get(0));
		assertEquals(arrayList.get(1), arrList.get(1));
		assertEquals(arrayList.get(2), arrList.get(2));
		assertEquals(arrayList.get(3), arrList.get(3));
		
	}

}
