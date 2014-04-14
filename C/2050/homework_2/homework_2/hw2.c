//  Joshua O'Steen
//  hw2.c
//  jrocnc
//  14041320
//
//
// INITIAL COMMENTS:
// I copied and pasted code from the given broken file
// into this file so some functions definitions are
// rearranged within my code to reflect the order to which
// they are used.
//
// Block commented errors out as instructed. Line comments
// were used for my own sake to more easily follow code
//
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Assume max char count in file */
#define MAX 20

/* library struct - DO NOT CHANGE */
typedef struct library
{
	char *genre;
	char *band;
	char *album;
	float rating;
	struct library *next;
}Library;

/* Function Prototypes - DO NOT CHANGE */
Library *Read_File(FILE *);
Library *Create_Album(char *, char *, char *, float);
Library *Create_List(Library *, Library *);
void Print(Library *);
Library *Merge_Sort(Library *);
int Count_List(Library *);
void Split_List(Library *, Library **, Library **);
Library *Merge(Library *, Library *);
Library *Delete_Genre(Library *);
void Free_Entry(Library *);
Library *Clean(Library *);

/* MAIN
 * Error check file parameter
 * Call Read_File to fill our list
 * Print our list
 * Merge Sort the linked list (by genre)
 * Delete a genre
 * Free the list
 */

int main(int argc, char **argv)
{
    // if not enough command line arguments
    if(argc != 2)
    {
        printf("\n\nNot enough arguments.\n");
        return 0;
    }
    
    // file pointer variable
    FILE *fp = NULL;
    
    // open file
    if( (fp = fopen(argv[1], "r") ) == NULL)
    {
        // prompt user if file was not opened properly
        printf("\n\nFile can not be opened.\n");
        return 0;
    }
    
    
    /*
     * Head should be pointer of type Library initialized to NULL
     */
    Library *Head = NULL;
    
    // read file and create list
    Head = Read_File(fp);
    
    /* Print(&Head);
     * parameter Head in Print() does not need the address
     * operand as the function takes a pointer of type Library
     */
    Print(Head);
    
    // merge sort list
    Head = Merge_Sort(Head);
    
    /* Print(&Head);
     * parameter Head in Print() does not need the address
     * operand as the function takes a pointer of type Library
     */
    Print(Head);
    
    // delete genre from list
    Head = Delete_Genre(Head);
    
    /* Print(&Head);
     * parameter Head in Print() does not need the address
     * operand as the function takes a pointer of type Library
     */
    Print(Head);
    
    // delete full list
    Head = Clean(Head);
    
    /* Print(&Head);
     * parameter Head in Print() does not need the address
     * operand as the function takes a pointer of type Library
     */
    Print(Head);
    
    
    return 0;
}




//
// FUNCTION DEFINITIONS
//

/* Read_File()
 * Open file fp
 * Create a struct from information in text file
 * Link our list with newly created struct
 */
Library * Read_File(FILE *fp)
{
    // variables
    Library *Head, *Entry;
    Head = Entry = NULL;
	
    /* char *genre, *band, *album;
     * good practice to initialize pointers.
     */
    char *genre = NULL, *band = NULL, *album = NULL;
    float rating;
    
    
    /*
    while(1)
    {
        fscanf(fp, "%s %s %s %f", genre, band, album, &rating);
        if(!feof(fp))
            break;
        Entry = Create_Album(genre, band, album, rating);
        Head = Create_List(Entry, Head);
    }
    * FAULTY LOGIC
    * while loop will try to scan one extra line from the file
    * since the if statement to break out of loop is after the fscanf
    * and the while condition is always true since it is 1.
    *
    * malloc memory for string read in from file since declared as char
    *
    * if condition "not EOF" should be the while condition.
    *
    * Create_Album function can be nested within Create_List function
    * an argument. Also, arguments for Creat_List are switched according
    * to the prototype definition
    *
    * NOTE: using "!feof(fp)" as while argument looped one too many times
    *       so i used the "fscanf() != EOF" as the argument to remedy this
    *       this error
    */
    while ((fscanf(fp, "%s %s %s %f", genre = malloc(MAX * sizeof(char)),
                   band = malloc(MAX * sizeof(char)), album = malloc(MAX * sizeof(char)), &rating)) != EOF)
    {
        // nested function to create struct, populate it, then
        // add it to the beginning of the link list
        Head = Create_List(Head, Create_Album(genre, band, album, rating) );

    }
    
    // return head of link list
    return Head;
}

