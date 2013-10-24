import groovy.util.GroovyTestCase
   
class ExampleTest extends GroovyTestCase {
    
    void testFailingTest() {
        Example myExample = new DaveExample()
        assertTrue(myExample.fail())
    }
}
