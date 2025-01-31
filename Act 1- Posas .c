#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    srand(time(NULL));
    int numberToGuess = rand() % 100 + 1;
    int numberOfTries = 0;
    int userGuess;

    printf("Welcome to Guess My Number!\n");
    printf("I'm thinking of a number between 1 and 100.\n");

    while (1) {
        printf("Enter your guess: ");

        if (scanf("%d", &userGuess) != 1) {
            printf("Invalid input. Please enter an integer.\n");
            while (getchar() != '\n');
            continue;
        }

        if (userGuess < 1 || userGuess > 100) {
            printf("Out of range! Enter a number between 1 and 100.\n");
            continue;
        }

        numberOfTries++;

        if (userGuess < numberToGuess) {
            printf("Too low! Try again.\n");
        } else if (userGuess > numberToGuess) {
            printf("Too high! Try again.\n");
        } else {
            printf("Congratulations! You found the number in %d tries.\n", numberOfTries);
            break;
        }
    }

    return 0;
}
