package nl.tudelft.oopp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class HasherTest {

    @Test
    void hashPassword1() {
        String password = "kanish";
        String res = "c80a11f2ddfff79d56fd0df037f23639a991938328c2f8cc33294d37df4849";
        assertEquals(res, Hasher.hashPassword(password));
    }

    @Test
    void hashPassword2() {
        String password = "werty7890plkmnbvcxzawe4567ui";
        int length = Hasher.hashPassword(password).length();
        assertEquals(62, length);
    }


}