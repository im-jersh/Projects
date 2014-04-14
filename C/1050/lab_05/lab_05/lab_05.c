//
//
//  Joshua O'Steen
//  03/14/2013
//  14041320
//  jrocnc
//  PUPPIES



#include <stdio.h>
#include <time.h>

// function protoypes //
int check_error(int x);
int generate_digit();
int create_number(int x, int y, int z);
int sum_digits(int x);
int check_digit(int x);
int reverse_number(int x, int y);
int concatenate_number(int x, int y, int n);

// begin main function //
int main(void)
{
    // initialize variables //
    int loop_counter, counter_error_check, counter = 1, x, y, z, x_error_check, three_digit_one, three_digit_two, a, b, c;
    
    // seed random number generator from comp clock //
    srand( time(NULL));
    
    // print prompt to user //
    printf("\n\nHow many times do you want to run the program: ");
    scanf("%d", &loop_counter);
    
    // function to error check input from user //
    counter_error_check = check_error(loop_counter);
    
    // prompt user to correct input //
    while (counter_error_check == 0)
    {
        printf("\nInvalid number. Reenter the number again: ");
        scanf("%d", &loop_counter);
        counter_error_check = check_error(loop_counter);
    }
    
    // run program as many times as user input //
    do
    {
        // generate numbers
        x = generate_digit();
        y = generate_digit();
        z = generate_digit();
        
        // check to see if x = 0 //
        x_error_check = check_digit(x);
        
        // correct x digit if x = 0 //
        while (x_error_check == 1)
        {
            x = generate_digit();
            x_error_check = check_digit(x);
        }
        
        // create three digit number from single digits //
        three_digit_one = create_number(x, y, z);
        
        // print first three digit number //
        printf("\nThe first three digit number is %d and the sum of its digits is %d.\n", three_digit_one, sum_digits(three_digit_one));
        
        // generate numbers
        x = generate_digit();
        y = generate_digit();
        z = generate_digit();
        
        // check to see if x = 0 //
        x_error_check = check_digit(x);
        
        // correct x digit if x = 0 //
        while (x_error_check == 1)
        {
            x = generate_digit();
            x_error_check = check_digit(x);
        }
        
        // create three digit number from single digits //
        three_digit_two = create_number(x, y, z);
        
        // print first three digit number //
        printf("The second three digit number is %d and the sum of its digits is %d.\n", three_digit_two, sum_digits(three_digit_two));
        
        // print six digit number and reverse //
        printf("The six digit number obtained by using numbers %d and %d is %d%d and its reverse is %d\n\n", three_digit_one, three_digit_two, three_digit_one, three_digit_two, reverse_number(three_digit_one, three_digit_two));
    }
    while (++counter <= loop_counter);
    
    // print when program ends //
    if (counter > loop_counter)
    {
        printf("\n\nProgram Terminated.");
        
    }
    
    return 0;
}


int check_error(int x)
{
    // local variables //
    int decision;
    
    // decide if x is less than or equal to zero //
    if (x <= 0)
    {
        decision = 0;
    }
    else
    {
        decision = 1;
    }
    
    // return decicion //
    return decision;
}

int generate_digit()
{
    // generate random number from 0 - 9 //
    return (rand() % 9);
}

int create_number(int x, int y, int z)
{
    // create three digit number //
    return (100 * x)+(10 * y)+z;
}

int sum_digits(int x)
{
    // local variables
    int hundreds, tens, ones, temp, sum;
    
    // seperate digits //
    ones = x % 10;
    temp = x / 10;
    tens = temp % 10;
    hundreds = x / 100;
    
    // add individual digits together //
    sum = hundreds + tens + ones;
    
    return sum;
}

int check_digit(int x)
{
    // local variable //
    int decision;
    
    // decide of x equals 0 //
    if (x == 0)
    {
        decision = 1;
    }
    else
        decision = 0;
    
    // return decision //
    return decision;
}

int reverse_number(int x, int y)
{
    // local variables //
    int hundreds1, hundreds2, tens1, tens2, ones1, ones2, temp1, temp2;
    
    // separate digits for first three digit number //
    ones1 = x % 10;
    temp1 = x / 10;
    tens1 = temp1 % 10;
    hundreds1 = x / 100;
    
    // separate digits for second three digit number //
    ones2 = y % 10;
    temp2 = y / 10;
    tens2 = temp2 %10;
    hundreds2 = y / 100;
    
    return ( (ones2 * 100000)+(tens2 * 10000)+(hundreds2 *1000)+(ones1 *100)+(tens1 * 10)+hundreds1);
    
    
}
               








