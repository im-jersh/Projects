//
//  main.c
//  lab_02
//
//  Created by Joshua O'Steen on 8/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//
// libraries
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

//
// color enumeration definition //
enum COLOR{
    red,
    blue,
    silver
};


//
// structures //
typedef struct{
    char* name;
    int model_year;
    int max_speed;
    int mileage;
    enum COLOR car_color;
} car;

//
// function protypes //
car* fill_Array(const int num_cars, FILE* inFilePtr);
void print_Garage(const int num_cars, car* garage); // loops through each car in the garage and prints its infor to screen //
void drive_Car(car* car); // takes pointer to a single car named car and increments its mileage by a random value between 1 and 100 //
void free_Array(const int num_cars, car* garage); // takes pointer to the garage and frees all memory that was allocated //
char* car_Color(enum COLOR car_color); // returns string of car color //

//
// begin main function
int main(int argc, char ** argv)
{
    //
    // break out of main if not enough arguments were supplied //
    if (argc < 2)
    {
        printf("\n\nYou did not include enough parameters. Please try again using './a.out' and 'garage.txt'.\n\n");
        return 0;
    }
    
    //
    // variables //
    int i;
    int numberOfCars;
    car *garage;
    FILE *txtFile;
    
    //
    // seed random number generator //
    srand(time(NULL));
    
    //
    // open files set confirm values //
    if ( (txtFile = fopen(*(argv+1), "r")) != NULL)
    {
        fscanf(txtFile, "%d", &numberOfCars);
    }
    else
    {
        printf("\n\nThe file did not open. Please try again using './a.out' and 'garage.txt'\n");
        return 0;
    }
    
    //
    // break out of main if file did not open properly and openfiles returns 0 //
    if (numberOfCars == 0 )
    {
        return 0;
    }
    
    //
    // fill array from file and return pointer to array //
    garage = fill_Array(numberOfCars, txtFile);
    
    //
    // close file //
    fclose(txtFile);
    
    //
    // print formatted array info //
    printf("\n\nGarage Information:");
    print_Garage(numberOfCars, garage);
    
    //
    // sends each car to be 'driven' //
    for (i = 0; i < numberOfCars; i++)
    {
        drive_Car( (garage+i) );
    }
    
    //
    // print formatted array info with new mileages //
    printf("\n\nAfter Driving:");
    print_Garage(numberOfCars, garage);
    
    //
    //free allocated memory //
    free_Array(numberOfCars, garage);
    
    //
    // return 0 to terminate main successfully //
    return 0;
}




// function defs //

car* fill_Array(const int num_cars, FILE* inFilePtr)
{
    //
    // variables //
    car *arrayInit;
    int i;
    
    //
    // allocate memory //
    arrayInit = (car*)malloc(num_cars * sizeof(car));
    
    //
    // loop through file and write values to properties of car, allocate memory to store each car name //
    for (i = 0; i < num_cars; i++)
    {
        
        fscanf(inFilePtr, "%s %d %d %d", (arrayInit + i)->name = (char*)malloc(256 * sizeof(char)), &(arrayInit + i)->model_year, &(arrayInit + i)->max_speed, &(arrayInit + i)->mileage);
        
        // get random number to determine car color
        (arrayInit + i)->car_color = (rand() % 3);
    }
    
    //
    // return pointer to beginning of structure array //
    return arrayInit;
}

void print_Garage(const int num_cars, car* garage)
{
    //
    // variables //
    int i;
    
    //
    // loop through array and print info // 
    for (i = 0; i < num_cars; i++)
    {
        printf("\nThe %s %d %s has %d miles and a top speed of %d mph.", car_Color((garage+i)->car_color),(garage+i)->model_year, (garage+i)->name, (garage+i)->mileage, (garage+i)->max_speed);
    }
    
}

void drive_Car(car* car)
{
    //
    // add random number between 1 and 100 to cars mileage //
    car->mileage += (1 + (rand() % 99)) ;
    
}

void free_Array(const int num_cars, car* garage)
{
    //
    // variables //
    int i;
    
    //
    // loop through array and free memory allocated for name //
    for (i = 0; i < num_cars; i++)
    {
        free( (garage+i)->name );
    }
    
    //
    // free garage array once memory for name is freed //
    free(garage);
    
}

char* car_Color(enum COLOR car_color)
{
    
    switch (car_color)
    {
        case 0:
            return "red";
        case 1:
            return "blue";
        case 2:
            return "silver";
    }
}









