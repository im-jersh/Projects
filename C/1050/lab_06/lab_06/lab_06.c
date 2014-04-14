//
//  Joshua O'Steen
//  03/21/2013
//  Lab 06 Arrays
//  FREEEE
//
//

// libraries //
#include <stdio.h>
#include <time.h>

// hard define array size //
#define array_size 10

// function prototypes //
void init();
void print(int start, int elements);
int frequency(int target);
void subsequence ();

// global array //
int array[array_size];

// begin main function //
int main(void)
{
    // local variables //
    int x=0, search=0;
    
    
    // seed random number gen //
    srand(time(NULL));
    
    // initialize array elements
    init();
    
    // print elements of array //
    printf("\n\nArray elements:  ");
    print(0, array_size);
    
    // find LIS //
    subsequence();
    
    // search for frequency of user input value //
    printf("\nEnter a number 0-9 to search:  ");
    scanf("%d", &search);
    
    while (search < 0 || search > 9)
    {
        printf("\n\nYou entered an invalid search input.\nPlease enter a number 0-9 to search:  ");
        scanf("%d", &search);
    }
    
    x = frequency(search);
    
    if (x==0)
        printf("The target value does not appear in the array.\n\n");
    else
        printf("The target value appears in the array %d time(s).\n\n", x);
    
    // end main function, return 0 //
    return 0;
}



void init()
{
    // local variable //
    int i;
    
    // for loop to initialize every element in global array //
    for(i=0; i<array_size; i++)
        array[i] = rand()%10;
    
}

void print(int start, int elements)
{
    // local variable //
    int i;
    
    // print array //
    for(i=start; i<elements; i++)
        printf("%d  ", array[i]);
    
}

void subsequence()
{
    // local variables //
    int i, max_subsequence = 1, subsequence_index = 0, temp_subsequence = 1, temp_sub_index = 0;
    
    // determine longest increasing subsequence //
    for(i=0; i < array_size; i++)
    {
        if(array[i]<=array[i+1])
        {
            temp_subsequence++;
        }
        else
        {
            if (max_subsequence < temp_subsequence)
            {
                max_subsequence = temp_subsequence;
                subsequence_index = temp_sub_index;
            }
        
            temp_subsequence = 1;
            temp_sub_index = i+1;
        }
    }
    
    // print results //
    printf("\nThe longest subsequence starts at index:  %d  with length:  %d.", subsequence_index, max_subsequence);
    printf("\nSubsequence:  ");
    print(subsequence_index, max_subsequence + subsequence_index);
    
}

int frequency(int target)
{
    // local variable //
    int i, x = 0;
    
    // determine frequecy of user input value //
    for (i=0; i<array_size; i++)
    {
        if (array[i]==target)
        {
            x++;
        }
    }
    
    // return frequency //
    return x;
}














