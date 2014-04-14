//
//  Joshua O'Steen
//  lab_11
//  jrocnc
//  
//
//

// libraries
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// macros
#define MAX_LINE 250

// prototypes
int isDate(char* string);
int isPhoneNumber(char* string);
int isName(char* string);
int isEmail(char* word);

// main
int main(int argc, char * argv[])
{
    FILE *input;
    
    // file check
    if (argc != 2) {
        printf("You did not enter enough arguments.");
        return 1;
    }
    if (!(input = fopen(argv[1], "r"))) {
        printf("The file '%s' could not be open.", argv[1]);
        return 1;
    }
    
    // variables
    char buffer[MAX_LINE];
    char *token = NULL, *token2 = NULL;
    int count = 0;
    
    // read line by line
    while (fgets(buffer, MAX_LINE, input) != NULL) {
        
        // replace newline character
      
            buffer[strlen(buffer) -1] = '\0';
        
        
        // get first token
        token = strtok(buffer, " ,\t");
        
        while (token != NULL) {
            
            // check for date
            if (strlen(token) == 8) {
                if (isDate(token)) {
                    printf("\nFound a date: %s", token);
                }
            }
            
            // check for phone number
            if (strlen(token) == 14) {
                if (isPhoneNumber(token)) {
                    printf("\nFound a phone number: %s", token);
                }
            }
            
            // check for email
            if (isEmail(token)) {
                printf("\nFound an email: %s", token);
            }
            
            // check for first and last name
            if (isName(token)) {
                
                if ( isName((token2 = strtok(NULL, " ,\t")))) {
                    printf("\nFound a name: %s %s", token, token2);
                    count++;
                }
            }
            
            // bump word counter
            count++;
            
            // get next token
            token = strtok(NULL, " ,\t");
        }
        
        
    }
    
    // print number of words found
    printf("\nThere were %d words in the file.", count);
    
    
    return 0;
}





int isDate(char* string)
{
    // check the special characters
    if (string[2] != 47 || string[5] != 47)
        return 0;
    
    // variables
    int month = 0, day = 0, year = 0;
    
    // scan string into int variables
    sscanf(string, "%d/%d/%d", &month, &day, &year );
    
    // error check variables
    if (month < 1 || month > 12)
        return 0;
    if (day < 1 || day > 31)
        return 0;
    if (year < 0 || year > 99)
        return 0;
    
    // return success
    return 1;
    
}



int isPhoneNumber(char* string)
{
    // check for special characters
    if (string[0] != 40 || string[4] != 41 || string[5] != 45 || string[9] != 45)
        return 0;
    
    // variables
    int first = 0, second = 0, third = 0;
    
    // scan string into int variables
    sscanf(string, "(%d)-%d-%d", &first, &second, &third);
    
    // error check variables
    if (first < 0 || first > 999)
        return 0;
    if (second < 0 || second > 999)
        return 0;
    if (third < 0 || third > 9999)
        return 0;
    
    // return success
    return 1;
}

int isEmail(char * word)
{
    // variables
    int i = 0, atSym = 0, period = 0;
    
    // check for special characters
    for (i = 0; i < strlen(word); i++) {
        if (word[i] == 64)
            atSym = 1;
        if (word[i] == 46)
            period = 1;
    }
    
    // check flags
    if (atSym == 1 && period == 1)
        // return success
        return 1;
    
    // failed
    return 0;
}


int isName(char *string)
{
    // check for capital letters
    if (string[0] < 65 || string[0] > 90)
        return 0;
    
    // variables
    int i = 0;
    
    // check for lower case letters
    for (i = 1; i < strlen(string); i++) {
        if (string[i] < 97 || string[i] > 122)
            return 0;
    }
    
    // return success
    return 1;
}














