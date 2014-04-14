//
//  main.c
//  lab_01
//
//  Created by Joshua O'Steen on 8/21/13.
//  
//


// include libraries //
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctype.h>

// define constants //
#define MAX_LENGTH 30
#define MAX 15

// define structures //
typedef struct {
	char name[MAX_LENGTH];
	int birth;
    int death;
	int reign;
}data;

// global variables//
data emperors[MAX];
FILE *in, *out;

// funtion prototypes //
int openFiles(char *input, char *output);
int readContent();
int emperor_to_live_longest(int size);
int youngest_emperor_to_die(int size);
int longest_lifetime_reign(int size);
int emperor_to_rule_longest(int size);
void writeFile(int oldest, int youngest, int longest, int successful, int size);
void closeFiles();


// start main //
int main(int argc, char * argv[])
{
    // local variables //
    int openFileReturn = 0, numberOfEmperors = 0, indexOfLongestLife = 0, indexOfYoungestDeath = 0, indexOfLongestReign = 0, indexOfLongestRule = 0, age = 0, i=0;
    
    // error check to make sure enough parameters were sent to main //
    if (argc < 3) 
    {
        printf("\n\nNot enough parameters.\nPlease try again using 'emperors.txt summarty.txt\n\n");
        return 0;
    }
    
    // call to open files with command line parameters //
    openFileReturn = openFiles(argv[1], argv[2]);
    
    // error check or continue //
    if ( openFileReturn == -1) // if files did not open //
    {
        printf("\n\nUnable to open the files\n\n");
    }
    else if (openFileReturn == 0)
    {
        // determine required indexes //
        numberOfEmperors = readContent();
        indexOfLongestLife = emperor_to_live_longest(numberOfEmperors);
        indexOfYoungestDeath = youngest_emperor_to_die(numberOfEmperors);
        indexOfLongestRule = emperor_to_rule_longest(numberOfEmperors);
        indexOfLongestReign = longest_lifetime_reign(numberOfEmperors);
        
        // write to file //
        writeFile(indexOfLongestLife, indexOfYoungestDeath, indexOfLongestRule, indexOfLongestReign, numberOfEmperors);
        
        // close files //
        closeFiles();
        
        }
    
    return 0;
}




// function definitions //
int openFiles(char *input, char *output)
{
    // local variables //
    int openFileValue = 0, confirm1 = 0, confirm2 = 0;
    
    
    // open files set confirm values //
    if ( (in = fopen("emperors.txt", "r")) != NULL)
    {
        confirm1 = 1;
    }
    else
    {
        confirm1 = 0;
    }
    
    if ( (out =fopen("summary.txt", "w")) != NULL)
    {
        confirm2 = 1;
    }
    else
    {
        confirm2 = 0;
    }
    
    
    // check that all files opened properly //
    if ((confirm1+confirm2) == 2)
    {
        openFileValue = 0;
    }
    else
        openFileValue = -1;
    
    
    // return value //
    return openFileValue;
}

int readContent()
{
    // local variables //
    int i = 0, emperorCount = 0;
    
    // scan file for number of customers //
    fscanf(in, "%d", &emperorCount);
    
    // scan file into array //
    while (fscanf(in,"%s %d %d %d", emperors[i].name, &emperors[i].birth, &emperors[i].death, &emperors[i].reign) != EOF)
    {
        i++;
    }
    
    // return //
    return emperorCount;
}

int emperor_to_live_longest(int size)
{
    // local variables //
    int i = 0, returnIndex = 0, hold = 0;
    
    // hold first age for comparison //
    hold = emperors[i].death - emperors[i].birth;
    
    // determing age of emperors //
    for (i = 0; i < size  ; i++)
    {
        // get oldest emperor //
        
        if (hold < emperors[i].death - emperors[i].birth)
        {
            hold = emperors[i].death - emperors[i].birth;
            returnIndex = i;
        }
        
    }
    
    // return index of oldest emperor //
    return returnIndex;
    
}

int youngest_emperor_to_die(int size)
{
    // local variables //
    int i = 0, returnIndex = 0, hold = 0;
    
    // hold first age for comparison //
    hold = emperors[i].death - emperors[i].birth;
    
    // determing age of emperors //
    for (i = 0; i < size  ; i++)
    {
        // get youngest emperor //
        
        if (hold > emperors[i].death - emperors[i].birth)
        {
            hold = emperors[i].death - emperors[i].birth;
            returnIndex = i;
        }
        
    }
    
    // return index of oldest emperor //
    return returnIndex;
    
}

int emperor_to_rule_longest(int size)
{
    // local variables //
    int i = 0, returnIndex = 0, hold = 0;
    
    // hold first age for comparison //
    hold = emperors[i].reign;
    
    // determing regin of emperors //
    for (i = 0; i < size  ; i++)
    {
        // get longest reign //
        
        if (hold < emperors[i].reign)
        {
            hold = emperors[i].reign;
            returnIndex = i;
        }
        
    }
    
    // return index of oldest emperor //
    return returnIndex;
    
}

int longest_lifetime_reign(int size)
{
    // local variables //
    int i = 0, returnIndex = 0, hold = 0;
    
    // hold first age for comparison //
    hold = ((emperors[i].death - emperors[i].birth) - emperors[i].reign);
    
    // determing age of emperors //
    for (i = 0; i < size  ; i++)
    {
        // get youngest emperor //
        
        if (hold > (emperors[i].death - emperors[i].birth) - emperors[i].reign)
        {
            hold = ((emperors[i].death - emperors[i].birth) - emperors[i].reign);
            returnIndex = i;
        }
        
    }
    
    // return index of oldest emperor //
    return returnIndex;
    
}

void writeFile(int oldest, int youngest, int longest, int successful, int size)
{
    // local variables //
    int i = 0;
    
    // write to file //
    for (i = 0; i < size; i++)
    {
        fprintf(out, "\n\n%s\nBirth: %d\nDeath: %d\nReign: %d", emperors[i].name, emperors[i].birth, emperors[i].death, emperors[i].reign);
    }
    
    fprintf(out, "\n\n---------------------");
    fprintf(out, "\nThe oldest emperor to die was %s who was %d years old.", emperors[oldest].name, (emperors[oldest].death - emperors[oldest].birth));
    fprintf(out, "\nThe youngest emperor to die was %s who was %d years old.", emperors[youngest].name, (emperors[youngest].death - emperors[youngest].birth));
    fprintf(out, "\nThe emperor to rule the longest was %s who reigned for %d years.", emperors[longest].name, emperors[longest].reign);
    fprintf(out, "\nThe emperor to spend the most of his life in reign was %s who reigned for %d and died at age %d.", emperors[successful].name, emperors[successful].reign, (emperors[successful].death - emperors[successful].birth));
}

void closeFiles()
{
    fclose(in);
    fclose(out);
}


/*
// test print array //
for (j = 0; j < (i); j++)
{
    printf("\n%s\n%d\n%d\n%d\n\n", emperors[j].name, emperors[j].birth, emperors[j].death, emperors[j].reign);
}
*/










