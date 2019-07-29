package com.jsb.snagflims.data;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class DataRepoTest {

    @Mock DataRepo mDataRepo;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mock(DataRepo.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMovies() {
        Assert.assertFalse(mDataRepo.getMovies("", null));
    }
}