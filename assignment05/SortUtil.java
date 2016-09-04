package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Class that has methods that are used to use mergesort and quicksort to sort data.
 * 
 * @author Hannah Potter and Minh Pham
 */
public class SortUtil
{
	/**
	 * If a sublist is less than or equal to the threshold, mergesort will switch to insertion sort to
	 * sort that sublist. 
	 */
	static int threshold = 0;
	
	/**
	 * Sets the value of the threshold. Mergesort will switch to insertion sort when the sublist is
	 * less than or equal to the threshold. 
	 * @param threshold
	 */
	public static void setThreshold(int threshold) 
	{
		SortUtil.threshold = threshold;
	}

	/**
	 * Used to decide which pivot method will be used in the quicksort method. This uses the median of three
	 * randomly chosen elements from the sublist.
	 */
	private static int MEDIAN_OF_THREE = 0;
	
	/**
	 * Used to decide which pivot method will be used in the quicksort method. This uses the element at the middle
	 * index of the sublist.
	 */
	private static int MIDDLE = 1;
	
	/**
	 * Used to decide which pivot method will be used in the quicksort method. This uses a randomly chosen 
	 * element from the sublist.
	 */
	private static int RANDOM = 2;
	
	/**
	 * Used in the choosePivot helper method to find a random index within a sublist.
	 */
	private static Random rand = new Random();

	/**
	 * Driver method that uses mergesort to sort the elements in array into ascending order.
	 * 
	 * @param array the ArrayList that is to be sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 */
	public static <T> void mergesort(ArrayList<T> array, Comparator<? super T> comparator)
	{
		T[] tempArr = (T[])new Object[array.size()];
		mergeSortRecursive(array, comparator, tempArr, 0, array.size() - 1);
	}

	/**
	 * Method that uses mergesort to sort the elements in array into ascending order. This method
	 * will switch to sorting by insertion sort when the sublist's size is less than or equal 
	 * to the threshold member variable of the SortUtil class.
	 * 
	 * @param array the ArrayList that is to be sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 * @param tempArr an array that is the same size as the ArrayList array
	 * @param left the left-most index of the sublist
	 * @param right the right-most index of the sublist
	 */
	private static <T> void mergeSortRecursive(ArrayList<T> array, Comparator<? super T> comparator, T[] tempArr, int left,int right)
	{
		if (left >= right)
		{
			return;
		}
		else if (right - left + 1 <= threshold)
		{
			insertionSort(array, comparator, left, right);
		}
		// arrays of size 1 are already sorted
		//else if (left >= right)
			//return;
		else
		{
			int mid = (left + right) / 2;
			mergeSortRecursive(array, comparator, tempArr, left, mid);
			mergeSortRecursive(array, comparator, tempArr, mid + 1, right);
			merge(array, comparator, tempArr, left, mid + 1, right);
		}

	}

	/**
	 * Merges two sorted sublists from the ArrayList arr (sorts the two lists together into the ArrayList)
	 * 
	 * @param arr the ArrayList that is to be sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 * @param tempArr an array that is the same size as the ArrayList array
	 * @param start the left-most index of the sublists
	 * @param mid the middle index of the sublists
	 * @param end the right-most index of the sublists
	 */
	private static <T> void merge(ArrayList<T> arr, Comparator<? super T> comparator, T[] tempArr, int start, int mid, int end)
	{
		int newLength = end - start + 1;
		//ArrayList<T> tmpArray = new ArrayList<>(newLength);
		//T[] tempArr = (T[])new Object[newLength];
		int i1 = start, i2 = mid;
		int j = 0;
		while (i1 < mid && i2 <= end)
		{
			if (comparator.compare(arr.get(i1), arr.get(i2)) < 0)
			{
				//tmpArray.add(j, arr.get(i1));
				tempArr[j] = arr.get(i1);
				i1++;
			}
			else
			{
				//tmpArray.add(j, arr.get(i2));
				tempArr[j] = arr.get(i2);
				i2++;
			}
			j++;
		}
		// copy anything left over from larger half to temp
		while (i1 < mid)
		{
			//tmpArray.add(j, arr.get(i1));
			tempArr[j] = arr.get(i1);
			i1++;
			j++;
		}
		while (i2 <= end)
		{
			//tmpArray.add(j, arr.get(i2));
			tempArr[j] = arr.get(i2);
			i2++;
			j++;
		}
		// copy temp over to arr
		for (j = 0; j < newLength; j++)
		{
			//arr.set(start + j, tmpArray.get(j));
			arr.set(start + j, tempArr[j]);
		}
	}

	/**
	 * Driver method that uses quicksort to sort array in ascending order.
	 * 
	 * @param array the ArrayList to be sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 */
	public static <T> void quicksort(ArrayList<T> array, Comparator<? super T> comparator)
	{
		quickSortRecursive(array, comparator, 0, array.size() - 1);
	}

	/**
	 * Method that uses quicksort to sort array in ascending order.
	 * 
	 * @param arr the ArrayList to be sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 * @param left the left-most index of the sublist
	 * @param right the right-most index of the sublist
	 */
	private static <T> void quickSortRecursive(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right)
	{
		// arrays of size 1 are already sorted
		if (left >= right)
			return;
		else
		{
			int pivot_index = partition(arr, comparator, left, right);
			quickSortRecursive(arr, comparator, left, pivot_index - 1);
			quickSortRecursive(arr, comparator, pivot_index + 1, right);
		}
	}

