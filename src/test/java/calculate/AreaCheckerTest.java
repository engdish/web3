package calculate;

import org.junit.Assert;
import org.junit.Test;

public class AreaCheckerTest {
    private final AreaChecker checker = new AreaChecker();

    @Test
    public void pointInsideRectangleShouldHit() {
        Assert.assertTrue(checker.isHit(2, -2.0, 3));
    }

    @Test
    public void pointOutsideAreaShouldMiss() {
        Assert.assertFalse(checker.isHit(3, 3.0, 2));
    }
}
