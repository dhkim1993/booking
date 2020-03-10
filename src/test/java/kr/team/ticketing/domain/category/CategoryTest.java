package kr.team.ticketing.domain.category;

import kr.team.ticketing.core.domain.category.CategoryRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class CategoryTest {

    @Autowired
    CategoryRepository categoryRepository;


}