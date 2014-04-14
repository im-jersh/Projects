//  Josh O'Steen
//  main.c
//  lab_07
//

// standard libraries
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// structure definitions
typedef struct spqueue
{
	int priority;
    char * value;
	struct spqueue *next;
}SubPQueue;

typedef struct pqueue
{
    int priority;
    SubPQueue * subQueue;
    struct pqueue *next;
}PQueue;

// constants
#define MAX_VALUE_SIZE 20
#define MAX_LINE_LENGTH 100

// function prototypes
FILE * openFile(char *argv, int argc, FILE *in);
void enqueue(PQueue** queue, int priority1, int priority2, char* Value);
char* dequeue(PQueue** queue);
void free_sub_node(SubPQueue* node);
void print_queue(PQueue* node);
int isElement(PQueue *head, int priority);
void print_queue(PQueue* node);

// begin main
int main(int argc, char **argv)
{
    // local variables
    FILE *input = NULL;
    PQueue *head = NULL;
    char *fileInstruct = malloc(1 + MAX_LINE_LENGTH * sizeof(char)),
        *value = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
    int priority1 = 0, priority2 = 0;
    
    // validate and open file
    if (!(input = openFile( *(argv + 1), argc, input)))
        return 1;

    
    // scan first word from line until EOF
    while ((fscanf(input, "%s", fileInstruct)) != EOF)
    {
        // decide which function to execute based on scanned instruction
        if (!strcmp(fileInstruct, "enqueue"))
        {
            // scan rest of line for values
            fscanf(input, "%d %d %s", &priority1, &priority2, value);
            
            // add value to priority queue
            enqueue(&head, priority1, priority2, value);
            
        }
        else if(!strcmp(fileInstruct, "dequeue"))
        {
            // pointer to value in subqueue node that was dequeued
            char *dequeueValue = NULL;
            
            // dequeue based on priority and print
            dequeueValue = dequeue(&head);
            printf("\n%s", dequeueValue);
            
            // free allocate memory
            free(dequeueValue);
        }
        else if(!strcmp(fileInstruct, "print"))
        {
            // print queue
            print_queue(head);
        }
        else
            // print instruction error
            printf("\n\nInvalid command: %s\n\n", fileInstruct);
        
    }
    
    // free allocated memory, close file pointer, print, terminate
    free(value);
    free(fileInstruct);
    fclose(input);
    printf("\n\nFile Closed &\nProgram Terminated.\n\n");
    
    return 1;
}


//function definitions

FILE * openFile(char *argv, int argc, FILE *in)
{
    // if not enough arguments or bad file name
    if (argc != 2)
    {
        printf("\n\nYou did not enter the correct number of arguments.\nPlease enter './a.out' then 'file.ext' separated by one space\n\nProgram Terminated.\n\n");
        return NULL;
    }
    else if ((in = fopen(argv, "r")) == NULL)
        printf("\n\nThe file '%s' was not opened properly.\nPlease try again ensuring the input file is in the\ncorrect folder and the argument is typed correctly\n\n", argv);
    
    // return file pointer
    return in;
}

int isElement(PQueue *head, int priority)
{
    // if queue is empty
    if (!head)
    {
        return 0;
    }
    
    // otherwise traverse queue to find priority value
    while (head)
    {
        // value found
        if (head->priority == priority)
        {
            return 1;
        }
        else
            head = head->next;
    }
    
    // value not found
    return 0;
}

int isSubElement(SubPQueue *head, int priority)
{
    // if subqueue is empty
    if (!head)
    {
        return 0;
    }
    
    // otherwise traverse subqueue to find priority value
    while (head)
    {
        // value found
        if (head->priority == priority)
        {
            return 1;
        }
        else
            head = head->next;
    }
    
    // value not found
    return 0;
}

