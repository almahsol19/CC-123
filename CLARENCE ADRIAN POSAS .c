#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    srand(time(NULL)); // Seed the random number generator with the current time
    int numberToGuess = rand() % 100 + 1; // Generate a random number between 1 and 100
    int numberOfTries = 0; // Initialize the number of tries to 0

    printf("Welcome to Guess My Number!\n");
    printf("I'm thinking of a number between 1 and 100.\n");

    while (1) { // Infinite loop to keep the game running until the correct number is guessed
        int userGuess;
        printf("Enter your guess: ");
        scanf("%d", &userGuess); // Read the user's guess
        numberOfTries++; // Increment the number of tries

        if (userGuess < numberToGuess) { // If the guess is too low 
            printf("Too low! Try again.\n");
        } else if (userGuess > numberToGuess) { // If the guess is too high
            printf("Too high! Try again.\n");
        } else { // If the guess is correct
            printf("Congratulations! You found the number in %d tries.\n", numberOfTries);
            break; // Exit the loop
        }
    }

    return 0; // End of the program
}
