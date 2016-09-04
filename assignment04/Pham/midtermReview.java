package assignment04.Pham;

import java.io.FileNotFoundException;
import java.util.Comparator;

import java.util.Scanner;

import assignment07.BalancedSymbolChecker;

public class midtermReview
{

	public static void main(String[] args)
	{
		
		BalancedSymbolChecker checker = new BalancedSymbolChecker();
		try
		{
			System.out.println(checker.checkFile("Class1.java"));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean binarySearch_1(int[] array, int item)
	{
		int low = 0;
		int high = array.length - 1;
		int mid;
		while (low <= high)
		{
			mid = (high - low) / 2;
			if (array[mid] < item)
				high = mid - 1;
			else if (array[mid] > item)
				low = mid + 1;
			else
				return true;
		}
		return false;
	}

	public static boolean binarySearch_2(int[] arr, int item)
	{
		if (arr.length == 0)
			return false;
		int low = 0;
		int high = arr.length - 1;
		int mid;
		while (low <= high)
		{
			mid = (high - low) / 2;
			if (arr[mid] < item)
				low = mid + 1;
			else
				high = mid;
		}
		if (arr[low] == item)
			return true;
		return false;

	}

	public boolean binarySearchRecursive(int[] arr, int item)
	{
		return binarySearch(arr, item, 0, arr.length - 1);

	}

	private boolean binarySearch(int[] arr, int item, int low, int high)
	{
		if (low > high)
			return false;
		int mid = (high - low) / 2;
		if (arr[mid] == item)
			return true;
		else if (arr[mid] < item)
			return binarySearch(arr, item, mid + 1, high);
		else
			return binarySearch(arr, item, low, mid - 1);
	}

	public static <T> boolean isSorted(T[] list, Comparator<? super T> comparator)
	{
		int i = 0;
		while (i < list.length)
		{
			if (comparator.compare(list[i], list[i + 1]) > 0)
				return false;
		}
		return true;

	}

	public static void insertionSort(int[] array)
	{
		for (int i = 1; i < array.length; i++)
		{
			int index = array[i];
			int j = i;
			while (j > 0 && array[j - 1] > index)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = index;
		}
	}

}
