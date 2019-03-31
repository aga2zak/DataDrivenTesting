package pl.codeleak.isa.ddt._2;

class FizzBuzz {

    String calculate(int number) {
        if (isDivisibleBy(number, 3) && isDivisibleBy(number, 5)) {
            return "FizzBuzz";
        }

        if (isDivisibleBy(number, 3)) {
            return "Fizz";
        }

        if (isDivisibleBy(number, 5)) {
            return "Buzz";
        }

        return String.valueOf(number);
    }

    private boolean isDivisibleBy(int dividend, int divisor) {
        return dividend % divisor == 0;
    }
}
