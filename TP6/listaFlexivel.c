#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_STR 512
#define MAX_ARRAY 50

typedef struct {
    int id;
    char name[MAX_STR];
    char releaseDate[11];
    int estimatedOwners;
    float price;
    char supportedLanguages[MAX_ARRAY][MAX_STR];
    int supportedLangCount;
    int metacriticScore;
    float userScore;
    int achievements;
    char publishers[MAX_ARRAY][MAX_STR];
    int publisherCount;
    char developers[MAX_ARRAY][MAX_STR];
    int developerCount;
    char categories[MAX_ARRAY][MAX_STR];
    int categoryCount;
    char genres[MAX_ARRAY][MAX_STR];
    int genreCount;
    char tags[MAX_ARRAY][MAX_STR];
    int tagCount;
} Game;

// Remove espaços do início e fim da string
void trim(char *str) {
    int start = 0, end = strlen(str) - 1;
    while(isspace(str[start])) start++;
    while(end >= start && isspace(str[end])) end--;
    str[end+1] = '\0';
    memmove(str, str+start, end-start+2);
}

// Mantém apenas dígitos
void onlyDigits(char *dest, const char *src) {
    int j = 0;
    for(int i = 0; src[i] && j < MAX_STR-1; i++)
        if(isdigit(src[i])) dest[j++] = src[i];
    dest[j] = '\0';
}

// Converte texto de lista para array
int parseList(char array[][MAX_STR], const char *text) {
    int count = 0;
    int i = 0, j = 0;
    char buffer[MAX_STR];
    int len = strlen(text);

    while(i < len) {
        if(text[i]=='[' || text[i]=='\'') { i++; continue; }
        if(text[i]==']') { 
            if(j>0){ buffer[j]='\0'; strcpy(array[count++], buffer); trim(array[count-1]); j=0; }
            break; 
        }
        if(text[i]==',' && j>0) { 
            buffer[j]='\0'; 
            strcpy(array[count++], buffer); 
            trim(array[count-1]);
            j=0; i++; while(text[i]==' ') i++; 
            continue; 
        }
        buffer[j++] = text[i++];
    }
    if(j>0) { buffer[j]='\0'; strcpy(array[count++], buffer); trim(array[count-1]); }
    return count;
}

float normalizePrice(const char *priceStr) {
    if(strcmp(priceStr, "Free to Play")==0 || strlen(priceStr)==0) return 0.0;
    return atof(priceStr);
}

int normalizeMetacritic(const char *score) {
    if(strlen(score)==0) return -1;
    return atoi(score);
}

float normalizeUserScore(const char *score) {
    if(strlen(score)==0 || strcmp(score,"tbd")==0) return -1.0;
    return atof(score);
}

int normalizeAchievements(const char *ach) {
    if(strlen(ach)==0) return 0;
    return atoi(ach);
}

void normalizeDate(char *out, const char *in) {
    char month[4];
    int day, year;
    sscanf(in, "%3s %d, %d", month, &day, &year);

    int m = 1;
    if(strcmp(month,"Jan")==0) m=1; else if(strcmp(month,"Feb")==0) m=2;
    else if(strcmp(month,"Mar")==0) m=3; else if(strcmp(month,"Apr")==0) m=4;
    else if(strcmp(month,"May")==0) m=5; else if(strcmp(month,"Jun")==0) m=6;
    else if(strcmp(month,"Jul")==0) m=7; else if(strcmp(month,"Aug")==0) m=8;
    else if(strcmp(month,"Sep")==0) m=9; else if(strcmp(month,"Oct")==0) m=10;
    else if(strcmp(month,"Nov")==0) m=11; else if(strcmp(month,"Dec")==0) m=12;

    sprintf(out,"%02d/%02d/%d", day, m, year);
}

void printArray(char array[][MAX_STR], int count) {
    printf("[");
    for(int i=0;i<count;i++) {
        printf("%s", array[i]);
        if(i<count-1) printf(", ");
    }
    printf("]");
}

void printGame(Game *g) {
    printf("=> %d ## %s ## %s ## %d ## %.2f ## ", g->id, g->name, g->releaseDate, g->estimatedOwners, g->price);
    printArray(g->supportedLanguages, g->supportedLangCount);
    printf(" ## %d ## %.1f ## %d ## ", g->metacriticScore, g->userScore, g->achievements);
    printArray(g->publishers, g->publisherCount);
    printf(" ## ");
    printArray(g->developers, g->developerCount);
    printf(" ## ");
    printArray(g->categories, g->categoryCount);
    printf(" ## ");
    printArray(g->genres, g->genreCount);
    printf(" ## ");
    printArray(g->tags, g->tagCount);
    printf(" ##\n");
}

int main() {
    char idBuscado[MAX_STR];
    while(1) {
        fgets(idBuscado, MAX_STR, stdin);
        idBuscado[strcspn(idBuscado,"\n")]=0;
        if(strcmp(idBuscado,"FIM")==0) break;
        FILE *fp = fopen("/tmp/games.csv","r");
        if(!fp){ printf("Arquivo não encontrado\n"); return 1; }
        char line[2048];
        fgets(line,2048,fp); // Ignora cabeçalho
        while(fgets(line,2048,fp)) {
            char *campos[14];
            int campoIndex=0;
            static char buffer[2048];
            int bufIndex=0;
            int inQuotes=0;
            for(int i=0; line[i]; i++) {
                if(line[i]=='"') inQuotes = !inQuotes;
                else if(line[i]==',' && !inQuotes) {
                    buffer[bufIndex]='\0';
                    campos[campoIndex] = malloc(strlen(buffer)+1);
                    strcpy(campos[campoIndex], buffer);
                    campoIndex++;
                    bufIndex=0;
                } else buffer[bufIndex++]=line[i];
            }
            buffer[bufIndex]='\0';
            campos[campoIndex]=malloc(strlen(buffer)+1);
            strcpy(campos[campoIndex], buffer);
            campoIndex++;

            if(campoIndex>=14 && strcmp(campos[0],idBuscado)==0) {
                Game g;
                g.id = atoi(campos[0]);
                strcpy(g.name, campos[1]);
                normalizeDate(g.releaseDate, campos[2]);
                char tmp[64]; onlyDigits(tmp, campos[3]);
                g.estimatedOwners = atoi(tmp);
                g.price = normalizePrice(campos[4]);
                g.supportedLangCount = parseList(g.supportedLanguages, campos[5]);
                g.metacriticScore = normalizeMetacritic(campos[6]);
                g.userScore = normalizeUserScore(campos[7]);
                g.achievements = normalizeAchievements(campos[8]);
                g.publisherCount = parseList(g.publishers, campos[9]);
                g.developerCount = parseList(g.developers, campos[10]);
                g.categoryCount = parseList(g.categories, campos[11]);
                g.genreCount = parseList(g.genres, campos[12]);
                g.tagCount = parseList(g.tags, campos[13]);
                printGame(&g);
            }
            for(int j=0;j<campoIndex;j++) free(campos[j]);
        }
        fclose(fp);
    }
    return 0;
}
