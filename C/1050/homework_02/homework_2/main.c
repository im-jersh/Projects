//
//  Joshua O'Steen
//  Homework Assignment 2
//  03/10/2013
//
//  
//

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

// function prototypes //
void display_menu();
int game_choice(int x);
int throw_dice();
int even_odd(int x, int option);
int lucky_seven(int x, int option);
int guess_number(int x, int guess);
float even_odd_bet(int decision, float bet, float buy_in);
float lucky_seven_bet(int decision, float bet, float buy_in, int option);
float guess_number_bet(int decision, float bet, float buy_in);
void print_decision(int decision, int x);



// begin main function //
int main(void)
{
    // declare variables //
    int game_number = 0, option = 0, x = 0, decision;
    float buy_in = 100.00, bet = 0;
    
    // seed random number generator with time function //
    srand( time ( NULL ) );
    
    // loop game play until player exits or loses all money
    while (game_number != 4 && buy_in > 0)
    {
        // print out main menu for game selection //
        display_menu();
        
        // show balance //
        printf("Your current balance is: $%.2f\n", buy_in);
        
        // select game and error check //
        game_number = game_choice(game_number);
        
        // choose game function //
        switch (game_number)
        {
            case 1:
            {
                // request choice for even or odd //
                printf("\nPlease make a selection:");
                printf("\n ( 1 ) Even\n ( 2 ) Odd\n");
                scanf("%d", &option);
                
                // errory check selection //
                while (option < 1 || option > 2)
                {
                    printf("\nYou made an invalid choice. \nPlease choice even or odd:\n");
                    printf("\n ( 1 ) Even\n ( 2 ) Odd\n");
                    scanf("%d", &option);
                }
                
                // request bet //
                printf("\nPlease place a bet: $");
                scanf("%f", &bet);
                
                // error check bet //
                while (bet > buy_in)
                {
                    printf("\nYou bet too much. Please make a new bet: $");
                    scanf("%f", &bet);
                }
                
                // set sum of dice //
                x = (throw_dice()) + (throw_dice());
                
                // set win decision //
                decision = even_odd( x, option);
                
                // function to print decision and adjust buy in value //
                buy_in = even_odd_bet( decision, bet, buy_in);
                
                // function to print decision and remaining balance //
                print_decision( decision, x);
                
                                
            }
                // break first case //
                break;
            
            case 2:
            {
                // request choice for even or odd //
                printf("\nPlease make a selection:");
                printf("\n ( 1 ) Over 7\n ( 2 ) Under 7\n ( 3 ) Lucky 7\n");
                scanf("%d", &option);
                
                // errory check selection //
                while (option < 1 || option > 3)
                {
                    printf("\nYou made an invalid choice. \nPlease make a selection:\n");
                    printf("\n ( 1 ) Over 7\n ( 2 ) Under 7\n ( 3 ) Lucky 7\n");
                    scanf("%d", &option);
                }
                
                // request bet //
                printf("\nPlease place a bet: $");
                scanf("%f", &bet);
                
                // error check bet //
                while (bet > buy_in)
                {
                    printf("\nYou bet too much. Please make a new bet: $");
                    scanf("%f", &bet);
                }
                
                // set sum of dice //
                x = (throw_dice()) + (throw_dice());
                
                // set win decision //
                decision = lucky_seven( x, option);
                
                // function to print decision and adjust buy in value //
                buy_in = lucky_seven_bet(decision, bet, buy_in, option);
                
                // function to print decision and remaining balance //
                print_decision( decision, x);
            
            }
                // break second case //
                break;
                
            case 3:
            {
                // request choice for even or odd //
                printf("\nPlease guess a number from 2 - 12:\n");
                scanf("%d", &option);
                
                // errory check selection //
                while (option < 2 || option > 12)
                {
                    printf("\nYou made an invalid choice. \nPlease guess a number from 2 - 12:\n");
                    scanf("%d", &option);
                }
                
                // request bet //
                printf("\nPlease place a bet: $");
                scanf("%f", &bet);
                
                // error check bet //
                while (bet > buy_in)
                {
                    printf("\nYou bet too much. Please make a new bet: $");
                    scanf("%f", &bet);
                }
                
                // set sum of dice //
                x = (throw_dice()) + (throw_dice());
                
                // set win decision //
                decision = guess_number( x, option);
                
                // function to print decision and adjust buy in value //
                buy_in = guess_number_bet(decision, bet, buy_in);
                
                // function to print decision and remaining balance //
                print_decision( decision, x);
            }
            
        }
    }
    
    // print if user exits or balance reaches 0 //
    if (game_number == 4)
    {
        printf("\nThanks for playing!\nYour balance is: $%.2f.\nGOODBYE", buy_in);
    }
    
    if (buy_in <= 0)
    {
        printf("\n\nYou lost all your money.\nYour balance is: $0.00.\nPlease play again.");
    }
    
    // return main //
    return 0;
}

