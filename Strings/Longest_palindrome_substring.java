public class LongestPalindromeSubstring {

    // Function to return the longest palindromic substring
    public String longestPalindrome(String s) {
        // Edge case: if string is null or empty
        if (s == null || s.length() < 1) return "";

        int start = 0;  // start index of longest palindrome
        int end = 0;    // end index of longest palindrome

        // Loop through each index, treating it as a possible center
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome (center at i)
            int odd = expand(s, i, i);

            // Even length palindrome (center between i and i+1)
            int even = expand(s, i, i + 1);

            // Get the maximum length found
            int len = Math.max(odd, even);

            // If this palindrome is longer than previously found one
            if (len > end - start) {
                // Update start and end using formula
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Extract and return the longest palindromic substring
        return s.substring(start, end + 1);
    }

    // Helper function: expand around the given center
    private int expand(String s, int left, int right) {
        // Expand outward as long as it's still a palindrome
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;   // move left pointer to left
            right++;  // move right pointer to right
        }
        // When expansion stops, return the palindrome length
        return right - left - 1;
    }

    // Driver code to test the function
    public static void main(String[] args) {
        LongestPalindromeSubstring sol = new LongestPalindromeSubstring();

        String s1 = "babad";
        System.out.println("Input: " + s1);
        System.out.println("Longest Palindrome: " + sol.longestPalindrome(s1));

        String s2 = "cbbd";
        System.out.println("Input: " + s2);
        System.out.println("Longest Palindrome: " + sol.longestPalindrome(s2));
    }
}

/**
🔹 Problem Statement

Given a string `s`, return the **longest palindromic substring** in `s`.

A **palindrome** is a string that reads the same forward and backward.

### Example 1:

```
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
```

### Example 2:

```
Input: s = "cbbd"
Output: "bb"
```

### Constraints:

```
1 <= s.length <= 1000
s consists of only digits and English letters.
```

---

# 🔹 Understanding the Problem

* We want the **longest substring** (continuous sequence of characters) that is a palindrome.
* Substring ≠ subsequence → must be continuous.
* Multiple answers possible (e.g., `"babad"` → `"bab"` or `"aba"`).

---

# 🔹 Concept Used: Palindrome & Expand Around Center

A **palindrome** is symmetric around its **center**.

* Odd-length palindrome: center at a single character
  Example: `"aba"` → center `"b"`
* Even-length palindrome: center between two characters
  Example: `"abba"` → center between the two `"b"`s

👉 So, if we expand outward from every possible center and check matching characters, we can find all palindromes.

Since there are **2n − 1 possible centers** for a string of length `n` (n odd centers + n−1 even centers), we can try each one.

---

# 🔹 Logic to Solve

1. Loop over each index `i` of the string.
2. For each `i`, expand in two ways:

   * Odd palindrome (`expand(i, i)`)
   * Even palindrome (`expand(i, i+1)`)
3. Take the longer palindrome from step 2.
4. If this palindrome is longer than the previous best, update `start` and `end` indices.
5. After the loop ends, return the substring from `start` to `end`.

```

---

# 🔹 Example Dry Run: `"babad"`

* `i = 0` (center `"b"`) → palindrome `"b"`, length = 1
* `i = 1` (center `"a"`) → palindrome `"bab"`, length = 3
* `i = 2` (center `"b"`) → palindrome `"aba"`, length = 3
* Max length = 3 → result = `"bab"` (or `"aba"`)

---

# 🔹 Time and Space Complexity

* **Time Complexity:**

  * Each expansion takes O(n) in the worst case.
  * There are O(n) centers.
  * Total = **O(n²)**

* **Space Complexity:**

  * We only use `start`, `end`, and a few variables → **O(1)** extra space.

---


 */