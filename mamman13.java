import java.util.*;
/**
 * Compares 2 versions of Quick Sort Algorithm.
 *
 * @author Dor Elbaz
 * @version 24.12.22
 */
public class mamman13
{
    private static int n, factOfN, counter, trackMax, trackMin;
    private static int totalComparisons1, totalComparisons2;
    private static int avgCaseComparisons1, avgCaseComparisons2, bestCaseComparisons1, bestCaseComparisons2, worstCaseComparisons1, worstCaseComparisons2;
    private static int bestCases1, worstCases1, bestCases2, worstCases2, averageCases1, averageCases2;
    private static String recordData;
    private static boolean isBestCase, isWorstCase;    

    
    public static void main(String [] args)
    {
        initialiseParameters();
       
        analiseQuickSort();
        
        // Prints the result of each permutation.
        System.out.println("Permutation:" + "\tAlgorithm 1 (B/W/A Case):" + "\tCounter 1:" + "\tAlgorithm 2 (B/W/A Case):" + "\tCounter 2:");
        System.out.println(recordData);
        
        // Prints the results of the analysis.
        System.out.println();
        System.out.println("Total Quicksort 1 comparisons: " + totalComparisons1);
        System.out.println("Total Best Cases: " + bestCases1 + "\t\tTotal Comparisons: " + bestCaseComparisons1);
        System.out.println("Total Worst Cases: " + worstCases1 + "\t\tTotal Comparisons: " + worstCaseComparisons1);
        System.out.println("Total Average Cases: " + averageCases1 + "\t\tTotal Comparisons: " + avgCaseComparisons1);
        
        System.out.println();
        System.out.println("Total Quicksort 2 comparisons: " + totalComparisons2);
        System.out.println("Total Best Cases: " + bestCases2 + "\t\tTotal Comparisons: " + bestCaseComparisons2);
        System.out.println("Total Worst Cases: " + worstCases2 + "\t\tTotal Comparisons: " + worstCaseComparisons2);
        System.out.println("Total Average Cases: " + averageCases2 + "\t\tTotal Comparisons: " + avgCaseComparisons2);
        
    }    
      
    /** 
     * Assigns to the variables an initial value. 
     */
    private static void initialiseParameters()
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter n:");   
        n = input.nextInt(); 
        
        // Represents the total permutations of a given n.
        factOfN = factorial(n);
               
        // Comparison counter.
        counter = 0; 
        
        // Records the results by the 2 versions
        recordData = "";
        
        // Keeps track whether a given permutation represents the best or worst cases.
        isBestCase = true;
        isWorstCase = true;
        
        // Keeps track of the total comparisons executed throughout this program.
        totalComparisons1 = 0;
        totalComparisons2 = 0;
        
        // Keeps track of the average case comparisons done by the 2 versions.
        avgCaseComparisons1 = 0;
        avgCaseComparisons2 = 0;
        
        // Keeps track of the best case comparisons done by the 2 versions.
        bestCaseComparisons1 = 0;
        bestCaseComparisons2 = 0;
        
        // Keeps track of the worst case comparisons done by the 2 versions.
        worstCaseComparisons1 = 0;
        worstCaseComparisons2 = 0;
        
        // Counts the worst cases of the 2 versions.
        worstCases1 = 0;
        worstCases2 = 0;
        
        // Counts the average cases of the 2 versions.
        averageCases1 = 0;
        averageCases2 = 0;
        
        // Counts the best cases of the 2 versions.
        bestCases1 = 0;       
        bestCases2 = 0;       
        
