//
//
//  Joshua O'Steen
//lab 9

// code: LOVEPOINTER 
//  //

#include <stdio.h>
#include <ctype.h>

#define MAX 50

char *read_string();
char *clean_string(char *str);
char *create_string(char *s);
char *reverse_string(char *s);
int replace_character(char *s, char c);


int main(void)
{
    // variables //
    char *string, *cleanedString, *hexString, *reverseString, c;
    int count = 0;
    
    
    // call read function to get input string //
    string = read_string();
    
    // print out string //
    printf("\nInput string is %s", string);
    
    // call clean function to clean string //
    cleanedString = clean_string(string);
    
    // print out cleaned string //
    printf("\n\nCleaned string is %s", cleanedString);
    
    // call create string to single out hex values //
    hexString = create_string(string);
    
    // pring out hex string //
    printf("\n\nHex string is %s", hexString);
    
    // call reverse function to reverse string //
    reverseString = reverse_string(string);
    
    // print out reversed string //
    printf("\n\nReversed string is %s", reverseString);
    
    // get count of replaced characters //
    printf("\n\nEnter a character to replace: ");
    scanf("%c", &c);
    count = replace_character(string, c);
    printf("\nThere is %d occurence(s) of character %c in the string.", count, c);
    
    
    
    
    
    
}


char *read_string()
{
    int i = 0;
    char c;
    char *s = malloc(MAX * sizeof(char));
    
    puts("Enter the input string: ");
    
    while ((c = getchar()) != '\n')
    {
        *(s+i) = c;
        i++;
    }
    
    return s;
}

char *clean_string(char *str)
{
    // variables //
    int i = 0, j = 0;
    char *string = malloc( MAX * sizeof(char));
    
    //
    while ( *(str+i) != '\0')
    {
        if ( isalnum( *(str+i)) )
        {
            *(string+j) = toupper( *(str+i));
            j++;
        }
        i++;
    }
    
    // return string //
    return string;
    
}

char *create_string(char *s)
{
    // variables //
    int i = 0, j = 0;
    char *string = malloc( MAX * sizeof(char));
    
    //
    while ( *(s+i) != '\0')
    {
        if ( isxdigit( *(s+i)) )
        {
            *(string+j) = toupper( *(s+i));
            j++;
        }
        i++;
    }
    
    // return string //
    return string;
}

char *reverse_string(char *s)
{
    int length, i = 0, j = 0;
    char *copy = malloc((string_length(s)-1)*sizeof(char));
    
    length = string_length(s);
    j = length-1;
    
    while (j>=0)
    {
        *(copy+i) = *(s+j);
        j--;
        i++;
    }
    
    return copy;
}

int string_length(char *s)
{
    int counter = 0, i = 0;
    
    while( *(s+i) != '\0' )
    {
        counter++;
        i++;
    }
    
    return counter;
}

int replace_character(char *s, char c)
{
    int i = 0, counter = 0;
    char *string = malloc( MAX * sizeof(char));
    
    while ( *(s+i) != '\0')
    {
        if ( *(s+i) == toupper(c) || *(s+i) == tolower(c))
        {
            *(string+i) = '@';
            counter++;
        }
        else
        {
            *(string+i) = *(s+i);
        }
        
        i++;
    }
    
    printf("\n\nString with replaced character is %s", string);
    
    return counter;
}








