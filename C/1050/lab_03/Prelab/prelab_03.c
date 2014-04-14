//
//  Joshua O'Steen
//  prelab 3
//  02.19.2013
//
//


#include <stdio.h>

// begin main function
int main( void )
{
    // define variables
    int pos_int;

    // prompt user for input
    printf("\n\nPlease enter a positive number: ");
    
    // scan input
    scanf("%d", &pos_int);
    
    // error check for negative input
    while ( pos_int < 1 )
    {
        printf("\nA negative number was entered.\nPlease enter a positive number: ");
        scanf("%d", &pos_int);
    }   // end while
    
    // print positive input
    printf("\nThe positive number entered was %d.", pos_int);

    // program ended successfully
    return 0;
    
}   // end main 

