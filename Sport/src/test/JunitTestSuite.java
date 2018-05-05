package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  MatchTennisTest.class,
  JoueurTennisTest.class,
  JoueurHandballTest.class,
  MatchHandballTest.class,
  ReadPropertiesTest.class
})

public class JunitTestSuite {

}
