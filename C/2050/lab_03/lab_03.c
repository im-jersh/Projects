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
Node* create_Node(int data);
Node* insert_Front(Node* list, Node* new_node);
void print_List(Node* list);
void delete_list(Node* list);
void add_Int(Node* list, int value);
Node* delete_Range(Node* list, int low, int high);

//
// begin main 
int main(void)
{
    // local variables
    int data = 0, i = 0, addValue = 0, low = 0, high = 0;
    Node* L = NULL;
    
    // seed random number generator //
    srand(time(NULL));
    
    // print newline
    printf("\n\n");
    
    // generate link list of 10 random numbers
    for (i = 0; i < 10; i++)
    {
        // nested function
        // send "data" to make new node
        // send new node to be inserted at front of link list
        // sets L to beginning of link list each time through the loop
        
        // create random number between 20 - 60
        data = ((rand() % 41) + 20);
        
        // send data to create new node, insert new node into list
        L = insert_Front(L, create_Node(data));
        
    }// end for
    
    // print link list
    print_List(L);
    
    // request value from user to add to each node
    printf("\n\nPlease enter an integer value to add to each node value: ");
    scanf("%d", &addValue);
    
    // send value to be added to each node
    add_Int(L, addValue);
    
    // print link list
    printf("\n\n");
    print_List(L);
    
    // request range to delete from user
    printf("\n\nPlease enter a range in the form 'low high' to delete from the list: ");
    scanf("%d%d", &low, &high);
    
    // delete range from list
    L = delete_Range(L, low, high);
    
    // print link list only if there are nodes to print
    if ( L != NULL)
    {
        printf("\n\n");
        print_List(L);
    }
    
    // delete link list
    delete_list(L);
    
    // main terminates successfully
    return 0;
}


//
// function definitions
//
// creates a new node with parameter 'data' placed
// in node.value and node.next as NULL
// returns pointer to node
Node* create_Node(int data)
{
    // node pointer
    Node *N;
    
    // allocate memory for node
    N = (Node*)malloc(sizeof(Node));
    
    // initialize node
    N->value = data;
    N->next = NULL;
    
    // return new node
    return N;
    
}
//
// takes parameter 'new_node' and adds it to the
// front of 'list'
// returns 'new_node' since it's at the beginning
Node* insert_Front(Node* list, Node* new_node)
{
    // link new node to exising link list
    new_node->next = list;
    
    // return updated link list
    return new_node;
    
}
//
// prints the contents of 'list' until end of list is reached
void print_List(Node* list)
{
    // node pointer
    Node *N;
    
    // set N to point to first node of L
    N = list;
    
    // while N doesn't point to an empty node
    // print N's property and advance to next node
    while (N != NULL)
    {
        printf("%d", N->value);
        N = N->next;
        
        // print arrow if current node is not last node
        if (N != NULL)
        {
            printf(" -> ");
        }
        
        // print arrow,NULL and new line if last node
        if (N == NULL)
        {
            printf(" -> NULL\n");
        }
    }
}
//
// deletes 'list' one node at a time by freeing
// allocated memory
// confirms deletion by attempting to print 'list'
void delete_list(Node* list)
{
    // placeholder pointer
    Node *hold, *N = list;
    
    while (N != NULL)
    {
        hold = N->next;
        free(N);
        N = hold;
    }
    
    //print confirm when list is deleted
    if (N == NULL)
    {
        // print the list just to be sure
        print_List(N);
        printf("\n\nList Successfully Deleted From Memory.\nProgram Terminated.\n\n");
    }
    
}
//
// adds value from user to each node's integer value,
void add_Int(Node* list, int value)
{
    // variables
    Node *node = list;
    
    // while loop to transverse each node to add value
    while ( node != NULL)
    {
        node->value += value;
        node = node->next;
    }
    
}
//
// delete nodes from within range
// return pointer to beginning of list
Node* delete_Range(Node* list, int low, int high)
{
    //variables
    Node *hold = NULL, *previousNode = list, *currentNode = list->next;
    
    
    // return from function of low > high
    if ( low > high)
    {
        printf("\n\nYou entered a range in the incorrect format.\n");
        return  list;
    }
    else
    {
        // delete node if found to be within range and restructure list
        while ( currentNode != NULL )
        {
            // if loop starts and finds that first node needs to be deleted
            if ( previousNode->value >= low && previousNode->value <= high )
            {
                hold = previousNode;
                previousNode = currentNode;
                currentNode = currentNode->next;
                list = previousNode;
                free(hold);
                
            }
            // if node to be deleted is in middle of list
            else if ( currentNode->value >= low && currentNode->value <= high)
            {
                // special case if node to be deleted is last node
                if (currentNode->next == NULL)
                {
                    previousNode->next = NULL;
                    free(currentNode);
                }
                //otherwise preceed to restructuring previous and next nodes
                else
                {
                    hold = currentNode;
                    currentNode = currentNode->next;
                    previousNode->next = currentNode;
                    free(hold);
                }
            }
            // if node to be deleted is only node on list
            
            else
            {
                previousNode = currentNode;
                currentNode = currentNode->next;
            }
        }
    }
    
    // special case where there is only one node left and it is within range
    if (previousNode->value >= low && previousNode->value <= high && previousNode->next == NULL)
    {
        free(previousNode);
        return NULL;
    }
    
    
    
    // return beginning of list
    return list;
}
