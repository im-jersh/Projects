//  Joshua O'Steen
//  homework 3
//  11/04/13
//  jrocnc
//  CS2050


//libraries
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//binary search tree Structure----/* DO NOT CHANGE */------
typedef struct BST {
	int value;
    int treeNum;
	struct BST* left;
	struct BST* right;
}BST;

//list of pointers to the root node----/* DO NOT CHANGE */------
typedef struct rootList {
	struct BST* root;
	struct rootList* next;
}rootList;

//BFS Queue Structure-----/* DO NOT CHANGE */-------
typedef struct bfsQ{
	struct BST* treeNode;
	struct bfsQ* next;
	
}bfsQ;

//Function Prototypes-------/* DO NOT CHANGE */------
void insert_BST(BST** root, int insertValue, int treeNum);
BST* createTreeNode(int nodeValue, int treeNum);
void printTrees(rootList* listHead);
void print_BST_inorder(BST* root);
void enqueue(bfsQ** qHead, BST* new_tree_node);
BST* dequeue(bfsQ** qHead);
void BFS (rootList* listHead, int searchValue);
void insert_rootList(rootList** listHead, BST* root);
void free_list(rootList** listHead);
void free_BSTs(BST* root);

//maximum line size
#define MAX_LINE_SIZE 20

/********************************** MAIN *****************************************
 
 parameters: int argc (total number of command line arguments.
 char*argv[] (an array that holds the command line arguments)
 returns: int value of success (0) or failure (1)
 
 -> Your main function should read two files from the command line:
 1.) argv[1] should read the file input.txt. This file will have
 integer values that you will use to insert into the binary search tree.
 2.) argv[2] should read the file search.txt. This file will have
 integer values that you will use to search the binary search tree using BFS
 -> Main will call the functions: insert_BST, insert_rootList and BFS.
 
 *********************************************************************************/

int main (int argc, char *argv[])
{
    // variables
    FILE *in1, *in2;
    
    // incorrect number of arguments
    if (argc != 3)
    {
        printf("\n\nYou did not enter the correct number of arguments.\nPlease enter './a.out' then 'file.ext' then 'file.ext' separated by spaces.\n\nProgram Terminated.\n\n");
        return 1;
    }
    // files weren't opened
    else if ((in1 = fopen(*(argv+1), "r")) == NULL || (in2 = fopen(*(argv+2), "r")) == NULL)
    {
        if (!in1 && !in2)
            printf("\n\nThe files '%s' and '%s' were not opened properly.\nPlease try again ensuring the input file is in the\ncorrect folder and the filenames are typed correctly.\n\n", *(argv+1), *(argv+2));
        else if (!in1)
            printf("\n\nThe file '%s' was not opened properly.\nPlease try again ensuring the input file is in the\ncorrect folder and the filenames are typed correctly.\n\n", *(argv+1));
        else
            printf("\n\nThe file '%s' was not opened properly.\nPlease try again ensuring the input file is in the\ncorrect folder and the filenames are typed correctly.\n\n", *(argv+2));
        
        return 1;
    }
    
    
    // variables
    int listTreeNumber = 1;
    signed int value = 0;
    BST *treeHead = NULL;
    rootList *listHead = NULL;
    
    // scan input file
    // create link list containing BSTs
    while ((fscanf(in1, "%d", &value)) != EOF)
    {
        if (value != -1)
            // insert value into tree
            insert_BST(&treeHead, value, listTreeNumber);
        
        else if ( value == -1)
        {
            // create list node
            insert_rootList(&listHead, treeHead);
            
            // reset treeHead for new tree
            treeHead = NULL;
            
            // bump tree number
            listTreeNumber++;
        }
    }
    
    // close file
    fclose(in1);
    
    // print list
    printf("\n");
    printTrees(listHead);

    // print column headers
    printf("\n\n\n%-10s%-10s%-10s%-10s\n%30s", "Number", "Found", "Tree",
           "Level", "------------------------------------");
    
    // search trees for values from second file
    while ((fscanf(in2, "%d", &value)) != EOF)
    {
        printf("\n%-10d", value);
        BFS(listHead, value);
    }
    
    // close file
    fclose(in2);
    
    // free list
    free_list(&listHead);
    
    // print confirm
    printf("\n\nAll memory freed.\nProgram terminated successfully!\n\n");
    
    // return successfully
    return 0;
}




