package bsu.edu.cs.InternetConnections;

public class RideConnection extends InternetConnection<Integer>{

    @Override
    public String createRequestUrl(Integer parkID) {
        return "https://queue-times.com/parks/"+parkID+"/queue_times.json";
    }

}