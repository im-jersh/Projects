/*
 Joshua O'Steen
 14041320
 jrocnc
 02/12/2013
 */

#include <stdio.h>  /* include standard C library */
#include <math.h>   /* include math library */


/* function main starts the program */
int main(void)

{
    /* set variable */
    float income=0, taxes_paid=0, net_income=0;
    int filing_status=1, tax_rate=0;
    
    
    // program functions until user inputs EOF = 0 //
    while ( filing_status != 0 )
    {
        // formatting printout text for user //
        printf( "\n\n\nTax Calculator\nFiling Status:\n\n" );
        printf( "%5s  %-25s\n", "(1)", "Single" );
        printf( "%5s  %-25s\n", "(2)", "Married" );
        printf( "%5s  %-25s\n", "(3)", "Head of Household" );
        printf( "%5s  %-25s\n\n", "(0)", "End Program" );
        printf( "Please enter your filing status: " );
        
        scanf( "%d", &filing_status);
        
        
        // error check filing status input //
        while ( filing_status < 0 || filing_status > 3 )
        {
            printf( "\nYou entered an invalid filing status. Please enter the number of the correct filing status: ");
            scanf( "%d", &filing_status );
        }
        
        
        // EOF //
        if (filing_status == 0)
        {
            printf("\nProgram Ended.");
            break;
        }
        
        // input income //
        printf("\nPlease enter your annual income: ");
        scanf( "%f", &income );
        
        
        
        // decide tax based off of filing status and income //
        switch ( filing_status )
        {
            // Single status, determine tax rate //
            case 1:
                if ( income >= 500000 )
                {
                    tax_rate = 40;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 397000 )
                {
                    tax_rate = 35;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 183000 )
                {
                    tax_rate = 30;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 87000 )
                {
                    tax_rate = 27;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 36000 )
                {
                    tax_rate = 25;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 9000 )
                {
                    tax_rate = 15;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 0 )
                {
                    tax_rate = 10;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else
                {
                    tax_rate = 0;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                break;
                
            // married status, determine tax rate //
            case 2:
                if ( income >= 550000 )
                {
                    tax_rate = 40;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 400000 )
                {
                    tax_rate = 35;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 225000 )
                {
                    tax_rate = 30;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 147000 )
                {
                    tax_rate = 27;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 72000 )
                {
                    tax_rate = 25;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 17000 )
                {
                    tax_rate = 15;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 0 )
                {
                    tax_rate = 10;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else
                {
                    tax_rate = 0;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                break;
                
            // Head of household status, determin tax rate //
            case 3:
                if ( income >= 450000 )
                {
                    tax_rate = 40;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 390000 )
                {
                    tax_rate = 35;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 205000 )
                {
                    tax_rate = 30;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 125000 )
                {
                    tax_rate = 27;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 47000 )
                {
                    tax_rate = 25;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 12000 )
                {
                    tax_rate = 15;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else if ( income >= 0 )
                {
                    tax_rate = 10;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                else
                {
                    tax_rate = 0;
                    printf("\nYour tax rate is: %d%%", tax_rate);
                }
                break;
        }
        
        // calculate taxes paid based from income and tax rate //
        taxes_paid = ((income * tax_rate)/100);
        printf("\nYou pay $%.2f in taxes.", taxes_paid);
        
        // calculate net income //
        net_income = income - taxes_paid;
        printf("\nAfter taxes your net income is $%.2f.", net_income);
    
    } // end while function //
   
   return 0; // function executed properly //
    
} // end main function //

