#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define MAX_STR 512
#define MAX_ARRAY 50
#define MAX_GAMES 100
#define MAX_LINE_LEN 4096
#define MATRICULA "00879283"

long long comparacoes = 0;
long long movimentacoes = 0;

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

int custom_strcmp(const char *s1, const char *s2) {
    while (*s1 && (*s1 == *s2)) {
        s1++;
        s2++;
    }
    return *(const unsigned char*)s1 - *(const unsigned char*)s2;
}

char* custom_strcpy(char *dest, const char *src) {
    char *original_dest = dest;
    while (*src) {
        *dest++ = *src++;
    }
    *dest = '\0';
    return original_dest;
}

size_t custom_strlen(const char *s) {
    size_t len = 0;
    while (*s) {
        len++;
        s++;
    }
    return len;
}

void custom_trim(char *str) {
    int start = 0, end = custom_strlen(str) - 1;
    while(start < custom_strlen(str) && isspace(str[start])) start++;
    while(end >= start && isspace(str[end])) end--;
    str[end+1] = '\0';
    if (start > 0) memmove(str, str+start, end-start+2);
}

int custom_atoi(const char *str) {
    int res = 0;
    int sign = 1;
    int i = 0;
    while (str[i] == ' ') i++;
    if (str[i] == '-') {
        sign = -1;
        i++;
    }
    while (str[i] >= '0' && str[i] <= '9') {
        res = res * 10 + (str[i] - '0');
        i++;
    }
    return sign * res;
}

float custom_atof(const char *str) {
    float res = 0.0f;
    float frac = 0.0f;
    float div = 1.0f;
    int sign = 1;
    int i = 0;
    int after_decimal = 0;

    while (str[i] == ' ') i++;
    if (str[i] == '-') {
        sign = -1;
        i++;
    }

    while (str[i] != '\0') {
        if (str[i] >= '0' && str[i] <= '9') {
            if (!after_decimal) {
                res = res * 10.0f + (float)(str[i] - '0');
            } else {
                frac = frac * 10.0f + (float)(str[i] - '0');
                div *= 10.0f;
            }
        } else if (str[i] == '.') {
            after_decimal = 1;
        }
        i++;
    }
    return sign * (res + frac / div);
}


void custom_onlyDigits(char *dest, const char *src) {
    int j = 0;
    for(int i = 0; src[i] && j < MAX_STR-1; i++)
        if(isdigit(src[i])) dest[j++] = src[i];
    dest[j] = '\0';
}

int custom_parseList(char array[][MAX_STR], const char *text) {
    int count = 0;
    int i = 0, j = 0;
    char buffer[MAX_STR];
    int len = custom_strlen(text);
    int inQuotes = 0;

    while(i < len && count < MAX_ARRAY) {
        if(text[i]=='"' && (i==0 || text[i-1]!='\\')) inQuotes = !inQuotes;
        
        if(text[i]=='[' || text[i]=='\'' || text[i]=='"') { i++; continue; }
        
        if(text[i]==']') { 
            if(j>0){ buffer[j]='\0'; custom_strcpy(array[count++], buffer); custom_trim(array[count-1]); j=0; }
            break; 
        }
        
        if(text[i]==',' && j>0 && !inQuotes) { 
            buffer[j]='\0'; 
            custom_strcpy(array[count++], buffer); 
            custom_trim(array[count-1]);
            j=0; i++; while(text[i]==' ') i++; 
            continue; 
        }
        
        if(text[i] != '\n' && text[i] != '\r') {
            buffer[j++] = text[i++];
        } else {
            i++;
        }
    }
    if(j>0 && count < MAX_ARRAY) { buffer[j]='\0'; custom_strcpy(array[count++], buffer); custom_trim(array[count-1]); }
    return count;
}


float custom_normalizePrice(const char *priceStr) {
    if(custom_strcmp(priceStr, "Free to Play")==0 || custom_strlen(priceStr)==0) return 0.0f;
    return custom_atof(priceStr);
}

int custom_normalizeMetacritic(const char *score) {
    if(custom_strlen(score)==0) return -1;
    return custom_atoi(score);
}

