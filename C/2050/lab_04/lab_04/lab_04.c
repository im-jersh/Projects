//
//  main.c
//  lab_03
//
//  Created by Joshua O'Steen on 9/9/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//
// standard libraries
#include <stdio.h>
#include <stdlib.h>

//
// structures
typedef struct node
{
    int value;
    struct node* next;
}Node;

//
// function prototypes
Node* insert_end(Node* head, int data);
void print_list(Node* head);
int search(Node* head, int data);
void factorial_list(Node *head);
int factorial(int n);
void delete_list(Node* head);


//
// begin main
int main(int argc, char **argv)
{
    // variables
    FILE *txtFile;
    Node *head;
    int data;
    
    
    // error check
    // not enough arguments, end main
    if ( argc < 2)
    {
        printf("\n\nIncorrect number of command line arguments.\n\n");
        return 0;
    }
    // if file opens, scan, send data, create node, append node to end of list
    if ( ( txtFile = fopen( *(argv + 1), "r")) != NULL)
    {
        while ( fscanf( txtFile, "%d", &data) != EOF)
        {
            head = insert_end(head, data);
        }
    }
    // otherwise, end main
    else
    {
        printf("\n\nUnable to open file.");
        return 0;
    }
    
    // print list
    printf("\n\n");
    print_list(head);
    
    // search list for user selection
    printf("\n\nPlease enter a number to search: ");
    scanf("%d", &data);
    
    // print whether data was found in list
    if ( search(head, data) )
    {
        printf("\n\n%d WAS found.", data);
    }
    else
    {
        printf("\n\n%d WAS NOT found.", data);
    }
    
    // replace values with their factorials
    factorial_list( head );
    
    // print list
    printf("\n\n");
    print_list( head );
    
    // delete the link list
    delete_list( head );
    
    // print confirm
    printf("\n\nThe list was deleted and the program was terminated.\n\n");
    
    // main terminates successfully
    return 0;
}


//
// function definitions
//

// insert node at end of list
Node* insert_end(Node* head, int data)
{
    // variables
    Node * newNode = (Node*)malloc(sizeof(Node));
    
    // case if list is empty
    if ( head == NULL)
    {
        newNode->next = NULL;
        newNode->value = data;
        return newNode;
    }
    
    // if looking at the last node on list, append new node to end
    if ( head->next == NULL)
    {
        newNode->value = data;
        newNode->next = NULL;
        head->next = newNode;
        return head;
    }
    // otherwise, recursively call insert node with node after the one you're looking at
    else
    {
        insert_end( head->next, data);
    }
    
    // return
    return head;
}

// recursively print list
void print_list(Node* head)
{
    // if end of list is reached
    if ( head->next == NULL)
    {
        printf(" %d -> NULL", head->value);
        return;
    }
    // print current node value and send next node back to print_list
    else
    {
        printf(" %d ->", head->value );
        print_list( head->next );
    }
    
}

// recursive list search
int search(Node* head, int data)
{
    // if node is null return 0
    if ( head == NULL )
    {
        return 0;
    }
    // if match is found return 1
    if (head->value == data)
    {
        return 1;
    }
    
    // return result
    return search( head->next, data);

}

// replace values with their factorials
void factorial_list(Node *head)
{
    // if head is null, breakout
    if ( head == NULL)
    {
        return;
    }
    else
    {
        head->value = factorial( head->value );
        return factorial_list( head->next );
    }
    
}

// find factorial
int factorial(int n)
{
    // if 1 is reached, return
    if ( n == 1)
    {
        return 1;
    }
    else
    {
        return (n * factorial( n-1 ));
    }
    
}

// delete link list
void delete_list(Node* head)
{
    // if end of list is reached, return
    if(head == NULL)
    {
        return;
    }
    
    delete_list(head->next);
    free(head);
    
    return;
    
}
















