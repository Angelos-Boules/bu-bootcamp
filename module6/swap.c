#include "stdio.h"

void swap(int *a, int *b);
void broken_swap(int a, int b);

int main(void) {
    int x = 10;
    int y = 20;

    printf("Before swap: x = %d, y = %d\n", x, y);
    swap(&x, &y);
    printf("After swap: x = %d, y = %d\n", x, y);

    printf("Before broken swap: x = %d, y = %d\n", x, y);
    broken_swap(x, y);
    printf("After broken swap: x = %d, y = %d\n", x, y);

    return 0;
}

void swap(int *a, int *b) {
    int temp = *b;
    *b = *a;
    *a = temp;
}

/* Will not work - this function is taking parameters by value and so it receves
a copy of a and b. The swap will occur only within the local scope of the function
but the actual values of the original variables will remain unchanged */
void broken_swap(int a, int b) {
    int temp = b;
    b = a;
    a = temp;
}