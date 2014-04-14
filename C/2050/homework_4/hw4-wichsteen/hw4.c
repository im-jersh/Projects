/*Eric Wichmann*/
/*Homework 4 - edw8n8 */
/* strtok example */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// structure
struct course
{
	char *ID;
	char *decs;
	int credithours;
	char *professor;
	struct course *next;
};

// prototypes
struct course*  readFile(struct course * c,char * file);
int isName(char * a);
int isCredit(char *a);
char * readLine(char * fname,int line);
int isCourseName(char * a);
int isInt(char * a);
void print(struct course * c);
struct course * searchCourse(struct course * c, char * coursename);
void addCourse(struct course * c,struct course * newcourse);
char * copyString(char * a, char *b);
void init(struct course * a );
int isEqual(char * a , char * b);



int main()
{
    char file[100];
    
    // request file input
	printf("Enter name of file: ");
	scanf("%s",file);
    
    // variables
	char option, temp[100];
	int addedCourse = 0, totalCreditHours = 0;
	struct course *courses = (struct course*)malloc(sizeof(struct course)*1) , *tmpCourse = (struct course*)malloc( sizeof (struct course)*1) , *selectedcourse;
    
    // initialize
	init(courses);
	init(tmpCourse);
    
    // parse and make course list
	courses = readFile(courses,file);
    print(courses);
	

	// menu
	while(1)
	{
		while ( getchar() != '\n' );
		printf("\nPlease select an option below:\n1-Register for a course\n2-See my total\n3-Exit\n");
		scanf("%c" , &option);

		if(option ==  '1')
		{
			printf("Which Course would you like to register for?  ");
            
			while ( getchar() != '\n' );
			scanf("%s",temp);
            
			selectedcourse = searchCourse(courses,temp);
            
			if(selectedcourse == NULL)
			{
				printf("INVALID Course not found, try again.\n");
			}
            else if(strcmp(selectedcourse->professor, "CANCELLED") == 0)
            {
                printf("\nSorry but the course you selected has been cancelled.\nYou have not been registered for this course.\n");
            }
			else
			{
				printf("You are now registered for the course.\n");
				addedCourse++;
				totalCreditHours += selectedcourse->credithours;
				addCourse(tmpCourse, selectedcourse);

			}
		}
		else if (option == '2')
		{
			if(addedCourse == 0)
				printf("You have not register for any courses.\n");
			else
			{
				printf("You are currently registered for:\n");
				print(tmpCourse);
			}
		}
		else if (option == '3')
		{
			if(addedCourse == 0)
				printf("You have not register for any courses.  Have a nice day!\n");
			else
			{
				printf("You are currently registered for:\n");
				print(tmpCourse);
				printf("\nFor a total of %d hours of credit.  Have a nice day!\n",totalCreditHours);
			}
			printf("PROGRAM TERMINATED\n");
			exit(0);
		}
		else
		{
			printf("\nplease select a valid option! \n");
		}
	}
	return 0;
}

//
//  definitions
//

// parses file and creates link list
struct course *  readFile (struct course * c,char * file)
{
    // variables
	int lines = 0, name = 0,title =0, teacher = 0,credit = 0;
	char * pch, *line;
	struct course * temp = (struct course *)malloc( sizeof (struct course)*1);
    
    // initiallize
	init(temp);
	lines++;
    
    // get line
	line = readLine(file,lines);
    
    // loop through lines and parse data
	while(line)
	{
		pch = strtok (line," ,.\t\n");
		while (pch != NULL)
		{
			if(name == 0)
			{
				if(isCourseName(pch) == 1)
				{
					temp->ID = pch;
					name = 1;
				}
			}
			else if(title == 0)
			{
				if(isCourseName(pch) == 1)
				{
					name = 1;
					init(temp);
					temp->ID = pch;
				}
				else  if(isCredit(pch))
				{
					temp->credithours = atoi(pch);
					title = 1;
				}
				else
				{
					temp->decs = copyString(temp->decs ,pch);
				}
			}
			else if(teacher == 0)
			{
				if(isCourseName(pch) == 1)
				{
					temp->professor = "CANCELLED";
					addCourse(c,temp);
					temp = (struct course *)malloc( sizeof (struct course)*1);
					init(temp);
					temp->ID = pch;
					name = 1; teacher = 0; title = 0,credit = 0;
				}
				else
				{
                    
					if (isName(pch) == 1)
					{
						temp->professor = 	copyString(temp->professor, pch);
						pch = strtok (NULL, " ,.\t\n");
						while(pch!= NULL)
						{
							if (!(isName(pch) == 0 && (strlen(pch) != 1)))
							{
								temp->professor =  copyString(temp->professor, pch);
							}
							pch = strtok (NULL, " ,.\t\n");
						}
						addCourse(c,temp);
						temp = (struct course *)malloc( sizeof (struct course)*1);
						init(temp);
						name = 0; teacher = 0; title = 0,credit = 0;
					}
				}
			}
            
			pch = strtok (NULL, " ,.\t\n");
		}
        
		lines++;
		line = readLine(file,lines);
	}
    
	return c;
}

