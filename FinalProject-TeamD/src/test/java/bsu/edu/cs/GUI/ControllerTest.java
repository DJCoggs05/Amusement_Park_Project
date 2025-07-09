package bsu.edu.cs.GUI;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void convertToHoursLessThan0MinTest(){
        GUIModel controller = new GUIModel();
        assertEquals("Invalid: minute cannot be negative",controller.convertMinToHours(-30));
    }

    @Test
    public void convertToHoursLessThan60MinTest(){
        GUIModel controller = new GUIModel();
        assertEquals("45 min",controller.convertMinToHours(45));
    }

    @Test
    public void convertToHoursGreaterThan60MinTest(){
        GUIModel controller = new GUIModel();
        assertEquals("1 hr 30 min",controller.convertMinToHours(90));
    }

    @Test
    public void convertToHoursTest(){
        GUIModel controller = new GUIModel();
        assertEquals("2 hr",controller.convertMinToHours(120));
    }

    @Test
    public void getWaitColorLessThan45Test(){
        GUIModel controller = new GUIModel();
        assertEquals("low-wait-time",controller.getWaitTimeColor(43));
    }

    @Test
    public void getWaitColorLessThan90Test(){
        GUIModel controller = new GUIModel();
        assertEquals("medium-wait-time",controller.getWaitTimeColor(67));
    }

    @Test
    public void getWaitColorEqualTo90Test(){
        GUIModel controller = new GUIModel();
        assertEquals("medium-wait-time",controller.getWaitTimeColor(90));
    }

    @Test
    public void getWaitColorGreaterThan90Test(){
        GUIModel controller = new GUIModel();
        assertEquals("high-wait-time",controller.getWaitTimeColor(100));
    }

    @Test
    public void getPhotoURLTest() throws IOException {
        GUIModel controller = new GUIModel();
        assertTrue(controller.getPhotoURl("AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA").matches("https://maps\\.googleapis\\.com/maps/api/place/photo\\?maxwidth=400&photoreference=AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA&key=[^&]+"));
    }



}
