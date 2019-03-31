package pl.codeleak.isa.ddt._1;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    FizzBuzz fizzBuzz = new FizzBuzz();

    // TODO Create several tests like "returns_<result>_WhenNumberIs_<number>"

    @Test
    public void returns_1_WhenNuberIs_1(){
        assertThat(fizzBuzz.calculate(1)).isEqualTo("1");
    }

    @Test
    public void returns_2_WhenNuberIs_2(){
        assertThat(fizzBuzz.calculate(2)).isEqualTo("2");
    }

    @Test
    public void returns_Fizz_WhenNuberIs_3(){
        assertThat(fizzBuzz.calculate(3)).isEqualTo("Fizz");
    }

    @Test
    public void returns_Fizz_WhenNuberIs_6(){
        assertThat(fizzBuzz.calculate(6)).isEqualTo("Fizz");
    }

    @Test
    public void returns_Buzz_WhenNuberIs_5(){
        assertThat(fizzBuzz.calculate(5)).isEqualTo("Buzz");
    }

    @Test
    public void returns_Buzz_WhenNuberIs_10(){
        assertThat(fizzBuzz.calculate(10)).isEqualTo("Buzz");
    }

    @Test
    public void returns_FizzBuzz_WhenNuberIs_1(){
        assertThat(fizzBuzz.calculate(15)).isEqualTo("FizzBuzz");
    }

    @Test
    public void returns_FizzBuzz_WhenNuberIs_30(){
        assertThat(fizzBuzz.calculate(30)).isEqualTo("FizzBuzz");
    }

}