/* Create_Album()
 * Create a struct and assign the given args to it as appropriate
 */
Library * Create_Album(char *genre, char *band, char *album, float rating)
{
    /* Library *Entry = malloc(sizeof(Library));
     * cast memory to type Library reference by pointer Entry
     */
    Library *Entry = (Library*)malloc(sizeof(Library));
    
    /*strcpy(Entry->genre, genre);
     *strcpy(Entry->band, band);
     *strcpy(Entry->album, album);
     *
     * SEG FAULT
     * unnecessary to use strcpy() function as memory has already been
     * allocated and assigned strings. simply assign arguments 
     * to node properties
     */
    Entry->genre = genre;
    Entry->band = band;
    Entry->album = album;
    Entry->rating = rating;
    Entry->next = NULL;
    
    // return pointer to Entry
    return Entry;
}

/* Create_List()
 * Push Entry onto beginning of List
 */
Library * Create_List(Library *Head, Library *Entry)
{
    // returns Entry if Head is NULL and list is empty
    if (!Head)
        return Entry;
    
    // otherwise, sets next property in Entry to head of
    // list and returns Entry as new head of list
    Entry->next = Head;
    return Entry;
}

/* Print()
 * Print the linked list
 */
void Print(Library *Head)
{
    // variable
    int i = 1;
    
    // if Head is NULL and list is empty
    if(!Head)
    {
        // print prompt and return to main
        printf("\nLibrary is empty.\n");
        return;
    }
    
    // print newlines to separate printed lists
    printf("\n\n%-5s%-12s%-25s%-20s%-10s\n", "#", "Genre", "Artist", "Album", "Rating");
    
    /*while(Head)
    {
        // print formatted node properties
        printf("%20s %20s %20s %20.2f \n",
               Head->genre, Head->band, Head->album, Head->rating);
        
        // set head to next node for printing
        Head = Head->next;
    }
     *
     * format specifiers should specify a negative width modifier so
     * all colums are aligned to the left left rather than the right
     *
     * NOTE: changed width length to make more sense of printed info
     */
    while (Head)
    {
        // print formatted node properties
        printf("%-5d%-12s%-25s%-20s%-5.2f\n", i++,
               Head->genre, Head->band, Head->album, Head->rating);
        
        // set head to next node from printing
        Head = Head->next;
    }
    
    
    /*return Head;
     * returning Head when function return is void.
     * nothing added just commented out
     */
}

/* Merge_Sort()
 * Recursively split our list between Left and Right
 * Merge our Left and Right lists
 */
Library *Merge_Sort(Library *Head)
{
    // variables
    Library *Tmp = Head;
    Library *Left, *Right, *Result;
    Left = Right = Result = NULL;
    
    
    // variable to hold number of nodes in list
    int count = Count_List(Head);
    
    /* if(count = 1)
        return Tmp;
     *
     * assignment operator used rather than equality operator.
     * could return Tmp or Head if only 1 element in list
     */
    if (count == 1)
    {
       return Tmp;
    }
    
    /* No use of Split_List() function to split list into
     * Left and Right
     *
     */
    Split_List(Tmp, &Left, &Right);
    
    /* Left = Merge_Sort(*Left);
     * Right = Merge_Sort(*Right);
     *
     * arguments for recursive calls are dereference when they 
     * should not be
     */
    Left = Merge_Sort(Left);
    Right = Merge_Sort(Right);
     
    
    Result = Merge(Left, Right);

    // return head of sorted list
    return Result;

}

/* Count_List()
 * Count the number of elements in a linked list
 */
int Count_List(Library *Head)
{
    // variables
    Library *Tmp = Head;
    int count = 0;
    
    /* while(Tmp->next != NULL)
    {
        count++;
        Tmp = Tmp->next;
    }
     *
     * loop runs short. should be 12 but is checking next
     * node for NULL rather than current node
     */
    while (Tmp != NULL)
    {
        count++;
        Tmp = Tmp->next;
    }
    
    /* function reaches end of flow without returning
     * an int value. return count
     */
    return count;
}

/* Split_List()
 * split our list in half
 */
void Split_List(Library *Head, Library **Left, Library **Right)
{
    /*
     * Use of undeclared Library pointer Tmp below
     * in for loop
     */
    Library *Tmp = Head;
    
    // variables
    int size = Count_List(Head);
    int i;
    
    // set the head of list to beginning of Left side
    *Left = Head;
    
    // traverse half the list based on size
    for(i=1; i < size/2; ++i)
        Tmp = Tmp->next;
     
    // set halfway point to beginning of Right side
    *Right = Tmp->next;
    Tmp->next = NULL;
    
}