	/**
	 * Sorts all elements greater than a chosen pivot from the sublist to the right of the pivot and all 
	 * elements less than the chosen pivot to the left of the pivot (the pivot will be in-between the 
	 * left and right sides). Returns the index of the pivot after sorting.
	 * 
	 * @param arr the ArrayList being sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 * @param left the left-most index of the sublist
	 * @param right the right-most index of the sublist
	 * @return the index of the pivot after sorting
	 */
	private static <T> int partition(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right)
	{
		// Chosen pivot and its index
		int pivotIndex = choosePivot(arr, comparator, RANDOM, left, right);
		T pivot = arr.get(pivotIndex);
		
		// Swap the pivot with the last item in the array section
		swap(arr, pivotIndex, right);
		pivotIndex = right;
		right = right - 1;
		
		// Continue until left and right stepping cross each other
		while (left < right)
		{
			// Step from left to right until find an item greater than the pivot
			while (comparator.compare(arr.get(left), pivot) < 0 && left < right)
			{
				left++;
			}
			// Step from right to left until find an item less than the pivot
			while (comparator.compare(arr.get(right), pivot) > 0 && left < right)
			{
				right--;
			}
			// swap items
			if (left < right)
			{
				swap(arr, left, right);
				right--;
				left++;
			}
		}
		
		// swap pivot with left stepping item (step left over until left > pivot as long
		// as left is less than or equal to the pivotIndex
		while (comparator.compare(arr.get(left), pivot) < 0 && left <= pivotIndex)
		{
			left++;
		}
		swap(arr, left, pivotIndex);
		pivotIndex = left;
		
		// return the index of the pivot
		return pivotIndex;
	}


	/**
	 * Method used to determine the pivot for quicksort. The pivot will be chosen by choosing a random index 
	 * within the sublist if pivotCase is 2. The pivot will be chosen by choosing the middle index
	 * within the sublist if pivotCase is 1. The pivot will be chosen by choosing the median of three randomly
	 * chosen indices within the sublist if pivotCase is 0. If pivotCase is not one of these, it is invalid and
	 * the method will return -1.
	 * 
	 * @param arr the ArrayList that is being sorted
	 * @param comparator the Comparator for the elements in the ArrayList (cannot be null)
	 * @param pivotCase the method used to determine the pivot 
	 * @param left the left-most element of the sublist
	 * @param right the right-most element of the sublist
	 * @return index of the chosen pivot or -1 if the pivotCase is invalid
	 */
	private static <T> int choosePivot(ArrayList<T> arr, Comparator<? super T> comparator, int pivotCase, int left, int right)
	{
		switch (pivotCase)
		{
		// pivot chosen using median of three
		case 0: 
			int r1 = rand.nextInt(right - left + 1) + left;
			int r2 = rand.nextInt(right - left + 1) + left;
			int r3 = rand.nextInt(right - left + 1) + left;
			
			int medianOfThree = r3;
			if (comparator.compare(arr.get(r1), arr.get(r2))>0 && comparator.compare(arr.get(r1), arr.get(r3))<0)
				medianOfThree = r1;
			else if(comparator.compare(arr.get(r2), arr.get(r1))>0 && comparator.compare(arr.get(r2), arr.get(r3))<0)
				medianOfThree = r2;
			return medianOfThree;
		// pivot chosen using middle element 
		case 1:
			int mid = ((right - left)/2) + left;
			return mid;
		// pivot chosen using randomly selected element	
		case 2:
			return rand.nextInt(right - left + 1) + left;	
		}
		
		return -1;
	}

	/**
	 * Swaps the elements at the two indices (they must be in arr)
	 * 
	 * @param arr the ArrayList of the elements
	 * @param i the index of the first element that needs to be swapped with the second element
	 * @param j the index of the second element that needs to be swapped with the first element
	 */
	private static <T> void swap(ArrayList<T> arr, int i, int j)
	{
		T temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
		
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in ascending order. If size is less than or
	 * equal to 0, returns an empty ArrayList.
	 * 
	 * @param size the desired size of the ArrayList to be generated 
	 * @return an ArrayList of int 1 through size in ascending order
	 */
	public static ArrayList<Integer> generateBestCase(int size)
	{
		ArrayList<Integer> array = new ArrayList<>();
		for (int i = 1; i <= size; i++)
		{
			array.add(i);
		}
		return array;

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered).
	 * If size is less than or equal to 0, returns an empty ArrayList.
	 * 
	 * @param size desired size of the ArrayList to be generated
	 * @return an ArrayList of int 1 through size in permuted order
	 */
	public static ArrayList<Integer> generateAverageCase(int size)
	{
		ArrayList<Integer> array = generateBestCase(size);
		Collections.shuffle(array);
		return array;

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in descending order. If size is 
	 * less than or equal to 0, returns an empty ArrayList.
	 * 
	 * @param size desired size of the ArrayList to be generated
	 * @return an ArrayList of int 1 through size in descending order
	 */
	public static ArrayList<Integer> generateWorstCase(int size)
	{

		ArrayList<Integer> array = new ArrayList<>();
		for (int i = size; i >= 1; i--)
		{
			array.add(i);
		}
		return array;

	}

	/**
	 * Uses insertion sort to sort the sublist from list.
	 * 
	 * @param list the ArrayList being sorted
	 * @param comparator the Comparator for the elements in ArrayList (cannot be null)
	 * @param left the left-most element in the sublist
	 * @param right the right-most element in the sublist
	 */
	private static <T> void insertionSort(ArrayList<T> list, Comparator<? super T> comparator, int left, int right)
	{
		for (int i = left; i <= right; i++)
		{
			T index = list.get(i);
			int j = i+1;
			while (j > 0 && comparator.compare(list.get(j - 1), index) > 0)
			{
				list.set(j, list.get(j - 1));
				list.set(j - 1, index);
				j--;
			}
		}
	}

}

