/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package teamproject;

import org.junit.jupiter.api.Test;
import teamproject.myapp.App;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
