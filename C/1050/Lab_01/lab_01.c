/*
 Joshua O'Steen
 02/07/2013
 Lab 1
 14041320
 Lab Code: FLOATERS
 */




#include <stdio.h> /* links to standard C library */
#include <math.h> /* links the math library */     /* don't forget to link math library using "gcc filename.c -lm" when compiling */


/* function main begins the program */
int main(void)


{
    /* set variables */
    float firstRequest, secondRequest, thirdRequest;       /* first and second number requested from user*/
    float area, p_value;                 /* variable to hold arithmetic operation results */
    
    
    /* request for first value */
    printf( "\n\n\nPlease enter your first positive number: "); /* request for first value*/
    scanf( "%f", &firstRequest ); /* reads first input from user */
    
    while ( firstRequest <= 0 ) /* check to see if number is negative */
    {
        printf( "\nPlease re-enter a positive number: " ); /* if number is negative, request new number */
        scanf( "%f", &firstRequest ); /*reads new input from user for first number */
    }
    
    
    /* request for second value */
    printf( "\nPlease enter your second positive number: "); /* request for second value*/
    scanf( "%f", &secondRequest ); /* reads second input from user */
    
    while ( secondRequest <= 0 ) /* check to see if number is negative */
    {
        printf( "\nPlease re-enter a positive number: " ); /* if second number is negative, request new number */
        scanf( "%f", &secondRequest ); /*reads new input from user for second number */
    }
    
    
    /* request for third value */
    printf( "\nPlease enter your third positive number: "); /* request for third value*/
    scanf( "%f", &thirdRequest ); /* reads third input from user */
    
    while ( thirdRequest <= 0 ) /* check to see if number is negative */
    {
        printf( "\nPlease re-enter a positive number: " ); /* if third number is negative, request new number */
        scanf( "%f", &thirdRequest ); /*reads new input from user for third number */
    }
    
    
    /* check to see if inputs can form a triagle */
    while ((firstRequest >= secondRequest + thirdRequest) || (secondRequest >= firstRequest + thirdRequest) || (thirdRequest >= firstRequest + secondRequest))
    {
        printf("\nThe lengths you entered can not form a triagle. Please try again."); /* prompt to re-enter side lengths */
        
        /* request for first value */
        printf( "\n\nPlease enter your first positive number: "); /* request for first value*/
        scanf( "%f", &firstRequest ); /* reads first input from user */
        
        while ( firstRequest <= 0 ) /* check to see if number is negative */
        {
            printf( "\nPlease re-enter a positive number: " ); /* if number is negative, request new number */
            scanf( "%f", &firstRequest ); /*reads new input from user for first number */
        }
        
        
        /* request for second value */
        printf( "\nPlease enter your second positive number: "); /* request for second value*/
        scanf( "%f", &secondRequest ); /* reads second input from user */
        
        while ( secondRequest <= 0 ) /* check to see if number is negative */
        {
            printf( "\nPlease re-enter a positive number: " ); /* if second number is negative, request new number */
            scanf( "%f", &secondRequest ); /*reads new input from user for second number */
        }
        
        
        /* request for third value */
        printf( "\nPlease enter your third positive number: "); /* request for third value*/
        scanf( "%f", &thirdRequest ); /* reads third input from user */
        
        while ( thirdRequest <= 0 ) /* check to see if number is negative */
        {
            printf( "\nPlease re-enter a positive number: " ); /* if third number is negative, request new number */
            scanf( "%f", &thirdRequest ); /*reads new input from user for third number */
        }
    }
    
    
    /* arithmetic operations to determine area of triangle */
    p_value = ((firstRequest + secondRequest + thirdRequest)/2); /* determine value of p for Heron's formula */
    area = sqrt(p_value * ((p_value - firstRequest)*(p_value - secondRequest)*(p_value - thirdRequest))); /* area using Heron's formula */
    
    
    /* print area to screen */
    printf("\nThe triangle with sides %.2f, %.2f, and %.2f form a triangle with area %.2f.\n", firstRequest, secondRequest, thirdRequest, area);
    
    
    /* BONUS */
    /* determine type of triangle */
    if ( (firstRequest == secondRequest) && (secondRequest== thirdRequest) ) /* determine equilateral */
        printf("The side lengths form an equilateral triangle.\n"); /* print */
        else if ((firstRequest == secondRequest) || (firstRequest == thirdRequest) || (secondRequest == thirdRequest)) /* determine isosceles */
                printf("The side lengths form an isosceles triangle.\n"); /* print */
                else if ( firstRequest != secondRequest != thirdRequest) /* determine scalene */
                        printf("The side lengths form a scalene triangle.\n"); /* print */
    /* BONUS */
    /* determine longest and shortest side */
    if ( firstRequest <= secondRequest && secondRequest <= thirdRequest ) /* A < B < C */
        printf( "The longest side has length %.2f and the shortest side has length %.2f.\n\n", thirdRequest, firstRequest); /* print */
        else if ( firstRequest <= thirdRequest && thirdRequest <= secondRequest ) /* A < C < B */
            printf( "The longest side has length %.2f and the shortest side has length %.2f.\n\n", secondRequest, firstRequest); /* print */
            else if ( secondRequest <= firstRequest && firstRequest <= thirdRequest ) /* B < A < C */
                printf( "The longest side has length %.2f and the shortest side has length %.2f.\n\n", thirdRequest, secondRequest); /* print */
                else if ( secondRequest <= thirdRequest && thirdRequest <= firstRequest ) /* B < C < A */
                    printf( "The longest side has length %.2f and the shortest side has length %.2f.\n\n", firstRequest, secondRequest); /* print */
                    else if ( thirdRequest <= firstRequest && firstRequest <= secondRequest ) /* C < A < B */
                        printf( "The longest side has length %.2f and the shortest side has length %.2f.\n\n", secondRequest, thirdRequest); /* print */
                        else if ( thirdRequest <= secondRequest && secondRequest <= firstRequest ) /* C < B < A */
                            printf( "The longest side has length %.2f and the shortest side has length %.2f.\n\n", firstRequest, thirdRequest); /* print */
                            
    
    
    

    return 0; /* indicates that program ended successfully*/
}

/* program has finished */
