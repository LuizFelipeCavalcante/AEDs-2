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

typedef struct Node {
    Game game;
    struct Node *next;
} Node;

Node *head = NULL;
int sizeList = 0;

void trim(char *str) {
    int start = 0, end = (int)strlen(str) - 1;
    while(start <= end && isspace((unsigned char)str[start])) start++;
    while(end >= start && isspace((unsigned char)str[end])) end--;
    str[end+1] = '\0';
    if(start > 0) memmove(str, str+start, end-start+2);
}

void onlyDigits(char *dest, const char *src) {
    int j = 0;
    for(int i = 0; src[i] && j < MAX_STR-1; i++)
        if(isdigit((unsigned char)src[i])) dest[j++] = src[i];
    dest[j] = '\0';
}

int parseList(char array[][MAX_STR], const char *text) {
    int count = 0;
    int i = 0, j = 0;
    char buffer[MAX_STR];
    int len = (int)strlen(text);
    while(i < len) {
        if(text[i]=='[' || text[i]=='\'' || text[i]=='\"') { i++; continue; }
        if(text[i]==']') {
            if(j>0){ buffer[j]='\0'; strcpy(array[count++], buffer); trim(array[count-1]); j=0; }
            break;
        }
        if(text[i]==',' && j>0) {
            buffer[j]='\0';
            strcpy(array[count++], buffer);
            trim(array[count-1]);
            j=0; i++; while(i<len && text[i]==' ') i++;
            continue;
        }
        buffer[j++] = text[i++];
    }
    if(j>0) { buffer[j]='\0'; strcpy(array[count++], buffer); trim(array[count-1]); }
    return count;
}

float normalizePrice(const char *priceStr) {
    if(strcmp(priceStr, "Free to Play")==0 || strlen(priceStr)==0) return 0.0f;
    char tmp[MAX_STR];
    int k=0;
    for(int i=0; priceStr[i] && k < MAX_STR-1; i++) {
        if((priceStr[i]>='0' && priceStr[i]<='9') || priceStr[i]=='.' || priceStr[i]==',') {
            tmp[k++]=priceStr[i]=='?'?'.':priceStr[i];
        }
    }
    tmp[k]='\0';
    for(int i=0;i<k;i++) if(tmp[i]==',') tmp[i]='.';
    return atof(tmp);
}

int normalizeMetacritic(const char *score) {
    if(strlen(score)==0) return -1;
    return atoi(score);
}

float normalizeUserScore(const char *score) {
    if(strlen(score)==0 || strcmp(score,"tbd")==0) return -1.0f;
    return atof(score);
}

int normalizeAchievements(const char *ach) {
    if(strlen(ach)==0) return 0;
    return atoi(ach);
}

