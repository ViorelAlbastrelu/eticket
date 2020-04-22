package com.faciee.cti.valbastrelu.eticket.database

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(TicketDaoTest::class, StationDaoTest::class)
class DatabaseDaoSuite