/********************************** createTreeNode *******************************
 parameters: int value to be inserted in the binary search tree
 returns: pointer to a newly created binary search tree node (BST*)
 
 -> createTreeNode simply creates a new tree node using the value passed as the
 parameter.
 *********************************************************************************/

BST* createTreeNode(int nodeValue, int treeNum)
{
    // allocate and initialize new tree node
    BST* newNode = (BST*)malloc(sizeof(BST));
    newNode->value = nodeValue;
    newNode->treeNum = treeNum;
    newNode->left = NULL;
    newNode->right = NULL;
    
    // return new node
    return newNode;
}

/********************************** insert_BST ****************************************
 parameters: the reference of the root (BST**) and the int value to be inserted.
 returns: void
 
 -> This function recursively finds the right position in the binary search tree
 for the new value and inserts the node containing the new value in that position.
 ****************************************************************************************/

void insert_BST(BST** root, int insertValue, int treeNum)
{
    // base case
    if (*root == NULL)
        *root = (createTreeNode(insertValue, treeNum));
    // recur left
    else if (insertValue < (*root)->value)
        insert_BST(&(*root)->left, insertValue, treeNum);
    // recur right
    else
        insert_BST(&(*root)->right, insertValue, treeNum);

}

/********************************** insert_rootList ******************************
 parameters: the reference of the head pointer to the list (BST**) and pointer
 to the root of the new binary search tree
 returns: void
 
 -> This function inserts the new binary search tree at the BACK of the linked
 list containing pointers to the roots of the binary search trees.
 *********************************************************************************/

void insert_rootList(rootList** listHead, BST* new_root)
{
    // base case
    if (!(*listHead))
    {
        // allocate and initialize new list node
        rootList *newNode = (rootList*)malloc(sizeof(rootList));
        newNode->next = NULL;
        newNode->root = new_root;
        *listHead = newNode;
        return;
    }
    //else, recur to next node
    insert_rootList(&(*listHead)->next, new_root);
}

/********************************** printTrees **************************************
 parameters: pointer to the head of the linked list
 returns: void
 
 -> This Function prints all the binary search trees in the linked list
 *********************************************************************************/

void printTrees(rootList* listHead)
{
    // base case
    if (!listHead)
        return;
    
    // else, print tree
    printf("\nTree %d: ", listHead->root->treeNum);
    print_BST_inorder(listHead->root);
    
    // recur
    printTrees(listHead->next);
    
}

/****************************** print_BST_inorder *******************************
 parameters: pointer to the root of the tree
 returns: void
 
 -> This Function prints the binary search tree using inorder traversal
 *********************************************************************************/

void print_BST_inorder(BST* root)
{
    // if tree exists print it INORDER
    if (root)
    {
        // recur left
        print_BST_inorder(root->left);
        
        // print value
        printf("%d ", root->value);
        
        // recur right
        print_BST_inorder(root->right);
    }
}

/************************************** BFS **************************************
 parameters: the pointer to the start of the linked list and the int value to be
 searched
 returns: void
 
 -> This function implements a variant of a level by level search or formally
 called as the BREADTH FIRST SEARCH.
 -> This function searches for a given value in the binary search trees and it does that
 by searching for level 1 in each binary search trees, then moving on to level 2 if
 it fails to find it that value in level 1 and so on.
 -> Basically, you have to search for a given value in all the binary search trees, one
 level at a time, in the linked list simultaneously.
 
 //////////////////////////////////////////////////////////////////////////////
 / HINT: Use the enqueue and dequeue functions to solve this problem. You will
 /       have a hard time solving this problem without using the enqueue and
 /       dequeue functions.
 /////////////////////////////////////////////////////////////////////////////
 
 *********************************************************************************/

