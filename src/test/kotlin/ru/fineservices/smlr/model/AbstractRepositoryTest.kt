package ru.fineservices.smlr.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import ru.fineservices.smlr.SmlrApplication

// Задача этого класса в том, чтобы своими аннотациями указать,
// каким образом будет подниматься контекст

//@TestPropertySource(locations = ["classpath:repositories-test.properties"])
@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringBootTest(classes = [SmlrApplication::class])
@DirtiesContext
abstract class AbstractRepositoryTest : AbstractTransactionalJUnit4SpringContextTests()
