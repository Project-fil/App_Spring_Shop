package com.github.ratel.repositories;

import com.github.ratel.entity.Brand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    @Sql(value = {"schema-brand.sql", "ratel-brand-data.sql"})
    public void findById(){
        Brand act = brandRepository.findById(2L).orElseThrow();
        Brand exp = BrandRepositoryMock.brand_2();
        Assert.assertEquals(exp, act);
    }

}