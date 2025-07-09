package bsu.edu.cs.Parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantTest {

    @Test
    public void restaurantGetNameTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals("Rainforest Cafe",testRestaurant.getName());
    }

    @Test
    public void restaurantGetCoordinatesLatTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates("28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","\"AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals("28.3546441",testRestaurant.getCoordinates().getLatitude());
    }

    @Test
    public void restaurantGetCoordinatesLngTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates("28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals("-81.59077189999999",testRestaurant.getCoordinates().getLongitude());
    }

    @Test
    public void restaurantGetRatingTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals(4.4,testRestaurant.getRating());
    }

    @Test
    public void restaurantGetPriceLevelTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals(2.0,testRestaurant.getPriceLevel());
    }

    @Test
    public void restaurantGetPlaceIDTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals("ChIJVVVVFbNi54gRtxun5fsZ9eg",testRestaurant.getPlaceID());
    }

    @Test
    public void restaurantGetPhotoReferenceIDTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg","AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA");
        assertEquals("AeeoHcJcovDTcxDcOqKTqIN0uevw2FN4ICKE_qHXIcN1RzjaUCZ4CUOpubP-whj1-DDgKVuQsYTscY3UMvT7g3_hlZ7fRLpPHNjM01KtRdHQPSlkM_3uu6q8_H1Z6xgzXVlMlorbStPNsxEFHlH80ao58LL4975ztgHQesKDywCq2EcfKziXJINLXbA00LLxNnPuMZ6ctlUZVJxH87QOmO_SDDxurvVc-uLBWPkiVZeiy-GzdWNLWdFM7ZpIe9ZY-jsm1L_4QPAHfr3mRTIp6pe2C2kI6d7g2nMdOnxYTiYlUAh9pmHAN2qu67ZvlaCg8QApBlplgxRja6x0sSAJkOrZVkKFVcQ5op_3fEZnJAm_gTxE2nUaoa0WYAroK1kOwfa1J-zSUoQZwM3qFTitZ6vlANo2zAu49mT6Ma9au4VFaXy0p4eLW9F6eikKA4caew1E7Rwd-smcXOhku-qqKvAE3SNh2iB30RqzUTzqxY4ojzk5gVwz-PKeeyzdcufbsuhu9Y8sa0w0u1EW4uZiq7gHya8zdp-w972T-bJq4q8jq_RXbrc-SZi8VgpFTm-SUlX3ef43aVgtO-a3yA",testRestaurant.getPhotoReferenceID());

    }

}
