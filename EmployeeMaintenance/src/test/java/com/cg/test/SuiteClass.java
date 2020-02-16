package com.cg.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class SuiteClass.
 * Used to run a test suit consisting of AdminSertivce, EmployeeService, ManagerService
 *
 * @author Gagandeep
 * @time 11:55:07 pm
 * @date 16-Feb-2020
 */

@RunWith(Suite.class)
@SuiteClasses({AdminServiceTest.class, EmployeeServiceTest.class, ManagerServiceTest.class})
public class SuiteClass {

}
