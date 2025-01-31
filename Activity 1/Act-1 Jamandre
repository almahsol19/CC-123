#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    srand(time(NULL)); // Seed the random number generator with the current time
    int numberToGuess = rand() % 100 + 1; // Generate a random number between 1 and 100
    int numberOfTries = 0; // Keep track of the number of attempts
    int userGuess; // Variable to store user's guess

    printf("Welcome to Guess My Number!\n");
    printf("I'm thinking of a number between 1 and 100.\n");

    while (1) { // Infinite loop until the correct number is guessed
        printf("Enter your guess: ");

        // Validate input to ensure it's an integer
        if (scanf("%d", &userGuess) != 1) {
            printf("Invalid input. Please enter an integer.\n");
            while (getchar() != '\n'); // Clear the input buffer
            continue; // Restart loop
        }

        // Check if guess is within the valid range
        if (userGuess < 1 || userGuess > 100) {
            printf("Out of range! Enter a number between 1 and 100.\n");
            continue; // Restart loop
        }

        numberOfTries++; // Increment attempt counter

        // Compare the guess with the actual number
        if (userGuess < numberToGuess) {
            printf("Too low! Try again.\n");
        } else if (userGuess > numberToGuess) {
            printf("Too high! Try again.\n");
        } else {
            printf("Congratulations! You found the number in %d tries.\n", numberOfTries);
            break; // Exit loop when guessed correctly
        }
    }

    return 0; // End program
}