void BFS(rootList* listHead, int searchValue)
{
    // don't search if list is empty
    if (!listHead)
        return;
    
    // variables
    BST *current = NULL;
    bfsQ *queue = NULL;
    int levelCounter = 0;
    
    // enqueue all root nodes
    while (listHead)
    {
        enqueue(&queue, listHead->root);
        listHead = listHead->next;
    }
    
    // look for searchValue by level in each tree before searching next level
    while (queue)
    {
        // get next node in queue
        current = dequeue(&queue);
        
        // searchValue was found
        if (current->value == searchValue)
        {
            // print where value was found
            printf("%-10s%-10d%-10d", "YES", current->treeNum, levelCounter);
            
            // clean up queue to ensure memory is freed
            bfsQ *hold = queue;
            while (queue)
            {
                hold = queue;
                queue = queue->next;
                free(hold);
            }
            
            // return to main
            return;
        }
        // otherwise, add children to queue
        else
        {
            if (current->left)
                enqueue(&queue, current->left);
            if (current->right)
                enqueue(&queue, current->right);
        }
        
        // since the queue always contains nodes from tree1 then tree2 then tree3 (repeat),
        // this condition says that if the next node in queue has a lesser tree number
        // than the current node then the queue contains only nodes that are
        // below the current node in the set of trees
        if (queue && queue->treeNode->treeNum < current->treeNum)
            levelCounter++;
    }
    
    // search value was not found
    printf("%-10s%-10s%-10s", "NO", "N/A", "N/A");
}

/************************************ enqueue ************************************
 parameters: the reference of the head of the queue and pointer to the tree node
 to be inserted in the queue
 returns: void
 
 -> This Function inserts the new tree node in the queue that is used to do a BFS.
 
 *********************************************************************************/

void enqueue(bfsQ** qHead, BST* new_tree_node)
{
    // base case
    if (!(*qHead))
    {
        // allocate and initialize new queue member
        bfsQ *newNode = (bfsQ*)malloc(sizeof(bfsQ));
        newNode->next = NULL;
        newNode->treeNode = new_tree_node;
        *qHead = newNode;
    }
    // recur
    else
        enqueue(&(*qHead)->next, new_tree_node);
}

/********************************** dequeue **************************************
 parameters: the reference of the head of the queue.
 returns: pointer to the dequeued tree node
 
 -> This Function dequeue's the tree node in front of the queue and returns it.
 
 *********************************************************************************/

BST* dequeue(bfsQ** qHead)
{
    // variables
    bfsQ *holdQueue = *qHead;
    BST *holdTree = holdQueue->treeNode;
    
    // set queue head to next node in queue
    *qHead = (*qHead)->next;
    
    // free queue node
    free(holdQueue);
    
    // return tree node for analysis
    return holdTree;
}

/********************************* free_list *************************************
 parameters: reference of the pointer to the head of the linked list
 returns: void
 
 -> This function frees all the allocated memory.
 -> This function also calls the free_BSTs function to free the binary search trees.
 *********************************************************************************/

void free_list(rootList** listHead)
{
    // recur
    if ((*listHead)->next)
        free_list(&(*listHead)->next);
    
    // base case
    free_BSTs((*listHead)->root);
    free(*listHead);
}

/********************************* free_BSTs *************************************
 parameters: Pointer to the root of the binary search tree
 returns: void
 
 -> This function frees all the nodes in the given binary search tree recursively.
 *********************************************************************************/

void free_BSTs(BST* root)
{
    // recur
    if (root->left)
        free_BSTs(root->left);
    if (root->right)
        free_BSTs(root->right);
    
    // base case
        free(root);
}