/* Merge()
 * Merge two linked lists Left and Right together in sorted order
 */
Library * Merge(Library *Left, Library *Right)
{
    /*Library *Result, *Tmp;
    Result = Tmp = NULL;
    
    if(strcmp(Left->genre, Right->genre) <= 0)
    {
        Result = Left;
        Left = Left->next;
    }
    else
    {
        Result = Right;
        Right = Right->next;
    }
    Tmp = Result;
    
    while(Left != NULL && Right != NULL)
    {
        if(Left != NULL && Right!= NULL)
        {
            if (strcmp(Left->genre, Right->genre) <= 0)
            {
                Tmp->next = Left;
                Left = Left->next;
            }
            else
            {
                Tmp->next = Right;
                Right = Right->next;
            }
            Tmp = Tmp->next;
        }
    }
    return Result;
     *
     * NOTE: too many logical errors so i decided to do
     * the Merge() function recursively
     */
    
    
    // variables
    Library *Result = NULL;
    
    // Base cases
    if (Left == NULL)
        return(Right);
    else if (Right == NULL)
        return(Left);
    
    // picks Left or Right and recursively selects which to add
    // to the list to return
    //
    // if left node comes before right node
    if (strcmp(Left->genre, Right->genre) <= 0)
    {
        // add left node to result, recur
        Result = Left;
        Result->next = Merge(Left->next, Right);
    }
    // else right node comes before left node
    else
    {
        // add right node to result, recur
        Result = Right;
        Right->next = Merge(Left, Right->next);
    }
    return(Result);
}

/* Delete_Genre()
 * Deletes a genre inputted by user
 * Logic:
 * prompt user for genre input
 * traverse list deleting all structs that contain the genre
 */
Library * Delete_Genre(Library *Head)
{
    // check list
    if(!Head)
    {
        /*printf("List Empty.\n");
         * 
         * redundant to print within this function since main
         * will call Print() and print the same thing
         */
        
        // return NULL if list is empty
        return NULL;
    }
    
    // variables
    char *input = malloc(MAX * sizeof(char *));
    Library *Current = Head,
    *Tail = Head;
    
    /*
     * need Tmp variable to hold node to be freed
     */
    Library *Tmp = NULL;

    
    // prompt user for selection
    printf("\n\nWhich genre would you like to delete?\n");
    scanf("%s", input);
	
    
    while(Current)
    {
        /*if(strcmp(Current->genre, input))
         *
         * condition should be compared to 0 or subsequent if/else
         * statments should follow a false condition
         */
        if(strcmp(Current->genre, input) == 0)
        {
            /*if(Current = Head)
             *
             * comparison operator instead of assignment operator
             */
            if(Current == Head)
            {
                Head = Head->next;
                Free_Entry(Current);
                Current = Head;
            }
            else
            {
                /*Tail->next = Current->next;
                 *
                 * LOGIC ERROR: should hold node to be freed in Tmp,
                 * set current to node after current, and link tail to
                 * current
                 */
                Tmp = Current;
                Current = Current->next;
                Tail->next = Current;
                Free_Entry(Tmp);
            }
        }
        else
        {
            /*Current = Current->next;
             *
             * LOGIC ERROR: should update Tail as well as Current
             */
            Tail = Current;
            Current = Current->next;
        }
    }
    
    /* Control reaches end of non-void function. return Head
     *
     */
    return Head;
}

/* Free_Entry()
 * wrapper function to free a struct Library
 */
void Free_Entry(Library *Entry)
{
    /* function should also free memory allocated to
     * hold the string properties
     */
    Entry->next = NULL;
    free(Entry->genre);
    free(Entry->band);
    free(Entry->album);
    free(Entry);
}

/* Clean()
 * Delete the linked list, recursively
 */
Library * Clean(Library *Head)
{
    /*if(Head) return NULL;
     *
     * should be if Head is NULL
     */
    if (!Head)
    {
        return NULL;
    }
    
    
    Library *Tmp = Head->next;
    Free_Entry(Head);
    
    /*Clean(Tmp->next);
     *
     * LOGIC ERROR: should recur Tmp not Tmp->next.
     * this would skip a node
     */
    Clean(Tmp);
    
    
}










