/*
    Josh O'Steen
    02/14/2013
    14041320
    Labcode: JINGLE
    Lab #2
 */



#include <stdio.h>
#include <math.h>


int main(void)
{
    int lower, upper, counter1, counter2, counter=0;
    float x;
    
    
    printf("\n\nPlease enter an integer for the lower bound: ");
    scanf("%d", &lower);
    
    
    printf("\nPlease enter an integer for the upper bound: ");
    scanf("%d", &upper);
    
    
    for (counter1 = lower; counter1 <= upper; counter1++)
    {
        // check even or odd //
        if (counter1 % 2 == 0)
            printf("%d is even.\n", counter1);
        else
            printf("%d is odd.\n", counter1);
        
        
        // check for prime or not //
        counter = 0;
        for (counter2 = 2; counter2 <= (int)sqrt(counter1); counter2++)
        {
            if ( counter1 % counter2 == 0 )
            {
                counter = 1;
            }
            
        }
        
        if( counter == 1 )
            printf("%d is not prime\n", counter1);
        else
            printf("%d is prime\n", counter1);
        
        
        
        // check for perfectt square //
        // x = sqrt(counter1)
    }
    
    
    
    return 0;
}