float custom_normalizeUserScore(const char *score) {
    if(custom_strlen(score)==0 || custom_strcmp(score,"tbd")==0) return 0.0f;
    return custom_atof(score);
}

int custom_normalizeAchievements(const char *ach) {
    if(custom_strlen(ach)==0) return 0;
    return custom_atoi(ach);
}

int get_month_index(char *m) {
    if(custom_strcmp(m,"Jan")==0) return 1; else if(custom_strcmp(m,"Feb")==0) return 2;
    else if(custom_strcmp(m,"Mar")==0) return 3; else if(custom_strcmp(m,"Apr")==0) return 4;
    else if(custom_strcmp(m,"May")==0) return 5; else if(custom_strcmp(m,"Jun")==0) return 6;
    else if(custom_strcmp(m,"Jul")==0) return 7; else if(custom_strcmp(m,"Aug")==0) return 8;
    else if(custom_strcmp(m,"Sep")==0) return 9; else if(custom_strcmp(m,"Oct")==0) return 10;
    else if(custom_strcmp(m,"Nov")==0) return 11; else if(custom_strcmp(m,"Dec")==0) return 12;
    return 1;
}

void custom_normalizeDate(char *out, const char *in) {
    char month_str[4] = "";
    int day = 0;
    int year = 0;
    int i = 0;
    int state = 0; 
    char temp_num[5] = "";
    int temp_idx = 0;

    while(in[i] != '\0' && i < custom_strlen(in)) {
        if(state == 0) { 
            if(isalpha(in[i]) && temp_idx < 3) month_str[temp_idx++] = in[i];
            else if(isdigit(in[i]) && temp_idx == 3) { month_str[temp_idx] = '\0'; temp_idx = 0; state = 1; }
            i++;
        } else if(state == 1) { 
            if(isdigit(in[i]) && temp_idx < 2) temp_num[temp_idx++] = in[i];
            else if(in[i] == ',' && temp_idx > 0) { temp_num[temp_idx] = '\0'; day = custom_atoi(temp_num); temp_idx = 0; state = 2; }
            else if(isspace(in[i])) { i++; continue; }
            else if(temp_idx > 0 && isdigit(in[i])) { temp_num[temp_idx] = '\0'; day = custom_atoi(temp_num); temp_idx = 0; state = 2; i++; continue; }
            i++;
        } else if(state == 2) { 
            if(isdigit(in[i]) && temp_idx < 4) temp_num[temp_idx++] = in[i];
            else if(!isdigit(in[i]) && temp_idx == 4) { temp_num[temp_idx] = '\0'; year = custom_atoi(temp_num); break; }
            i++;
        } else { i++; }
    }
    if (temp_idx == 4 && state == 2) { temp_num[temp_idx] = '\0'; year = custom_atoi(temp_num); }
    if (year == 0) {
        if (custom_strlen(month_str) == 3) {
            if (sscanf(in + custom_strlen(month_str), " %d", &year) == 1) day = 1;
        } else {
            custom_strcpy(out, "01/01/1970");
            return;
        }
    }
    
    int m = get_month_index(month_str);

    sprintf(out,"%02d/%02d/%d", day > 0 ? day : 1, m, year > 0 ? year : 1970);
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

void loadGame(Game *g, char *campos[]) {
    g->id = custom_atoi(campos[0]);
    strncpy(g->name, campos[1], MAX_STR - 1); g->name[MAX_STR-1] = '\0';
    custom_normalizeDate(g->releaseDate, campos[2]);
    char tmp[64]; custom_onlyDigits(tmp, campos[3]);
    g->estimatedOwners = custom_atoi(tmp);
    g->price = custom_normalizePrice(campos[4]);
    g->supportedLangCount = custom_parseList(g->supportedLanguages, campos[5]);
    g->metacriticScore = custom_normalizeMetacritic(campos[6]);
    g->userScore = custom_normalizeUserScore(campos[7]);
    g->achievements = custom_normalizeAchievements(campos[8]);
    g->publisherCount = custom_parseList(g->publishers, campos[9]);
    g->developerCount = custom_parseList(g->developers, campos[10]);
    g->categoryCount = custom_parseList(g->categories, campos[11]);
    g->genreCount = custom_parseList(g->genres, campos[12]);
    g->tagCount = custom_parseList(g->tags, campos[13]);
}

void swap(Game *a, Game *b) {
    Game temp = *a;
    *a = *b;
    *b = temp;
    movimentacoes += 3;
}

void selectionSort(Game array[], int n) {
    for (int i = 0; i < n - 1; i++) {
        int menor = i;
        for (int j = i + 1; j < n; j++) {
            comparacoes++;
            if (custom_strcmp(array[j].name, array[menor].name) < 0) {
                menor = j;
            }
        }
        if (menor != i) {
             swap(&array[menor], &array[i]);
        }
    }
}

void get_fields(const char *line, char *fields[]) {
    int field_idx = 0;
    int char_idx = 0;
    int in_quotes = 0;
    char buffer[MAX_LINE_LEN];
    int buffer_idx = 0;

    while (line[char_idx] != '\0' && field_idx < 14) {
        char current_char = line[char_idx];

        if (current_char == '"') {
            if (in_quotes && line[char_idx + 1] == '"') {
                buffer[buffer_idx++] = current_char;
                char_idx += 2; 
                continue;
            }
            in_quotes = !in_quotes;
            char_idx++;
        } else if (current_char == ',' && !in_quotes) {
            buffer[buffer_idx] = '\0';
            fields[field_idx] = (char *)malloc(custom_strlen(buffer) + 1);
            if (fields[field_idx] == NULL) exit(1);
            custom_strcpy(fields[field_idx], buffer);
            field_idx++;
            buffer_idx = 0;
            char_idx++;
        } else if (current_char != '\n' && current_char != '\r') {
            buffer[buffer_idx++] = current_char;
            char_idx++;
        } else {
            char_idx++;
        }
    }

    if (buffer_idx > 0 && field_idx < 14) {
        buffer[buffer_idx] = '\0';
        fields[field_idx] = (char *)malloc(custom_strlen(buffer) + 1);
        if (fields[field_idx] == NULL) exit(1);
        custom_strcpy(fields[field_idx], buffer);
        field_idx++;
    }
    
    while (field_idx < 14) {
        fields[field_idx] = (char *)malloc(1);
        if (fields[field_idx] == NULL) exit(1);
        fields[field_idx][0] = '\0';
        field_idx++;
    }
}

void free_fields(char *fields[]) {
    for (int i = 0; i < 14; i++) {
        if (fields[i]) free(fields[i]);
    }
}


int main() {
    Game games[MAX_GAMES];
    int gameCount = 0;
    char idBuscado[MAX_STR];

    while(1) {
        if(fgets(idBuscado, MAX_STR, stdin) == NULL) break;
        custom_trim(idBuscado);
        if(custom_strcmp(idBuscado,"FIM")==0) break;

        FILE *fp = fopen("/tmp/games.csv","r");
        if(!fp){ return 1; }
        
        char line[MAX_LINE_LEN];
        if (fgets(line, MAX_LINE_LEN, fp) == NULL) { fclose(fp); continue; }
        
        while(fgets(line,MAX_LINE_LEN,fp) && gameCount < MAX_GAMES) {
            char *campos[14] = {NULL};
            get_fields(line, campos);

            if(campos[0] && custom_strcmp(campos[0], idBuscado) == 0) {
                loadGame(&games[gameCount], campos);
                gameCount++;
                free_fields(campos);
                break;
            }
            
            free_fields(campos);
        }
        fclose(fp);
    }
    
    clock_t start_t, end_t;
    start_t = clock();
    
    selectionSort(games, gameCount);
    
    end_t = clock();
    double tempoExecucaoMs = (double)(end_t - start_t) * 1000.0 / CLOCKS_PER_SEC;

    for (int i = 0; i < gameCount; i++) {
        printGame(&games[i]);
    }

    char nomeLogFinal[MAX_STR];
    sprintf(nomeLogFinal, "%s_selecao.txt", MATRICULA);

    FILE *logFp = fopen(nomeLogFinal, "w");
    if (logFp) {
        fprintf(logFp, "%s\t%lld\t%lld\t%.0f\n",
                MATRICULA,
                comparacoes, 
                movimentacoes,          
                tempoExecucaoMs); 
        fclose(logFp);
    }

    return 0;
}