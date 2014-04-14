//Joshua O'Steen
//  lab 11
//  FREE
//
//  Created by Joshua O'Steen on 5/9/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctype.h>

#define MAX_LENGTH 30
#define MAX 15

typedef struct {
	char name[MAX_LENGTH];
	int account;
	float amount;;
}bank;

typedef struct{
    int account;
    float ammount;
}update;

// Global variables //
bank customer[MAX];
FILE *in, *out, *utp;

int openFiles(char *input, char *output, char *trp);
int readContent();
void updateInfo(int size);
void writefile(int size);

int main(int argc, char * argv[])
{
    int openFileReturn = 0, numCustomers = 0;
    
    
    if (argc < 4) // if not enough arguments from command line //
    {
        printf("\n\nNot enough parameters.\n\n");
        return 0;
    }
    
    // call to open files with command line input //
    openFileReturn = openFiles(argv[1], argv[2], argv[3]);
    
    // error check or continue //
    if ( openFileReturn == -1) // if files did not open //
    {
        printf("\n\nUnable to open the files\n\n");
    }
    else if (openFileReturn == 0) // all files were opened //
    {
        
        printf("\n\nAll files were opened.\n\n");
        
        
        // call to populate array //
        numCustomers = readContent();
        
        // update info //
        //updateInfo(numCustomers);
        
        
    }
    
    return 0;
    
}



int openFiles(char *input, char *output, char *trp)
{
    // local variables //
    int openFileValue = 0, confirm1 = 0, confirm2 = 0, confirm3 = 0;
    FILE *in, *out, *utp;
    
    // open file streams //
    in = fopen("input.txt", "r");
    out = fopen("output.txt", "r");
    utp = fopen("update.txt", "w");
    
    // confirm files opened //
    if ( in != NULL)
    {
        confirm1 = 1;
    }
    else
    {
        confirm1 = 0;
    }
    
    if ( out != NULL)
    {
        confirm2 = 1;
    }
    else
    {
        confirm2 = 0;
    }
    
    if ( utp != NULL)
    {
        confirm3 = 1;
    }
    else
    {
        confirm3 = 0;
    }
    
    // print confirms //
    printf("\n\n%d    %d    %d", confirm1, confirm2, confirm3);
    
    
    
    // check that all three files opened properly //
    if ((confirm1+confirm2+confirm3) == 3)
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
    int i = 0, j = 0, customerCount = 0;
    FILE *in;
    
    in = fopen("input.txt", "r");
    
    // scan file for number of customers //
    fscanf(in, "%d", &customerCount);
    
    // scan file into array //
    while (fscanf(in,"%d %s %f", &customer[i].account, customer[i].name, &customer[i].amount) != EOF)
    {
        i++;
    }
    
    
    for (j = 0; j < (i); j++)
    {
        printf("\n%d\n%s\n%.2f\n\n\n", customer[j].account, customer[j].name, customer[j].amount);
    }
    
    
    
    // return //
    return customerCount;
}

/*
void updateInfo(int size)
{
    // local variables //
    int i = 0, customerCount = 0, j, k, u;
    
    // scan file for number of customers //
    fscanf(utp, "%d", &customerCount);
    
    // make array for update info //
    update updateInfo[customerCount];
    
    // read update file //
    
    int account;
    float amount;
    
    for (j = 0; j < customerCount; j++)
    {
        fscanf(utp,"%d %f", &account, &amount);
        for (k = 0; k < size; k++)
        {
            if (customer[k].account == account)
            {
                customer[k].amount += amount;
            }
        }
    }
    
    for (u = 0; u < size; u++) {
        printf("\n%d %s %f", customer[u].account, customer[u].name, customer[u].amount);
    }
    
}

 */










