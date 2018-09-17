package interview.com.doordashlite.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class RestaurantTest {
    private static final String TEST_ID = "testID";
    private static final String TEST_NAME ="testName";
    private static final String TEST_DESCRIPTION = "testDesc";
    private static final String TEST_STATUS_TYPE ="testStatusType";
    private static final String TEST_STATUS ="testStatus";
    private static final String TEST_COVER_IMG_URL ="testUrl";
    private static final boolean TEST_IS_ONLY_CATERING = false;


    private Restaurant restaurant;
    @Before
    public void setUp() {
        restaurant = new Restaurant(TEST_ID, TEST_NAME, TEST_DESCRIPTION, TEST_STATUS_TYPE, TEST_STATUS, TEST_COVER_IMG_URL, TEST_IS_ONLY_CATERING);
    }

    @After
    public void tearDown() {
        restaurant = null;

    }

    @Test
    public void getId() {
        assertEquals(restaurant.getId(), TEST_ID);
    }

    @Test
    public void getName() {
        assertEquals(restaurant.getName(), TEST_NAME);
    }

    @Test
    public void getDescription() {
        assertEquals(restaurant.getDescription(), TEST_DESCRIPTION);
    }

    @Test
    public void getStatusType() {
        assertEquals(restaurant.getStatusType(), TEST_STATUS_TYPE);
    }

    @Test
    public void getStatus() {
        assertEquals(restaurant.getStatus(), TEST_STATUS);
    }

    @Test
    public void getCoverImageUrl() {
        assertEquals(restaurant.getCoverImageUrl(), TEST_COVER_IMG_URL);
    }

    @Test
    public void isOnlyCatering() {
        assertEquals(restaurant.isOnlyCatering(), TEST_IS_ONLY_CATERING);
    }
}