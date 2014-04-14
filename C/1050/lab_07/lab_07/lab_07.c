//
//  Joshua O'Steen
//  04/04/2013
//  CS 1050
//  Lab J
//  jrocnc
//  EXHAUST

// standard libraries //
#include <stdio.h>
#include <time.h>

// hard defined variables //
#define column 2
#define row 10

// funciton prototypes //
void create_array(int a[][column], int size);
void print_array(int a[][column], int size);
int three_digit();
int two_digit();
int search_info(int a[][column], int size);
void sort_array(int a[][column], int size);

// begin main function //
int main(void)
{
    // seed random number generator //
    srand(time(NULL));
    
    //declare local main variables //
    int students = 0, x;
    int a[row][column];
    
    // ask for user input //
    printf("\n\nPlease enter the size of the array: ");
    scanf("%d", &students);
    
    // populate the array //
    create_array(a, students);
    
    // print the array //
    print_array(a, students);
    
    // find lowest grade //
    x = search_info(a, students);
    
    // print lowest grade //
    printf("\nThe student with the lowest grade has an ID %d with the grade %d\n", a[x][0], a[x][1]);
    
    // sort array by descending ID //
    sort_array(a, students);
    
    
    
    
    
}

// functions //
void create_array(int a[][column], int size)
{
    // local variables //
    int i;
    
    // populate array //
    for (i = 0; i < size; i++)
    {
        a[i][0] = three_digit();
        a[i][1] = two_digit();
    }
}

int three_digit()
{
    // local variables //
    int x = 0;
    
    // generate number between 100-999 //
    while (x < 100 || x > 999)
    {
        x = rand() % 1000;
    }
    
    // return x //
    return x;
}

int two_digit()
{
    // local variables //
    int x = 0;
    
    // generate number between 0-99 //
    x = rand() % 100;
    
    // return x //
    return x;
}

void print_array(int a[][column], int size)
{
    // local variables //
    int i;
    
    // print column headers //
    printf("\n%5s %5s\n", "ID", "GRADE");
    
    // print out array //
    for (i = 0; i < size; i++)
    {
        printf("%5d %5d\n", a[i][0], a[i][1]);
    }
}

int search_info(int a[][column], int size)
{
    // local variables //
    int i,x, min = 99;
    
    for (i = 0; i < size; i++)
    {
        if (a[i][1] < min)
        {
            min = a[i][1];
            x = i;
        }
    }
    
    // return index of lowest grade //
    return x;
    
}

void sort_array(int a[][column], int size)
{
    // local variables //
    int i, x, hold1, hold2;
    
    for ( x = 1; x < size; x++ )
    {
        /* loop to control number of comparisons per pass */
        for ( i = 0; i < size - 1; i++ )
        {
            /* compare adjacent elements and swap them if first
             element is greater than second element */
            if ( a[i][0] < a[i + 1][0] )
            {
                hold1 = a[i][0];
                a[i][0] = a[i + 1][0];
                a[i + 1][0] = hold1;
                
                hold2 = a[i][1];
                a[i][1] = a[i + 1][1];
                a[i + 1][1] = hold2;
            } 
        }
    }
    
    printf("\nSorted by descending ID");
    
    // print newly arranged array //
    print_array(a, size);
}






