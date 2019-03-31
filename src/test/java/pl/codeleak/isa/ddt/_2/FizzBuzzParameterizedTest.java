package pl.codeleak.isa.ddt._2;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.mappers.CsvWithHeaderMapper;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class FizzBuzzParameterizedTest {

    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void fizzBuzzWithLoop() {

        Map<Integer, String> params = new HashMap<>();
        params.put(1, "1");
        params.put(2, "2");
        params.put(3, "Fizz");
        params.put(4, "4");
        params.put(6, "Fizz");
        params.put(5, "Buzz");
        params.put(10, "Buzz");
        params.put(15, "FizzBuzz");
        params.put(30, "FizzBuzz");

        params.forEach((number,result) -> assertThat(fizzBuzz.calculate(number)).isEqualTo(result));
    }

    @Parameters({"1,1", "2,2", "3,Fizz", "6,Fizz1", "5,Buzz", "10,Buzz", "15,FizzBuzz", "30,FizzBuzz"})
    @TestCaseName("FizzBuzz returns {1} when number is {0}")
    @Test
    public void fizzBuzzWithParametersAnnotation(int number, String result) {
        assertThat(fizzBuzz.calculate(number)).isEqualTo(result);

    }

    @Test
    @Parameters
//    @Parameters(method ="nazwa metody ktora zwraca parametry ale nie musi miec nazwy parametrsFornazwametody")
    public void fizzBuzzWithMethodSource(int number, String result) {
        assertThat(fizzBuzz.calculate(number)).isEqualTo(result);
    }

    private Object[][] parametersForFizzBuzzWithMethodSource(){
        return new Object[][]{
                {1, "1"},
                {2, "2"},
                {3, "Fizz"},
                {6, "Fizz"},
                {5, "Buzz"},
                {10, "Buzz"},
                {15, "FizzBuzz"},
                {30, "FizzBuzz"}
        };
    }

    @Test
    //classpath to jest tam gdzie maven ma resources
    @FileParameters(value = "classpath:fizz-buzz.csv", mapper = CsvWithHeaderMapper.class)
    public void fizzBuzzWitFileSource(int number, String result) {
        assertThat(fizzBuzz.calculate(number)).isEqualTo(result);
    }
}