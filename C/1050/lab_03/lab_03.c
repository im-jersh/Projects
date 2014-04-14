//
//  Joshua O'Steen
//  jrocnc
//  14041320
//  02.21.2013
//  lab: 3
//  code: snowman
//

#include <stdio.h>

int main( void )
{
    // set variables
    int account_type, x, year;
    float interest_rate, compound_interest, base_amount, y, total_amount, pow_copy;
    
    while (account_type != 0)
    {
        
    printf("\n\nPlease enter your account type:\n");
    printf( "%5s  %-25s\n", "(1)", "Checking" );
    printf( "%5s  %-25s\n", "(2)", "Saving" );
    printf( "%5s  %-25s\n", "(3)", "Fixed Deposit" );
    printf( "%5s  %-25s\n\n", "(0)", "End Program" );
    
    // input account type from user //
    scanf("%d", &account_type);
    
    // error check //
    while ( account_type < 0 || account_type > 3 )
    {
        printf( "\nYou entered an invalid account type. Please enter the number of the correct account type to continue: ");
        scanf( "%d", &account_type );
    }   // end while //
    
    
    // EOF //
    if ( account_type == 0)
    {
        printf("\nProgram Ended.");
        return 1;
    }   // end if //
    
    // switch based on account type //
    switch ( account_type )
    {
        case 1:
            interest_rate = 0.01;
            break;
            
        case 2:
            interest_rate = 0.03;
            break;
            
        case 3:
            interest_rate = 0.07;
            break;
    }   // end switch //
    
    // inpute year from user //
    printf("\nPlease enter the year(s): ");
    scanf("%d", &year);
    
    while ( year <=0)
    {
        printf("\nYear can not be negative or zero.\nPlease re-enter the year(s): ");
        scanf("%d", &year);
    }   // wnd while
    
    // input base amount from user //
    printf("\nPlease enter your base amount: $");
    scanf("\%f", &base_amount);
    
    // error check for negative base amount //
    while ( base_amount <= 0)
    {
        printf("\nThe base amount must be greater than $0.00.\nPlease re-enter your base amount. $");
        scanf("%f", &base_amount);
    }   // end while
    
    // compound interester calculation
    y = 1 + interest_rate;
    pow_copy = y;
        
    for ( x = 1; x < year; x++)
    {
        y = y * pow_copy;
    }   // end for //
    
    // interest and total calculation //
    total_amount = base_amount * y;
    compound_interest = total_amount - base_amount;
    
    // print out
    printf("\nTotal amount after %d year(s) is $%.2f.", year, total_amount);
    printf("\nTotal interest earned in %d year(s) on the amount of $%.2f is $%.2f.\n", year, base_amount, compound_interest);
    
        
    }  // end while //
    return 0;
}