        // Helps in determining whether a permutation represents a worst case in Quicksort 2.
        trackMax = n-1;
        trackMin = 2;        
    }
    
      
    /** 
     * Analises the 2 versions of Quicksort.
     */
        private static void analiseQuickSort()
    {
        int j = 1;
        int[] temp = new int[n];
        int[] temp1 = new int[n];
        int[] temp2 = new int[n];
        
        // Initialises temp, which is used to generate the permutations.
        for (int i = 0; i < n; i++)
        {
            temp[i] = j;
            j++;
        }
        
        j = 0;
        while (j < factOfN)
        {
            nextPermutation(temp);
            
            // Copies the current permutation to temp1 and temp2, which will be worked on by Quicksort 1 and 2 respectively.
            for (int i = 0; i < n; i++)
            {
                temp1[i] = temp[i];
                temp2[i] = temp[i];
            }
            
            // Records the current permutation.
            recordData += Arrays.toString(temp) + "\t";
            
            // Determines the case the current permutation represents for the first Quicksort.
            detectCase1(temp1);
            
            // Sorts the current permutation.
            Quicksort1(temp1, 0, n-1);
            
            // Counting the total comparisons and the comparisons of each case for the first Quicksort.
            totalComparisons1 += counter;
            if (isBestCase) {bestCases1++; bestCaseComparisons1 += counter;}
            else if (isWorstCase) {worstCases1++; worstCaseComparisons1 += counter;}
            else {averageCases1++; avgCaseComparisons1 += counter;}
            
            // Records the case represented by the current permutation for the first Quicksort.
            if (isBestCase || isWorstCase)
            {
                recordData += isBestCase + "/" + isWorstCase + "/" + false + "\t\t";
            }
            else
            {
                recordData += isBestCase + "/" + isWorstCase + "/" + true + "\t\t";
            }
            // Records the comparisons executed on the current permutation for the first Quicksort.
            recordData += counter + "\t\t";
            
            // Resets parameters
            isBestCase = true;
            isWorstCase = true;
            counter = 0;           
            
            // Sorts the current permutation.
            Quicksort2(temp2, 0, n-1);
            
            // Counting the total comparisons and the comparisons of each case for the second Quicksort.
            totalComparisons2 += counter;
            if (isBestCase) {bestCases2++; bestCaseComparisons2 += counter;}
            else if (isWorstCase) {worstCases2++; worstCaseComparisons2 += counter;}
            else {averageCases2++; avgCaseComparisons2 += counter;}
            
            // Records the case represented by the current permutation for the second Quicksort.
            if (isBestCase || isWorstCase)
            {
                recordData += isBestCase + "/" + isWorstCase + "/" + false + "\t\t\t";
            }
            else
            {
                recordData += isBestCase + "/" + isWorstCase + "/" + true + "\t\t\t";
            }
            // Records the comparisons executed on the current permutation for the second Quicksort.
            recordData += counter + "\n";
               
            // Resets parameters for next permutation.
            isBestCase = true;
            isWorstCase = true;
            counter = 0;
            trackMax = n-1;
            trackMin = 2;
            
            j++;
        }
    }
    
          
    /** 
     * Determines the case of a given permutation for the first Quicksort. 
     * @param B Current permutation 
     */
    private static void detectCase1(int[] B)
    {
       Best_Scenario1(B);
       Worst_Scenario1(B);
    }
    
          
    /** 
     * Sorts an array (This is the standard version of Quicksort). 
     * @param A Array to be sorted
     * @param left Left most index
     * @param right Right most index
     */
    private static void Quicksort1(int[] A, int left, int right)
    {
        if (left < right)
        {
            int pivot = Partition1(A,left,right);            
            Quicksort1(A,left,pivot-1);
            Quicksort1(A,pivot+1,right);
        }
    }
    
          
    /** 
     * Divides an array into 2 sections according to a pivot (right most value), smaller than pivot and bigger than pivot.
     * @param A Array to be sorted
     * @param l Left most index
     * @param r Right most index
     * @return Pivot index after division
     */
    private static int Partition1(int[] A, int l, int r)
    {
        int x = A[r];
        int i = l-1;
        for (int j = l; j < r; j++)
        {
            if (A[j] < x)
            {
                i++;
                Swap(A,i,j);
            }
            counter++;
        }
        Swap(A,i+1,r);
        return i+1;
    }    
    
          
    /** 
     * Determines the case of a given permutation for the second Quicksort. 
     * @param A Section done by partition 2
     * @param p Pivot index
     * @param l Left most index
     * @param r Right most index
     * 
     */
    private static void detectCase2(int[] A, int p, int l, int r)
    {
       /*
        * We examine each section done by partition 2. If the given permutation represents the best case, each pivot of each call should be in the middle of A, thus
        * meaning A has been divided into 2 equal sub arrays.
        * If the given permutation represents the worst case, each pivot of each call should be the second min or max value in A, thus meaning A has been divided
        * into 2 sections, a section containing the max or min value and the other section containing the rest of the values.
          */
       if ((r + l)/2 + 1 != A[p])
       {
           isBestCase = false;
       }
       if (A[p] != trackMin && A[p] != trackMax)
       {
           isWorstCase = false;
       }
       else
       {
           if (A[p] == trackMin) {trackMin += 2;}
           if (A[p] == trackMax) {trackMax -= 2;}
       }
    }
    
     /** 
     * Sorts an array. 
     * @param A Array to be sorted
     * @param left Left most index
     * @param right Right most index
     */
    private static void Quicksort2(int[] A, int left, int right)
    {
        if (right - left + 1 >= 3)
        {
            int pivot = Partition2(A,left,right); 
            detectCase2(A, pivot, left, right);
            Quicksort2(A,left,pivot-1);
            Quicksort2(A,pivot+1,right);
        }        
        if (right - left + 1 == 2 && A[left] > A[right])
        {
            Swap(A,left,right);
            counter++;
        }
    }
    
    /** 
     * Divides an array into 2 sections according to a pivot (that is not the max or min value in A), smaller than pivot and bigger than pivot.
     * @param A Array to be sorted
     * @param l Left most index
     * @param r Right most index
     * @return Pivot index after division
     */
    private static int Partition2(int[] A, int l, int r)
    {
        int p = selectPivot(A, r);
        Swap(A,p,r);
        return Partition1(A,l,r);
    }
    
    /** 
     * Selects the pivot index. Examines the 3 right most values in A and selects the index of the value that is not the max or min between the 3.
     * @param A Array to be sorted
     * @param end Right most index
     * @return Pivot index
     */
    private static int selectPivot(int[] A, int end)
    {
        int pivotIndex = end, max = end, min = end;
        
        // First, we determine which indices hold the max and min value among the 3.
        for (int i = 1; i < 3; i++)
        {
            if (A[end-i] > A[max])
            {
                max = end-i;
            }
            if (A[end-i] < A[min])
            {
                min = end-i;
            }
        }
        
        // Determines the index of the value that is not max or min value.
        for (int i = 1; i < 3; i++)
        {
            if (end-i != max && end-i != min)
            {
                pivotIndex = end-i;
            }
        }

        return pivotIndex;
    }
    
    /** 
     * Swaps 2 values in A.
     * @param A Array 
     */
    private static void Swap(int[] A, int x, int y)
    {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;       
    }
    
    /** 
     * Calculates the factorial of n.
     * @param k Represents n.
     * @return Factorial of n.
     */
    private static int factorial(int k)
    {
        if (k == 1)
        {
            return 1;
        }
        return factorial(k-1)*k;
    }
    
    /** 
     * Generates the next permutation.
     * @param A Current permutation
     */
    static void nextPermutation(int[] A)
    {
        int n = A.length, i, j;
    
        // Find for the pivot element.
        // A pivot is the first element from
        // end of sequence which doesn't follow
        // property of non-increasing suffix.
        for (i = n - 2; i >= 0; i--) 
        {
          if (A[i] < A[i + 1]) 
          {
            break;
          }
        }
    
        // Check if pivot is not found.
        if (i < 0) 
        {
          Reverse(A, 0, A.length - 1);
        }            
        else // If pivot is found.
        {   
          // Find for the successor of pivot in suffix.
          for (j = n - 1; j > i; j--) 
          {
            if (A[j] > A[i]) 
            {
              break;
            }
          }
    
          // Swap the pivot and successor.
          Swap(A, i, j);
    
          // Minimise the suffix part.
          Reverse(A, i + 1, A.length - 1);
        }
    }

    /** 
     * Reverses an array.
     * @param A Array to be reversed
     * @param start Left most index
     * @param end Right most index
     */
    static void Reverse(int[] A, int start, int end)
    {
        while (start < end) 
        {
          Swap(A, start, end);
          start++;
          end--;
        }
    }  
    
    /** 
     * Checks whether the given permutation represents the best case for Quicksort 1.
     * @param B Current permutation
     */
    private static void Best_Scenario1(int[] B)
    {
        int j = 1;
        int[] A = new int[B.length];
        
        // Initialises A to {1,2,...,n}
        for (int i = 0; i < n; i++)
        {
            A[i] = j;
            j++;
        }
        
        Best_Scenario1(A,0,A.length-1);
        
        for (int i = 0; i < A.length; i++)
        {
            if (A[i] != B[i])
            {
                isBestCase = false;
                return;
            }
        }
    }
    
    /** 
     * Reorganise the values of A to represent the best case of a given n for Quicksort 1 (based on the pseudo code from mamman 12 question 3). 
     * @param A Array to be reorganised
     * @param l Left most index
     * @param r Right most index
     */
    private static void Best_Scenario1(int[] A, int l, int r)
    {
        int mid = (l+r)/2;
        
        if (r - l > 1)
        {
            Best_Scenario1(A,l,mid-1);
            Best_Scenario1(A,mid+1,r);
            Swap(A,mid,r);
        }        
    }
    
    /** 
     * Checks whether the given permutation represents the worst case for Quicksort 1. (I.E if the permutation is already sorted)
     * @param A Current permutation
     */
    private static void Worst_Scenario1(int[] A)
    {
        for (int i = 0; i < A.length-1; i++)
        {
            if (Math.abs(A[i] - A[i+1]) != 1)
            {
                isWorstCase = false;
                return;
            }
        }
    }
}
