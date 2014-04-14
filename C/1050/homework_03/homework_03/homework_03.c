//
//  Joshua O'Steen
//  Homework Assignment 3
//  14041320
//  jrocnc
//  04/09/2013
//

// standard libraries //
#include <stdio.h>
#include <time.h>

// hard defined values //
#define TURNS 5
#define COLUMNS 10
#define ROWS 10

// function prototypes //
void initGarden(char garden[][10]);
char soilSelect();
void printGarden(char garden[][10]);
int placeFlower(char garden[][10]);
int getCoordinate();
char getFlower();
int bugAttack();
int searchSurroundings(char garden[][10], int x, int y);

// begin main function //
int main(void)
{
    // variables //
    int i, points = 0;
    char garden[ROWS][COLUMNS];
    
    // seed random num gen //
    srand(time(NULL));
    
    // initialize array with characters //
    initGarden(garden);
    
    // print title of game //
    printf("\nGARDEN GAME!");
    
    
    // for loop to control turn //
    for (i = 0; i < 5; i++)
    {
        // print round number and array //
        printf("\n\n### ROUND %d ###\n\n", (i+1));
        printGarden(garden);
        
        // add up points //
        points += placeFlower(garden);
        
        // don't print total points if final round //
        if (i == 4)
        {
            printf("\n");
            break;
        }// end if //
        
        // print total points after round ends //
        printf("\nTotal points: %d", points);
    } // end for //

    // print final game board and points total //
    printGarden(garden);
    printf("\n\nFinal outcome: %d\n\n", points);
    
    // return main value // 
    return 0;
}// end main function //



// function definitions //
void initGarden(char garden[][10])
{
    // variables //
    int i, j;
    
    // populate array row //
    for (i = 0; i < ROWS; i++)
    {
        // populate array column //
        for (j = 0; j < COLUMNS; j++)
        {
            // populate array index with soil selection //
            garden[i][j] = soilSelect();
        }// end for //
    }// end for //
}

char soilSelect()
{
    // variables //
    int i;
    char j;
    
    // select random number between 0 and 99 //
    i = rand() % 99;
    
    // assign ASCII value based on random number //
    if (i < 29)
    {
        // ASCII value for '#' //
        j = 35;
    }// end if //
    else
    {
        // ASCII value for ',' //
        j = 44;
    }// end else //
    
    // return ASCII value //
    return j;
}

void printGarden(char garden[][10])
{
    // variables //
    int h, i, j;
    
    // spacer //
    printf("  ");
    
    // print column header //
    for (h = 0 ; h < COLUMNS; h++)
    {
        // print values 0 - 9 //
        printf(" %d", h);
    }// end for //
    
    // newline and spacer //
    printf("\n  ");
    
    // print underscore lines for top of box //
    for (h = 0; h <= (COLUMNS * 2); h++)
    {
        // print ASCII for "_" //
        printf("%c", 95);
    }// end for //
    
    // print array //
    for (i = 0; i < ROWS; i++)
    {
        // print row number and v-bar character //
        printf("\n%d%c", i, 124);
        
        // print column value in given row //
        for (j = 0; j < COLUMNS; j++)
        {
            printf(" %c", garden[i][j]);
        }// end for //
        
        // ASCII value for v-bar character "|" //
        printf(" %c", 124);
        
    }// end for //
    
    // print newline and spaceer //
    printf("\n  ");
    
    // print underscore lines for bottom of box //
    for (h = 0; h <= (COLUMNS * 2); h++)
    {
        // ASCII value for "_" //
        printf("%c", 95);
    }// end for // 
}

