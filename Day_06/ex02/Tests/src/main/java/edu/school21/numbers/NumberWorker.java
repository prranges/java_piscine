package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        int i = 2;
        boolean bool = true;
        if (number < 2) {
            throw new IllegalNumberException("IllegalArgument");
        }
        while (i * i <= number && bool) {
            if (number % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public int digitsSum(int number) {
        int res = 0;

        while (number > 0) {
            res += number % 10;
            number /= 10;
        }
        return res;
    }
}

class IllegalNumberException extends IllegalArgumentException {
    public IllegalNumberException(String message) {
        super(message);
    }
}
