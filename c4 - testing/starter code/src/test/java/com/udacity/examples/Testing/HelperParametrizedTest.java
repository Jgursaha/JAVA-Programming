package com.udacity.examples.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class HelperParametrizedTest {

    private String input;
    private String output;

    public HelperParametrizedTest(String input) {
        super();
        this.input = input;
    }

    @Parameterized.Parameters
    public static Collection initData(){
        String empName[][] = {{"J","K"}, {"J", "J"}};
        return Arrays.asList(empName);
    }

    @Test
    public void verify_input_name_is_not_the_same_as_the_output_name(){
        assertNotEquals(input,output);
    }
}