void display_menu()
{
    // print out main manue //
    printf("\n\nWelcome to the casino. Please select a game:\n");
    printf( "%4s  %-20s\n", "( 1 )", "Even or Odd" );
    printf( "%4s  %-20s\n", "( 2 )", "Lucky 7" );
    printf( "%4s  %-20s\n", "( 3 )", "Guess a Number." );
    printf( "%4s  %-20s\n", "( 4 )", "Leave the casino." );
}

int game_choice(int x)
{
    // game selection from user //
    scanf("%d", &x);
    
    // error check game selection //
    while (x < 1 || x > 4)
    {
        printf("\nYou entered an invalid choice.\nPlease select a game:\n");
        printf( "%4s  %-20s\n", "( 1 )", "Even or Odd" );
        printf( "%4s  %-20s\n", "( 2 )", "Lucky 7" );
        printf( "%4s  %-20s\n", "( 3 )", "Guess a Number." );
        printf( "%4s  %-20s\n\n", "( 4 )", "Leave the casino." );
        scanf("%d", &x);
    }
    
    // return valid game selection //
    return x;

}

int throw_dice()
{    
    // return random value between 1 and 6 //
    return 1 + (rand() % 6);
}

int even_odd(int x, int option)
{
    // set local variable //
    int decision;
    
    // decide of even or odd and if option was correct //
    if ( x % 2 == 0 && option == 1)
    {
        decision = 1;
    }
    else if  ( x % 2 != 0 && option == 2)
    {
        decision = 1;
    }
    else
    {
        decision = 0;
    }
    
    // return 1 for win and 0 for lose //
    return decision;
}

int lucky_seven(int x, int option)
{
    // set local variables //
    int decision;
    
    // determine if guess was correct or not //
    if ( x > 7 && option == 1)
    {
        decision = 1;
    }
    else if ( x < 7 && option == 2)
    {
        decision = 1;
    }
    else if ( x == 7 && option == 3)
    {
        decision = 1;
    }
    else
    {
        decision = 0;
    }
    
    //return 1 for win and 0 for lose //
    return decision;
}

int guess_number(int x, int guess)
{
    // local variable //
    int decision;
    
    // decide if won or lost //
    if (x == guess)
        decision = 1;
    else
        decision = 0;
    
    // return decision //
    return decision;
        
}

float even_odd_bet(int decision, float bet, float buy_in)
{
    // decide if won or lost, adjust balance accordingly //
    if ( decision == 1)
    {
        buy_in += (2 * bet);
    }
    else
    {
        buy_in -= bet;
    }
    
    // return balance //
    return buy_in;
}

float lucky_seven_bet(int decision, float bet, float buy_in, int option)
{
    // decide if won or lost, adjust balance accordingly //
    if ( decision == 1 && option == 1)
    {
        buy_in += (2 * bet);
    }
    else if ( decision == 1 && option == 2)
    {
        buy_in += (2 * bet);
    }
    else if ( decision == 1 && option == 3)
    {
        buy_in += (7 * bet);
    }
    else
    {
        buy_in -= bet;
    }
    
    // return balance //
    return buy_in;
}

float guess_number_bet(int decision, float bet, float buy_in)
{
    // decide if won or lost, adjust balance accordingly //
    if (decision == 1)
        buy_in += (5 * bet);
    else
        buy_in -= bet;
    
    // return balance //
    return buy_in;
}

void print_decision(int decision, int x)
{
    if (decision == 1)
    {
        printf("\nYOU WIN!\nThe sum of the dice was %d.", x);
    }
    else
    {
        printf("\nYOU LOST.\nThe sum of the dice was %d.", x);
    }
    
}







