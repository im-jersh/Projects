//
//  main.c
//  lab9
//
//  Created by Joshua O'Steen on 10/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

typedef struct bt_{
    int value;
    struct bt_ *left;
    struct bt_ *right;
}BST;

BST* insert(BST* root, int value);
void printTree(BST* root);
void displayBST(BST* root, int depth);
void padding(char toPrint, int numTimes);
FILE * openFile(char *argv, int argc, FILE *in);
int search(BST* root, int value);
BST* findMin(BST* root);
BST* delete(BST* root, int value);



int main(int argc, char * argv[])
{
    FILE *input = NULL;
    BST *root = NULL;
    int value;
    
    // validate and open file
    if (!(input = openFile( *(argv + 1), argc, input)))
        return 1;

    while ( (fscanf(input, "%d", &value)) != EOF)
        root = insert(root, value);
    
    
    fclose(input);
    
    printTree(root);
    
    printf("\n\nPlease enter a number to SEARCH for in the tree: ");
    scanf("%d", &value);
    if (search(root, value))
        printf("\nThe value ""%d"" was found in the tree.", value);
    else
        printf("\nThe value ""%d"" was not found in the tree.", value);
    
    
    printf("\n\nPlease enter a number to DELETE from the tree: ");
    scanf("%d", &value);
    root = delete(root, value);
    
    printTree(root);
    
    return 0;
}



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


BST* insert(BST* root, int value)
{
    if (root == NULL)
    {
        BST* newNode = (BST*)malloc(sizeof(BST));
        newNode->value = value;
        newNode->left = NULL;
        newNode->right = NULL;
        
        root = newNode;
        return root;
    }
    if (value < root->value)
        root->left = insert(root->left, value);
    if (value > root->value)
        root->right = insert(root->right, value);
    
    return root;
}


void displayBST(BST* root, int depth)
{
    if (root == NULL)
    {
        padding(' ', depth);
        printf("-\n");
        return;
    }
    
    
    displayBST(root->left, depth+4);
    padding(' ', depth);
    printf("%d\n", root->value);
    displayBST(root->right, depth+4);
}

void printTree(BST* root)
{
    displayBST(root, 0);
}

void padding(char toPrint, int numTimes)
{
    int i;
    for (i = 0; i < numTimes; i++)
		printf("%c", toPrint);
}


int search(BST* root, int value)
{
    if (!root)
    {
        return 0;
    }
    else if (value == root->value)
    {
        return 1;
    }
    else
    {
        if (value < root->value)
            search(root->left, value);
        if (value > root->value)
            search(root->right, value);
    }

}


BST* findMin(BST* root)
{
    if (!root->left)
        root;
    
    findMin(root->left);
    
}


BST* delete(BST* root, int value)
{
    if (!root)
    {
        return NULL;
    }
    
    if (search(root, value))
    {
        if (value < root->value)
        {
            root->left = delete(root->left, value);
        }
        else if (value > root->value)
        {
            root->right = delete(root->right, value);
        }
        else
        {
            // if root has two children
            if (root->left && root->right)
            {
                // find successor
                BST* tmp = findMin(root->right);
                //replace value
                root->value = tmp->value;
                // if tmp node has a right child
                if (tmp->right)
                {
                    // find parent to tmp and relink tmp's right child
                    // to parent's left link
                    BST* parent = root->right;
                    while (parent->left != tmp)
                    {
                        parent = parent->left;
                    }
                    
                    parent->left = tmp->right;
                    
                }
                
                // delete min value
                free(tmp);
            }
            // only one child
            else if (root->left || root->right)
            {
                // if left child
                if (root->left)
                    return root->left;
                else
                    return root->right;
                
            }
            // if no child
            else
            {
                free(root);
                return NULL;
                
            }
            
        }
    
    }
    else
    {
        printf("\nThe value ""%d"" was not found in the tree.\nTree was not changed.\n\n", value);
        return root;
    }
    
    
    return root;
}

    





























