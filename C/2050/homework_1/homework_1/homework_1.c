//  Joshua O'Steen
//  14041320
//  homework 1
//  cs2050
//
//
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SONG_LENGTH 10
#define ARTIST_LENGTH 16
#define CD_LENGTH 20

typedef struct song_ {
    char* song;
    char* artist;
    char* cd;
    int rating;
    struct song_ *next;
}Song;

Song* new_song(char* song, char* artist, char* cd, int rating);
Song* insert_by_song(Song* head, Song* node);
void print_list(Song* head);
int update_song(Song* head, char* name, int rating);
void free_node(Song* node);
Song* delete_odd(Song* head);
Song* delete_even(Song* head);

/*
 * This function begins by checking that exactly two command line arguments are given. If not
 * the program is terminated with an appropriate error message. It then attempts to open the input
 * file and terminates with an appropriate error message if it fails. The function then runs through
 * the input file, calls new_song() and then insert_by_song() to create a linked list. print_list() is
 * called when the linked list is fully completed. The program will then ask for a name of a song a
 * new rating and call update song. The function will prompted for a new song and rating until update_song()
 * is successful. After, the list is successfully updated print_list() will be called. The function will
 * then call delete_even(), print_list(), delete_odd() and print_list() one last time before exiting.
 */
int main(int argc, char** argv)
{
    //
    // local variables
    int rating = 0, updateReturn = 0, updateRating = 0;
    char *song, *artist, *cd, *updateSong = (char*)malloc(SONG_LENGTH * sizeof(char));
    Song *listHead = NULL;
    FILE *filePointer;
    
    
    //
    //
    if (argc != 2)
    {   // break out of main if not enough or too many arguments were supplied //
        printf("\n\nYou did not include the proper number of parameters.\nPlease try again using './a.out' and 'playlist.txt' in that order.\n\n");
        return 0;
    }
    else if ( strncmp( *(argv + 1), "playlist.txt" , 12) != 0 )
    {   // break out of main if name of file was not spelled correctly //
        printf("\n\nYou did not include the proper parameters.\nPlease try again using './a.out' and 'playlist.txt' in that order.\n\n");
        return 0;
    }
    else if ( (filePointer = fopen( *(argv +1), "r")) == NULL)
    {
        // break out of main if file fails to open properly
        printf("\n\nThe file failed to open properly.\nPlease try again using './a.out' and 'playlist.txt' in that order.\n\n");
        return 0;
    }
    else
    {
        // otherwise, continue with file pointer //
        printf("\n\n");
        
        // loop to fill nodes until EOF is reached
        // parameter for while loop will scan file line by line, temporarily holding the line values to send to new_song
        
        
        while ( (fscanf( filePointer, "%s %s %s %d", song = (char*)malloc(SONG_LENGTH * sizeof(char)), artist = (char*)malloc(ARTIST_LENGTH * sizeof(char)), cd = (char*)malloc(CD_LENGTH * sizeof(char)), &rating)) != EOF )
        {
            //
            // nested functions. new_song will create a node, populate it with sent arguments
            // then return a pointer to that new node, which in turn is used as an argument
            // for insert_by_song which will then insert the new node into the list alphabetically
            // by song title. insert_by_song will return the pointer for the beginning of the list
            listHead = insert_by_song(listHead, new_song(song, artist, cd, rating));
        }
        
        fclose(filePointer);
    }
    
    // print formatted link list
    print_list(listHead);

    // update rating
    while ( updateReturn == 0)
    {
        // reinitialize song and rating

        updateRating = 0;
        
        // request user input for song and rating
        printf("\n\nPlease enter a song title and a new rating for that song: ");
        scanf("%s%d", updateSong, &updateRating);
        
        // call to function
        updateReturn = update_song(listHead, updateSong, updateRating);
    }
    
    // print updated list
    print_list(listHead);

    
    // delete even rating nodes from list
    listHead = delete_even(listHead);
    
    // print list
    print_list(listHead);
    
    // delete odd rating nodes from the list
    listHead = delete_odd(listHead);
    
    // print list
    print_list(listHead);
    
    // main executed successfully
    return 0;
}


Song* new_song(char* song, char* artist, char* cd, int rating)
{
    // variables
    Song *nodePointer = (Song*)malloc(sizeof(Song));
    
    // initialize node properties
    nodePointer->song = song;
    nodePointer->artist = artist;
    nodePointer->cd = cd;
    nodePointer->rating = rating;
    nodePointer->next = NULL;
    
    // return new node
    return nodePointer;
    
}

