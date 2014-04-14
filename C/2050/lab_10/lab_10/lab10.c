//
//  main.c
//  lab_10
//
//  Created by Joshua O'Steen on 11/13/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

// libraries
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// prototypes
void quick_sort(int* arr, int start, int end, int (*compare)(int, int));
int partition(int* arr, int start, int end, int (*compare)(int, int));
int compareSmallerOnLeft(int num1, int num2);
int compareSmallerOnRight(int num1, int num2);
int b_search(int *arr, int data, int start, int end,int (*compare)(int,int));
void print(int* arr, int length);


int main(int argc, const char * argv[])
{
    // variables
    FILE *in, *in2;
    int list = 0;
    
    // command line argument check
    if (argc != 3)
    {
        printf("\nYou did not enter the correct number of arguments.\n");
        return 1;
    }
    else if (!(in = fopen((argv[1]), "r")))
    {
        printf("\n\nThe file '%s' was not opened properly.\nPlease try again.\n\n", (argv[1]));
        return 1;
    }
    else if (!(in2 = fopen(argv[2], "r")))
    {
        printf("\n\nThe file '%s' was not opened properly.\nPlease try again.\n\n", (argv[2]));
        return 1;
    }
    
    // get number of elemets for array
    fscanf(in,"%d", &list);
    
    // variables
    int array[list];
    int number = 0, i = 0, choice = 0 ;
    int (*functionChoice)(int, int);
    
    // fill array from file
    while (fscanf(in, "%d", &number) != EOF)
    {
        array[i] = number;
        i++;
    }
    
    // close input file
    fclose(in);
    
    // print unsorted array
    printf("\nUnsorted array is:\n");
	print(array, list);
    
    // choose ascending/descending sort
    printf("\n\nDo you want to sort decending order (1) or ascending order (2)? ");
    scanf("%d", &choice);

    // set or error check
    if (choice == 1)
        functionChoice = compareSmallerOnRight;
    else
        functionChoice = compareSmallerOnLeft;
    if (choice != 1 && choice != 2)
    {
        printf("\n\nDo you want to sort decending order (1) or ascending order (2)? ");
        scanf("%d", &choice);
    }
    
    // sort array
	quick_sort(array, 0, list - 1, functionChoice);
    
    // print sorted array
	printf("\n\nSorted array is:\n");
    print(array, list);
    
    // get search value and binary search array
    while (fscanf(in2, "%d", &number) != EOF) {
        if (b_search(array, number, 0, list-1, functionChoice))
            printf("\n\n%d was found in the array", number);
        else
            printf("\n\n%d was not found in the array", number);
    }
    
    
    
    // return successfully
    return 0;

}


void quick_sort(int* arr, int start, int end, int (*compare)(int, int))
{
    int j;
    
    // divide and conquer
    if (start < end)
    {
        j = partition(arr, start, end, compare);
        quick_sort(arr, start, j-1, compare);
        quick_sort(arr, j+1, end, compare);
    }
}

int partition(int* arr, int start, int end, int (*compare)(int, int))
{
    int i = start-1, j = start;
    int tmp;
    srand(time(NULL));
    int pivotIndex = start + (rand() % (end - start) + 1);
    int pivot = arr[pivotIndex];

    // swap end and pivot
    tmp = arr[end];
    arr[end] = arr[pivotIndex];
    arr[pivotIndex] = tmp;
    pivotIndex = end;
    
    // loop though array and swap
    for (j = start; j <= end; j++)
    {
        if (compare(arr[j], pivot) > 0 )
        {
            i++;
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
    
    // replace pivot point
    tmp = arr[i+1];
    arr[i+1] = arr[pivotIndex];
    arr[pivotIndex] = tmp;
    
    // return pivot point
    return i+1;

}

int compareSmallerOnLeft(int num1, int num2)
{
    // compare and return
    if (num1 == num2){
		return 0;
    }
	if (num1 < num2){
		return 1;
    }
	if (num1 > num2){
		return -1;
    }
    return 0;
}

int compareSmallerOnRight(int num1, int num2)
{
    // compare and return
    if (num1 == num2){
		return 0;
    }
	if (num1 < num2){
		return -1;
    }
	if (num1 > num2){
		return 1;
    }
    return 0;
}

int b_search(int *arr, int data, int start, int end,int (*compare)(int,int))
{
	
	// ran out of time
    // could not figure that shit out
    //
    
    return 0;
}

void print(int* arr, int length)
{
    int i = 0;
    
    // iterate and print values
	for(i = 0; i < length; ++i){
		printf("%d ", arr[i]);
    }
    printf("\n");
}