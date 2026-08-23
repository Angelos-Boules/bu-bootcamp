#include "stdio.h"

void print_math(int a, int b);

int main(void) {
    int a;
    int b;

    printf("Enter first number: ");
    scanf("%d", &a);
    printf("Enter second number: ");
    scanf("%d", &b);

    print_math(a, b);
    return 0;
}

void print_math(int a, int b) {
    printf("Sum: %6d\n", a+b);
    printf("Product: %2d\n", a*b);
}