int placeFlower(char garden[][10])
{
    // variables //
    int x, y, points = 0, bug, soil;
    char flower;
    
    // print and scan for user input for x & y coords and flower selection //
    printf("\n\nPlease select a ROW coordinate to plant a flower (0-9): ");
    x = getCoordinate();
    printf("\nPlease select a COLUMN coordinate to plant a flower (0-9): ");
    y = getCoordinate();
    
    // check if space selected has already been played and rescan input if it has //
    while (garden[x][y] != ',' && garden[x][y] != '#')
    {
        printf("\nYou chose a space that has already been played.\nPlease try again.\n");
        printf("\nPlease select a ROW coordinate to plant a flower (0-9): ");
        x = getCoordinate();
        printf("\nPlease select a COLUMN coordinate to plant a flower (0-9): ");
        y = getCoordinate();
    }// end while // 
    
    // select flower // 
    printf("\nPlease select a flower to place at row %d column %d:", x, y);
    flower = getFlower();
    
    // print outcome //
    printf("\n\n### OUTCOME ###\n");
    
    // selection based on flower choice //
    switch (flower)
    {
        // Mundane Marigold //
        case 'M':
            if (garden[x][y] == 44)// bad soil //
            {
                // print outcome //
                printf("The Mundande Marigold produced a modest bloom.\nYou received 1 point this turn.");
                
                // assign points //
                points = 1;
                
                // change array value to flower choice //
                garden[x][y] = 'M';
            }// end if //
            else if (garden[x][y] == 35)// good soil //
            {
                // decide if bug attacked //
                bug = bugAttack();
                
                if (bug < 50)// bug attacked //
                {
                    // print outcome //
                    printf("A giant bug attacked the new flower!\nThe Mundane Marigold repelled the attack with its mundane-ness.\nThe Mundane Marigold produced a beautiful bloom.\nYou received 4 points this turn.\n");
                    
                    // assign points //
                    points = 4;
                    
                    // change array value to flower choice //
                    garden[x][y] = 'M';
                }// end if //
                else if (bug > 49)// bug did not attack //
                {
                    // print outcome //
                    printf("The Mundane Marigold produced a beautiful bloom.\nYou received 4 points this turn.\n");
                    
                    // assign points //
                    points = 4;
                    
                    // change array value to flower choice //
                    garden[x][y] = 'M';
                }// end else //
            } // end else //
            break;
            
            
        // Bomb Blossom //
        case 'B':
            if (garden[x][y] == 44)// bad soil //
            {
                // decide if flower blew up using same num gen as bug attack // 
                soil = bugAttack();
                
                if (soil < 34) // bad soil and flower blew up //
                {
                    // print outcome //
                    printf("The Bomb Blossom blew up and died.\n");
                    
                    // assign points and call function to scan for explosion radius //
                    points = searchSurroundings(garden, x, y);
                    
                    // print point outcome to deduct from "searchSurroundings" //
                    printf("You received %d points this turn.\n", points);
                } // end iff //
                else if (soil > 33) // bad soil and flower did not explode //
                {
                    // print outcome //
                    printf("The Bomb Blossom produced a modest bloom.\nYou received 2 points this turn.\n");
                    
                    // assign points //
                    points = 2;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = 'B';
                }// end else //
            }// end if //
            else if (garden[x][y] == 35) // good soil //
            {
                // decide if bug attacked // 
                bug = bugAttack();
                
                if (bug < 50)// bug attacked //
                {
                    // print outcome //
                    printf("A giant bug attacked the new flower!\nThe Bomb Blossom blew up the bug.\nYou received 5 points this turn.\n");
                    
                    // assign points //
                    points = 5;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = '@';
                }// end if //
                else if (bug > 49)// bug did not attack //
                {
                    // print outcome //
                    printf("The Bomb Blossom produced a beautiful bloom.\nYou received 3 points this turn.\n");
                    
                    // assign points //
                    points = 3;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = 'B';
                }// end else //
            }// end else //
            break;
            
            
        // Radiant Rose //
        case 'R':
            if (garden[x][y] == 44) // bad soil //
            {
                // decide if flower bloomed in poor soil //
                soil = bugAttack();
                
                if (soil > 50)// flower bloomed in poor soil //
                {
                    // print outcome //
                    printf("\nThe Radiant Rose produced a modest bloom.\nYou received 4 points this turn.\n");
                    
                    // assign points //
                    points = 4;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = 'R';
                }// end if //
                else if (soil < 49) // flower did not bloom in poor soil //
                {
                    // print outcome //
                    printf("The Radiant Rose wilted and died in the poor soil.\nYou received 0 points this turn.\n");
                    
                    // assign points //
                    points = 0;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = '?';
                }// end else //
            }// end if //
            else if (garden[x][y] == 35) // good soil //
            {
                // decide if bug attacked //
                bug = bugAttack();
                
                if (bug < 50) // bug attacked //
                {
                    // print outcome //
                    printf("A giant bug attacked the new flower!\nThe Radiant Rose was eaten by the bug.\nYou received 0 points this turn.\n");
                    
                    // assign points //
                    points = 0;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = 'X';
                }// end if //
                else if (bug > 49)// bug did not attack //
                {
                    // print outcome //
                    printf("The Radiant Rose produced a beautiful bloom.\nYou received 8 points this turn.\n");
                    
                    // assign points //
                    points = 8;
                    
                    // change array value to reflect flower choice //
                    garden[x][y] = 'R';
                }// end else //
            }// end else // 
            break;
    } // end switch //
    
    // return points //
    return points;
}

int getCoordinate()
{
    // variables //
    int x;
    
    // scan user input //
    scanf("%d", &x);
    
    // detect input error and rescan //
    while (x < 0 || x > 9)
    {
        printf("\nCoordinate out of range!\nPlease pick a new coordinate: ");
        scanf("%d", &x);
    }// end while //
    
    // return valid coordinate //
    return x;
}

char getFlower()
{
    // variables //
    char flower = '0';
    
    // scan for character input //
    printf("\nMundane Marigold (M)\nBomb Blossom (B)\nRadiant Rose (R)\nFlower: ");
    scanf(" %c", &flower);
    
    // detect input error and rescan //
    while (flower != 'M' && flower != 'B' && flower != 'R')
    {
        printf("\nInvalid flower selection!\nPlease try again:\nMundane Marigold (M)\nBomb Blossom (B)\nRadiant Rose (R)\nFlower: ");
        scanf(" %c", &flower);
    }// end while //

    // return selection //
    return flower;
}

int bugAttack()
{
    // variables //
    int x;
    
    // generate random number //
    x = rand() % 99;
    
    // return random number //
    return x;
}

int searchSurroundings(char garden[][10], int x, int y)
{
    // variables //
    int i, j, counter = 0;
    char ch;
    
    // search row //
    for (i = (x - 1); i <= (x + 1); i++)
    {
        // if row index is outside array bounds, skip to next row //
        if (i < 0 || i > 9)
        {
            continue;
        }// end if //
        
        // search column //
        for (j = (y - 1); j <= (y + 1); j++)
        {
            // if column index is outside array bound, skip to next column //
            if (j < 0 || j > 9)
            {
                continue;
            }// end if//
            
            // check if previously planted flower was blown up //
            ch = garden[i][j];
            if (ch == 'M' || ch == 'R' || ch == 'B')
            {
                counter++;
            }// end if //
            
            // change array value to reflect exploded spot in garden //
            garden[i][j] = '@';
        }// end for //
    } // end for //
    
    // return number of points lost //
    return 0 - counter;
}