void enqueue(PQueue** queue, int priority1, int priority2, char* value)
{
    // pointers to traverse respective queue
    PQueue *traverse = *queue;
    SubPQueue *subTraverse = NULL;
    
    // empty queue
    if (traverse == NULL)
    {
        // allocate and initialize queue node
        PQueue *insert = (PQueue*)malloc(sizeof(PQueue));
        insert->priority = priority1;
        insert->next = NULL;
        insert->subQueue = NULL;
        
        // allocate and initialize subqueue node
        SubPQueue *subInsert = (SubPQueue*)malloc(sizeof(SubPQueue));
        subInsert->priority = priority2;
        subInsert->next = NULL;
        subInsert->value = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
        strcpy(subInsert->value, value);
        
        // link subnode to main node
        *queue = insert;
        insert->subQueue = subInsert;
        
        return;
    }
    // priority1 is in queue already
    else if(isElement(*queue, priority1))
    {
        // copy string value into local variable
        char *subInsertValue = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
        strcpy(subInsertValue, value);
        
        // traverse queue until node with priority1 is found
        while (traverse->priority != priority1)
        {
            traverse = traverse->next;
        }
        
        // link subtraverse pointer to current nodes subqueue
        subTraverse = traverse->subQueue;
        
        // if subqueue priority already exists
        if (isSubElement(subTraverse, priority2))
        {
            // find node with priority2
            while (subTraverse->priority != priority2)
            {
                subTraverse = subTraverse->next;
            }
            
            // allocate and initialize then add to tail end
            // of existing priority2 node
            SubPQueue *subInsert = (SubPQueue*)malloc(sizeof(SubPQueue));
            subInsert->priority = priority2;
            subInsert->next = subTraverse->next;
            subInsert->value = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
            strcpy(subInsert->value, value);
            subTraverse->next = subInsert;
            
            return;
        }
        // otherwise subqueue priority does not exist
        else
        {
            // allocate and initialize subqueue node
            SubPQueue *subInsert = (SubPQueue*)malloc(sizeof(SubPQueue));
            subInsert->priority = priority2;
            subInsert->next = NULL;
            subInsert->value = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
            strcpy(subInsert->value, value);

            // node goes at beginning of subqueue
            if (priority2 > subTraverse->priority)
            {
                // add and relink
                traverse->subQueue = subInsert;
                subInsert->next = subTraverse;
                
                return;
            }
            // otherwise traverse the subqueue and add in appropriate spot
            else
            {
                // while there is a next node, peek at its priority
                while (subTraverse->next != NULL)
                {
                    // if next node's priority is greater than priority of
                    // node to insert, then go to next node
                    if (subTraverse->next->priority > priority2)
                    {
                        subTraverse = subTraverse->next;
                    }
                    // break while loop when next node's priority is lesser
                    else
                        break;
                
                }
                
                // current node's priority is greater than priority2
                // and next node's is lesser. add node here
                subInsert->next = subTraverse->next;
                subTraverse->next = subInsert;
                
                return;
            }

        }
        
        return;
    }
    else // priority1 is not in queue, add to queue
    {
        // allocate and initialize subqueue node
        SubPQueue *subInsert = (SubPQueue*)malloc(sizeof(SubPQueue));
        subInsert->priority = priority2;
        subInsert->next = NULL;
        subInsert->value = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
        strcpy(subInsert->value, value);
        
        // allocate and initialize queue node
        PQueue *insert = (PQueue*)malloc(sizeof(PQueue));
        insert->priority = priority1;
        insert->next = NULL;
        insert->subQueue = subInsert;
        
        // new node needs to be added at beginning of queue
        if (priority1 > traverse->priority)
        {
            // insert and relink
            insert->next = traverse;
            *queue = insert;
            
            return;
        }
        // otherwise traverse the list and add in appropriate spot
        else
        {
            // while there is a next node, peek at its priority
            while (traverse->next != NULL)
            {
                // if next node's priority is greater than priority of
                // node to insert, then go to next node
                if (traverse->next->priority > priority1)
                {
                    traverse = traverse->next;
                }
                // break while loop when next node's priority is lesser
                else
                    break;
            }
            
            // current node's priority is greater than priority1
            // and next node's is lesser. add node here
            insert->next = traverse->next;
            traverse->next = insert;
            
        }
    
        return;
    }
    
}

void print_queue(PQueue* node)
{
    // empty queue
    if (!node)
    {
        printf("\nQueue is empty");
        return;
    }
    
    // pointer to traverse queue and subqueue
    PQueue *traverse = node;
    SubPQueue *subTraverse = NULL;
    
    printf("QUEUE");
    
    // traverse queue and print
    while (traverse)
    {
        // print queue priority
        printf("\n%d : ", traverse->priority);
        
        // set traverse pointer to current node's subqueue
        subTraverse = traverse->subQueue;
        
        // traverse subqueue and print values
        while (subTraverse)
        {
            printf("%s -> ", subTraverse->value);
            subTraverse = subTraverse->next;
        }
        
        printf("NULL\n|\nv");
        
        traverse = traverse->next;
    }
    
    printf("\nNULL\n");
    
}

char* dequeue(PQueue** queue)
{
    // local variables
    char *returnValue = malloc(1 + MAX_VALUE_SIZE * sizeof(char));
    SubPQueue *tmp = NULL;
    PQueue *current = *queue;
    
    // copy return value before freeing
    strcpy(returnValue, current->subQueue->value);
    
    // hold first subqueue node to free
    tmp = current->subQueue;
    
    // relink subqueue
    current->subQueue = current->subQueue->next;
    
    // free held subqueue node
    free_sub_node(tmp);
    
    // if subqueue is empty, free queue node and reset head pointer
    if (current->subQueue == NULL)
    {
        *queue = current->next;
        free(current);
    }
    
    // return value to printing
    return returnValue;
}

void free_sub_node(SubPQueue* node)
{
    // free value then node
    free(node->value);
    free(node);
    
}


