void normalizeDate(char *out, const char *in) {
    char month[16];
    int day = 1, year = 1970;
    if(sscanf(in, "%15s %d, %d", month, &day, &year) < 3) { strcpy(out,"01/01/0000"); return; }
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

void printGameNoIndex(Game *g) {
    printf("%d ## %s ## %s ## %d ## %.2f ## ", g->id, g->name, g->releaseDate, g->estimatedOwners, g->price);
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

Game readGameFromCSVById(const char *idStr, int *found) {
    Game gtemp;
    *found = 0;
    FILE *fp = fopen("/tmp/games.csv","r");
    if(!fp) return gtemp;
    char line[4096];
    if(!fgets(line,4096,fp)) { fclose(fp); return gtemp; }
    while(fgets(line,4096,fp)) {
        char *campos[20];
        int campoIndex=0;
        static char buffer[4096];
        int bufIndex=0;
        int inQuotes=0;
        for(int i=0; line[i] && line[i] != '\n'; i++) {
            if(line[i]=='"') {
                inQuotes = !inQuotes;
            } else if(line[i]==',' && !inQuotes) {
                buffer[bufIndex]='\0';
                campos[campoIndex] = malloc(strlen(buffer)+1);
                strcpy(campos[campoIndex], buffer);
                campoIndex++;
                bufIndex=0;
            } else {
                buffer[bufIndex++]=line[i];
            }
        }
        buffer[bufIndex]='\0';
        campos[campoIndex]=malloc(strlen(buffer)+1);
        strcpy(campos[campoIndex], buffer);
        campoIndex++;
        if(campoIndex>=14 && strcmp(campos[0], idStr)==0) {
            *found = 1;
            memset(&gtemp,0,sizeof(Game));
            gtemp.id = atoi(campos[0]);
            strncpy(gtemp.name, campos[1], MAX_STR-1);
            normalizeDate(gtemp.releaseDate, campos[2]);
            char tmp[64]; onlyDigits(tmp, campos[3]);
            gtemp.estimatedOwners = atoi(tmp);
            gtemp.price = normalizePrice(campos[4]);
            gtemp.supportedLangCount = parseList(gtemp.supportedLanguages, campos[5]);
            gtemp.metacriticScore = normalizeMetacritic(campos[6]);
            gtemp.userScore = normalizeUserScore(campos[7]);
            gtemp.achievements = normalizeAchievements(campos[8]);
            gtemp.publisherCount = parseList(gtemp.publishers, campos[9]);
            gtemp.developerCount = parseList(gtemp.developers, campos[10]);
            gtemp.categoryCount = parseList(gtemp.categories, campos[11]);
            gtemp.genreCount = parseList(gtemp.genres, campos[12]);
            gtemp.tagCount = parseList(gtemp.tags, campos[13]);
            for(int j=0;j<campoIndex;j++) free(campos[j]);
            break;
        }
        for(int j=0;j<campoIndex;j++) free(campos[j]);
    }
    fclose(fp);
    return gtemp;
}

void inserirInicio(Game game) {
    Node *n = (Node*)malloc(sizeof(Node));
    n->game = game;
    n->next = head;
    head = n;
    sizeList++;
}

void inserirFim(Game game) {
    Node *n = (Node*)malloc(sizeof(Node));
    n->game = game;
    n->next = NULL;
    if(head==NULL) { head = n; }
    else {
        Node *p = head;
        while(p->next) p = p->next;
        p->next = n;
    }
    sizeList++;
}

void inserir(Game game, int pos) {
    if(pos < 0) pos = 0;
    if(pos == 0) { inserirInicio(game); return; }
    if(pos >= sizeList) { inserirFim(game); return; }
    Node *n = (Node*)malloc(sizeof(Node));
    n->game = game;
    Node *p = head;
    for(int i=0;i<pos-1;i++) p = p->next;
    n->next = p->next;
    p->next = n;
    sizeList++;
}

Game removerInicio() {
    Game ret;
    memset(&ret,0,sizeof(Game));
    if(head==NULL) return ret;
    Node *r = head;
    head = head->next;
    ret = r->game;
    free(r);
    sizeList--;
    return ret;
}

Game removerFim() {
    Game ret;
    memset(&ret,0,sizeof(Game));
    if(head==NULL) return ret;
    if(head->next==NULL) {
        ret = head->game;
        free(head);
        head = NULL;
        sizeList--;
        return ret;
    }
    Node *p = head;
    while(p->next->next) p = p->next;
    Node *r = p->next;
    ret = r->game;
    p->next = NULL;
    free(r);
    sizeList--;
    return ret;
}

Game remover(int pos) {
    Game ret;
    memset(&ret,0,sizeof(Game));
    if(head==NULL) return ret;
    if(pos <= 0) return removerInicio();
    if(pos >= sizeList-1) return removerFim();
    Node *p = head;
    for(int i=0;i<pos-1;i++) p = p->next;
    Node *r = p->next;
    ret = r->game;
    p->next = r->next;
    free(r);
    sizeList--;
    return ret;
}

int main() {
    char line[MAX_STR];
    while(1) {
        if(!fgets(line, MAX_STR, stdin)) return 0;
        line[strcspn(line,"\n")] = 0;
        trim(line);
        if(strcmp(line,"FIM")==0) break;
        if(strlen(line)==0) continue;
        int found=0;
        Game g = readGameFromCSVById(line, &found);
        if(found) inserirFim(g);
    }
    if(!fgets(line, MAX_STR, stdin)) return 0;
    trim(line);
    int commands = atoi(line);
    for(int i=0;i<commands;i++) {
        if(!fgets(line, MAX_STR, stdin)) break;
        line[strcspn(line,"\n")] = 0;
        trim(line);
        if(strlen(line)==0) { i--; continue; }
        if(strncmp(line,"II ",3)==0) {
            char idstr[MAX_STR];
            sscanf(line+3,"%s",idstr);
            int found=0;
            Game g = readGameFromCSVById(idstr,&found);
            if(found) inserirInicio(g);
        } else if(strncmp(line,"IF ",3)==0) {
            char idstr[MAX_STR];
            sscanf(line+3,"%s",idstr);
            int found=0;
            Game g = readGameFromCSVById(idstr,&found);
            if(found) inserirFim(g);
        } else if(strncmp(line,"I* ",3)==0) {
            int pos=0; char idstr[MAX_STR];
            sscanf(line+3,"%d %s",&pos, idstr);
            int found=0;
            Game g = readGameFromCSVById(idstr,&found);
            if(found) inserir(g, pos);
        } else if(strcmp(line,"RI")==0) {
            Game r = removerInicio();
            printf("(R) %s\n", r.name);
        } else if(strcmp(line,"RF")==0) {
            Game r = removerFim();
            printf("(R) %s\n", r.name);
        } else if(strncmp(line,"R* ",3)==0) {
            int pos=0;
            sscanf(line+3,"%d",&pos);
            Game r = remover(pos);
            printf("(R) %s\n", r.name);
        }
    }
    Node *p = head;
    int idx = 0;
    while(p) {
        printf("[%d] => ", idx++);
        printGameNoIndex(&p->game);
        p = p->next;
    }
    return 0;
}