// adds new course to list
void addCourse(struct course * c,struct course * newcourse)
{
	if(c != NULL)
	{
		while(c->next != NULL)
		{
			c = c->next;
		}
        
		newcourse->next = NULL;
		c->next = newcourse;
	}
	else
	{
		newcourse->next = NULL;
		c = newcourse;
        
	}
    
}

// searches list for specific course, returns pointer if found
struct course *searchCourse(struct course *c, char *coursename)
{
    // variables
	int i = 0;
	struct course * temp = (struct course *) malloc(sizeof(struct course));
    
	for(i = 0 ; i < strlen(coursename); i++)
	{
		if(coursename[i] >= 'a' && coursename[i] <= 'z'  )
		{
			coursename[i] -= 32;
		}
	}
    
	c = c->next;
    
	do
	{
        
		if( isEqual(c->ID, coursename))
		{
			temp->decs = (char *)malloc(sizeof(char)*strlen(c->decs));
			temp->ID = (char *)malloc(sizeof(char)*strlen(c->ID));
			temp->professor = (char *)malloc(sizeof(char)*strlen(c->professor));
			temp->credithours =c->credithours;
			strcpy(temp->decs,c->decs);
			strcpy(temp->ID,c->ID);
			strcpy(temp->professor,c->professor);
            
			return temp;
		}
        
		c = c->next;
        
	}while(c != NULL);
    
	return NULL;
}

// prints link list
void print(struct course * c)
{
    // variables
	int i;
    
	c = c->next;
    
	while(c != NULL)
	{
		printf("\n %s %s ",c->ID,c->decs);
		for(i = 0 ; i < 35-strlen(c->decs);i++)
			printf(" ");
		printf(" %d hours   Prof:  %s", c->credithours,c->professor);
		c = c->next;
		printf("\n");
	}
}

// searches string for integers
int isInt(char * a)
{
    // variables
	int i ;
    
	for(i= 0 ; i < strlen(a); i++)
	{
		//printf("  " );
		if(a[i] < '0' || a[i] > '9')
			return 0;
	}
	return 1;
}

// checks if course name
int isCourseName(char * a)
{
    // variables
	int i = 0 ;
    
	if(strlen(a) == 8)
	{
		for(i = 0 ;i < 4 ; i++)
		{
			if(a[i] < 'A' || a[i] > 'Z' )
			{
				return 0;
			}
		}
		if(a[4] != '-' )
		{
			return 0;
		}
		for(i = 5 ;i < 8 ; i++)
		{
			if(a[i] < '0' || a[i] > '9' )
				return 0;
		}
        return 1;
	}
    
	return 0;
}

// check equality of two strings
int isEqual(char * a , char * b)
{
    // variables
	int i = 0;
    
	if(strlen(a) != strlen(b))
		return 0;
	for(i = 0 ; i < strlen(a);i++)
	{
		if(a[i] != b[i])
			return 0;
	}
    
	return 1;
}

// initializes a course structure
void init(struct course * a )
{
	a->credithours = 0;
	a->decs = NULL;
    a->professor = NULL;
	a->ID = NULL;
	a->next = NULL;
}

// concatenates b to a and adds null terminator to end of string
char * copyString(char * a, char *b)
{
    // variables
	unsigned long int i;
	char * temp;
    
	if(a != NULL)
	{
		temp  = (char * ) malloc (sizeof(char)* (strlen(a) + strlen(b) + 2));
		for(i = 0 ; i < strlen(a);i++)
		{
			temp[i] = a[i];
		}
		for(i = strlen(a); i < strlen(a)+strlen(b);i++)
		{
			temp[i] = b[i-strlen(a)];
		}
        
		temp[i] = ' ';
		temp[i+1] = '\0';
		a = temp;
	}
	else
	{
		temp  = (char * ) malloc (sizeof(char)* (strlen(b) + 2));
		for(i = 0 ; i < strlen(b);i++)
		{
			temp[i] = b[i];
            
            
		}
		temp[i] = ' ';
		temp[i+1] = '\0';
		a = temp;
	}
    
	return a;
}

// gets entire line from file
char * readLine(char * fname,int line)
{
    // variables
	char *newLine = (char *) malloc(sizeof(char) * 100);
	FILE *pFile;
    
    // open file
	pFile = fopen (fname , "r");
    
	if (pFile == NULL)
	{
		perror ("Error opening file");
		printf("\n");
		exit(0);
	}
	else {
		int i;
		for( i = 0 ; i < line ; i++)
		{
			if ( fgets (newLine , 100 , pFile) == NULL )
			{
				return NULL;
			}
		}
        // close file
		fclose (pFile);
	}
	return  newLine;
}

// checks for credit hour
int isCredit(char *a)
{
	if(strlen(a) == 1 && a[0] >= '1' && a[0] <= '5')
		return 1;
    
	return 0;
}

// checks for valid name
int isName(char * a)
{
    // varialbes
	int i = 0;
    
	if(a[0] >= 'A' && a[0] <= 'Z' && strlen(a) >  1)
	{
		for(i = 1 ; i < strlen(a);i++)
		{
			if(a[i] < 'a' || a[i] > 'z')
				return 0;
		}
		return 1;
	}
	return 0;
}



