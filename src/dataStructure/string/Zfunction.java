package src.dataStructure.string;

/**
 * Zfunction is used to find the count of pattern in a string.
 */
public class Zfunction {

    public static void main(String[] args) {
        String target = "abacabadabacaba";
        String pattern = "aba"; // Change to a pattern that exists in the target

        // Step 1: Build the combined string
        String combined = pattern + "$" + target;

        // Step 2: Initialize the Z-array
        int[] z = new int[combined.length()];

        // Step 3: Initialize window pointers
        int left = 0;
        int right = 0;

        // Step 4: Compute the Z-array
        for (int i = 1; i < combined.length(); i++) {
            // If the current index is within the window, reuse some previous Z-values
            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            // Try to extend the match as far as possible
            while (i + z[i] < combined.length() &&
                    combined.charAt(z[i]) == combined.charAt(i + z[i])) {
                z[i]++;
            }

            // Update the window if the extended match goes beyond the current window
            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }

        // Step 5: Count how many times the pattern fully matches in the target
        int count = 0;
        for (int i = 0; i < z.length; i++) {
            if (z[i] == pattern.length()) {
                count++;
            }
        }

        // Step 6: Print the total number of occurrences
        System.out.println("Number of occurrences of pattern \"" + pattern + "\": " + count);
    }
}
