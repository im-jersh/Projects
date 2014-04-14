//
//  lab5
//  lab_05
//
//  Created by Joshua O'Steen on 10/1/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

// standard libraries
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// function prototypes
void merge(int* array, int l, int m, int r);
void mergeSort(int* array, int l, int r);
int linearSearch(int* array, int length, int key);
int binarySearch(int* array, int low, int high, int key);


int main(int argc, char * argv[])
{
    // variables
    FILE *filePtr = NULL;
    int arr_size = 0, i = 0, j = 0, key = 0, outcome = 0;
    clock_t start, end;
    double time = 0;
    
    if (argc < 8)
    {
        printf("\n\nNot enough command line arguments.\nPlease run again using the form:\n'./a.out key file1.txt file2.txt ... file6.txt'\n\n");
        return 1;
    }
    
    // print table header
    printf("%-18s%-18s%-18s%-18s", "LinearSearch 1", "MergeSort", "LinearSearch 2", "BinarySearch");
    
    // set key
    key = atoi(argv[1]);
    
    // loop through each txt file passed in from command line
    for (i = 2; i < argc ; i++)
    {
        // open file
        filePtr = fopen(argv[i], "r");
    
        // check file was opened
        if (filePtr != NULL)
        {
            // scan first number to determine size of array
            fscanf(filePtr, "%d", &arr_size);
            
            // declare array
            int *array = malloc(arr_size * sizeof(int)), *holdArrayPtr = array;
            
            // populate array
            for (j = 0; j < arr_size; j++)
            {
                fscanf(filePtr, "%d", (array + j));
            }
            
            // first linear search array for key, timed using standard time functions
            start = clock(); // set clock
            outcome = linearSearch(array, arr_size, key); // run search
            end = clock(); // end clock
            time = (double)(end - start)/CLOCKS_PER_SEC; // calculate run time for printing
            printf("\n%-18.8f", time); // print run time to table
            
            // merge sort unsorted array, timed using standard time functions
            start = clock(); // set clock
            mergeSort(array, 0, arr_size - 1); // run merge sort
            end = clock(); // end clock
            time = (double)(end - start)/CLOCKS_PER_SEC; // calculate run time for printing
            printf("%-18.8f", time); // print run time to table
            
            // second linear search array for key, timed using standard time functions
            start = clock(); // set clock
            outcome = linearSearch(array, arr_size, key); // run search
            end = clock(); // end clock
            time = (double)(end - start)/CLOCKS_PER_SEC; // calculate run time for printing
            printf("%-18.8f", time); // print run time to table
            
            // binary search array for key, timed using standard time functions
            start = clock(); // set clock
            outcome = binarySearch(array, 0, (arr_size - 1), key); // run search
            end = clock(); // end clock
            time = (double)(end - start)/CLOCKS_PER_SEC; // calculate run time for printing
            printf("%-18.8f", time); // print run time to table
            
            // free array
            free(holdArrayPtr);
            
            //close file
            fclose(filePtr);
        }
        else
        {
            printf("\nFile %s was unable to be opened.", argv[i]);
        }
    
    }
    
    // return main
    return 0;
}

// merge two halves of array[low..middle], array[middle+1..high] into one array
void merge(int* array, int low, int middle, int high)
{
    // variables
    int i, j, k, n1 = middle - low + 1, n2 =  high - middle, L[n1], R[n2];
    
    // Copy data to temp arrays L[] and R[]
    for(i = 0; i < n1; i++)
    {
        L[i] = array[low + i];
    }
    
    //
    for(j = 0; j <= n2; j++)
    {
        R[j] = array[middle + 1+ j];
    }
    
    // Merge the temp arrays back into arr[l..r]
    i = 0;
    j = 0;
    k = low;
    
    //
    while (i < n1 && j < n2)
    {
        //
        if (L[i] <= R[j])
        {
            array[k] = L[i];
            i++;
        }
        else
        {
            array[k] = R[j];
            j++;
        }
        k++;
    }
    
    // Copy the remaining elements of L[], if there are any
    while (i < n1)
    {
        array[k] = L[i];
        i++;
        k++;
    }
    
    // Copy the remaining elements of R[], if there are any
    while (j < n2)
    {
        array[k] = R[j];
        j++;
        k++;
    }
    
}

// low is left index, high is right index of sub-arrays of unsorted array
void mergeSort(int* array, int low, int high)
{
    if (low < high)
    {
        // Same as (low+high)/2, avoids overflow for large low and high
        int middle = low + (high - low) / 2;
        
        // recursively calls and sorts array
        mergeSort(array, low, middle);
        mergeSort(array, middle+1, high);
        
        // merge separate arrays into one
        merge(array, low, middle, high);
    }
}

// linear search array for key
int linearSearch(int* array, int length, int key)
{
    // variables
    int i;
    
    // run through each element of array until key is found
    for (i = 0 ; i < length; i++)
    {
        // if key is found
        if (array[i] == key)
        {
            // return 1
            return 1;
        }
    }
    
    // else return 0 for not found
    return 0;
    
}

// binary search array for key
int binarySearch(int* array, int low, int high, int key)
{
    // variables
    int middle = low + (high - low) / 2; // set mid point
    
    // split array into two and analyze to see which half the key could be in
    while( low <= high )
    {
        // if key is in upper half
        if ( array[middle] < key )
            low = middle + 1;
        // if key is found, return 1
        else if ( array[middle] == key )
        {
            return 1;
        }
        // else set high to middle - 1
        else
            high = middle - 1;
        
        // set middle to half the new field of search within array and loop through again
        middle = (low + high)/2;
    }
    
    // return 0 if not found
    return 0;

}


