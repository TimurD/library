package com.timur.library.services;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by timur on 06.06.2017.
 */
public class AuthorizationServiceTest {

    private AuthorizationService authorizationService=AuthorizationService.getInstance();
    private String validName="Timur";
    private String invalidName="Timur1";
    private String invalidName2="Timu r";

    @Test
    public void checkNameTest(){
        Assert.assertTrue(authorizationService.checkName(validName));
        Assert.assertFalse(authorizationService.checkName(invalidName));
        Assert.assertFalse(authorizationService.checkName(invalidName2));
    }

}