/*
 * Inserts Song* node into the linked list specified by the pointer head. The node
 * should be inserted in alphabetical order by the field song.
 * Return: A pointer to the head of the linked list
 */
Song* insert_by_song(Song* head, Song* node)
{
    
    
    
    // break out if first node in list
    if ( head == NULL)
    {
        return node;
    }
    
    //variables
    Song *previousNode = head, *nodePointer = head->next;
    
    
    // loop to traverse list, and place node in alphabetical order.
    //
    do
    {   // if node comes first on list
        if ( node->song[0] < previousNode->song[0] )
        {
            node->next = previousNode;
            
            return node;
        }
        // if node comes between pointer pair
        else if ( (node->song[0] > previousNode->song[0]) && ( node->song[0] < nodePointer->song[0] ))
        {
            node->next = nodePointer;
            previousNode->next = node;
            return head;
        }
        // if first letter of node song is same as previous song
        else if ( (node->song[0] == previousNode->song[0]) && (node->song[0] < nodePointer->song[0]))
        {
            node->next = nodePointer;
            previousNode->next = node;
            return head;
        }
        // if no conditions are met then move the pointer pair to the next nodes
        else
        {
            previousNode = nodePointer;
            nodePointer = nodePointer->next;
        }
    } while ( nodePointer != NULL );
    
    
    // return pointer to beginning of list
    return head;
}


/*
 * Runs through the linked list specified by the pointer head and prints it
 * to the screen
 */
void print_list(Song* head)
{
    
    if ( head == NULL )
    {
        printf("\n\nThe playlist is emptry.\n\n");
        return;
    }
    
    // print column headers
    printf("\n\n%15s%20s%20s%10s", "Song", "Artist", "Album", "Rating");
    
    
    while ( head != NULL )
    {
        printf("\n\n%15s%20s%20s%10d", head->song, head->artist, head->cd, head->rating);
        
        head = head->next;
    }
    
    
}





//* Searches throught the linked list specified by the pointer head for a Song with a
//* field song that matches the string name. If found, the nodes rating is updated to
//* match that of the parameter rating
//* Return: 1 if the linked list is updated, 0 otherwise

int update_song(Song* head, char* name, int rating)
{
    //
    while ( head != NULL)
    {
        if ( strncmp(name, head->song, 20) == 0 )
        {
            head->rating = rating;
            return 1;
        }
        else
        {
            head = head->next;
        }
    }
    
    // if song not found on list return 0 to caller
    if ( head == NULL)
    {
        printf("\n\nThe song was not found in the playlist.\nPlease try again.");
        return 0;
    }
    
    return 0;
}



//* Runs through the linked list specifed by the pointer head and deletes every node
//* whose rating is even. This function should call free_node().
//* Return: The head of the linked list

Song* delete_even(Song* head)
{
    Song *currentSong = NULL, *free = NULL;
    
    if (head == NULL)
    {
        return head;
    }
    else
    {
        
        // Deletes head node and reassigns
        while( head->rating % 2 == 0)
        {
            free = head;
            head = head->next;
            free_node(free);
            
        }
        
        //
        currentSong = head;
        
        //
        while( currentSong->next != NULL )
        {
            
            if ((currentSong->next->rating % 2) == 0)
            {
                currentSong->next = currentSong->next->next;
                free_node(currentSong->next);
            }
            else
            {
                currentSong = currentSong->next;
            }
        }
        
    }
    
    // return beginning of list
    return head;
}



//* Runs through the linked list specifed by the pointer head and deletes every node
//* whose rating is odd. This function should call free_node().
//* Return: The head of the linked list

Song* delete_odd(Song* head)
{
    Song *currentSong = NULL, *free = NULL;
    
    if (head == NULL)
    {
        return head;
    }
    else
    {
        
        // Deletes head node and reassigns
        while( head->rating % 2 != 0)
        {
            free = head;
            head = head->next;
            free_node(free);
            
            if ( head == NULL)
            {
                return head;
            }
        }
        
        //
        currentSong = head;
        
        //
        while( currentSong->next != NULL )
        {
            
            if ((currentSong->next->rating % 2) != 0)
            {
                currentSong->next = currentSong->next->next;
                free_node(currentSong->next);
            }
            else
            {
                currentSong = currentSong->next;
            }
        }
        
    }
    
    // return beginning of list
    return head;
}



//* Frees all allocated memory associated with the node "node"
//* Return : None
void free_node(Song* node)
{
    free(node);
}




