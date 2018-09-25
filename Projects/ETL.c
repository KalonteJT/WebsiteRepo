#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "airPdata.h"
#define SIZE 5000

void panic ()
{
    printf("\nPanic!!!!!!!!!!!!!!!!\n");
}


//Prints Header of Program
void printHeader()
{
    printf("%-12s %-11s %-42s %-34s %-3s %-15s %-16s Tower\n",
    "FAA Site", "Short Name", "Airport Name", "City", "ST",
    "Latitude", "Longitude");
    printf("%-12s %-11s %-42s %-34s %-3s %-15s %-16s =====\n",
    "========", "==========", "============", "====", "==",
    "========", "=========");
}

// Takes in the csv file, parses through the information, and puts the information in the appropriate index
int parse(FILE *ifp, airPdata* data[])
{
    char buffer[SIZE];
    int num=0;
    while(fgets(buffer, SIZE, ifp)!=NULL)
 {
     char *line = buffer;
     char *field;
     field = malloc(sizeof(char)*50);
     int index = 0;
     data[num] = malloc(sizeof(airPdata));
     while ((field=strsep(&line, ","))!=NULL)
     {
        if(index==0){
        data[num]->siteNumber = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->siteNumber, field);
        }
        else if(index==1){
        data[num]->LocID = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->LocID, field);
        }
        else if(index==2){
        data[num]->fieldName = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->fieldName, field);
        }
        else if(index==3){
        data[num]->city = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->city, field);
        }
        else if(index==4){
        data[num]->state = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->state, field);
        }
        else if(index==8){
        data[num]->latitude = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->latitude, field);
        }
        else if(index==9){
        data[num]->longitude = malloc((strlen(field)+1)*sizeof(char));
        strcpy(data[num]->longitude, field);
        }
        else if(index==14){
        data[num]->controlTower = *field;
        }
        index++;
        if(index==19)
        {
            num++;
        }
     }
 }
return num;
}

// Frees all of the dynamically allocated data
void freeData(airPdata *airport)
{
    free(airport->siteNumber);
    free(airport->LocID);
    free(airport->fieldName);
    free(airport->city);
    free(airport->state);
    free(airport->latitude);
    free(airport->longitude);
    free(airport);
}

// Prints Airport Data
void printData(airPdata *airport)
{
    if(airport == NULL){
        fprintf(stderr,"ERROR");
        return;
}
fprintf(stdout, "%-12s %-11s %-42s %-34s %-3s %-15s %-16s %c\n", airport->siteNumber, airport->LocID, airport->fieldName,
       airport->city, airport->state, airport->latitude, airport->longitude, airport->controlTower );
}

// Main Function
int main(int argc, char **argv)
{
    FILE *ifp;
    char buffer[SIZE];
    int line =0;
    int i;
    airPdata* data[SIZE];
    ifp = fopen(argv[1], "r");
    if(ifp == NULL){
        fprintf(stderr, "etl ERROR: File \"%s\" not found.\n", argv[1]);
        exit(1);
        }
    line = parse(ifp,data);
    fclose(ifp);
    printHeader();
    for(i=0; i<line; i++){
         printData(data[i]);
    }
     for(i=0; i<line; i++){
         freeData(data[i]);
     }
    return 0;
}
