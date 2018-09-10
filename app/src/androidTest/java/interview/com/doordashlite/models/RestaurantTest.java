package interview.com.doordashlite.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


class RestaurantTest {
    private static final String TEST_ID = "testID";
    private static final String TEST_NAME ="testName";
    private static final String TEST_DESCRIPTION = "testDesc";
    private static final String TEST_STATUS_TYPE ="testStatusType";
    private static final String TEST_STATUS ="testStatus";
    private static final String TEST_COVER_IMG_URL ="testUrl";
    private static final boolean TEST_IS_ONLY_CATERING = false;


    private Restaurant restaurant;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        restaurant = new Restaurant(TEST_ID, TEST_NAME, TEST_DESCRIPTION, TEST_STATUS_TYPE, TEST_STATUS, TEST_COVER_IMG_URL, TEST_IS_ONLY_CATERING);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        restaurant = null;

    }

    @org.junit.jupiter.api.Test
    void getId() {
        assertEquals(restaurant.getId(), TEST_ID);
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals(restaurant.getName(), TEST_NAME);
    }

    @org.junit.jupiter.api.Test
    void getDescription() {
        assertEquals(restaurant.getDescription(), TEST_DESCRIPTION);
    }

    @org.junit.jupiter.api.Test
    void getStatusType() {
        assertEquals(restaurant.getStatusType(), TEST_STATUS_TYPE);
    }

    @org.junit.jupiter.api.Test
    void getStatus() {
        assertEquals(restaurant.getStatus(), TEST_STATUS);
    }

    @org.junit.jupiter.api.Test
    void getCoverImageUrl() {
        assertEquals(restaurant.getCoverImageUrl(), TEST_COVER_IMG_URL);
    }

    @org.junit.jupiter.api.Test
    void isOnlyCatering() {
        assertEquals(restaurant.isOnlyCatering(), TEST_IS_ONLY_CATERING);
    }
}